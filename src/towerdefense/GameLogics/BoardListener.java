package towerdefense.gamelogics;

/**
 * Interface for classes that want to know when the board has changed. BoardPainter and BoardFrame implement this interface.
 */
public interface BoardListener
{
    public void boardChanged();
}
