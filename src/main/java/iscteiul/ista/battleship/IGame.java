/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a Battleship game, responsible for processing shots fired
 * at the fleet and keeping track of the game's statistics (hits,
 * misses, invalid shots, repeated shots and sunk ships).
 *
 * @author Mihaita Ferent
 */
public interface IGame {

    /**
     * Fires a shot at the given position.
     *
     * If the position is outside the board, the shot is counted as
     * invalid. If the position has already been shot at before, it is
     * counted as repeated. Otherwise, the shot is registered and, if it
     * hits a ship, the hit counter is increased; if that hit causes the
     * ship to sink, the sunk-ship counter is also increased and the ship
     * is returned.
     *
     * @param pos the position being targeted
     * @return the {IShip} that was sunk by this shot, or
     *         {null} if no ship was sunk (miss, invalid shot,
     *         repeated shot, or a hit that did not sink the ship)
     */
    IShip fire(IPosition pos);


    /**
     * Returns the list of valid, non-repeated shots fired so far.
     *
     * @return the list of positions that have been shot at
     */
    List<IPosition> getShots();


    /**
     * Returns the number of shots fired at a position that had already
     * been targeted before.
     *
     * @return the count of repeated shots
     */
    int getRepeatedShots();


    /**
     * Returns the number of shots fired outside the boundaries of the
     * board.
     *
     * @return the count of invalid shots
     */
    int getInvalidShots();


    /**
     * Returns the number of shots that hit part of a ship.
     *
     * @return the count of hits
     */
    int getHits();


    /**
     * Returns the number of ships that have been completely sunk so
     * far.
     *
     * @return the count of sunk ships
     */
    int getSunkShips();


    /**
     * Returns the number of ships from the fleet that are still
     * floating (not yet fully sunk).
     *
     * @return the count of remaining, still-floating ships
     */
    int getRemainingShips();


    /**
     * Prints the board showing all valid shots that have been fired so
     * far, marking each targeted position.
     */
    void printValidShots();


    /**
     * Prints the board showing the current positions occupied by the
     * fleet's ships.
     */
    void printFleet();
}
