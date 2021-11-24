package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.moveobj.Ghost;
import edu.rice.comp504.model.moveobj.IMovable;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Tile;

import java.awt.*;
import java.util.*;

public class ChaseWithShortestPathStrategy implements IUpdateStrategy{

    private IObject target;
    private Tile[][] gameWorld;
    private final int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * @param target target object to chase
     * @param gameWorld pacman game world
     */
    public ChaseWithShortestPathStrategy(IObject target, Tile[][] gameWorld) {
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

        // use bfs to find shortest path
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{currentX, currentY});
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        boolean found = false;
        visited.add(serializeTile(currentX, currentY));

        while (!queue.isEmpty()) {
            int[] loc = queue.poll();

            // assume iobject.getPosition will return tile position instead of screen position
            if (loc[0] == (int)target.getPosition().getX() && loc[1] == (int)target.getPosition().getY()) {
                found = true;
                break;
            }
            for (int[] dir : direction) {
                int newX = loc[0] + dir[0];
                int newY = loc[1] + dir[1];
                if (newX < 0 || newX >= gameWorld.length || newY < 0 || newY >= gameWorld[0].length) {
                    continue;
                }
                if (!gameWorld[newX][newY].isMoveThrough() || visited.contains(serializeTile(newX, newY))) {
                    continue;
                }

                queue.add(new int[]{newX, newY});
                visited.add(serializeTile(newX, newY));
                parentMap.put(serializeTile(newX, newY), serializeTile(loc[0], loc[1]));
            }
        }
        if (!found) {
            System.out.println("Does not found any shortest path from " + serializeTile(currentX, currentY) +
                    " to " + serializeTile((int)target.getPosition().getX(), (int)target.getPosition().getY()));
            return;
        }

        // trace back
        String str = serializeTile((int)target.getPosition().getX(), (int)target.getPosition().getY());
        String currentStr = serializeTile(currentX, currentY);
        while (parentMap.containsKey(str) && !parentMap.get(str).equals(currentStr)) {
            str = parentMap.get(str);
        }

        // find next move
        int[] intendNextLoc = deserializeTile(str);
        if (intendNextLoc[0] == currentX - 1 && intendNextLoc[1] == currentY) {
            ghost.moveWithDirection("left");
        } else if (intendNextLoc[0] == currentX + 1 && intendNextLoc[1] == currentY) {
            ghost.moveWithDirection("right");
        } else if (intendNextLoc[0] == currentX && intendNextLoc[1] == currentY - 1) {
            ghost.moveWithDirection("down");
        } else if (intendNextLoc[0] == currentX && intendNextLoc[1] == currentY + 1) {
            ghost.moveWithDirection("up");
        } else {
            System.out.println(str + " is not the next location of " + currentStr);
        }
    }

    private String serializeTile(int x, int y) {
        return x + " " + y;
    }

    private int[] deserializeTile(String str) {
        int[] loc = new int[2];
        String[] split = str.split(" ");
        if (split.length != 2) {
            System.out.println("input " + str + " format error");
            return loc;
        }
        loc[0] = Integer.parseInt(split[0]);
        loc[1] = Integer.parseInt(split[1]);
        return loc;
    }

    @Override
    public String getName() {
        return "shortestPath";
    }
}
