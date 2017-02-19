package za.co.extinctgaming.drawinggraphics.styling;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomCursors {
    public static Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
}
