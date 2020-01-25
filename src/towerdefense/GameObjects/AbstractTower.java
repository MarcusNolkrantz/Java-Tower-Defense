package towerdefense.gameobjects;

import towerdefense.layout.LayoutConstants;

import javax.swing.*;

/**
 * Abstract class for the game's towers. This class has the following subclasses: BasicTower, MediumTower and AdvancedTower.
 * Instances of this class' subclasses are stored in Board, and they are only created by an instance of
  * TowerMaker.
 */
public abstract class AbstractTower extends GameObject implements Tower
{
    protected final static int ATTACK_DRAW_COOLDOWN = 8;
    protected final static int MAX_LEVEL = 10;
    private static final double FONT_LENGHT_HEIGT_RATIO = 0.55;

    protected int height;
    protected int width;
    protected int cost;
    protected ImageIcon image = null;
    protected int range;
    protected int cooldown;
    protected int timeToNextShoot;
    protected int damage;
    protected Enemy targetEnemy = null;
    protected int level;
    protected int upgradeCost;
    protected int currentAttackDraw; //checks how long an attack currently has been drawn
    protected int totalCost;
    protected String name;

    protected boolean isAttacking = false;
    protected boolean canAttack = false;

    protected AbstractTower(final String name, final int height, final int width, final int x, final int y, final int cost,
			    final ImageIcon image, final int range, final int cooldown, final int damage, final int upgradeCost) {
        super(x, y);
        this.name = name;
	if(image != null) {
	    this.height = height;
	    this.image = image;
	}
	else{
	    this.height = LayoutConstants.FONTSIZE_2;
	}
	this.width = width;
	this.cost = cost;
	this.range = range;
	this.cooldown = cooldown;
	this.timeToNextShoot = cooldown;
	this.damage = damage;
	this.currentAttackDraw = 0;
	this.level = 1;
	this.upgradeCost = upgradeCost;
	this.totalCost = cost;

    }

    public int getCost() {
	return cost;
    }

    public int getCurrentAttackDraw() {
	return currentAttackDraw;
    }

    public Enemy getTargetEnemy() { return targetEnemy; }

    public int getDamage() {
	return damage;
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public ImageIcon getImage() {
	return image;
    }

    public int getLeftX() {
	return x - height/2;
    }

    public int getTopY() { return y - height/2; }

    public int getLevel() {
	return level;
    }

    public int getUpgradeCost() {
	return upgradeCost * level;
    }

    public int getSellPrice() { return totalCost / 2; }

    public boolean canShoot() {
	return timeToNextShoot == 0;
    }

    public int getRange() {
	return range;
    }

    public void resetTime() {
        timeToNextShoot = cooldown;
    }

    public void updateAttackDraw() {

        if(isAttacking){
	    currentAttackDraw--;
	    if (currentAttackDraw <= 0) {
	        currentAttackDraw = 0;
	        stopAttack();
	    }
	}

    }

    private void updateTime(){
        timeToNextShoot--;
        if (timeToNextShoot < 0) {
            timeToNextShoot = 0;
	}
    }

    public void attack(final Enemy enemy) {
	currentAttackDraw = ATTACK_DRAW_COOLDOWN;
        startAttack();
	targetEnemy = enemy;
	resetTime();
	enemy.takeDamage(this);
    }


    /**
     * Checks if an enemy is within a tower's range using Pythagorean theorem.
     * @param enemy to compare with.
     * @return wether or not the enemy is within range of the tower calling this function.
     */
    public boolean withinRange(Enemy enemy){
       int distanceX = getX() - enemy.getCenterPointX();
       int distanceY = getY() - enemy.getCenterPointY();
       double hypotenuse = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
       return hypotenuse <= range;
   }

    protected boolean isBelowMaxLevel() {
        return level < MAX_LEVEL;
    }
    protected void setCooldownNonZero() {
        if (cooldown == 0) {
            cooldown = 1;
	}
    }

    public void noShot(){
        updateTime();
    }

    private void startAttack(){
        isAttacking = true;
    }

    private void stopAttack(){
        isAttacking = false;
    }

    public void enableAttack(){
        canAttack = true;
    }

    public void disableIndivdualAttacks(){
        canAttack = true;
    }

    @Override public boolean canDamageAgain() {
	return canAttack;
    }

    @Override public int getStringX() {

	return x - (int) (name.length() * LayoutConstants.FONTSIZE_2 * FONT_LENGHT_HEIGT_RATIO / 2);
    }


}