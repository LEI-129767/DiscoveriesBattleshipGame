package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class containing CLI tasks and workflow helpers for testing and running Battleship game components.
 * <p>
 * Provides interactive command-loop drivers to build ships, construct fleets, execute firing rounds, 
 * and display game states using a {@link Scanner} for console input.
 * </p>
 *
 * @author Rui
 * @version 1.1
 */
public class Tasks {

    /** Log4j logger instance for reporting information and debug messages. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Number of shots fired in a single firing round/burst. */
    private static final int NUMBER_SHOTS = 3;

    /** Farewell message displayed when exiting interactive loops. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Command keyword to initialize a new fleet. */
    private static final String NOVAFROTA = "nova";

    /** Command keyword to quit/surrender the current task loop. */
    private static final String DESISTIR = "desisto";

    /** Command keyword to trigger a round of shots. */
    private static final String RAJADA = "rajada";

    /** Command keyword to view past valid shots. */
    private static final String VERTIROS = "ver";

    /** Command keyword to display the full map (cheat mode). */
    private static final String BATOTA = "mapa";

    /** Command keyword to output current fleet status. */
    private static final String STATUS = "estado";

    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Executes Task A: Tests individual ship construction and occupancy checks.
     * <p>
     * Continuously reads ship configurations followed by {@value #NUMBER_SHOTS} positions,
     * logging whether each position is occupied by the constructed ship.
     * </p>
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Executes Task B: Tests fleet construction and status display via interactive commands.
     * <p>
     * Processes commands from standard input until the {@value #DESISTIR} command is entered.
     * Supports {@value #NOVAFROTA} to create a fleet and {@value #STATUS} to output status.
     * </p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executes Task C: Extends Task B by enabling fleet map inspection (cheat mode).
     * <p>
     * Adds support for the {@value #BATOTA} command to log the internal representation of the fleet.
     * </p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executes Task D: Tests game combat dynamics including shot rounds and stats.
     * <p>
     * Integrates full gameplay workflow allowing fleet creation, firing bursts ({@value #RAJADA}),
     * valid shot history inspection ({@value #VERTIROS}), and fleet map checks ({@value #BATOTA}).
     * </p>
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Builds a new {@link Fleet} populated with ships read from input.
     *
     * @param in The {@link Scanner} instance to read input from. Must not be {@code null}.
     * @return The populated {@link Fleet} instance.
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Reads parameters for a single ship from input, instantiates it, and returns it.
     *
     * @param in The {@link Scanner} instance to read from.
     * @return The constructed {@link Ship}, or {@code null} if the ship category/type is unknown.
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Reads grid coordinates (row and column) from input and constructs a position object.
     *
     * @param in The {@link Scanner} instance to read from.
     * @return A new {@link Position} with the parsed row and column values.
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Fires a round of {@value #NUMBER_SHOTS} shots targeting the game fleet.
     *
     * @param in   The {@link Scanner} instance to read target coordinates from.
     * @param game The {@link IGame} context managing shot verification and fleet updates.
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
