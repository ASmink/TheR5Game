package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.core.GameState;
import za.co.extinctgaming.drawinggraphics.entities.Wall;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomCursors.BLANK_CURSOR;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.LEVEL_TIMER_FONT;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.LEVEL_TITLE_FONT;

public class Game implements Screen {

    private long updateCount = 0;

    private JPanel panel;

    public Game(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(LEVEL_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (updateCount < 90) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE") / 2);
            graphics2D.drawString(GameState.getInstance().getCurrentLevel().getLevelName(), text_start_point, 350);
        } else {

            graphics2D.setColor(WALL_COLOR);
            for (Wall wall : GameState.getInstance().getCurrentLevel().getWalls()) {
                graphics2D.fill(wall.getPolygon());
            }

            graphics2D.setColor(CHAR_COLOR);
            graphics2D.fill(GameState.getInstance().getCurrentLevel().getCharacter());

            graphics2D.setColor(FINNISH_COLOR);
            graphics2D.fill(GameState.getInstance().getCurrentLevel().getGoal());

            if (GameState.getInstance().getCurrentLevel().isWallTouched() || GameState.getInstance().getCurrentLevel().isOutOfBounds()) {
                graphics2D.setColor(FAILED_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_FAILED_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String failedMessage = GameState.getInstance().getCurrentLevel().getLevelName() + " FAILED";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(failedMessage) / 2);
                graphics2D.drawString(failedMessage, text_start_point, 300);

                graphics2D.setFont(LEVEL_TITLE_FONT);
                String spaceMessage = "PRESS SPACE TO RETRY";
                text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(spaceMessage) / 2);
                graphics2D.drawString(spaceMessage, text_start_point, 400);
            }

            if (GameState.getInstance().getCurrentLevel().isGoalReached()) {
                graphics2D.setColor(COMPLETED_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_COMPLETED_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String endMessage = GameState.getInstance().getCurrentLevel().getLevelName() + " COMPLETE";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(endMessage) / 2);
                graphics2D.drawString(endMessage, text_start_point, 300);

                graphics2D.setFont(LEVEL_TITLE_FONT);
                String spaceMessage = "PRESS SPACE TO CONTINUE";
                text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(spaceMessage) / 2);
                graphics2D.drawString(spaceMessage, text_start_point, 400);
            }

            graphics2D.setColor(LEVEL_TIMER_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TIMER_FONT);
            String formattedDuration = String.format("%02d", (GameState.getInstance().getCurrentLevel().getDuration() / 1000) / 60) + ":" + String.format("%02d", (GameState.getInstance().getCurrentLevel().getDuration() / 1000) % 60);
            graphics2D.drawString(formattedDuration, panel.getWidth() - graphics2D.getFontMetrics().stringWidth(formattedDuration) - 20, 30);
        }
    }

    @Override
    public void update() {
        if (updateCount < Long.MAX_VALUE) {
            updateCount++;
        }

        GameState.getInstance().getCurrentLevel().calcWallTouch();
        GameState.getInstance().getCurrentLevel().calcGoalReached();
        GameState.getInstance().getCurrentLevel().calcDuration();

        if (GameState.getInstance().getCurrentLevel().isGoalReached()) {
            GameState.getInstance().saveScore();
        }

        if (GameState.getInstance().getCurrentLevel().getCharacter().contains(Mouse.mousePos.getX(), Mouse.mousePos.getY()) && Mouse.mouseButtons[MouseEvent.BUTTON1]) {
            GameState.getInstance().getCurrentLevel().setActive(true);
        }

        if (GameState.getInstance().getCurrentLevel().isActive()) {
            panel.setCursor(BLANK_CURSOR);
        } else {
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        if (GameState.getInstance().getCurrentLevel().isActive()) {
            GameState.getInstance().getCurrentLevel().getCharacter().x = (int) Mouse.mousePos.getX() - 20;
            GameState.getInstance().getCurrentLevel().getCharacter().y = (int) Mouse.mousePos.getY() - 20;
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(panel);
        }

        if (Keyboard.keys[KeyEvent.VK_SPACE]) {
            Keyboard.keys[KeyEvent.VK_SPACE] = false;
            if (GameState.getInstance().getCurrentLevel().isWallTouched()) {
                GameState.getInstance().reloadLevel();
                updateCount = 0;
            } else if (GameState.getInstance().getCurrentLevel().isGoalReached()) {
                GameState.getInstance().getNextLevel();
                updateCount = 0;
            }
        }

        if (!panel.getBounds().contains(GameState.getInstance().getCurrentLevel().getCharacter())) {
            GameState.getInstance().getCurrentLevel().setOutOfBounds(true);
        }
    }
}
