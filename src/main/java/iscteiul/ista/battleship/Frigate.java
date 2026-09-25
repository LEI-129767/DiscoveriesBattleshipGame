/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a frigate ("Fragata"), a 4-cell ship placed in a straight line.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate at a given position and orientation.
     *
     * @param bearing the direction the ship extends in (NORTH, SOUTH, EAST or WEST)
     * @param pos     the starting position of the ship
     * @throws IllegalArgumentException if bearing is not a valid direction
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
     * @return the number of cells occupied by a frigate
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
