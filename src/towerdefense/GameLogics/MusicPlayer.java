package towerdefense.gamelogics;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Class that handles the sound.
 */
public class MusicPlayer
{

    private Clip clip = null;


    public MusicPlayer(final String name) {

	final String fileName = ("/resources/audio/" + name + ".wav");
        load(fileName);
    }

    private void load(String name){
	try {
	    URL url = MusicPlayer.class.getResource(name);
	    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
	    AudioFormat format = ais.getFormat();
	    Info info = new Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(ais);

	    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
	    e1.printStackTrace();
	}


    }

    public void playBackgroundMusic(){
        clip.setMicrosecondPosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopbackgroundMusic(){
	clip.stop();
	clip.close();
    }

    public void pauseBackgroundMusic(){
        clip.stop();
    }

    public void continueBackgroundMusic(){
        clip.start();
    }

    public void playSoundEffect(){
	clip.setMicrosecondPosition(0);
        clip.start();
    }
}
