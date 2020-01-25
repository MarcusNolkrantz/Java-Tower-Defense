package towerdefense.spawns;

import towerdefense.gamelogics.EnemyType;

/**
 * Class for keeping track of how many basic enemies should be made each round and their stats.
 *
 * This class is only instantiated in Board's function newGame(). The instance is put into a list of SpawnStats along with every
  * subclass of AbstractSpawnStats.
 */
public class BasicSpawnStats extends AbstractSpawnStats
{
    public BasicSpawnStats(final float periodDecrement, final float healthIncrement,
			   final float amountIncrement, final float maxAmount, final float health, final float period)
    {
	super(EnemyType.BASIC, periodDecrement, healthIncrement, amountIncrement, maxAmount, health, period);
    }

    public void newRound(int round) {
        maxAmount = maxAmount * amountIncrement + round;
        health += healthIncrement;
        period *= periodDecrement;
		currentTime = 0;

	if(round % 10 != 0){
	    currentAmount = (int)Math.ceil(maxAmount);
	}
	else{
	    currentAmount = 0;
	}
    }


}
