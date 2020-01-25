package towerdefense.gameobjects;

/**
 * Abstract class for the game's objects seen on screen. AbstractTower and AbstractEnemy are subclasses to this
 * class.
 */
public abstract class GameObject
{
    protected int x;
    protected int y;

    protected GameObject(final int x, final int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }
}
