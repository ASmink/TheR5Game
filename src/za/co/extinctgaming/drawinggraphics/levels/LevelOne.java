package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.entities.Wall;

import java.awt.*;

public class LevelOne implements Level {

    private String levelName;
    private Wall[] walls;
    private Rectangle character;
    private Rectangle goal;
    private boolean active;

    private boolean goalReached = false;
    private boolean wallTouched = false;

    public LevelOne() {
        levelName = "LEVEL ONE";
        walls = new Wall[2];
        walls[0] = new Wall(new Polygon(new int[]{0, 0, 130, 130, 445, 445, 760, 760, 1280, 1280}, new int[]{0, 340, 340, 250, 250, 560, 560, 120, 120, 0}, 10), null);
        walls[1] = new Wall(new Polygon(new int[]{0, 0, 190, 190, 385, 385, 820, 820, 1280, 1280}, new int[]{720, 400, 400, 310, 310, 620, 620, 180, 180, 720}, 10), null);

        character = new Rectangle(10, 350, 40, 40);
        goal = new Rectangle(1225, 130, 40, 40);
    }

    @Override
    public Wall[] getWalls() {
        return walls;
    }

    @Override
    public void calcWallTouch() {
        for (Wall wall : walls) {
            if (wall.getPolygon().intersects(character)) {
                wallTouched = true;
                active = false;
                break;
            }
        }
    }

    @Override
    public boolean isWallTouched() {
        return wallTouched;
    }


    @Override
    public Rectangle getCharacter() {
        return character;
    }

    @Override
    public Rectangle getGoal() {
        return goal;
    }

    @Override
    public String getLevelName() {
        return levelName;
    }

    @Override
    public boolean isGoalReached() {

        return goalReached;
    }

    public void calcGoalReached() {
        if (character.intersects(goal)) {
            goalReached = true;
            active = false;
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
