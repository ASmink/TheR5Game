package za.co.extinctgaming.drawinggraphics.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    public enum ImageNames {
        R5_COIN_FRONT_128_128,
        R5_COIN_FRONT_64_64
    }

    private BufferedImage r5CoinFront_128_128;
    private BufferedImage r5CoinFront_64_64;

    public Images() throws IOException {
        r5CoinFront_128_128 = ImageIO.read(getClass().getResource("/images/r5-coin-front-128x128.png"));
        r5CoinFront_64_64 = ImageIO.read(getClass().getResource("/images/r5-coin-front-64x64.png"));
    }

    public BufferedImage getImage(ImageNames name) {
        switch (name) {
            case R5_COIN_FRONT_128_128:
                return r5CoinFront_128_128;
            case R5_COIN_FRONT_64_64:
                return r5CoinFront_64_64;
            default:
                return null;
        }
    }
}
