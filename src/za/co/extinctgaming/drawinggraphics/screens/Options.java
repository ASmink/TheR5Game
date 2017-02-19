package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Options extends Screen {

    Options(GameState state, JPanel panel) {
        super(state, panel);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(ERROR_FOREGROUND_COLOR);
        graphics2D.setFont(ERROR_FONT);
        graphics2D.drawString("OPTIONS GOES HERE", 100, 100);
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(state, panel);
        }
    }
}
