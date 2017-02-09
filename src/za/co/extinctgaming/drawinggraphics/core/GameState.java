package za.co.extinctgaming.drawinggraphics.core;

import za.co.extinctgaming.drawinggraphics.levels.Level;
import za.co.extinctgaming.drawinggraphics.levels.LevelOne;
import za.co.extinctgaming.drawinggraphics.levels.LevelTwo;

import java.io.Serializable;

public class GameState implements Serializable {
    private Level[] levels;
    private int currentLevel;

    public GameState() {
        levels = new Level[2];
        currentLevel = 0;

        loadLevels();
    }

    private void loadLevels() {
        levels[0] = new LevelOne();
        levels[1] = new LevelTwo();
    }

    public Level[] getLevels() {
        return levels;
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

    public void reloadLevel(){
        try {
            levels[currentLevel] = levels[currentLevel].getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
