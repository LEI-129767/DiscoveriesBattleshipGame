/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's fleet of ships in the Battleship game.
 */
public class Fleet implements IFleet {

    /**
     * Prints all the given ships, one per line.
     *
     * @param ships the list of ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** The ships in this fleet. */
    private List<IShip> ships;

    /**
     * Creates an empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * @return the list of ships in this fleet
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adds a ship to the fleet, if it fits on the board and doesn't collide with other ships.
     *
     * @param s the ship to add
     * @return true if the ship was added, false otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns the ships of a given category (e.g. "Fragata").
     *
     * @param category the category to search for
     * @return the ships matching that category
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Returns the ships that haven't been sunk yet.
     *
     * @return the floating ships in this fleet
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Returns the ship occupying a given position, if any.
     *
     * @param pos the position to check
     * @return the ship at that position, or null if none
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks if a ship fits entirely within the board.
     *
     * @param s the ship to check
     * @return true if the ship is inside the board
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks if a ship would be too close to an existing ship in the fleet.
     *
     * @param s the ship to check
     * @return true if there's a collision risk
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Prints all ships, floating ships, and ships grouped by category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints the ships belonging to a given category.
     *
     * @param category the category of ships to print
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints the ships that haven't been sunk yet.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }

}
