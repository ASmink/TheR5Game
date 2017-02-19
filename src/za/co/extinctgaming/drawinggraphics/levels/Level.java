package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.levels.entities.CharacterEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.FinnishEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.WallEntity;
import za.co.extinctgaming.drawinggraphics.resources.Textures;

import java.awt.*;
import java.io.Serializable;


public class Level implements Serializable {
    protected String levelName;
    protected Textures.TextureName background = Textures.TextureName.ERROR;
    protected WallEntity[] walls;
    protected CharacterEntity character;
    protected FinnishEntity finnish;
    protected boolean active;

    protected boolean goalReached = false;
    protected boolean wallTouched = false;
    protected boolean outOfBounds = false;

    protected long startTime;
    protected long duration;

    public WallEntity[] getWalls() {
        return walls;
    }

    public void calcWallTouch() {
        for (WallEntity wall : walls) {
            if (wall.getPolygon().intersects(character.getRectangle())) {
                wallTouched = true;
                active = false;
                break;
            }
        }
    }


    public boolean isWallTouched() {
        return wallTouched;
    }


    public CharacterEntity getCharacter() {
        return character;
    }


    public FinnishEntity getFinnish() {
        return finnish;
    }


    public String getLevelName() {
        return levelName;
    }

    public Textures.TextureName getBackground() {
        return background;
    }

    public boolean isGoalReached() {
        return goalReached;
    }

    public void calcGoalReached() {
        if (character.getRectangle().intersects(finnish.getRectangle())) {
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
