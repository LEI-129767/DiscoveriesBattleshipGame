/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls a single player's side of a Battleship match: it holds that
 * player's {@link IFleet}, records every shot fired at it, and keeps
 * running statistics about invalid shots, repeated shots, hits and sunk
 * ships.
 *
 * @author fba
 */
public class Game implements IGame {

    /** The fleet being targeted in this game. */
    private IFleet fleet;

    /** The positions of every valid, non-repeated shot fired so far. */
    private List<IPosition> shots;

    /** The number of shots fired outside the board boundaries. */
    private Integer countInvalidShots;

    /** The number of shots fired at a position already targeted before. */
    private Integer countRepeatedShots;

    /** The number of shots that hit a ship. */
    private Integer countHits;

    /** The number of ships sunk as a result of fired shots. */
    private Integer countSinks;

    /**
     * Creates a new game controlling shots fired against the given fleet.
     *
     * @param fleet the fleet to be targeted during this game
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Fires a shot at the given position and updates this game's
     * statistics accordingly.
     * <p>
     * The outcome depends on the position:
     * <ul>
     *   <li>if {@code pos} is outside the board, it counts as an invalid
     *       shot and nothing else happens;</li>
     *   <li>if {@code pos} has already been shot before, it counts as a
     *       repeated shot and nothing else happens;</li>
     *   <li>otherwise the shot is recorded and, if it hits a ship, the
     *       hit count is increased and the ship is told it was shot; if
     *       that hit causes the ship to sink, the sink count is also
     *       increased and the sunk ship is returned.</li>
     * </ul>
     *
     * @param pos the position being targeted
     * @return the ship that was sunk by this shot, or {@code null} if no
     *         ship was sunk (whether because the shot missed, was
     *         invalid, was repeated, or only damaged a ship without
     *         sinking it)
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Checks whether the given position lies within the board boundaries.
     *
     * @param pos the position to check
     * @return {@code true} if {@code pos} is a valid position on the
     *         board, {@code false} otherwise
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks whether the given position has already been shot at in this
     * game.
     *
     * @param pos the position to check
     * @return {@code true} if {@code pos} is already present in
     *         {@link #shots}, {@code false} otherwise
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Prints a textual representation of the board to the standard
     * output, marking each of the given positions with {@code marker}
     * and every other cell with {@code '.'}.
     *
     * @param positions the positions to mark on the board
     * @param marker    the character used to mark each position
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Prints the board showing every valid shot that has been fired so
     * far, marked with {@code 'X'}.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board showing every position occupied by the fleet,
     * marked with {@code '#'}.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
