package towerdefense.gamelogics;

import java.util.Comparator;

/**
 * Class for comparing highscores. This class is instantiated and used in HighscoreList for sorting the list of highscores of a
 * map when a new score is added.
 */
public class ScoreComparator implements Comparator<Highscore>
{
    public ScoreComparator() {


      }

      /**
       * Identify the object with the highest score.
       * @param score1 the first object.
       * @param score2 the second object.
       * @return 1 if score1 has the highest score, -1 if 2 has the highest score, 0 if they are equal.
       */
      @Override public int compare(Highscore score1, Highscore score2) {
          return Integer.compare(score2.getScore(), score1.getScore());
      }
}
