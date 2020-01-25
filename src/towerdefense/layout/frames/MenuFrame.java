package towerdefense.layout.frames;

import towerdefense.layout.Alignment;
import towerdefense.layout.Box;
import towerdefense.layout.TextLine;
import towerdefense.layout.painters.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static towerdefense.layout.LayoutConstants.*;

/**
 * Class for the main menu's frame. From here the user is able to create a SettingsFrame, ScoreFrame and HelpFrame. This class
 * is the first thing to be instantiated when the application is started.
 */
public class MenuFrame extends JFrame
{
	private List<Box> boxes;

    public MenuFrame() throws HeadlessException {
	super("Tower Defense");

	boxes = new ArrayList<>();
	final List<TextLine> lines = new ArrayList<>();

	boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP, BOX_WIDTH, BOX_HEIGHT, "New game", FONTSIZE_2, STROKE_SIZE));
	boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + SPACE_BETWEEN_BOXES,
			  BOX_WIDTH, BOX_HEIGHT, "Highscore", FONTSIZE_2, STROKE_SIZE));
	boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 2 * SPACE_BETWEEN_BOXES,
			  BOX_WIDTH, BOX_HEIGHT, "Help", FONTSIZE_2, STROKE_SIZE));
	boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 3 * SPACE_BETWEEN_BOXES,
			  BOX_WIDTH, BOX_HEIGHT, "Quit", FONTSIZE_2, STROKE_SIZE));

	lines.add(new TextLine(FONTSIZE_1, "Tower Defense", HEADLINE_PADDING_UP, Alignment.CENTER));

	Painter painter = new Painter(boxes, lines);
	JPanel menuPanel = new JPanel();
	menuPanel.add(painter);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setVisible(true);
	MenuEventHandler handler = new MenuEventHandler();
	menuPanel.addMouseListener(handler);
	this.add(menuPanel);
	this.pack();
    }

    private class MenuEventHandler extends MouseAdapter
    {
	@Override public void mouseClicked(final MouseEvent e) {
	    super.mouseClicked(e);
		for (Box box : boxes) {
			if(box.hasBeenClicked(e.getX(), e.getY())){
				String name = box.getText();

				switch(name){
					case "New game":
						SettingsFrame settingsFrame = new SettingsFrame(); //this warning can be ignored according to Mariusz
						dispose();
						break;
					case "Highscore":
						ScoreFrame scoreFrame = new ScoreFrame(); //this warning can be ignored according to Mariusz
						dispose();
						break;
					case "Help":
						HelpFrame helpFrame = new HelpFrame(); //this warning can be ignored according to Mariusz
						dispose();
						break;
					case "Quit":
						int selected = JOptionPane.showConfirmDialog(null, "Do you really want to quit?",
								"Quit?", JOptionPane.YES_NO_OPTION);
						if (selected == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
						break;

				}
			}
		}
	}


    }
}
