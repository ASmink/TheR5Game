package za.co.extinctgaming.drawinggraphics.levels.entities;

import za.co.extinctgaming.drawinggraphics.resources.Textures;

import java.awt.*;
import java.io.Serializable;

public class CharacterEntity implements Serializable {
    private Rectangle rectangle;
    private Textures.TextureName texture;

    public CharacterEntity(Rectangle rectangle, Textures.TextureName texture) {
        this.rectangle = rectangle;
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Textures.TextureName getTexture() {
        return texture;
    }
}
