package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class representing a generic ship in the Battleship game.
 * <p>
 * Provides common functionality for managing ship attributes (category, bearing, reference position),
 * tracking occupied grid positions, detecting collisions or adjacency, and processing target shots.
 * </p>
 *
 * @author Rui
 * @version 1.1
 */
public abstract class Ship implements IShip {

    /** Identifier constant for a GALLEAO ship type. */
    private static final String GALEAO = "galeao";

    /** Identifier constant for a FRAGATA ship type. */
    private static final String FRAGATA = "fragata";

    /** Identifier constant for a NAU ship type. */
    private static final String NAU = "nau";

    /** Identifier constant for a CARAVELA ship type. */
    private static final String CARAVELA = "caravela";

    /** Identifier constant for a BARCA ship type. */
    private static final String BARCA = "barca";

    /**
     * Factory method that creates and instantiates a specific subclass of {@link Ship} 
     * based on the provided ship kind string.
     *
     * @param shipKind The string identifier representing the ship category (e.g: "galeao", "fragata").
     * @param bearing  The compass orientation {@link Compass} for the ship.
     * @param pos      The starting reference {@link Position} for the ship.
     * @return A concrete instance of a {@link Ship} subclass, or {@code null} if the ship kind is unrecognized.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** The category or type name of the ship. */
    private String category;

    /** The cardinal direction/bearing facing of the ship. */
    private Compass bearing;

    /** The reference (anchor/bow) position of the ship. */
    private IPosition pos;

    /** The collection of all positions occupied by this ship on the grid. */
    protected List<IPosition> positions;

    /**
     * Constructs a new {@code Ship} instance with the specified category, orientation, and reference position.
     *
     * @param category The category or class name of the ship.
     * @param bearing  The orientation {@link Compass} of the ship. Must not be {@code null}.
     * @param pos      The reference anchor {@link IPosition} of the ship. Must not be {@code null}.
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     *
     * @return The category name of the ship.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link List} of all {@link IPosition} instances occupied by this ship.
     */
    @Override
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * {@inheritDoc}
     *
     * @return The reference {@link IPosition} of the ship.
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@link Compass} direction of the ship.
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Iterates through all occupied positions and checks if at least one position remains unhit.
     * </p>
     *
     * @return {@code true} if at least one position is not hit; {@code false} if all positions are hit.
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return The minimum row index occupied by any part of the ship.
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * {@inheritDoc}
     *
     * @return The maximum row index occupied by any part of the ship.
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * {@inheritDoc}
     *
     * @return The minimum column index occupied by any part of the ship.
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * {@inheritDoc}
     *
     * @return The maximum column index occupied by any part of the ship.
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * {@inheritDoc}
     *
     * @param pos The {@link IPosition} to check. Must not be {@code null}.
     * @return {@code true} if this ship occupies the given position; {@code false} otherwise.
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param other The other {@link IShip} to test for proximity. Must not be {@code null}.
     * @return {@code true} if any position of the other ship is adjacent to or overlaps with this ship; {@code false} otherwise.
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param pos The {@link IPosition} to test for adjacency.
     * @return {@code true} if any position of this ship is adjacent to or matches the specified position; {@code false} otherwise.
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Checks if the specified position belongs to this ship and, if so, marks that position as hit.
     * </p>
     *
     * @param pos The target {@link IPosition} of the shot. Must not be {@code null}.
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Returns a string representation of this ship.
     *
     * @return A formatted String containing category, bearing, and reference position.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}
