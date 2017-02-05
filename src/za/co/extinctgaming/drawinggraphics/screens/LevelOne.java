package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.styling.CustomColors;
import za.co.extinctgaming.drawinggraphics.styling.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelOne implements Screen {

    int updateCount = 0;

    int charYPos = 0;
    int charXPos = 0;

    @Override
    public void draw(JPanel panel, Graphics2D graphics2D) {
        graphics2D.setColor(CustomColors.LEVEL_ONE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (updateCount < 90) {
            graphics2D.setColor(CustomColors.LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(CustomFonts.LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE") / 2);
            graphics2D.drawString("LEVEL ONE", text_start_point, 350);
        } else {
            graphics2D.setColor(CustomColors.LEVEL_ONE_CHAR_COLOR);
            graphics2D.fillOval(charXPos, charYPos, 50, 100);
        }
    }

    @Override
    public void update() {
        if (updateCount < Integer.MAX_VALUE) {
            updateCount++;
        }

        if (Keyboard.keys[KeyEvent.VK_W]) {
            charYPos -= 2;
        }

        if (Keyboard.keys[KeyEvent.VK_S]) {
            charYPos += 2;
        }

        if (Keyboard.keys[KeyEvent.VK_A]) {
            charXPos -= 2;
        }

        if (Keyboard.keys[KeyEvent.VK_D]) {
            charXPos += 2;
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu();
        }
    }
}
