package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomCursors.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.entities.Wall;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;
import za.co.extinctgaming.drawinggraphics.levels.Level;
import za.co.extinctgaming.drawinggraphics.levels.LevelOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Game implements Screen {

    private long updateCount = 0;

    private JPanel panel;
    private Level level;
    private long levelStartTimestamp;
    private long levelDuration;

    public Game(JPanel panel, Level level) {
        this.panel = panel;
        this.level = level;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(LEVEL_ONE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (updateCount < 90) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE") / 2);
            graphics2D.drawString(level.getLevelName(), text_start_point, 350);
        } else {
            graphics2D.setColor(Color.cyan);

            for (Wall wall : level.getWalls()) {
                graphics2D.fill(wall.getPolygon());
            }

            graphics2D.setColor(LEVEL_ONE_CHAR_COLOR);
            graphics2D.fill(level.getCharacter());

            graphics2D.setColor(Color.magenta);
            graphics2D.fill(level.getGoal());

            if (level.isWallTouched()) {
                graphics2D.setColor(LEVEL_ONE_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String failedMessage = level.getLevelName() + " FAILED";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(failedMessage) / 2);
                graphics2D.drawString(failedMessage, text_start_point, 350);
            }

            if (level.isGoalReached()) {
                graphics2D.setColor(LEVEL_ONE_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String endMessage = level.getLevelName() + " COMPLETE";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(endMessage) / 2);
                graphics2D.drawString(endMessage, text_start_point, 350);
            }

            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(DEBUG_FONT);
            String formattedDuration = String.format("%02d", (levelDuration / 1000) / 60) + ":" + String.format("%02d", (levelDuration / 1000) % 60);
            graphics2D.drawString(formattedDuration, 100, 20);
        }
    }

    @Override
    public void update() {
        if (updateCount < Long.MAX_VALUE) {
            updateCount++;
        }

        if (level.getCharacter().contains(Mouse.mousePos.getX(), Mouse.mousePos.getY()) && Mouse.mouseButtons[MouseEvent.BUTTON1]) {
            levelStartTimestamp = System.currentTimeMillis();
            level.setActive(true);
        }

        if (level.isActive()) {
            panel.setCursor(BLANK_CURSOR);
            levelDuration = System.currentTimeMillis() - levelStartTimestamp;
        } else {
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        if (level.isActive()) {
            level.getCharacter().x = (int) Mouse.mousePos.getX() - 20;
            level.getCharacter().y = (int) Mouse.mousePos.getY() - 20;
        }

        level.calcWallTouch();
        level.calcGoalReached();

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(panel);
        }

        if (Keyboard.keys[KeyEvent.VK_SPACE]) {
            Keyboard.keys[KeyEvent.VK_SPACE] = false;

            try {
                Main.activeScreen = new Game(panel, level.getClass().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
