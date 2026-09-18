// RUI

/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IShip {
    String getCategory();

    Integer getSize();

    List<IPosition> getPositions();

    IPosition getPosition();

    Compass getBearing();

    boolean stillFloating();

    int getTopMostPos();

    int getBottomMostPos();

    int getLeftMostPos();

    int getRightMostPos();

    boolean occupies(IPosition pos);

    boolean tooCloseTo(IShip other);

    boolean tooCloseTo(IPosition pos);

    void shoot(IPosition pos);
}
