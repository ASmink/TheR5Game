package za.co.extinctgaming.drawinggraphics.core;

import za.co.extinctgaming.drawinggraphics.levels.*;

import java.io.*;

public class GameState implements Serializable {
    private Level[] levels;
    private long[] highScores;
    private int currentLevel;

    public GameState() {
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
        if (currentLevel < levels.length - 1) {
            currentLevel++;
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

    public long[] getHighScores() {
        return highScores;
    }
}
