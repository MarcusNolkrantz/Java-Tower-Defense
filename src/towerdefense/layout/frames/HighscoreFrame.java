package towerdefense.layout.frames;

import towerdefense.gamelogics.FileManager;
import towerdefense.gamelogics.maps.Map;
import towerdefense.gamelogics.HighscoreList;
import towerdefense.layout.Alignment;
import towerdefense.layout.Box;
import towerdefense.layout.painters.Painter;
import towerdefense.layout.TextLine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static towerdefense.layout.LayoutConstants.*;

/**
 * Class for the frame showing highscores of a selected map. An instance of this class is created if the user press
 * the any of the map buttons on ScoreFrame.
 */
public class HighscoreFrame extends JFrame {
    private List<towerdefense.layout.Box> boxes;

    public HighscoreFrame(Map map) {
        super("Tower Defense");

        boxes = new ArrayList<>();
        List<TextLine> lines = new ArrayList<>();

        boxes.add(new towerdefense.layout.Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 3 * SPACE_BETWEEN_BOXES,
                                              BOX_WIDTH, BOX_HEIGHT, "Back", FONTSIZE_2, STROKE_SIZE));
        lines.add(new TextLine(FONTSIZE_1, "Highscores " + map, HEADLINE_PADDING_UP, Alignment.CENTER));


        HighscoreList highscoreList = null;
        try {
            highscoreList = FileManager.readHighscores();
        }catch(FileNotFoundException e){
            highscoreList = new HighscoreList();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (highscoreList != null) {
            int spaceBetweenLines = 10;
            int i = 0;
            int y = 100;
            int limit;

            limit = highscoreList.getLenght(map);
            while (i < limit) {
                lines.add(new TextLine(FONTSIZE_2, i + 1 + ". " + highscoreList.getName(map, i) + " \t" +
                        highscoreList.getScore(map, i) + " points", y, Alignment.LEFT));

                y += spaceBetweenLines + FONTSIZE_2;
                i++;
            }
        }

        Painter painter = new Painter(boxes, lines);
        JPanel highscorePanel = new JPanel();
        highscorePanel.add(painter);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        HighscoreEventHandler handler = new HighscoreEventHandler();
        highscorePanel.addMouseListener(handler);
        this.add(highscorePanel);
        this.pack();
    }

    private class HighscoreEventHandler extends MouseAdapter {
        @Override
        public void mouseClicked(final MouseEvent e) {
            super.mouseClicked(e);
            for (Box box : boxes) {
                if (box.hasBeenClicked(e.getX(), e.getY())) {
                    MenuFrame menuFrame = new MenuFrame();
                    dispose();
                }
            }
        }
    }


}

