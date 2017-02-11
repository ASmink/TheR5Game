package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.core.GameState;
import za.co.extinctgaming.drawinggraphics.utils.PrettyPrint;

import javax.swing.*;
import java.awt.*;

public class HighScores implements Screen {
    private JPanel panel;

    public HighScores(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(Color.BLACK);
        int yPos = 50;
        for (int i = 0; i < GameState.getInstance().getHighscores().length; i++) {
            graphics2D.drawString("LEVEL " + (i + 1) + ": " + PrettyPrint.prettyPrintDuration(GameState.getInstance().getHighscores()[i]), 20, yPos);
            yPos += 30;
        }
    }

    @Override
    public void update() {

    }
}
