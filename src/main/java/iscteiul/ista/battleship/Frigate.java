/**
 * Provides the {@link Frigate} ship class.
 */
package iscteiul.ista.battleship;

/**
 * Represents a frigate ("Fragata"), a ship that occupies 4 consecutive
 * cells on the board, oriented either horizontally or vertically.
 * <p>
 * In the Discoveries Battleship version, each player has exactly one
 * frigate in their fleet.
 */
public class Frigate extends Ship {

    /** The number of grid cells occupied by a frigate. */
    private static final Integer SIZE = 4;

    /** The display name used for this ship category. */
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate positioned on the board starting at {@code pos},
     * extending {@link #SIZE} cells in the direction given by
     * {@code bearing}.
     * <p>
     * If the bearing is {@code NORTH} or {@code SOUTH}, the ship extends
     * vertically (increasing rows); if it is {@code EAST} or {@code WEST},
     * it extends horizontally (increasing columns).
     *
     * @param bearing the orientation of the frigate; must be one of
     *                {@code NORTH}, {@code SOUTH}, {@code EAST} or
     *                {@code WEST}
     * @param pos     the starting position (bow) of the frigate
     * @throws IllegalArgumentException if {@code bearing} is not a valid
     *                                   orientation
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
