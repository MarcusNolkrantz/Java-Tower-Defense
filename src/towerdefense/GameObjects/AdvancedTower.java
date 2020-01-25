package towerdefense.gameobjects;

import towerdefense.layout.LayoutConstants;
import towerdefense.gamelogics.LoadResources;

import java.awt.*;

/**
 * Class for the game's advanced tower. This tower deals damage to all enemies around it within it's range. Instances of this
 * class are only created in TowerMaker. If created, an instance is stored in Board's fields towers,selectedTower or
 * flowingTower.
 */
public class AdvancedTower extends AbstractTower
{
    private static final int RANGE =100;
    private static final int COST = 130;
    private static final int HEIGHT = 40;
    private static final int WIDTH = 40;
    private static final int COOLDOWN = 50;
    private static final float COOLDOWN_REDUCTION = 0.95F;
    private static final int START_DAMAGE = 1;
    private static final int UPGRADE_COST = 70;

    public AdvancedTower(final int x, final int y)
    {
     super("Yellow", HEIGHT, WIDTH, x, y, COST,
	   new LoadResources().getImage("/resources/yellowTower.png", "pinkTower"),
	   RANGE, COOLDOWN, START_DAMAGE, UPGRADE_COST);
    }

    public static int getCOST() {
	return COST;
    }

    @Override public void upgradeTower() {
        if (isBelowMaxLevel()) {
	    totalCost += getUpgradeCost();
	    level++;
	    cooldown *= COOLDOWN_REDUCTION;
	    setCooldownNonZero();
	    if (level % 4 == 3) {
		damage++;
	    }
	}
    }

    public boolean canDestroyIron(){
    	return false;
}

    @Override public void draw(final Graphics2D g2d) {
	g2d.setColor(new Color(LayoutConstants.MAX_VALUE_COLOR, 0, 0, LayoutConstants.SEMI_TRANSPARENT_COLOR));
		    g2d.fillOval(getX() - getRange(), getY() - getRange(),
				 getRange() * 2,
				 getRange() * 2);
    }

    @Override public String toString() {
	return name;
    }
}

