package za.co.extinctgaming.drawinggraphics;

import za.co.extinctgaming.drawinggraphics.levels.Level;
import za.co.extinctgaming.drawinggraphics.levels.Level_1;
import za.co.extinctgaming.drawinggraphics.levels.Level_2;

import java.io.Serializable;

public class GameState implements Serializable {
    private Level[] levels;
    private long[] highScores;
    private int currentLevel;

    GameState() {
        levels = new Level[2];
        highScores = new long[2];
        currentLevel = 0;

        loadLevels();
        loadHighScores();
    }

    private void loadLevels() {
        levels[0] = new Level_1();
        levels[1] = new Level_2();
    }

    private void loadHighScores() {
        highScores[0] = -1;
        highScores[1] = -1;
    }

    public Level getCurrentLevel() {
        return levels[currentLevel];
    }

    public Level getNextLevel() {
        try {
            levels[currentLevel] = levels[currentLevel].getClass().newInstance();

            if (currentLevel < levels.length - 1) {
                currentLevel++;
            } else {
                currentLevel = 0;
            }
            levels[currentLevel] = levels[currentLevel].getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return levels[currentLevel];
    }

    public void reloadLevel() {
        try {
            levels[currentLevel] = levels[currentLevel].getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void saveScore() {
        if (highScores[currentLevel] == -1 || highScores[currentLevel] > levels[currentLevel].getDuration()) {
            highScores[currentLevel] = levels[currentLevel].getDuration();
        }
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public long[] getHighScores() {
        return highScores;
    }
}
