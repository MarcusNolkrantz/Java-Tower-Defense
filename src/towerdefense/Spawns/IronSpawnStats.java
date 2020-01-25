package towerdefense.spawns;

import towerdefense.gamelogics.EnemyType;

/**
 * Class for keeping track of how many iron enemies should be made each round and their stats.
 *
 * This class is only instantiated in Board's function newGame(). The instance is put into a list of SpawnStats along with every
  * subclass of the interface SpawnStats.
 */
public class IronSpawnStats extends AbstractSpawnStats
{
    public IronSpawnStats(final float periodDecrement, final float healthIncrement,
			  final float amountIncrement, final float maxAmount, final float health, final float period)
    {
	super(EnemyType.IRON, periodDecrement, healthIncrement, amountIncrement, maxAmount, health, period);
    }

    public void newRound(int round) {
	period *= periodDecrement;
        health = healthIncrement;
	currentTime = 0;
        if (round % 5 == 3) {
            maxAmount = (int) Math.ceil((maxAmount+round)*amountIncrement);
            currentAmount = (int) maxAmount;
	}
	else {
            currentAmount = 0;
	}
    }
}
