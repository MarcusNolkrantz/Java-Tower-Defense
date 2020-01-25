package towerdefense.layout.frames;

import towerdefense.layout.Alignment;
import towerdefense.gamelogics.Board;
import towerdefense.layout.Box;
import towerdefense.gamelogics.maps.Map;
import towerdefense.layout.TextLine;
import towerdefense.layout.painters.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static towerdefense.layout.LayoutConstants.*;

/**
 * Class for a frame that let you choose a map. An instance of this class is created if the user press the
 * "new game" button on the MenuFrame.
 */
public class SettingsFrame extends JFrame
{

    private Board board = null;
    private java.util.List<Box> boxes;

    public SettingsFrame() throws HeadlessException {
        super("Tower Defense");

		boxes = new ArrayList<>();
	final List<TextLine> lines = new ArrayList<>();

		boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP, BOX_WIDTH, BOX_HEIGHT, "Map 1", FONTSIZE_2, STROKE_SIZE));
		boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + SPACE_BETWEEN_BOXES,
				  BOX_WIDTH, BOX_HEIGHT, "Map 2", FONTSIZE_2, STROKE_SIZE));
		boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 2 * SPACE_BETWEEN_BOXES,
				  BOX_WIDTH, BOX_HEIGHT, "Map 3", FONTSIZE_2, STROKE_SIZE));
		boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 3 * SPACE_BETWEEN_BOXES,
				  BOX_WIDTH, BOX_HEIGHT, "Back", FONTSIZE_2, STROKE_SIZE));
		lines.add(new TextLine(FONTSIZE_1, "Choose", HEADLINE_PADDING_UP, Alignment.CENTER));

		Painter painter = new Painter(boxes, lines);
       	JPanel menuPanel = new JPanel();
       	menuPanel.add(painter);
       	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       	this.setResizable(false);
       	this.setVisible(true);
       	SettingsEventHandler handler = new SettingsEventHandler();
       	menuPanel.addMouseListener(handler);
       	this.add(menuPanel);
       	this.pack();
    }


    private class SettingsEventHandler extends MouseAdapter {

        @Override
	public void mouseClicked(final MouseEvent e) {
            super.mouseClicked(e);
            for (Box box : boxes) {
                if (box.hasBeenClicked(e.getX(), e.getY())) {
                    String name = box.getText();
                    Map map;
                    GameFrame gameFrame;

                    switch (name) {
                        case "Map 1":
                            map = Map.MAP1;
                            board = new Board(map.getHeight(), map.getWidth(), map);
                            startClock();
                            gameFrame = new GameFrame(board); //this warning can be ignored according to Mariusz
			    dispose();
			    break;
			    case "Map 2":
			        map = Map.MAP2;
			        board = new Board(map.getHeight(), map.getWidth(), map);
			        startClock();
			        gameFrame = new GameFrame(board); //this warning can be ignored according to Mariusz
				dispose();
				break;
				case "Map 3":
				    map = Map.MAP3;
				    board = new Board(map.getHeight(), map.getWidth(), map);
				    startClock();
				    gameFrame = new GameFrame(board); //this warning can be ignored according to Mariusz
				    dispose();
				    break;
				    case "Back":
				        MenuFrame menuFrame = new MenuFrame();
				        dispose();
                    }
                }
            }
        }

        private void startClock() {
            final Action doOneStep = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					board.tick();
				}
			};
            final Timer clockTimer = new Timer(20, doOneStep);
            clockTimer.setCoalesce(false);
            clockTimer.start();
        }
    }
}

