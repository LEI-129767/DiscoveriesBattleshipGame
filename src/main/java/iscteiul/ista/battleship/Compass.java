package iscteiul.ista.battleship;

/**
 * Enumeration representing the four cardinal bearings used to position
 * ships on the board (NORTH, SOUTH, EAST, WEST), including an UNKNOWN
 * value for invalid or unrecognized bearings. Each bearing is
 * associated with an identifying character used in the game's text
 * representation.
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character that identifies this bearing.
     *
     * @return the character corresponding to the bearing (n, s, e, o or u)
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the text representation of this bearing, corresponding to
     * its identifying character.
     *
     * @return the bearing's character, as a String
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character into its corresponding bearing (Compass).
     *
     * @param ch character to convert ('n', 's', 'e' or 'o')
     * @return the bearing corresponding to the character, or UNKNOWN if the character is not recognized
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}