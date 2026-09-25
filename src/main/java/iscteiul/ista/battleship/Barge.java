package iscteiul.ista.battleship;

/**
 * Represents the Barge, the smallest ship in the fleet (equivalent to
 * the submarine in traditional Battleship), occupying a single
 * position on the board.
 
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a new Barge at a given board position. Since it occupies
     * only one cell, the bearing does not affect its layout.
     *
     * @param bearing the barge's bearing
     * @param pos     upper left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the number of cells occupied by the Barge on the board.
     *
     * @return the barge's size, always equal to 1
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}