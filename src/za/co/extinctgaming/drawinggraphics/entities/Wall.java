package za.co.extinctgaming.drawinggraphics.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by andre on 2017/02/06.
 */
public class Wall {
    private Polygon polygon;
    private BufferedImage texture;

    public Wall(Polygon polygon, BufferedImage texture) {
        this.polygon = polygon;
        this.texture = texture;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }
}
