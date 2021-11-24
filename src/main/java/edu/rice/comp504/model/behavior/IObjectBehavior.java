package edu.rice.comp504.model.behavior;

import edu.rice.comp504.model.moveobj.ICollidable;

public interface IObjectBehavior {

    void changeState(ICollidable object, ICollidable collider);
}
