package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.moveobj.Ghost;
import edu.rice.comp504.model.moveobj.IMovable;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ChaseWithRelativeDistanceStrategy implements IUpdateStrategy {

    private IObject target;
    private Tile[][] gameWorld;

    /**
     * @param target target object to chase
     * @param gameWorld pacman game world
     */
    public ChaseWithRelativeDistanceStrategy(IObject target, Tile[][] gameWorld) {
        this.target = target;
        this.gameWorld = gameWorld;
    }

    @Override
    public void updateState(IMovable context) {
        if (!(context instanceof Ghost)) {
            System.out.println("Input context has wrong type");
            return;
        }
        Ghost ghost = (Ghost)context;

        Point currentLocation = ghost.getTilePosition();
        int currentX = (int)currentLocation.getX();
        int currentY = (int)currentLocation.getY();
        List<String> availableMove = getAvailableMove(currentX, currentY);

        if (availableMove.size() == 0) {
            System.out.println("Found no path from " + currentX + " " + currentY);
            return;
        }

        // assume iobject.getPosition will return tile position instead of screen position
        int targetX = (int)target.getPosition().getX();
        int targetY = (int)target.getPosition().getY();

        if (currentX > targetX && currentY > targetY) {
            if (availableMove.contains("left")) {
                ghost.moveWithDirection("left");
            } else if (availableMove.contains("down")) {
                ghost.moveWithDirection("down");
            } else {
                ghost.moveWithDirection(availableMove.get(0));
            }
        } else if (currentX > targetX && currentY <= targetY) {
            if (availableMove.contains("right")) {
                ghost.moveWithDirection("right");
            } else if (availableMove.contains("down")) {
                ghost.moveWithDirection("down");
            } else {
                ghost.moveWithDirection(availableMove.get(0));
            }
        } else if (currentX <= targetX && currentY > targetY) {
            if (availableMove.contains("left")) {
                ghost.moveWithDirection("left");
            } else if (availableMove.contains("up")) {
                ghost.moveWithDirection("up");
            } else {
                ghost.moveWithDirection(availableMove.get(0));
            }
        } else if (currentX <= targetY && currentY <= targetY) {
            if (availableMove.contains("right")) {
                ghost.moveWithDirection("right");
            } else if (availableMove.contains("up")) {
                ghost.moveWithDirection("up");
            } else {
                ghost.moveWithDirection(availableMove.get(0));
            }
        } else {
            System.out.println("No move is made from " + currentX + " " + currentY);
        }
    }

    public List<String> getAvailableMove(int currentX, int currentY) {
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
        return availableMove;
    }

    @Override
    public String getName() {
        return "relativeDistance";
    }
}
