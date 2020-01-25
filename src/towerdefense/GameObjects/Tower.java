package towerdefense.gameobjects;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for the game's towers. This interface is implemented by AbstractTower.
 */
public interface Tower
{
    int getX();
    int getY();
    int getHeight();
    int getWidth();
    ImageIcon getImage();
    int getDamage();
    boolean canShoot();

    void attack(Enemy enemy);

    int getLeftX();
    int getTopY();
    int getLevel();
    void upgradeTower();
    int getUpgradeCost();
    int getSellPrice();
    void updateAttackDraw();
    int getCurrentAttackDraw();
    int getCost();
    boolean canDestroyIron();
    void draw(Graphics2D g2d);
    boolean withinRange(Enemy enemy);
    void noShot();
    void enableAttack();
    void disableIndivdualAttacks();
    boolean canDamageAgain();
    int getStringX();
}
