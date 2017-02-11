package za.co.extinctgaming.drawinggraphics.core;

import za.co.extinctgaming.drawinggraphics.levels.*;

import java.io.Serializable;

public class GameState implements Serializable {
    private static GameState ourInstance = new GameState();

    public static GameState getInstance() {
        return ourInstance;
    }

    private Level[] levels;
    private long[] highscores;
    private int currentLevel;

    private GameState() {
        levels = new Level[2];
        highscores = new long[2];
        currentLevel = 0;

        loadLevels();
        loadHighScores();
    }

    private void loadLevels() {
        levels[0] = new Level_1();
        levels[1] = new Level_2();
    }

    private void loadHighScores() {
        highscores[0] = -1;
        highscores[1] = -1;
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
        if (highscores[currentLevel] == -1 || highscores[currentLevel] > levels[currentLevel].getDuration()) {
            highscores[currentLevel] = levels[currentLevel].getDuration();
        }
    }

    public long[] getHighscores() {
        return highscores;
    }
}
