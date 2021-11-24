package edu.rice.comp504.model.moveobj;

import edu.rice.comp504.model.PacManGameWorld;
import edu.rice.comp504.model.behavior.IObjectBehavior;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.util.ScreenTileConverter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Player implements IObject, IMovable, ICollidable, PropertyChangeListener {

    private IUpdateStrategy moveStrategy;
    private IObjectBehavior collideBehavior;
    private Point screenPosition;
    private Point tilePosition;

    private int score = 0;
    private int life = 3;
    private int direction;
    private int speed = 1;

    public Player()
    {

    }

    @Override
    public void onCollide(ICollidable other) {

        if(other != this)
        {
            collideBehavior.changeState(this, other);
        }

    }

    @Override
    public boolean detectCollision() {

        return false;
    }

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
            tilePosition = newTilePosition;
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
            tilePosition = newTilePosition;
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
            tilePosition = newTilePosition;
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
            tilePosition = newTilePosition;
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

    public void setPosition(Point point) {
        tilePosition = point;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        update();

    }

    public void addScore(int amount)
    {
        score += amount;
    }

    public void removeScore(int amount)
    {
        score -= amount;
    }

    public void removeLife(int amount)
    {
        life -= amount;
    }

    public void addLife(int amount)
    {
        life += amount;
    }
}
