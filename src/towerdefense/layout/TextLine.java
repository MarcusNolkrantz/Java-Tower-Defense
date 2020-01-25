package towerdefense.layout;


/**
 * Class for the textlines in the menu.
 */
public class TextLine {
    private int paddingUp;
    private int fontSize;
    private String words;
    private Alignment alignment;

    public TextLine(int fontSize, String words, int paddingUp, Alignment alignment) {
        this.fontSize = fontSize;
        this.words = words;
        this.paddingUp = paddingUp;
        this.alignment = alignment;
    }

    public int getTextAlignment() {
        switch (alignment) {
            case LEFT:
                return LayoutConstants.TEXT_PADDING_EDGE;
            case CENTER:
                return (int) (LayoutConstants.MENU_WIDTH - fontSize * LayoutConstants.FONT_LENGHT_HEIGT_RATIO * words.length()) / 2;
            case RIGHT:
                return (int) (LayoutConstants.MENU_WIDTH - (fontSize * LayoutConstants.FONT_LENGHT_HEIGT_RATIO * words.length() + LayoutConstants.TEXT_PADDING_EDGE));
            default: return LayoutConstants.TEXT_PADDING_EDGE;
        }
    }

    public int getPaddingUp() {
        return paddingUp;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getWords() {
        return words;
    }
}
