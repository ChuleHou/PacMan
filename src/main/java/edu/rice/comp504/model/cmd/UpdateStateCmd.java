package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Tile;

public class UpdateStateCmd implements IGameObjCmd{

    private Tile[][] world;
    public UpdateStateCmd(Tile[][] world)
    {
        this.world = world;
    }
    @Override
    public void execute(IObject context) {
        // update ball screen position.
        // If position goes to next tile position, check if tile is movable.
        // If tile is movable, check if collide with any other object
        // fire onCollide if collide with other object




        // update object
        context.update();

    }
}
