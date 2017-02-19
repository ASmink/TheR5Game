package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.utils.PrettyPrint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;

public class HighScores extends Screen {

    HighScores(GameState state, JPanel panel) {
        super(state, panel);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(HIGH_SCORE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(HIGH_SCORE_LEVEL_FOREGROUND_COLOR);
        graphics2D.setFont(HIGH_SCORE_LEVEL_FONT);
        int yPos = 50;
        for (int i = 0; i < state.getHighScores().length; i++) {
            graphics2D.drawString("LEVEL " + (i + 1) + ": " + PrettyPrint.prettyPrintDuration(state.getHighScores()[i]), 20, yPos);
            yPos += 50;
        }
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(state, panel);
        }
    }
}
