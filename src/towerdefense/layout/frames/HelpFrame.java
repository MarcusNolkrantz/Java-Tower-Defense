package towerdefense.layout.frames;

import towerdefense.layout.Alignment;
import towerdefense.layout.Box;
import towerdefense.layout.TextLine;
import towerdefense.layout.painters.Painter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static towerdefense.layout.LayoutConstants.*;


/**
 * Class for the help menu's frame. An instance of this class is created if the user press the "help" button on the MenuFrame.
 */
public class HelpFrame extends JFrame
{
    private List<Box> boxes;

    public HelpFrame(){
        super("Tower Defense");

        boxes = new ArrayList<>();
        final List<TextLine> lines = new ArrayList<>();


        boxes.add(new Box(BOX_PADDING_LEFT, BOX_PADDING_UP + 3 * SPACE_BETWEEN_BOXES,
                          BOX_WIDTH, BOX_HEIGHT, "Back", FONTSIZE_2, STROKE_SIZE));

        lines.add(new TextLine(FONTSIZE_1, "Help", HEADLINE_PADDING_UP, Alignment.CENTER));

        String[] text = { ("Your goal is to prevent the ballons from crossing"),
                ("the map, by strategically buying, selling, upgrading"),
                ("and placing out towers. To place a tower simply"),
                ("click at it in the sidemenu and click again at your"),
                ("preferred location. You earn money by destroying"),
                ("ballons or when you finish a round. Every time a"),
                ("enemy gets across the map you loose health. The game"),
                ("ends when all your health is gone. Rumors says that"),
                ("a legend named Marcus Nolkrantz once scored a score "),
                ("of 1000 points, can you beat him?") };

        int y = 100;
        int spaceBeetweenLines = 10;
        for (String line : text) {
            lines.add(new TextLine(FONTSIZE_3, line, y, Alignment.LEFT));
            y += FONTSIZE_2 + spaceBeetweenLines;
        }

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

    private class MenuEventHandler extends MouseAdapter {
        @Override public void mouseClicked(final MouseEvent e) {
            super.mouseClicked(e);
            for (Box box : boxes) {
                if(box.hasBeenClicked(e.getX(), e.getY())){
                   MenuFrame menuFrame = new MenuFrame();
                   dispose();

                }
            }
        }
    }
}
