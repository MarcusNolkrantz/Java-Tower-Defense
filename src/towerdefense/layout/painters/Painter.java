package towerdefense.layout.painters;

import towerdefense.layout.Box;
import towerdefense.layout.LayoutConstants;
import towerdefense.layout.TextLine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Class that draws the menu.
 */
public class Painter extends JComponent {

    private List<Box> boxes;
    private List<TextLine> lines;

    public Painter(List<Box> boxes, List<TextLine> lines) {
        this.boxes = boxes;
        this.lines = lines;
    }

    @Override public Dimension getPreferredSize() {

        return new Dimension(LayoutConstants.MENU_WIDTH, LayoutConstants.MENU_HEIGHT);
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(LayoutConstants.TEXT_COLOR);
        g2d.fillRect(0,0,LayoutConstants.MENU_WIDTH,LayoutConstants.MENU_HEIGHT);
        g2d.setColor(LayoutConstants.BORDER_COLOR);


        for (TextLine line : lines) {
            g2d.setFont(new Font("Courier New", Font.BOLD, line.getFontSize()));
            g2d.drawString(line.getWords(),line.getTextAlignment(),line.getPaddingUp());
        }



        for (Box box : boxes) {
            g2d.setStroke(new BasicStroke(box.getBorderSize()));
            g2d.setFont(new Font("Courier New", Font.BOLD, box.getFontSize()));

            g2d.drawRect(box.getPaddingLeft(), box.getPaddingUp(), box.getBoxWidth(),box.getBoxHeight());
            g2d.drawString(box.getText(), box.getTextPaddingLeft(),box.getTextPaddingUp());
        }
    }
}
