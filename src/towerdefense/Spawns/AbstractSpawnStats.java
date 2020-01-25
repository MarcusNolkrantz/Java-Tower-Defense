package towerdefense.spawns;

import towerdefense.gamelogics.EnemyType;

/**
 * Abstract class for keeping track of how many enemies should be made each round and their stats. This abstract class
 * has the following subclasses: BasicSpawnStats, TeleportingSpawnStats and IronSpawnStats. Subclasses to thisclass are only
 * instantiated in Board's function newGame(), and they are only being used in Board.
 */
public abstract class AbstractSpawnStats implements SpawnStats
{
    protected EnemyType enemyType;
    protected float periodDecrement;
    protected float healthIncrement;
    protected float amountIncrement;
    protected float maxAmount;
    protected float health;
    protected float period;
    protected int currentTime;
    protected int currentAmount;

    protected AbstractSpawnStats(final EnemyType type, final float periodDecrement, final float healthIncrement,
				 final float amountIncrement, final float maxAmount, final float health, final float period)
    {
	this.enemyType = type;
	this.periodDecrement = periodDecrement;
	this.healthIncrement = healthIncrement;
	this.amountIncrement = amountIncrement;
	this.maxAmount = maxAmount;
	this.health = health;
	this.period = period;
	this.currentTime = 0;
	this.currentAmount = 0;
    }

    public EnemyType getEnemyType() {
	return enemyType;
    }

    public float getHealth() {
	return health;
    }

    public int getCurrentAmount() {
	return currentAmount;
    }

    public boolean canSpawn() { return currentTime == (int) Math.ceil(period) && currentAmount != 0; }

    public void enemySpawned() {
        currentTime = 0;
        currentAmount--;
    }

    public void updateTime() {
        currentTime++;
    }
}
