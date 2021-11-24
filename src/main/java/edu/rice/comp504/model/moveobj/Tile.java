package edu.rice.comp504.model.moveobj;

public class Tile {

    // true: not wall, false: wall
    boolean moveThrough;

    public boolean isMoveThrough() {
        return moveThrough;
    }

    public void setMoveThrough(boolean moveThrough) {
        this.moveThrough = moveThrough;
    }
}
