package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.entities.Wall;

import java.awt.*;
import java.io.Serializable;

public class Level_1 extends Level implements Serializable {
    public Level_1() {
        levelName = "LEVEL ONE";
        walls = new Wall[2];

        walls[0] = new Wall(new Polygon(
                new int[]{0, 0, 1280, 1280},
                new int[]{0, 330, 330, 0},
                4), null);

        walls[1] = new Wall(new Polygon(
                new int[]{0, 0, 1280, 1280},
                new int[]{390, 720, 720, 390},
                4), null);

        character = new Rectangle(10, 340, 40, 40);
        goal = new Rectangle(1225, 340, 40, 40);
    }
}
