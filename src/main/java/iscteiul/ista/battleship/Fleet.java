/**
 * Provides the {@link Fleet} class, which manages the collection of ships
 * belonging to a player in the Battleship game.
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the fleet of ships belonging to a player in the Battleship game.
 * <p>
 * A fleet manages a collection of {@link IShip} instances, ensuring that
 * ships are placed within the board boundaries and do not collide with one
 * another. It also provides operations to query ships by category, check
 * which ships are still floating, and locate a ship at a given position.
 *
 */
public class Fleet implements IFleet {

    /**
     * Prints all the given ships to the standard output, one per line.
     *
     * @param ships the list of ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** The list of ships that currently make up this fleet. */
    private List<IShip> ships;

    /**
     * Creates an empty fleet with no ships.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Attempts to add a ship to this fleet.
     *
     * The ship is only added if the fleet has not yet reached its maximum
     * size ({@link IFleet#FLEET_SIZE}), the ship lies entirely within the
     * board, and it does not risk colliding with any ship already present
     * in the fleet.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added,
     *         {@code false} otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns the ships in this fleet belonging to the given category.
     *
     * @param category the category of ships to search for (e.g. "Galeao",
     *                  "Fragata", "Nau", "Caravela", "Barca")
     * @return a list containing every ship in this fleet whose category
     *         matches {@code category}; the list is empty if none match
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Returns the ships in this fleet that have not yet been sunk.
     *
     * @return a list containing every ship in this fleet that is still
     *         floating; the list is empty if the whole fleet has sunk
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Returns the ship in this fleet that occupies the given position, if
     * any.
     *
     * @param pos the position to check
     * @return the ship occupying {@code pos}, or {@code null} if no ship
     *         in this fleet occupies that position
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether the given ship lies entirely within the board
     * boundaries.
     *
     * @param s the ship to check
     * @return {@code true} if every cell occupied by {@code s} is within
     *         the board, {@code false} otherwise
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether the given ship would be positioned too close to any
     * ship already in this fleet, i.e. whether placing it would violate
     * the game's no-touching rule between ships.
     *
     * @param s the candidate ship to check
     * @return {@code true} if {@code s} is too close to an existing ship
     *         in this fleet, {@code false} otherwise
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Prints the full status of this fleet to the standard output,
     * including all ships, the ships still floating, and the ships
     * grouped by each known category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all the ships of this fleet belonging to a particular
     * category.
     *
     * @param category the category of ships of interest; must not be
     *                  {@code null}
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints all the ships of this fleet that have not yet been shot down.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all the ships of this fleet, regardless of their status.
     */
    void printAllShips() {
        printShips(ships);
    }

}
