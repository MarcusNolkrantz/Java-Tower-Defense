package towerdefense.layout.frames;

import towerdefense.layout.Alignment;
import towerdefense.layout.Box;
import towerdefense.gamelogics.maps.Map;
import towerdefense.layout.TextLine;
import towerdefense.layout.painters.Painter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static towerdefense.layout.LayoutConstants.*;

/**
 * Class for a frame/menu that shows the possible highscores. An instance of this class is created if the user press the
 * "highscore" button on the MenuFrame.
 */
public class ScoreFrame extends JFrame
{

    private List<Box> boxes;

    public ScoreFrame() {
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
        lines.add(new TextLine(FONTSIZE_1, "Choose Highscore", HEADLINE_PADDING_UP, Alignment.CENTER));

        Painter painter = new Painter(boxes, lines);

        JPanel menuPanel = new JPanel();
        menuPanel.add(painter);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        ScoreEventHandler handler = new ScoreEventHandler();
        menuPanel.addMouseListener(handler);
        this.add(menuPanel);
        this.pack();
    }

    private class ScoreEventHandler extends MouseAdapter
    {
        @Override public void mouseClicked(final MouseEvent e) {
            super.mouseClicked(e);
            for (Box box : boxes) {
                if (box.hasBeenClicked(e.getX(), e.getY())) {
                    String name = box.getText();
                    HighscoreFrame highscoreFrame = null;
                    switch (name) {
                        case "Map 1":
                            highscoreFrame = new HighscoreFrame(Map.MAP1);
                            dispose();
                            break;
                        case "Map 2":
                            highscoreFrame = new HighscoreFrame(Map.MAP2);
                            dispose();
                            break;
                        case "Map 3":
                            highscoreFrame = new HighscoreFrame(Map.MAP3);
                            dispose();
                            break;
                        case "Back":
                            MenuFrame menuFrame = new MenuFrame();
                            dispose();


                    }
                }
            }
        }
    }
}
