package towerdefense.layout.frames;

import net.miginfocom.swing.MigLayout;
import towerdefense.gamelogics.Board;
import towerdefense.gamelogics.BoardListener;
import towerdefense.gameobjects.AdvancedTower;
import towerdefense.gameobjects.BasicTower;
import towerdefense.gameobjects.MediumTower;
import towerdefense.gameobjects.Tower;
import towerdefense.layout.LayoutConstants;
import towerdefense.gamelogics.LoadResources;
import towerdefense.gamelogics.TowerType;
import towerdefense.layout.painters.BoardPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Class for the game's frame that displays the game, it's stats (round, money, etc.) and the shop. An instance of this class
 * is created in SettingsFrame if the user choose to start a game.
 */
public class GameFrame extends JFrame implements BoardListener
{
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, LayoutConstants.FONTSIZE_2);
    private static final int SIDE_MARGIN = 20;

    private Board board;
    private BoardPainter boardPainter;
    private JLabel score;
    private JLabel health;
    private JLabel selected;
    private JLabel level;
    private JButton upgrade;
    private JButton sell;
    private JLabel money;
    private JLabel round;

    public GameFrame(final Board board) {
        super("Tower Defense");
	this.board = board;

	this.boardPainter = new BoardPainter(board);
	board.addBoardListener(boardPainter);
	LoadResources img = new LoadResources();
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	board.addBoardListener(this);

	JPanel mainPanel = new JPanel(new GridBagLayout());
	JPanel sidePanel = new JPanel(new MigLayout());
	JPanel statPanel = new JPanel(new MigLayout());
	JPanel imgPanel = new JPanel(new MigLayout(""));
	JPanel moneyPanel = new JPanel(new MigLayout());
	JPanel headLine1 = new JPanel(new MigLayout());
	JPanel selectedPanel = new JPanel(new MigLayout());
	JPanel changePanel = new JPanel(new MigLayout());
	JPanel runPanel = new JPanel(new MigLayout());

	Font labelFont = LABEL_FONT;

	JButton quitButton = new JButton("Quit");
	quitButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e) {
	        int selected = JOptionPane.showConfirmDialog(null, "Do you really want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
	        if (selected == JOptionPane.YES_OPTION) {
	            board.stopBackgroundMusic();
	            MenuFrame menuFrame = new MenuFrame(); //this warning can be ignored according to Mariusz
		    dispose();
	            }
	    }
	});
	JButton playButton = new JButton("New round");
	playButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e) {
		if(!board.isActiveRound()){
		    board.newRound();
		}
	    }
	});
	JButton pauseButton = new JButton("pause");
	pauseButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e) {
		board.pauseGame();
	    }
	});

	JLabel tower1;
	ImageIcon image1 = img.getImage("/resources/pinkTower.png", "pinkTower");
	if(image1 != null){
	  tower1 = new JLabel(image1);
	}
	else{

	    tower1 = new JLabel("Pink");
	}

	tower1.addMouseListener(new MouseAdapter()
	{
	    @Override public void mouseClicked(final MouseEvent e) {
		super.mouseClicked(e);
		board.selectTower(TowerType.BASIC);
	    }
	});
	JLabel tower2;
	ImageIcon image2 = img.getImage("/resources/blueTower.png", "blueTower");
	if(image2 != null){
	    tower2 = new JLabel(image2);
	}
	else{
	    tower2 = new JLabel("Blue");
	}

	tower2.addMouseListener(new MouseAdapter()
	{
	    @Override public void mouseClicked(final MouseEvent e) {
		super.mouseClicked(e);
		board.selectTower(TowerType.MEDIUM);
	    }
	});

	JLabel tower3;
	ImageIcon image3 = img.getImage("/resources/yellowTower.png", "yellowTower");
	if(image3 != null){
	    tower3 = new JLabel(image3);
	}
	else{
	    tower3 = new JLabel("Yellow");
	}
	tower3.addMouseListener(new MouseAdapter()
		{
		    @Override public void mouseClicked(final MouseEvent e) {
		super.mouseClicked(e);
		board.selectTower(TowerType.ADVANCED);
	    }
	});

	upgrade = new JButton("Upgrade: xxxx");
	upgrade.setVisible(false);
	upgrade.addMouseListener(new MouseAdapter()
	{
	    @Override public void mouseClicked(final MouseEvent e) {
	        super.mouseClicked(e);
	        if(board.getSelectedTower() != null){
		    board.upgrade();
		    String levelLabel = ("Level: " + board.getSelectedTower().getLevel());
		    level.setText(levelLabel);
		    String upgradeLabel = ("Upgrade: " + board.getSelectedTower().getUpgradeCost() + "$");
		    upgrade.setText(upgradeLabel);
	        }
	    }
	});

	sell = new JButton("Sell: xxxx");
	sell.setVisible(false);
	sell.addMouseListener(new MouseAdapter()
	{
	    @Override public void mouseClicked(final MouseEvent e) {
		super.mouseClicked(e);
		if(board.getSelectedTower() != null){
		    board.sellTower();
		    board.setSelectedTower(null);
		    level.setVisible(false);
		    selected.setVisible(false);
		    upgrade.setVisible(false);
		    sell.setVisible(false);
		}
	    }
	});

	changePanel.add(upgrade);
	changePanel.add(sell);
	this.score = new JLabel("Score:");
	this.health = new JLabel("Health:");
	score.setFont(labelFont);
	health.setFont(labelFont);

	statPanel.add(score,"gap left 30, gap right 50");
	statPanel.add(health);


	JLabel tower = new JLabel("Towers");
	tower.setFont(labelFont);
	headLine1.add(tower);

	imgPanel.add(tower1,"gap right 10,gap left 10,center");
	imgPanel.add(tower2,"gap right 10,gap left 10, center");
	imgPanel.add(tower3,"gap left 10,center, gap right 10, wrap");
	imgPanel.add(new JLabel(BasicTower.getCOST() + "$"), "center");
	imgPanel.add(new JLabel(MediumTower.getCOST() + "$"), "center");
	imgPanel.add(new JLabel(AdvancedTower.getCOST() + "$"), "center");

	JLabel headLine2 = new JLabel("Selected tower:");
	headLine2.setFont(labelFont);
	level = new JLabel();
	selected = new JLabel(img.getImage("/resources/yellowTower.png", "yellowTower"));
	selected.setFont(labelFont);
	selected.setVisible(false);
	selectedPanel.add(headLine2,"wrap");
	selectedPanel.add(selected,"center, wrap, gapy 10");
	selectedPanel.add(level,"wrap,center");

	money = new JLabel("Money: 100$");
	money.setFont(labelFont);
	round = new JLabel("Round: 0");
	round.setFont(labelFont);
	moneyPanel.add(money, "wrap, center");
	moneyPanel.add(round, "center, gapy 20");

	runPanel.add(playButton);
	runPanel.add(pauseButton);
	runPanel.add(quitButton);

	statPanel.setPreferredSize(new Dimension(350,0));


	sidePanel.add(statPanel,"wrap");
	sidePanel.add(headLine1, "gap left 100, gapy 10, wrap");
	sidePanel.add(imgPanel, "gap left 45,wrap");
	sidePanel.add(selectedPanel,"gapy 20, gap left 65, wrap");
	sidePanel.add(changePanel,"wrap,gap left 20");
	sidePanel.add(moneyPanel,"wrap, gapy 40, gap left 80");
	sidePanel.add(runPanel,"gapy 20");

	addComponent(0,0,mainPanel,boardPainter,1,1,GridBagConstraints.WEST);
	addComponent(1,0,mainPanel,sidePanel,1,1,GridBagConstraints.EAST);

	this.add(mainPanel);
	this.pack();
	this.setVisible(true);
	this.setResizable(false);

	ActionEventHandler handler = new ActionEventHandler();
	mainPanel.addMouseListener(handler);
	mainPanel.addMouseMotionListener(handler);
    }

    /**
     * Update labels. If the game is over, display an input box asking for the user's name and add the score to the highscore
     * list, then ask if the user wants to play again.
     */
    @Override public void boardChanged() {
	updateLabelText();
        if(board.isGameOver()) {
	    String name = JOptionPane.showInputDialog("Please enter your name");
	    board.addScore(name,board.getScore());
	    int selected = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Try again?", JOptionPane.YES_NO_OPTION);
	    	if (selected == JOptionPane.NO_OPTION) {
	    	    board.stopBackgroundMusic();
	    	    dispose();
		    MenuFrame menuFrame = new MenuFrame(); //this warning can be ignored according to Mariusz
	    	    }
	    	else if (selected == JOptionPane.YES_OPTION) {
		    board.newGame();
		}

	}
    }

    private void updateLabelText(){

        health.setText("Health: " + board.getHealth());
        score.setText("Score: " + board.getScore());
        money.setText("Money: " + board.getMoney() + "$");
        round.setText("Round: " + board.getRound());
        if(board.getSelectedTower() != null){
            sell.setText("Sell: " +  board.getSelectedTower().getSellPrice());

	}
        this.pack();
    }

    private void addComponent(int x, int y, JPanel panel, JComponent component, int componentWidth, int componentHeight, int place){
        GridBagConstraints constants = new GridBagConstraints();

        constants.gridx = x;
        constants.gridy = y;
        constants.gridheight = componentHeight;
        constants.gridwidth = componentWidth;
        constants.weightx = 0;
        constants.weighty = 0;
        constants.insets = new Insets(5,5,5,5);
        constants.anchor = place;

        panel.add(component, constants);
    }

    private class ActionEventHandler extends MouseAdapter
    {
	@Override public void mouseClicked(final MouseEvent e) {
	    int mousePosX = e.getX();
	    int mousePosY = e.getY();
	if(board.getHoldingTower() != null) {
	    if (0 <= mousePosX && mousePosX <= boardPainter.getPreferredSize().getWidth() - SIDE_MARGIN && 0 <= mousePosY &&
		mousePosY <= boardPainter.getPreferredSize().getHeight() - SIDE_MARGIN) {
		board.placeTower(e.getX(), e.getY());
	    } else {
		board.setFlowingTower(null);
		board.setHoldingTower(null);
	    }
	}
	    if(board.getTowerAtPos(mousePosX,mousePosY) != null){
	        Tower tower = board.getTowerAtPos(mousePosX, mousePosY);
		board.setSelectedTower(tower);

		ImageIcon img = tower.getImage();
		if(img != null) {
		    selected.setIcon(tower.getImage());
		}
		else {
		    selected.setText(tower.toString());
		}
	        selected.setVisible(true);

	        String levelLabel = ("Level: " + board.getSelectedTower().getLevel());
		level.setText(levelLabel);
		level.setVisible(true);

		String upgradeLabel = ("Upgrade: " + tower.getUpgradeCost() + "$");
		upgrade.setText(upgradeLabel);
		upgrade.setVisible(true);

		String sellLabel = ("Sell: " + tower.getSellPrice() + "$");
		sell.setText(sellLabel);
		sell.setVisible(true);

	    }
	    else{
	        selected.setVisible(false);
	        board.setSelectedTower(null);
	        level.setVisible(false);
	        upgrade.setVisible(false);
	        sell.setVisible(false);
	    }
	    
	}

	@Override public void mouseMoved(final MouseEvent e) {
	    int mousePosX = e.getX();
	    int mousePosY = e.getY();
	    if (board.getHoldingTower() != null){
	        board.flowTower(mousePosX, mousePosY);
	    }
	
	}
    }
}
