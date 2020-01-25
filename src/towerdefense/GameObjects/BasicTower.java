package towerdefense.gameobjects;

import towerdefense.gamelogics.LoadResources;

import java.awt.*;

/**
 * Class for the game's basic tower. This tower deals damage to a single enemy that is within range. Instances of this class are
 * only created in TowerMaker. If created, an instance is stored in Board's fields towers,selectedTower or
  * flowingTower.
 */
public class BasicTower extends AbstractTower
{
    private static final int RANGE = 120;
    private static final int COST = 30;
    private static final int HEIGHT = 40;
    private static final int WIDTH = 40;
    private static final int COOLDOWN = 40;
    private static final float COOLDOWN_REDUCTION = 0.9F;
    private static final int START_DAMAGE = 1;
    private static final int UPGRADE_COST = 25;

    public static int getCOST() {
	return COST;
    }

    public BasicTower(final int x, final int y) {
	super("Pink",HEIGHT, WIDTH, x, y, COST,
	      new LoadResources().getImage("/resources/pinkTower.png", "pinkTower"),
	      RANGE, COOLDOWN, START_DAMAGE, UPGRADE_COST);
    }



    @Override public void upgradeTower() {
        if (isBelowMaxLevel()) {
	    totalCost += getUpgradeCost();
	    level++;
		 damage++;
		 range += 5;
	    if (level % 2 == 0) {
	      cooldown *= COOLDOWN_REDUCTION;
	      setCooldownNonZero();
	    }
	}
    }

    public boolean canDestroyIron(){
        return false;
    }

    @Override public void draw(Graphics2D g2d) {
        int shotWidth = 3;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(shotWidth));
        Enemy enemy = getTargetEnemy();
        if(enemy != null) {
	    g2d.drawLine(getX(), getY(), enemy.getCenterPointX(), enemy.getCenterPointY());
	}
    }

    @Override public void disableIndivdualAttacks() {
	canAttack = false;
    }

    @Override public String toString() {
	return name;
    }
}
