package za.co.extinctgaming.drawinggraphics.levels;

import za.co.extinctgaming.drawinggraphics.levels.entities.CharacterEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.FinnishEntity;
import za.co.extinctgaming.drawinggraphics.levels.entities.WallEntity;
import za.co.extinctgaming.drawinggraphics.resources.Textures;

import java.awt.*;
import java.io.Serializable;

public class Level_2 extends Level implements Serializable {
    public Level_2() {
        levelName = "LEVEL TWO";
        background = Textures.TextureName.BLUE_BRICKS;
        walls = new WallEntity[2];
        walls[0] = new WallEntity(new Polygon(
                new int[]{0, 0, 130, 130, 445, 445, 760, 760, 1280, 1280},
                new int[]{0, 340, 340, 250, 250, 560, 560, 120, 120, 0},
                10), Textures.TextureName.RED_BRICKS);

        walls[1] = new WallEntity(new Polygon(
                new int[]{0, 0, 190, 190, 385, 385, 820, 820, 1280, 1280},
                new int[]{720, 400, 400, 310, 310, 620, 620, 180, 180, 720},
                10), Textures.TextureName.RED_BRICKS);

        character = new CharacterEntity(new Rectangle(10, 350, 40, 40), Textures.TextureName.BROWN_LEOPARD);
        finnish = new FinnishEntity(new Rectangle(1225, 130, 40, 40), Textures.TextureName.GRAY_LEOPARD);
    }
}
