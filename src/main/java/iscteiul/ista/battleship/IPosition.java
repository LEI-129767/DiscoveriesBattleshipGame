/**
 *
 */
package iscteiul.ista.battleship;

/**
 * @author Mihaita Ferent
 */
public interface IPosition {
    int getRow();

    int getColumn();

    boolean equals(Object other);

    boolean isAdjacentTo(IPosition other);

    void occupy();

    void shoot();

    boolean isOccupied();

    boolean isHit();
}
