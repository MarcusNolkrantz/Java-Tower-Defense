package towerdefense.gameobjects;

import towerdefense.gamelogics.maps.Checkpoint;

import java.awt.*;

import static java.awt.Color.RED;

/**
 * Class for the game's basic enemy. BasicEnemy is a subclass of AbstractEnemy, which uses the Enemy interface. Instances of
 * this class are stored in a list in Board, and they are only created by an instance of EnemyMaker.
 */
public class BasicEnemy extends AbstractEnemy
{
    private final static int START_SPEED = 2;
    private final static Color COLOR = RED;
    private final static int RADIUS = 40;

    public BasicEnemy(final int x, final int y, final int health, final Checkpoint checkpoint) {
	super(START_SPEED, x, y, health, COLOR, RADIUS, checkpoint);
    }
}
