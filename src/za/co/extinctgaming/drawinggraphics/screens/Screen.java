package za.co.extinctgaming.drawinggraphics.screens;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andre on 2017/02/05.
 */
public interface Screen {
    void draw(JPanel panel, Graphics2D graphics2D);

    void update();
}
