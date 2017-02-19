package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.levels.entities.FinnishEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.WallEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.CharacterEntity;
import za.co.extinctgaming.drawinggraphics.resources.Textures;

import java.awt.*;
import java.io.Serializable;

public class Level_1 extends Level implements Serializable {
    public Level_1() {
        levelName = "LEVEL ONE";

        background = Textures.TextureName.FLOWER_GRASS;

        walls = new WallEntity[2];

        walls[0] = new WallEntity(new Polygon(
                new int[]{0, 0, 1280, 1280},
                new int[]{0, 330, 330, 0},
                4), Textures.TextureName.BLUE_BRICKS);

        walls[1] = new WallEntity(new Polygon(
                new int[]{0, 0, 1280, 1280},
                new int[]{390, 720, 720, 390},
                4), Textures.TextureName.RED_BRICKS);

        character = new CharacterEntity(new Rectangle(10, 340, 40, 40), Textures.TextureName.GRAY_LEOPARD);
        finnish = new FinnishEntity(new Rectangle(1225, 340, 40, 40), Textures.TextureName.BROWN_LEOPARD);
    }
}
