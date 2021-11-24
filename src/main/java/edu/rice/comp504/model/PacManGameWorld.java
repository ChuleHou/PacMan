package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.moveobj.*;
import org.eclipse.jetty.util.IO;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PacManGameWorld {
    private static PacManGameWorld ONLY = null;

    private int width;
    private int height;

    private Tile[][] world = new Tile[10][20];

    private ArrayList<IObject> allGameObject;
    private final PropertyChangeSupport pcs;
    private static Point dims;

    public static PacManGameWorld getOnly() {
        if(ONLY == null) {
            ONLY = new PacManGameWorld();
        }
        return ONLY;
    }

    public PacManGameWorld()
    {
        pcs = new PropertyChangeSupport(this);
    }

    public static void setCanvasDims(String height, String width) {
        int x = Integer.parseInt(width);
        int y = Integer.parseInt(height);
        PacManGameWorld.dims = new Point(x, y);
    }

    public void addObject(String type) {

        Player newPlayer = new Player();
        addListener(newPlayer);
    }

    public ICollidable[] getCollidables()
    {
        return null;
    }

    public Tile[][] getTiles()
    {
        return world;
    }



    public PropertyChangeListener[] updateGameWorld()
    {
        UpdateStateCmd cmd = new UpdateStateCmd(world);
        pcs.firePropertyChange("theClock", null, cmd);
        return pcs.getPropertyChangeListeners();
    }

    public void detectCollision(IMovable context)
    {
        Point currentPosition = new Point(-1,-1);
        if(context instanceof  IObject)
        {
            IObject object = (IObject) context;
            currentPosition = object.getPosition();
        }
        for (PropertyChangeListener listener: pcs.getPropertyChangeListeners("theClock")) {
            if(listener instanceof IObject)
            {
                IObject targetObject = (IObject) listener;
                if(targetObject.getPosition() == currentPosition)
                {
                    if(context instanceof ICollidable)
                    {
                        ICollidable col = (ICollidable) context;
                        col.onCollide((ICollidable) targetObject);
                    }
                }
            }
        }

    }

    public void removeListeners() {

    }

    private void addListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener("theClock", pcl);
    }

    public void destroy(IObject object)
    {
        pcs.removePropertyChangeListener("theClock", (PropertyChangeListener) object);
    }





}
