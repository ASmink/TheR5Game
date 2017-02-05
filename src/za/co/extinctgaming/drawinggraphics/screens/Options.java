package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.styling.CustomColors;
import za.co.extinctgaming.drawinggraphics.styling.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Options implements Screen {
    @Override
    public void draw(JPanel panel, Graphics2D graphics2D) {
        graphics2D.setColor(CustomColors.ERROR_FOREGROUND_COLOR);
        graphics2D.setFont(CustomFonts.ERROR_FONT);
        graphics2D.drawString("OPTIONS GOES HERE", 100, 100);
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu();
        }
    }
}
