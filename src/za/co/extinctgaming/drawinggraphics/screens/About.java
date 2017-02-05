package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.styling.CustomColors;
import za.co.extinctgaming.drawinggraphics.styling.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class About {
    Rectangle backButton = new Rectangle(0, 0, 0, 0);

    public void draw(JPanel panel, Graphics2D graphics2D) {
        graphics2D.setColor(CustomColors.ABOUT_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(CustomColors.ABOUT_TITLE_FOREGROUND_COLOR);
        graphics2D.setFont(CustomFonts.ABOUT_TITLE_FONT);
        int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("ABOUT") / 2);
        graphics2D.drawString("ABOUT", text_start_point, 150);

        graphics2D.setColor(CustomColors.ABOUT_KEY_FOREGROUND_COLOR);
        graphics2D.setFont(CustomFonts.ABOUT_KEY_FONT);

        graphics2D.drawString("Version: ", 200, 280);
        graphics2D.drawString("Designed By:", 200, 350);
        graphics2D.drawString("Developed By:", 200, 400);
        graphics2D.drawString("Produced By:", 200, 450);
        graphics2D.drawString("Published By:", 200, 500);

        graphics2D.setColor(CustomColors.ABOUT_VALUE_FOREGROUND_COLOR);
        graphics2D.setFont(CustomFonts.ABOUT_VALUE_FONT);

        String formattedVersion = Main.GAME_VERSION_MAJOR + "." + Main.GAME_VERSION_MINOR + "." + Main.GAME_VERSION_BABY;
        graphics2D.drawString(formattedVersion, 1080 - graphics2D.getFontMetrics().stringWidth(formattedVersion), 280);
        graphics2D.drawString("André Smink", 1080 - graphics2D.getFontMetrics().stringWidth("André Smink"), 350);
        graphics2D.drawString("André Smink", 1080 - graphics2D.getFontMetrics().stringWidth("André Smink"), 400);
        graphics2D.drawString("André Smink", 1080 - graphics2D.getFontMetrics().stringWidth("André Smink"), 450);
        graphics2D.drawString("André Smink", 1080 - graphics2D.getFontMetrics().stringWidth("André Smink"), 500);
    }

    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = Main.Screen.MAIN_MENU;
        }
    }
}
