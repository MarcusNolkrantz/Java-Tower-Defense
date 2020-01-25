package towerdefense.gameobjects;

import towerdefense.layout.LayoutConstants;
import towerdefense.gamelogics.LoadResources;

import java.awt.*;

/**
 * Class for a tower. This tower targets an enemy within range, then damages all enemies around that target. Instances of this
  * class are only created in TowerMaker. If created, an instance is stored in Board's fields towers,selectedTower or
  * flowingTower.
 */
public class MediumTower extends AbstractTower
{

    private static final int RANGE = 100;
    private static final int COST = 150;
    private static final int HEIGHT = 40;
    private static final int WIDTH = 40;
    private static final int COOLDOWN = 30;
    private static final float COOLDOWN_REDUCTION = 0.9F;
    private static final int START_DAMAGE = 1;
    private static final int ROCKET_RADIUS = 80;
    private static final int UPGRADE_COST = 130;

    public static int getRocketRadius() {
	return ROCKET_RADIUS;
    }

    public static int getCOST() {
	return COST;
    }

    public MediumTower(final int x, final int y)
    {
	super("Blue",HEIGHT, WIDTH, x, y, COST, new LoadResources().getImage("/resources/blueTower.png", "pinkTower"),
	      RANGE, COOLDOWN, START_DAMAGE, UPGRADE_COST);
    }


    @Override public void upgradeTower() {
        if (isBelowMaxLevel()) {
	    totalCost += getUpgradeCost();
	    level++;
	    range += 10;
	    cooldown *= COOLDOWN_REDUCTION;
	    setCooldownNonZero();
	    if (level % 3 == 2) {
		damage++;
	    }
	}
    }

    public boolean canDestroyIron(){
        return true;
    }

    @Override public void draw(final Graphics2D g2d) {
	int rocketRadius = MediumTower.getRocketRadius();
	g2d.setColor(new Color(LayoutConstants.MAX_VALUE_COLOR, 0, 0, LayoutConstants.SEMI_TRANSPARENT_COLOR));
	g2d.fillOval(getTargetEnemy().getCenterPointX() - rocketRadius,
		     getTargetEnemy().getCenterPointY() - rocketRadius,
		     rocketRadius * 2, rocketRadius * 2);
    }

    @Override public String toString() {
	return name;
    }
}

