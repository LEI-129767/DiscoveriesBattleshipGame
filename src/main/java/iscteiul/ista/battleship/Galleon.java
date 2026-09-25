/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a galleon ("Galeao"), the largest ship in the fleet,
 * occupying 5 cells arranged in an irregular, arrow-like shape rather
 * than a straight line.
 * <p>
 * In the Discoveries Battleship version, each player has exactly one
 * galleon in their fleet, acting as the aircraft-carrier equivalent.
 */
public class Galleon extends Ship {

    /** The number of grid cells occupied by a galleon. */
    private static final Integer SIZE = 5;

    /** The display name used for this ship category. */
    private static final String NAME = "Galeao";

    /**
     * Creates a galleon positioned on the board starting at {@code pos},
     * with its 5 cells arranged according to the direction given by
     * {@code bearing}.
     * <p>
     * Unlike straight ships, the galleon's shape is asymmetric: the exact
     * arrangement of cells depends on the bearing and is computed by one
     * of {@link #fillNorth}, {@link #fillSouth}, {@link #fillEast} or
     * {@link #fillWest}.
     *
     * @param bearing the orientation of the galleon; must be one of
     *                {@code NORTH}, {@code SOUTH}, {@code EAST} or
     *                {@code WEST}
     * @param pos     the reference position from which the galleon's
     *                shape is built
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if {@code bearing} is not a valid
     *                                   orientation
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Fills this galleon's positions for a {@code NORTH} bearing: a row
     * of 3 cells starting at {@code pos}, plus 2 cells extending downward
     * from the middle of that row, forming a T-like shape.
     *
     * @param pos the reference position for the shape
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Fills this galleon's positions for a {@code SOUTH} bearing: a
     * column of 2 cells starting at {@code pos}, plus a row of 3 cells
     * extending from the bottom of that column.
     *
     * @param pos the reference position for the shape
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Fills this galleon's positions for an {@code EAST} bearing: a
     * single cell at {@code pos}, a row of 3 cells one row below it, and
     * a single cell 2 rows below {@code pos}, forming an arrow pointing
     * east.
     *
     * @param pos the reference position for the shape
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Fills this galleon's positions for a {@code WEST} bearing: a
     * single cell at {@code pos}, a row of 3 cells one row below it, and
     * a single cell 2 rows below {@code pos}, forming an arrow pointing
     * west.
     *
     * @param pos the reference position for the shape
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
