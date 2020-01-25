package towerdefense.layout;

/**
 * Class for the boxes in the menu.
 */

public class Box {
    private int paddingLeft;
    private int paddingUp;
    private int boxWidth;
    private int boxHeight;
    private String text;
    private int fontSize;
    private int borderSize;


    public Box(int paddingLeft, int paddingUp, int boxWidth, int boxHeight, String text, int fontSize, int borderSize) {
        this.paddingLeft = paddingLeft;
        this.paddingUp = paddingUp;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.text = text;
        this.fontSize = fontSize;
        this.borderSize = borderSize;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingUp() {
        return paddingUp;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public int getTextPaddingUp(){
        if(fontSize > boxHeight){
            fontSize = boxHeight;
        }
        int adjust = (boxHeight - fontSize) / 2 + borderSize/2;


        return paddingUp - adjust + boxHeight;
    }

    public int getTextPaddingLeft(){
        int adjust = (int)(boxWidth - fontSize*LayoutConstants.FONT_LENGHT_HEIGT_RATIO*text.length()) / 2 + borderSize/2;
        return paddingLeft + adjust;
    }

    public String getText() {
        return text;
    }

    public boolean hasBeenClicked(int x, int y){
        return 	paddingLeft <= x && x <= paddingLeft + boxWidth && paddingUp <= y && y <= paddingUp + boxHeight;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getBorderSize() {
        return borderSize;
    }
}
