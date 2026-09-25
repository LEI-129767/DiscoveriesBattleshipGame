/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a fleet of ships in the Battleship game, responsible for
 * managing the collection of ships placed on the board, validating
 * their placement and querying their status.
 *
 * @author Mihaita Ferent
 */
public interface IFleet {
    /**
     * The size of the board, i.e. the number of rows and columns of the
     * grid (a {@code BOARD_SIZE} x {@code BOARD_SIZE} grid).
     */
    Integer BOARD_SIZE = 10;
    /**
     * The maximum number of ships allowed in a fleet.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Returns all the ships currently in the fleet.
     *
     * @return the list of ships belonging to this fleet
     */
    List<IShip> getShips();

    /**
     * Adds a ship to the fleet, provided the fleet is not already full,
     * the ship fits entirely inside the board, and it does not collide
     * with or lie too close to any ship already in the fleet.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added,
     *         {@code false} otherwise
     */
    boolean addShip(IShip s);

    /**
     * Returns all the ships in the fleet that belong to the given
     * category.
     *
     * @param category the ship category to filter by (e.g. "Galeao",
     *                 "Fragata", "Nau", "Caravela", "Barca")
     * @return the list of ships belonging to that category
     */
    List<IShip> getShipsLike(String category);

    /**
     * Returns all the ships in the fleet that are still floating, i.e.
     * have not yet been completely sunk.
     *
     * @return the list of still-floating ships
     */
    List<IShip> getFloatingShips();

    /**
     * Returns the ship, if any, that occupies the given position.
     *
     * @param pos the position to check
     * @return the {@link IShip} occupying that position, or
     *         {@code null} if no ship occupies it
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the current status of the fleet, namely all ships, the
     * still-floating ships and the ships grouped by category.
     */
    void printStatus();
}
