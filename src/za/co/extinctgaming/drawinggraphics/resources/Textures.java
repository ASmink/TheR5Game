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
        // - ARCHITECTURE
        // -- BRICKS
        // --- COLORED BRICKS
        // ---- RUSTIC
        BRICKS_BLUE,
        BRICKS_RED,
        // - NATURE ELEMENTS
        // -- VEGETATION
        // --- FLOWERY FIELDS
        GRASS_FLOWERED,
        // - MATERIALS
        // -- FUR ANIMAL
        LEOPARD_SKIN_GRAY,
        LEOPARD_SKIN_BROWN,
        // -- FABRICS
        // --- TARTAN
        TARTAN_FABRIC_RED_GREEN
    }

    private Textures() {
        textures.put(TextureName.ERROR, new Texture("/textures/error.png", 128, 128));
        textures.put(TextureName.BRICKS_BLUE, new Texture("/textures/7_texture colored bricks rustic-seamless.jpg", 128, 158));
        textures.put(TextureName.BRICKS_RED, new Texture("/textures/18_texture colored bricks rustic-seamless.jpg", 128, 158));
        textures.put(TextureName.GRASS_FLOWERED, new Texture("/textures/1_flowery meadow texture-seamless.jpg", 128, 128));
        textures.put(TextureName.LEOPARD_SKIN_GRAY, new Texture("/textures/11_gray leopard faux fake fur animal texture-seamless.jpg", 128, 128));
        textures.put(TextureName.LEOPARD_SKIN_BROWN, new Texture("/textures/2_ghepardo faux fake fur animal texture-seamless.jpg", 128, 128));
        textures.put(TextureName.TARTAN_FABRIC_RED_GREEN, new Texture("/textures/12_tartan fabric texture-seamless.jpg", 128, 128));
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
