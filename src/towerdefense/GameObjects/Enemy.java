package towerdefense.gameobjects;

import towerdefense.gamelogics.maps.Map;

import java.awt.*;

/**
 * Interface for enemies. This interface is implemented by AbstractEnemy.
 */
public interface Enemy
{
    int getX();
    int getY();
    int getHealth();
    Color getColor();
    int getRadius();
    void move(Map map);
    int getCenterPointX();
    int getCenterPointY();
    boolean isDead();
    void takeDamage(Tower tower);
    boolean outOfBoard();
    boolean hasMaxHealth();
    int getMaxHealth();
    boolean isDamaged();
    void resetDamageTaken();
}
