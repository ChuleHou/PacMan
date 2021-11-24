package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.moveobj.Ghost;
import edu.rice.comp504.model.moveobj.IObject;
import edu.rice.comp504.model.moveobj.Player;
import edu.rice.comp504.model.moveobj.Tile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class StrategyTest {
    private RandomStrategy randomStrategy;
    private ChaseWithShortestPathStrategy chaseShortestPathStrategy;
    private ChaseWithRelativeDistanceStrategy chaseRelativeStrategy;
    private Player target;
    private Ghost ghost;
    private Tile[][] gameWorld;

    @Before
    public void setUp() throws Exception {
        target = new Player();
        target.setPosition(new Point(4, 3));

        gameWorld = new Tile[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = new Tile();
                gameWorld[i][j] = tile;
            }
        }
        //   0 1 2 3   ← y
        // 0 x x x G
        // 1
        // 2   x x x
        // 3
        // 4 x x x T
        // ↑
        // x
        gameWorld[0][3].setMoveThrough(true);
        for (int i = 0; i <= 3; i++) {
            gameWorld[1][i].setMoveThrough(true);
        }
        gameWorld[2][0].setMoveThrough(true);
        for (int i = 0; i <= 3; i++) {
            gameWorld[3][i].setMoveThrough(true);
        }
        gameWorld[4][3].setMoveThrough(true);
        randomStrategy = new RandomStrategy(gameWorld);
        ghost = new Ghost(randomStrategy, new Point(800, 800));
        ghost.setTilePosition(new Point(0, 3));

        chaseShortestPathStrategy = new ChaseWithShortestPathStrategy(target, gameWorld);
        ghost = new Ghost(chaseShortestPathStrategy, new Point(800, 800));
        ghost.setTilePosition(new Point(0, 3));

        chaseRelativeStrategy = new ChaseWithRelativeDistanceStrategy(target, gameWorld);
        ghost = new Ghost(chaseRelativeStrategy, new Point(800, 800));
        ghost.setTilePosition(new Point(0, 3));
    }

    @Test
    public void updateState() {
//        randomStrategy.updateState(ghost);
//        chaseShortestPathStrategy.updateState(ghost);
        chaseRelativeStrategy.updateState(ghost);
    }
}