package towerdefense.gamelogics;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for loading and saving highscores. This class is used in Board to save a highscore and in HighscorePainter to draw
 * the scores.
 */
public final class FileManager
{
    private FileManager() {
    }

    public static void saveHighscores(HighscoreList hs){
	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscore.bin"))) {
	    oos.writeObject(hs);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

   public static HighscoreList readHighscores() throws IOException, ClassNotFoundException, FileNotFoundException {
       HighscoreList highscores;

       	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscore.bin"))) {
	    highscores = (HighscoreList)(ois.readObject());
	}
       return highscores;
    }

}

