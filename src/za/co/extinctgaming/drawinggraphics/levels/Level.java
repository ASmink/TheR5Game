package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.entities.Wall;

import java.awt.*;

public interface Level {
    public Wall[] getWalls();

    public boolean isWallTouched();

    public Rectangle getCharacter();

    public Rectangle getGoal();

    public String getLevelName();

    public boolean isGoalReached();

    public boolean isActive();

    public void setActive(boolean active);

    public void calcWallTouch();

    public void calcGoalReached();
}
