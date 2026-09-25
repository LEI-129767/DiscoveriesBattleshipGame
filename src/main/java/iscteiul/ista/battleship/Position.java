package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Represents a concrete position (coordinate) on the Battleship game board.
 * <p>
 * Stores row and column coordinates along with state flags indicating whether 
 * the position is occupied by a ship or has been hit by a shot.
 * </p>
 *
 * @author Rui
 * @version 1.1
 */
public class Position implements IPosition {

    /** The row index on the grid. */
    private int row;

    /** The column index on the grid. */
    private int column;

    /** State flag indicating if a ship occupies this position. */
    private boolean isOccupied;

    /** State flag indicating if a shot has targeted this position. */
    private boolean isHit;

    /**
     * Constructs a new position with the specified row and column coordinates.
     * By default, the position is created as unoccupied and not hit.
     *
     * @param row    The row coordinate.
     * @param column The column coordinate.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return The row index of this position.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     *
     * @return The column index of this position.
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Computes a hash code for this position using its field values.
     *
     * @return The hash code value for this position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compares this position with another object for equality.
     * Two positions are considered equal if they both implement {@link IPosition} 
     * and share identical row and column coordinates.
     *
     * @param otherPosition The object to compare with this position.
     * @return {@code true} if the object is an {@link IPosition} with matching coordinates; 
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Checks if the specified position is adjacent (including diagonally) to this position.
     * Adjacency holds if the difference in both row and column indices is at most 1.
     * </p>

     * @param other The other {@link IPosition} to test for adjacency.
     * @return {@code true} if adjacent or identical; {@code false} otherwise.
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marks this position as occupied by a ship.
     * </p>
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Marks this position as having received a shot.
     * </p>
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if occupied by a ship; {@code false} otherwise.
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this position has been hit by a shot; {@code false} otherwise.
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of this position.
     *
     * @return A formatted String containing row and column values.
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}
