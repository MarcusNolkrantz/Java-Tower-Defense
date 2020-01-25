package towerdefense.gamelogics;

import javax.swing.*;
import java.net.URL;

/**
 * Class for loading images of the game's towers.
 */
public class LoadResources
{
    public LoadResources() { }

    public ImageIcon getImage(String path, String desciption){
        ImageIcon img = null;
	URL imgUrl = getClass().getResource(path);
	if(imgUrl != null){
	    img = new ImageIcon(imgUrl, desciption);
	}

	return img;
	}

    }

