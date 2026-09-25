package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a ship in the Battleship game.
 * <p>
 * Defines the core attributes of a ship (such as size, orientation, and occupied positions)
 * as well as the behavior required to check for position overlaps, proximity to other ships,
 * and state updates when targeted by shots.
 * </p>
 *
 * @author Rui
 * @version 1.0
 */
public interface IShip {

    /**
     * Gets the category or type of the ship (e.g., "Aircraft Carrier", "Frigate", "Submarine").
     *
     * @return The category name of the ship.
     */
    String getCategory();

    /**
     * Gets the size or number of grid positions occupied by the ship.
     *
     * @return The size of the ship in grid units.
     */
    Integer getSize();

    /**
     * Gets the list of all grid positions occupied by the ship.
     *
     * @return A list containing all {@link IPosition} instances occupied by the ship.
     */
    List<IPosition> getPositions();

    /**
     * Gets the reference (anchor) position of the ship, typically corresponding to its bow or starting point.
     *
     * @return The primary reference {@link IPosition} of the ship.
     */
    IPosition getPosition();

    /**
     * Gets the orientation or compass direction the ship is facing.
     *
     * @return The {@link Compass} orientation of the ship (e.g., NORTH, SOUTH, EAST, WEST).
     */
    Compass getBearing();

    /**
     * Checks if the ship is still floating (i.e., whether it has at least one position 
     * that has not been hit by a shot).
     *
     * @return {@code true} if the ship is still afloat; {@code false} if it has been sunk.
     */
    boolean stillFloating();

    /**
     * Gets the topmost row index (minimum row coordinate) occupied by the ship.
     *
     * @return The minimum row index occupied by the ship.
     */
    int getTopMostPos();

    /**
     * Gets the bottommost row index (maximum row coordinate) occupied by the ship.
     *
     * @return The maximum row index occupied by the ship.
     */
    int getBottomMostPos();

    /**
     * Gets the leftmost column index (minimum column coordinate) occupied by the ship.
     *
     * @return The minimum column index occupied by the ship.
     */
    int getLeftMostPos();

    /**
     * Gets the rightmost column index (maximum column coordinate) occupied by the ship.
     *
     * @return The maximum column index occupied by the ship.
     */
    int getRightMostPos();

    /**
     * Checks whether the ship occupies a specific position on the board.
     *
     * @param pos The {@link IPosition} to check.
     * @return {@code true} if the ship occupies the specified position; {@code false} otherwise.
     */
    boolean occupies(IPosition pos);

    /**
     * Checks whether this ship is too close to another ship (including overlaps or adjacent positions, 
     * according to game rules).
     *
     * @param other The other {@link IShip} to check proximity against.
     * @return {@code true} if the ships are adjacent or overlapping; {@code false} otherwise.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks whether the ship is too close to a specific grid position.
     *
     * @param pos The {@link IPosition} to test.
     * @return {@code true} if the position is adjacent to or occupied by the ship; {@code false} otherwise.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot fired at a specific position. If the position corresponds to one of 
     * the positions occupied by the ship, that section's status is updated to hit.
     *
     * @param pos The target {@link IPosition} where the shot is fired.
     */
    void shoot(IPosition pos);
}
