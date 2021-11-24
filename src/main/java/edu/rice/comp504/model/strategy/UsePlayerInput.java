package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.PacManGameWorld;
import edu.rice.comp504.model.moveobj.ICollidable;
import edu.rice.comp504.model.moveobj.IMovable;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Player;

public class UsePlayerInput implements IUpdateStrategy{


    @Override
    public void updateState(IMovable context) {
        switch (DispatchAdapter.currentPlayerInput)
        {
            case "up":
                context.moveUp();
                break;
            case "down":
                context.moveDown();
                break;
            case "left":
                context.moveLeft();
                break;
            case "right":
                context.moveRight();
                break;
            default:
        }
        // detect collision with ghost and points
        if(context instanceof Player)
        {
            Player player = (Player) context;
            // detect player collision
            PacManGameWorld.getOnly().detectCollision(player);
            //player.detectCollision();
        }
    }

    @Override
    public String getName() {
        return null;
    }
}
