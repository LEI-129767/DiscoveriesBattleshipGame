package iscteiul.ista.battleship;

/**
 * Represents the Carrack, a 3-cell ship, corresponding to the 3-cannon
 * ship in traditional Battleship. There are two units of this ship per
 * fleet. It can be placed horizontally or vertically, depending on the
 * given bearing.
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a new Carrack from an initial position and a bearing,
     * computing and registering all the positions occupied by the ship
     * on the board.
     *
     * @param bearing the bearing where the Carrack heads to (NORTH, SOUTH, EAST or WEST)
     * @param pos     initial point for positioning the Carrack
     * @throws IllegalArgumentException if the bearing is not one of the four valid values
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the number of cells occupied by the Carrack on the board.
     *
     * @return the carrack's size, always equal to 3
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}