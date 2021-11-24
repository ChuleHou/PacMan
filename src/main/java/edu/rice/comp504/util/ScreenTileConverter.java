package edu.rice.comp504.util;

import java.awt.*;

public class ScreenTileConverter {

    private int tileSize;


    public ScreenTileConverter(int tileSize)
    {

    }

    public Point screenPointToTilePoint(int screenPointX, int screenPointY)
    {
        int tileX = screenPointX/tileSize;
        int tileY = screenPointY/tileSize;

        return new Point(tileX, tileY);
    }

}
