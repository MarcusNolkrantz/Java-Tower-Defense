package towerdefense.gameobjects;

import towerdefense.gamelogics.maps.Checkpoint;
import towerdefense.gamelogics.maps.Map;

import java.awt.*;

/**
 * Class for a teleporting enemy. TeleportingEnemy is a subclass of AbstractEnemy, which uses the Enemy interface. Instances of
 * this class are stored in a list in Board, and they are only created by an instance of EnemyMaker. This enemy moves by
 * teleporting from a checkpoint to the next, with [TELEPORT_PERIOD] delay inbetween.
 */
public class TeleportingEnemy extends AbstractEnemy
{
    private final static int SPEED = 0;
    private final static Color COLOR = Color.CYAN;
    private final static int RADIUS = 50;
    private final static int TELEPORT_PERIOD = 70;
    private int currentTeleportTime;

    public TeleportingEnemy(final int x, final int y, final int health, final Checkpoint checkpoint) {
	super(SPEED, x, y, health, COLOR, RADIUS, checkpoint);
	currentTeleportTime = TELEPORT_PERIOD;
    }

    public void move(Map map) {
        currentTeleportTime--;
        if (currentTeleportTime == 0) {
            currentTeleportTime = TELEPORT_PERIOD;
            checkpointIndex++;
	    checkpoint = map.getCheckpoint(checkpointIndex);
	    x = checkpoint.getX();
	    y = checkpoint.getY();
	    direction = checkpoint.getDirection();
	}
    }
}
