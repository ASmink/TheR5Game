package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.core.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.utils.PrettyPrint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;

public class HighScores implements Screen {
    private JPanel panel;

    public HighScores(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(HIGH_SCORE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(HIGH_SCORE_LEVEL_FOREGROUND_COLOR);
        graphics2D.setFont(HIGH_SCORE_LEVEL_FONT);
        int yPos = 50;
        for (int i = 0; i < GameState.getInstance().getHighscores().length; i++) {
            graphics2D.drawString("LEVEL " + (i + 1) + ": " + PrettyPrint.prettyPrintDuration(GameState.getInstance().getHighscores()[i]), 20, yPos);
            yPos += 50;
        }
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(panel);
        }
    }
}
