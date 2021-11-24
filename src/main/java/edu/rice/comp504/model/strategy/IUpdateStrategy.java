package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.moveobj.IMovable;
import edu.rice.comp504.model.moveobj.IObject;

public interface IUpdateStrategy {

    /**
     * Update the state of every game object.
     * @param context the game object to apply the strategy to.
     */
    void updateState(IMovable context);

    /**
     * Get name of the strategy.
     * @return Name of the strategy.
     */
    String getName();
}
