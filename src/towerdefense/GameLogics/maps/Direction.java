package towerdefense.gamelogics.maps;

/**
 * Enum for directions the enemies can have. This enum is used in both Checkpoint and Enemy.
 */
public enum Direction
{
    /**
     * No movement.
     */
    NONE(0, 0),
    /**
     * Upward movement.
     */
    UP(0, -1),
    /**
     * Downward movement.
     */
    DOWN(0, 1),
    /**
     * Movement left.
     */
    LEFT(-1, 0),
    /**
     * Movement right.
     */
    RIGHT(1, 0);
    /**
     * Amount of jumps horizontally.
     */
    public final int deltaX;
    /**
     * Amount of jumps vertically.
     */
    public final int deltaY;

    Direction(final int deltaX, final int deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
    }
}
