package towerdefense.gamelogics;

import java.io.Serializable;

/**
 * Class for highscores.
 */
public class Highscore implements Serializable
{
    private String name;
        private int score;

        public Highscore(final String name, final int score) {
    	this.name = name;
    	this.score = score;
        }

        @Override public String toString() {
    	return name;
        }

        public int getScore() {
    	return score;
        }
}
