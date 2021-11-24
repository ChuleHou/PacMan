package edu.rice.comp504.model.stillObject;

import edu.rice.comp504.model.PacManGameWorld;
import edu.rice.comp504.model.moveobj.ICollidable;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Bean implements ICollidable, IObject, PropertyChangeListener {

    private int reward = 10;

    public Bean()
    {

    }

    public Bean(int value)
    {
        reward = value;
    }
    @Override
    public void onCollide(ICollidable other) {
        if(other instanceof Player)
        {
            Player thisPlayer = (Player) other;
            thisPlayer.addScore(reward);
        }
    }

    @Override
    public boolean detectCollision() {
        return false;
    }


    @Override
    public void update() {

    }

    @Override
    public java.awt.Point getPosition() {
        return null;
    }

    @Override
    public void destroy() {
        PacManGameWorld.getOnly().destroy(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setReward(int value)
    {
        reward = value;
    }

    public int getReward()
    {
        return reward;
    }

}
