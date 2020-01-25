package towerdefense.gamelogics;

import towerdefense.gameobjects.AdvancedTower;
import towerdefense.gameobjects.BasicTower;
import towerdefense.gameobjects.MediumTower;
import towerdefense.gameobjects.Tower;

/**
 * Class for making towers. This class is instantiated in Board's constructor and are used in Board's functions flowTower() and
 * placeTower().
 */
public class TowerMaker
{

    public TowerMaker() {
    }

    public Tower createTower(TowerType type, int x, int y) {
        switch(type) {
			case BASIC:
	        	return new BasicTower(x, y);
			case MEDIUM:
	        	return new MediumTower(x, y);
			case ADVANCED:
	        	return new AdvancedTower(x, y);
	    	default:
	       	 return null;
        }
    }
}
