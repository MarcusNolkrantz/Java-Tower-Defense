package towerdefense.spawns;

import towerdefense.gamelogics.EnemyType;

/**
 * Class for keeping track of how many teleporting enemies should be made each round and their stats.
 *
 * This class is only instantiated in Board's function newGame(). The instance is put into a list of SpawnStats along with every
 * subclass of the interface SpawnStats.
 */
public class TeleportingSpawnStats extends AbstractSpawnStats
{
    public TeleportingSpawnStats(final float periodDecrement, final float healthIncrement,
				 final float amountIncrement, final float maxAmount, final float health, final float period)
    {
	super(EnemyType.TELEPORTING, periodDecrement, healthIncrement, amountIncrement, maxAmount, health, period);
    }

    public void newRound(int round) {
        period *= periodDecrement;
        maxAmount *= amountIncrement;
        health = health * healthIncrement + (float)round/10;
	currentTime = 0;
        if (round % 2 == 0) {
            if(round % 10 != 0){
                currentAmount = (int)Math.ceil(maxAmount);
	    }
        }
	else {
            currentAmount = 0;
	}
    }
}
