package towerdefense.gameobjects;

import towerdefense.gamelogics.maps.Checkpoint;
import towerdefense.gamelogics.maps.Direction;
import towerdefense.gamelogics.maps.Map;

import java.awt.*;

import static towerdefense.gamelogics.maps.Direction.NONE;

/**
 * Abstract class for the game's enemies. This abstract class has the following subclasses: BasicEnemy, TeleportingEnemy and
 * IronEnemy. Instances of this class' subclasses are stored in a list in Board, and they are only created by an instance of
 * EnemyMaker.
 */
public abstract class AbstractEnemy extends GameObject implements Enemy
{
    protected int speed;
    protected int health;
    protected int maxHealth;
    protected int radius;
    protected int checkpointIndex;
    protected Color color;
    protected Direction direction;
    protected Checkpoint checkpoint;
    protected boolean damaged;

    protected AbstractEnemy(final int speed, final int x, final int y, final int health, final Color color,
			    final int radius, final Checkpoint checkpoint) {
        super(x, y);
	this.speed = speed;
	this.health = health;
	this.maxHealth = health;
	this.color = color;
	this.radius = radius;
	this.checkpoint = checkpoint;
	this.checkpointIndex = 0;
	this.direction = checkpoint.getDirection();
	this.damaged = false;
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public int getHealth() {
	return health;
    }

    public Color getColor() {
	return color;
    }

    public int getRadius() {
	return radius;
    }

    public boolean isDamaged() {
	return damaged;
    }


    public int getCenterPointY() {
  	return y + radius/2;
      }

    public int getCenterPointX() {
  	return x + radius/2;
      }

    public boolean hasMaxHealth() {
        return health == maxHealth;
    }

    /**
     * From the starting checkpoint, the enemy is given a direction to the next checkpoint. The enemy will move to that
     * checkpoint, and if the enemy and the checkpoint are overlapping, get that checkpoint's direction to the next checkpoint.
     */
    public void move(Map map) {
        if (isOverlappingCheckpoint()) {
    	    x = checkpoint.getX();
    	    y = checkpoint.getY();
    	    direction = checkpoint.getDirection();
    	    if(direction != NONE){
    	    	checkpointIndex++;
    	    	checkpoint = map.getCheckpoint(checkpointIndex);
    	    }
        }
     	this.x += direction.deltaX*speed;
     	this.y += direction.deltaY*speed;
    }

    public void resetDamageTaken() {
	damaged = false;
    }

    public boolean isDead() {
	return health <= 0;
    }

    public void takeDamage(Tower tower) {
        health -= tower.getDamage();
        damaged = true;
    }

    public boolean outOfBoard() {
	return this.direction == NONE;
    }

    protected boolean isOverlappingCheckpoint() {
	return (Math.abs(y - checkpoint.getY()) < speed && Math.abs(x - checkpoint.getX()) < speed);
    }


}


