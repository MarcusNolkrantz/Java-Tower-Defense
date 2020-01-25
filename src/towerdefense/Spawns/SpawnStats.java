package towerdefense.spawns;

import towerdefense.gamelogics.EnemyType;

/**
 * Interface for keeping track of how many enemies should be made each round and their stats. AbstractSpawnStats implements
 * this interface.
 */

public interface SpawnStats
{
    void enemySpawned();
    void updateTime();
    EnemyType getEnemyType();
    float getHealth();
    int getCurrentAmount();
    void newRound(int round);
    boolean canSpawn();
}
