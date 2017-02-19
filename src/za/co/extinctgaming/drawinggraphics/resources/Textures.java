package za.co.extinctgaming.drawinggraphics.resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Textures {
    private static Textures ourInstance = new Textures();

    public static Textures getInstance() {
        return ourInstance;
    }

    private Map<TextureName, Texture> textures = new HashMap<>();

    public enum TextureName {
        ERROR,
        BLUE_BRICKS,
        RED_BRICKS,
        FLOWER_GRASS,
        GRAY_LEOPARD,
        BROWN_LEOPARD
    }

    private Textures() {
        textures.put(TextureName.ERROR, new Texture("/textures/error.png", 128, 128));
        textures.put(TextureName.BLUE_BRICKS, new Texture("/textures/7_texture colored bricks rustic-seamless.jpg", 128, 158));
        textures.put(TextureName.RED_BRICKS, new Texture("/textures/18_texture colored bricks rustic-seamless.jpg", 128, 158));
        textures.put(TextureName.FLOWER_GRASS, new Texture("/textures/1_flowery meadow texture-seamless.jpg", 128, 128));
        textures.put(TextureName.GRAY_LEOPARD, new Texture("/textures/11_gray leopard faux fake fur animal texture-seamless.jpg", 128, 128));
        textures.put(TextureName.BROWN_LEOPARD, new Texture("/textures/2_ghepardo faux fake fur animal texture-seamless.jpg", 128, 128));
    }

    public Map<TextureName, Texture> getTextures() {
        return textures;
    }

    public class Texture {
        private BufferedImage image;
        private Rectangle size;

        Texture(String path, int width, int height) {
            try {
                this.image = ImageIO.read(getClass().getResource(path));
                this.size = new Rectangle(width, height);
            } catch (IOException e) {
                this.image = null;
            }
        }

        public BufferedImage getImage() {
            return image;
        }

        public Rectangle getSize() {
            return size;
        }
    }
}
