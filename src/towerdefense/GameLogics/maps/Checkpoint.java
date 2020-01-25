package towerdefense.gamelogics.maps;

import towerdefense.gamelogics.Board;

/**
 * Class for the map's checkpoint. An instance of Checkpoint is used to guide an enemy through the map's path and also acts as
 * an start- and endpoint of the path.
 */
public class Checkpoint
{
    private int x;
    private int y;
    private Direction direction;

    public Checkpoint(final int x, final int y, final Direction direction) {
	this.x = x * Board.getTileSize();
	this.y = y*Board.getTileSize();
	this.direction = direction;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public Direction getDirection() {
	return direction;
    }
}
