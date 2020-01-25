package towerdefense.gamelogics;

import towerdefense.gamelogics.maps.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing the game's highscores.
 */
public class HighscoreList implements Serializable
{
    private List<Highscore> highscoreMap1;
    private List<Highscore> highscoreMap2;
    private List<Highscore> highscoreMap3;

    public HighscoreList() {
        this.highscoreMap1 = new ArrayList<>();
        this.highscoreMap2 = new ArrayList<>();
        this.highscoreMap3 = new ArrayList<>();

       setDeafaultHighscores();
    }

    public void addScore(Map map, String name, int score) {
        switch (map) {
            case MAP1:
                highscoreMap1.add(new Highscore(name, score));
                highscoreMap1.sort(new ScoreComparator());
                highscoreMap1.remove(getLenght(map) - 1);
                break;
            case MAP2:
                highscoreMap2.add(new Highscore(name, score));
                highscoreMap2.sort(new ScoreComparator());
                highscoreMap2.remove(getLenght(map) - 1);
                break;
            case MAP3:
                highscoreMap3.add(new Highscore(name, score));
                highscoreMap3.sort(new ScoreComparator());
                highscoreMap3.remove(getLenght(map) - 1);
        }
    }


    public String getName(Map map, int i) {
        switch (map) {
            case MAP1:
                return highscoreMap1.get(i).toString();
            case MAP2:
                return highscoreMap2.get(i).toString();
            case MAP3:
                return highscoreMap3.get(i).toString();
            default:
                return "";
        }
    }

    public int getScore(Map map, int i) {
        switch (map) {
            case MAP1:
                return highscoreMap1.get(i).getScore();
            case MAP2:
                return highscoreMap2.get(i).getScore();
            case MAP3:
                return highscoreMap3.get(i).getScore();
            default:
                return 0;
        }
    }

    public int getLenght(Map map) {
        switch (map) {
            case MAP1:
                return highscoreMap1.size();
            case MAP2:
                return highscoreMap2.size();
            case MAP3:
                return highscoreMap3.size();
            default:
                return 0;
        }
    }

   private void setDeafaultHighscores(){
        for (int i = 0; i < 9; i++) {
            highscoreMap1.add(new Highscore("Marcus", 100));
            highscoreMap2.add(new Highscore("Marcus", 100));
            highscoreMap3.add(new Highscore("Marcus", 100));
        }
    }



}


