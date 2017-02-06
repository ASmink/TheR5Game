package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomCursors.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelOne implements Screen {

    private long updateCount = 0;

    private int charYPos = 200;
    private int charXPos = 200;

    private JPanel panel;

    public LevelOne(JPanel panel) {
        this.panel = panel;
        panel.setCursor(BLANK_CURSOR);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(LEVEL_ONE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (updateCount < 90) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE") / 2);
            graphics2D.drawString("LEVEL ONE", text_start_point, 350);
        } else {
            graphics2D.setColor(LEVEL_ONE_CHAR_COLOR);

            int[] xPoints = {10, 10, 50, 100, 100};
            int[] yPoints = {10, 100, 150, 100, 10};
            graphics2D.fillPolygon(new Polygon(xPoints, yPoints, xPoints.length));

            graphics2D.fillRect((int) Mouse.mousePos.getX() - 50, (int) Mouse.mousePos.getY() - 50, 100, 100);
        }
    }

    @Override
    public void update() {
        if (updateCount < Long.MAX_VALUE) {
            updateCount++;
        }

        if (Keyboard.keys[KeyEvent.VK_W]) {
            if (charYPos > 0) {
                charYPos -= 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_S]) {
            if (charYPos < panel.getHeight()) {
                charYPos += 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_A]) {
            if (charXPos > 0) {
                charXPos -= 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_D]) {
            if (charXPos < panel.getWidth()) {
                charXPos += 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(panel);
        }
    }
}
