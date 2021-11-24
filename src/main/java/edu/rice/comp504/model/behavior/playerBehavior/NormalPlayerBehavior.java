package edu.rice.comp504.model.behavior.playerBehavior;

import edu.rice.comp504.model.PacManGameWorld;
import edu.rice.comp504.model.behavior.IObjectBehavior;
import edu.rice.comp504.model.moveobj.ICollidable;
import edu.rice.comp504.model.moveobj.Player;
import edu.rice.comp504.model.stillObject.Bean;

public class NormalPlayerBehavior implements IObjectBehavior {
    @Override
    public void changeState(ICollidable object, ICollidable collider) {

        if(collider instanceof Bean)
        {
            Bean thisBean = (Bean) collider;
            if(object instanceof Player)
            {
                thisBean.onCollide(object);
            }
            thisBean.destroy();
        }

    }
}
