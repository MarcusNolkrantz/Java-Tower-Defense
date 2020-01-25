package towerdefense.gamelogics;

import towerdefense.gamelogics.maps.Checkpoint;
import towerdefense.gameobjects.BasicEnemy;
import towerdefense.gameobjects.Enemy;
import towerdefense.gameobjects.IronEnemy;
import towerdefense.gameobjects.TeleportingEnemy;

/**
 * Class for creating enemies. An instance of this class is created in Board's constructor. This class is used in Board to spawn
 * enemies each round according to SpawnStats' values.
 */
public class EnemyMaker
{
    private int startX;
    private int startY;
    private Checkpoint startPoint;

    public EnemyMaker(final Checkpoint startPoint) {
	this.startPoint = startPoint;
	this.startX = startPoint.getX();
	this.startY = startPoint.getY();
    }

    public Enemy createEnemy(EnemyType type, int health) {
        switch (type) {
			case BASIC:
				return new BasicEnemy(startX, startY, health, startPoint);
			case TELEPORTING:
				return new TeleportingEnemy(startX, startY, health, startPoint);
			case IRON:
				return new IronEnemy(startX, startY, health, startPoint);
	    	default:
	        	return null;
	}
    }
}
