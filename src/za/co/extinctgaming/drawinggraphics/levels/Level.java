package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.entities.Wall;

import java.awt.*;


public class Level {
    protected String levelName;
    protected Wall[] walls;
    protected Rectangle character;
    protected Rectangle goal;
    protected boolean active;

    protected boolean goalReached = false;
    protected boolean wallTouched = false;
    protected boolean outOfBounds = false;

    protected long startTime;
    protected long duration;

    public Wall[] getWalls() {
        return walls;
    }

    public void calcWallTouch() {
        for (Wall wall : walls) {
            if (wall.getPolygon().intersects(character)) {
                wallTouched = true;
                active = false;
                break;
            }
        }
    }


    public boolean isWallTouched() {
        return wallTouched;
    }


    public Rectangle getCharacter() {
        return character;
    }


    public Rectangle getGoal() {
        return goal;
    }


    public String getLevelName() {
        return levelName;
    }


    public boolean isGoalReached() {
        return goalReached;
    }

    public void calcGoalReached() {
        if (character.intersects(goal)) {
            goalReached = true;
            active = false;
        }
    }

    public void calcDuration() {
        if (this.startTime != 0 && active) {
            duration = System.currentTimeMillis() - startTime;
        }
    }

    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        if (active) {
            startTime = System.currentTimeMillis();
        }
        this.active = active;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    public void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }

    public long getDuration() {
        return duration;
    }
}
