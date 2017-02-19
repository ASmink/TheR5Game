package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class About extends Screen {

    About(GameState state, JPanel panel) {
        super(state, panel);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(ABOUT_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(ABOUT_TITLE_FOREGROUND_COLOR);
        graphics2D.setFont(ABOUT_TITLE_FONT);
        int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("ABOUT") / 2);
        graphics2D.drawString("ABOUT", text_start_point, 150);

        graphics2D.setColor(ABOUT_KEY_FOREGROUND_COLOR);
        graphics2D.setFont(ABOUT_KEY_FONT);

        graphics2D.drawString("Version: ", 200, 280);
        graphics2D.drawString("Designed By:", 200, 350);
        graphics2D.drawString("Developed By:", 200, 400);
        graphics2D.drawString("Produced By:", 200, 450);
        graphics2D.drawString("Published By:", 200, 500);

        graphics2D.setColor(ABOUT_VALUE_FOREGROUND_COLOR);
        graphics2D.setFont(ABOUT_VALUE_FONT);

        String formattedVersion = Main.GAME_VERSION_MAJOR + "." + Main.GAME_VERSION_MINOR + "." + Main.GAME_VERSION_BABY;
        graphics2D.drawString(formattedVersion, panel.getWidth() - graphics2D.getFontMetrics().stringWidth(formattedVersion) - 200, 280);
        graphics2D.drawString("André Smink", panel.getWidth() - graphics2D.getFontMetrics().stringWidth("André Smink") - 200, 350);
        graphics2D.drawString("André Smink", panel.getWidth() - graphics2D.getFontMetrics().stringWidth("André Smink") - 200, 400);
        graphics2D.drawString("André Smink", panel.getWidth() - graphics2D.getFontMetrics().stringWidth("André Smink") - 200, 450);
        graphics2D.drawString("André Smink", panel.getWidth() - graphics2D.getFontMetrics().stringWidth("André Smink") - 200, 500);
    }

    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(state, panel);
        }
    }
}
