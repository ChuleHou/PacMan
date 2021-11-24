package edu.rice.comp504.model.moveobj;

//Every object that can collide with other should implement this interface
public interface ICollidable {


    /**
     * This method is called when another object collide with this object
     * @param other the other object
     */
    void onCollide(ICollidable other);

    boolean detectCollision();
}
