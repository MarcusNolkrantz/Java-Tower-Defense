package towerdefense.layout.painters;

import towerdefense.gamelogics.Board;
import towerdefense.gamelogics.BoardListener;
import towerdefense.gameobjects.Enemy;
import towerdefense.gameobjects.Tower;
import towerdefense.gamelogics.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * Class for drawing the game's board. An instance of this class is created in GameFrame and stored in a field in both
 * Board and GameFrame. This class implements BoardListener so that the board is repainted every time it changes.
 */
public class BoardPainter extends JComponent implements BoardListener
{
    private static final Font ENEMY_HEALTH_FONT = new Font("Garamond", Font.BOLD, 20);
    private static final int GROUND_RED = 34;
    private static final int GROUND_GREEN = 139;
    private static final int GROUND_BLUE = 34;
    private static final Map<Tile, Color> COLORMAP = new EnumMap<>(Tile.class);
    static {
        COLORMAP.put(Tile.PATH, Color.GRAY);
        COLORMAP.put(Tile.GROUND, new Color(GROUND_RED, GROUND_GREEN, GROUND_BLUE));
    }

    private Board board;

    public BoardPainter(final Board board) {
        this.board = board;

    }

    public void boardChanged() { repaint(); }

    @Override public Dimension getPreferredSize() {
        return new Dimension(board.getWidth()*Board.getTileSize(), board.getHeight()*Board.getTileSize());
    }

    @Override public void paint(Graphics g) {
        Image image = createImage(getWidth(),getHeight());
        Graphics graphics = image.getGraphics();
        paintComponent(graphics);
        g.drawImage(image,0,0,this);

    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	//draw tiles
	for (int y = 0; y < board.getHeight(); y++) {
	    for (int x = 0; x < board.getWidth(); x++) {
		Tile tile = board.getTileAt(x, y);
		Color color = COLORMAP.get(tile);
		g2d.setColor(color);
		g2d.fillRect(x * Board.getTileSize(), y * Board.getTileSize(), Board.getTileSize(), Board.getTileSize());
	    }
	}

	//draw enemies
	for (int i = 0; i < board.getEnemyAmount(); i++) {
	    Enemy enemy = board.getEnemy(i);
	    g2d.setColor(enemy.getColor());
	    g2d.fillOval(enemy.getX(), enemy.getY(), enemy.getRadius(), enemy.getRadius());
	}

	//draw enemies health
	g2d.setFont(ENEMY_HEALTH_FONT);
	for (int i = 0; i < board.getEnemyAmount(); i++) {
	    Enemy enemy = board.getEnemy(i);
	    if (!enemy.hasMaxHealth()) {
	        g2d.setColor(Color.BLACK);
	        g2d.drawString(enemy.getHealth() + "/" + enemy.getMaxHealth(), enemy.getX(), enemy.getY());
	    }
	}

	//draw towers
	for (int i = 0; i < board.getTowerAmount(); i++) {
	    Tower tower = board.getTowerAt(i);
	    ImageIcon image = tower.getImage();
	    if(image != null){
		image.paintIcon(this, g, tower.getLeftX(), tower.getTopY());
	    }
	    else{
		g2d.setColor(Color.BLACK);
	        g2d.drawString(tower.toString(),tower.getStringX(),tower.getY());
	    }

	}

	//Draw tower attacks
	for (int i = 0; i < board.getTowerAmount(); i++) {

	    Tower tower = board.getTowerAt(i);
	    if (tower.getCurrentAttackDraw() != 0) {
		tower.draw(g2d);
	    }
	}

	//draw the tower flowing on the cursor
	Tower flowingTower = board.getFlowingTower();
	if(flowingTower != null) {
	    ImageIcon image = flowingTower.getImage();
	    if(image != null) {
		image.paintIcon(this, g, flowingTower.getLeftX(), flowingTower.getTopY());
	    }
	    else{
	        g2d.setColor(Color.BLACK);
	        g2d.drawString(flowingTower.toString(),flowingTower.getStringX(),flowingTower.getY());
	    }
	}
    }
}
