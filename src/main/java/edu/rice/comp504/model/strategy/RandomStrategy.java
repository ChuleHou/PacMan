package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.moveobj.Ghost;
import edu.rice.comp504.model.moveobj.IMovable;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements IUpdateStrategy{

    private Tile[][] gameWorld;
    private Random random;

    /**
     * @param gameWorld pacman game board
     */
    public RandomStrategy(Tile[][] gameWorld) {
        this.gameWorld = gameWorld;
        this.random = new Random();
    }

    @Override
    public void updateState(IMovable context) {
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost)context;

            // assume iobject.getPosition will return tile position instead of screen position
            Point currentLocation = ghost.getPosition();
            int currentX = (int)currentLocation.getX();
            int currentY = (int)currentLocation.getY();
            List<String> availableMove = new ArrayList<>();
            if (currentX - 1 >= 0 && gameWorld[currentX - 1][currentY].isMoveThrough()) {
                availableMove.add("down");
            }
            if (currentX + 1 < gameWorld.length && gameWorld[currentX + 1][currentY].isMoveThrough()) {
                availableMove.add("up");
            }
            if (currentY - 1 >= 0 && gameWorld[currentX][currentY - 1].isMoveThrough()) {
                availableMove.add("left");
            }
            if (currentY + 1 < gameWorld[0].length && gameWorld[currentX][currentY + 1].isMoveThrough()) {
                availableMove.add("right");
            }
            int index = random.nextInt(availableMove.size());
            ghost.moveWithDirection(availableMove.get(index));
        }
    }

    @Override
    public String getName() {
        return "random";
    }
}
