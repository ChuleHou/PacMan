package edu.rice.comp504.model.moveobj;

import java.awt.*;

public interface IObject {

    /**
     * every object in the game world have to have an update function, which updates its' position in the game world
     */
    void update();

    /**
     * get the position of this game object
     * @return this object's current position
     */
    Point getPosition();

    void destroy();
}
