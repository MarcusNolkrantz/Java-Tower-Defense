package towerdefense.gameobjects;

import towerdefense.gamelogics.maps.Checkpoint;

import java.awt.*;
/**
 * Class for the iron enemy.IronEnemy is a subclass of AbstractEnemy, which uses the Enemy interface. Instances of
 * this class are stored in a list in Board, and they are only created by an instance of EnemyMaker. This enemy can only take
 * damage from instances of MediumTower.
 */
public class IronEnemy extends AbstractEnemy
{
    private final static int SPEED = 2;
    private final static Color COLOR = new Color(220,220,220);
    private final static int RADIUS = 30;

    public IronEnemy(final int x, final int y, final int health, final Checkpoint checkpoint) {
	super(SPEED, x, y, health, COLOR, RADIUS, checkpoint);
    }

    @Override public void takeDamage(final Tower tower) {
        if (tower.canDestroyIron()) {
	    super.takeDamage(tower);
	}
    }
}
