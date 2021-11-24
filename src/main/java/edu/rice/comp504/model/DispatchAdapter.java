package edu.rice.comp504.model;

import edu.rice.comp504.model.moveobj.IObject;

import java.beans.PropertyChangeListener;

public class DispatchAdapter {
    private final PacManGameWorld pacManGameWorld;
    public static String currentPlayerInput;

    /**
     * Constructor.
     * @param gameWorld pac-man game world
     */
    public DispatchAdapter(PacManGameWorld gameWorld)
    {
        pacManGameWorld = gameWorld;
    }

    public PropertyChangeListener[] updateGameWorld(String userInput)
    {
        currentPlayerInput = userInput;

        return pacManGameWorld.updateGameWorld();
    }

    public void removeListeners() {
        pacManGameWorld.removeListeners();
    }

}
