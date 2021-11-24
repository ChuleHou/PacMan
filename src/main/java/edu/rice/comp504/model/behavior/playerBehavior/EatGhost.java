package edu.rice.comp504.model.behavior.playerBehavior;

import edu.rice.comp504.model.moveobj.Ghost;
import edu.rice.comp504.model.moveobj.ICollidable;
import edu.rice.comp504.model.moveobj.Player;

public class EatGhost extends NormalPlayerBehavior{
    @Override
    public void changeState(ICollidable object, ICollidable collider) {
        // still handle eat beans
        super.changeState(object, collider);

        // handle eat ghost
        if(collider instanceof Ghost)
        {
            Ghost thisGhost = (Ghost) collider;
            if(object instanceof Player)
            {
                Player thisPlayer = (Player) object;
                if(!thisGhost.getIsEaten())
                {
                    thisPlayer.addScore(thisGhost.getReward());
                    thisGhost.eatByPlayer();
                }
            }
        }

    }
}
