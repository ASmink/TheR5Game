package za.co.extinctgaming.drawinggraphics.levels.entities;

import za.co.extinctgaming.drawinggraphics.resources.Textures;

import java.awt.*;
import java.io.Serializable;

public class WallEntity implements Serializable {
    private Polygon polygon;
    private Textures.TextureName texture;

    public WallEntity(Polygon polygon, Textures.TextureName texture) {
        this.polygon = polygon;
        if (texture == null) {
            this.texture = Textures.TextureName.ERROR;
        } else {
            this.texture = texture;
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Textures.TextureName getTexture() {
        return texture;
    }
}