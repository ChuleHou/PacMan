package edu.rice.comp504.model.moveobj;

import edu.rice.comp504.model.PacManGameWorld;
import edu.rice.comp504.model.behavior.IObjectBehavior;
import edu.rice.comp504.model.behavior.NoInteract;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.strategy.ChaseWithShortestPathStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.util.ScreenTileConverter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Ghost implements ICollidable,IMovable, IObject, PropertyChangeListener {


    private IUpdateStrategy moveStrategy;
    private IObjectBehavior collideBehavior;
    private Point screenPosition;
    private Point tilePosition;
    private int speed;
    private boolean isEaten = false;
    private static int reward = 200;

    public Ghost(IUpdateStrategy strategy, Point screenPosition)
    {

    }

    @Override
    public void onCollide(ICollidable other) {
        //collideBehavior.changeState(this, other);
    }

    @Override
    public boolean detectCollision() {
        int currentWidth = this.getPosition().x;
        int currentHeight = this.getPosition().y;
        ICollidable[] allObjects = PacManGameWorld.getOnly().getCollidables();
        // todo
        // if()
        return false;
    }

    /**
     *
     * @param world the tile map
     * @return true if current position is not move through
     */
    public boolean detectCollisionWithWalls(Tile[][] world) {
        int currentWidth = this.getPosition().x;
        int currentHeight = this.getPosition().y;
        return !world[currentWidth][currentHeight].moveThrough;
    }

    @Override
    public void moveUp() {
        ScreenTileConverter converter = new ScreenTileConverter(50);
        Point newScreenPosition = new Point(screenPosition.x, screenPosition.y + speed);
        Point newTilePosition = converter.screenPointToTilePoint(newScreenPosition.x, newScreenPosition.y);
        if(!detectCollisionWithWalls(PacManGameWorld.getOnly().getTiles()))
        {
            screenPosition = newScreenPosition;
        }
    }

    @Override
    public void moveDown() {
        ScreenTileConverter converter = new ScreenTileConverter(50);
        Point newScreenPosition = new Point(screenPosition.x, screenPosition.y - speed);
        Point newTilePosition = converter.screenPointToTilePoint(newScreenPosition.x, newScreenPosition.y);
        if(!detectCollisionWithWalls(PacManGameWorld.getOnly().getTiles()))
        {
            screenPosition = newScreenPosition;
        }
    }

    @Override
    public void moveRight() {
        ScreenTileConverter converter = new ScreenTileConverter(50);
        Point newScreenPosition = new Point(screenPosition.x + speed, screenPosition.y);
        Point newTilePosition = converter.screenPointToTilePoint(newScreenPosition.x, newScreenPosition.y);
        if(!detectCollisionWithWalls(PacManGameWorld.getOnly().getTiles()))
        {
            screenPosition = newScreenPosition;
        }
    }

    @Override
    public void moveLeft() {
        ScreenTileConverter converter = new ScreenTileConverter(50);
        Point newScreenPosition = new Point(screenPosition.x - speed, screenPosition.y);
        Point newTilePosition = converter.screenPointToTilePoint(newScreenPosition.x, newScreenPosition.y);
        if(!detectCollisionWithWalls(PacManGameWorld.getOnly().getTiles()))
        {
            screenPosition = newScreenPosition;
        }
    }

    public void moveWithDirection(String direction) {
        if (direction == null || direction.length() == 0) {
            return;
        }
        if (direction.equals("left")) {
            moveLeft();
        } else if (direction.equals("right")) {
            moveRight();
        } else if (direction.equals("up")) {
            moveUp();
        } else if (direction.equals("down")) {
            moveDown();
        }
    }

    @Override
    public void update() {
        moveStrategy.updateState(this);
    }

    @Override
    public Point getPosition() {
        return tilePosition;
    }

    @Override
    public void destroy() {
        PacManGameWorld.getOnly().destroy(this);
    }

    public Point getTilePosition() {
        return tilePosition;
    }

    public void setTilePosition(Point position) {
        tilePosition = position;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ((UpdateStateCmd) evt.getNewValue()).execute(this);
    }

    public boolean getIsEaten()
    {
        return isEaten;
    }

    public void setIsEaten(boolean state)
    {
        isEaten = state;
    }

    public void eatByPlayer()
    {
        if(!isEaten)
        {
            isEaten = true;
            reward *= 2;

            // Change ghost move strategy to flee back to room
            collideBehavior = new NoInteract();
            //moveStrategy =
        }
    }

    public void resetReward()
    {
        reward = 200;
    }

    public int getReward()
    {
        return reward;
    }

    public void setReward(int value)
    {
        reward = value;
    }
}
