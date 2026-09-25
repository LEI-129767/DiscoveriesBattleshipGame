/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents the Caravel, a 2-cell ship, corresponding to the 2-cannon
 * ship in traditional Battleship. It can be placed horizontally or
 * vertically, depending on the given bearing.
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a new Caravel from an initial position and a bearing,
     * computing and registering all the positions occupied by the ship
     * on the board.
     *
     * @param bearing the bearing where the Caravel heads to (NORTH, SOUTH, EAST or WEST)
     * @param pos     initial point for positioning the Caravel
     * @throws NullPointerException     if the bearing is null
     * @throws IllegalArgumentException if the bearing is not one of the four valid values
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Returns the number of cells occupied by the Caravel on the board.
     *
     * @return the caravel's size, always equal to 2
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
