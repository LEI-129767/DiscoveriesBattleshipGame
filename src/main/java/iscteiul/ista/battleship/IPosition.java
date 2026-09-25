/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a position (cell) on a grid in the Battleship game,
 * identified by a row and a column.
 * *
 * A position may be occupied by part of a ship and may already have
 * been targeted by a shot, thus holding the state needed to progress
 * the game.
 *
 * @author Mihaita Ferent
 */
public interface IPosition {

    /**
     * Returns the row number of this position on the grid.
     *
     * @return the row index (typically starting at 0)
     */
    int getRow();


    /**
     * Returns the column number of this position on the grid.
     *
     * @return the column index (typically starting at 0)
     */
    int getColumn();


    /**
     * Compares this position with another object, checking whether they
     * represent the same grid cell (same row and same column).
     *
     * @param other the object to compare with this position
     * @return {@code true} if {@code other} is a position with the same
     *         row and column, {@code false} otherwise
     */
    boolean equals(Object other);


    /**
     * Checks whether this position is adjacent to another one, i.e.
     * whether they are next to each other (horizontally, vertically or
     * diagonally) on the grid.
     * <p>
     * Used to ensure that ships are not placed touching one another.
     *
     * @param other the position to compare with
     * @return {@code true} if the two positions are adjacent,
     *         {@code false} otherwise
     */
    boolean isAdjacentTo(IPosition other);


    /**
     * Marks this position as occupied by part of a ship.
     */
    void occupy();


    /**
     * Registers a shot fired at this position, marking it as hit.
     */
    void shoot();


    /**
     * Checks whether this position is occupied by part of a ship.
     *
     * @return {true} if occupied, {false} otherwise
     */
    boolean isOccupied();


    /**
     * Checks whether this position has already been targeted by a shot.
     *
     * @return {true} if already hit, {false} otherwise
     */
    boolean isHit();
}
