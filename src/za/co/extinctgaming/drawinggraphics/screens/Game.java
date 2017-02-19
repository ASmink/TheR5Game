package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.GameState;
import za.co.extinctgaming.drawinggraphics.levels.entities.WallEntity;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;
import za.co.extinctgaming.drawinggraphics.resources.Textures;
import za.co.extinctgaming.drawinggraphics.utils.SerializeObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomCursors.BLANK_CURSOR;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.LEVEL_TIMER_FONT;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.LEVEL_TITLE_FONT;

public class Game extends Screen {

    private long updateCount = 0;

    Game(GameState state, JPanel panel) {
        super(state, panel);
    }

    Game(GameState state, JPanel panel, int level) {
        super(state, panel);
        state.setCurrentLevel(level);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (updateCount < 60) {
            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL SPLASH SCREEN                                                                                  |
            // +------------------------------------------------------------------------------------------------------+
            graphics2D.setColor(LEVEL_BACKGROUND_COLOR);
            graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(state.getCurrentLevel().getLevelName()) / 2);
            graphics2D.drawString(state.getCurrentLevel().getLevelName(), text_start_point, 350);
        } else {
            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL PATH BACKGROUND                                                                                |
            // +------------------------------------------------------------------------------------------------------+
            Textures.Texture backgroundPaint = Textures.getInstance().getTextures().get(state.getCurrentLevel().getBackground());
            graphics2D.setPaint(new TexturePaint(backgroundPaint.getImage(), backgroundPaint.getSize()));
            graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
            graphics2D.setPaint(null);

            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL WALLS                                                                                          |
            // +------------------------------------------------------------------------------------------------------+
            for (WallEntity wall : state.getCurrentLevel().getWalls()) {
                Textures.Texture wallPaint = Textures.getInstance().getTextures().get(wall.getTexture());
                graphics2D.setPaint(new TexturePaint(wallPaint.getImage(), wallPaint.getSize()));
                graphics2D.fill(wall.getPolygon());
                graphics2D.setPaint(null);
            }

            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL CHARACTER                                                                                      |
            // +------------------------------------------------------------------------------------------------------+
            Textures.Texture characterPaint = Textures.getInstance().getTextures().get(state.getCurrentLevel().getCharacter().getTexture());
            graphics2D.setPaint(new TexturePaint(characterPaint.getImage(), characterPaint.getSize()));
            graphics2D.fill(state.getCurrentLevel().getCharacter().getRectangle());
            graphics2D.setPaint(null);

            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL FINNISH                                                                                        |
            // +------------------------------------------------------------------------------------------------------+
            Textures.Texture finnishPaint = Textures.getInstance().getTextures().get(state.getCurrentLevel().getFinnish().getTexture());
            graphics2D.setPaint(new TexturePaint(finnishPaint.getImage(), finnishPaint.getSize()));
            graphics2D.fill(state.getCurrentLevel().getFinnish().getRectangle());
            graphics2D.setPaint(null);

            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL FAILED                                                                                         |
            // +------------------------------------------------------------------------------------------------------+
            if (state.getCurrentLevel().isWallTouched() || state.getCurrentLevel().isOutOfBounds()) {
                graphics2D.setColor(FAILED_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_FAILED_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String failedMessage = state.getCurrentLevel().getLevelName() + " FAILED";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(failedMessage) / 2);
                graphics2D.drawString(failedMessage, text_start_point, 300);

                graphics2D.setFont(LEVEL_TITLE_FONT);
                String spaceMessage = "PRESS SPACE TO RETRY";
                text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(spaceMessage) / 2);
                graphics2D.drawString(spaceMessage, text_start_point, 400);
            }

            // +------------------------------------------------------------------------------------------------------+
            // | LEVEL COMPLETED                                                                                      |
            // +------------------------------------------------------------------------------------------------------+
            if (state.getCurrentLevel().isGoalReached()) {
                graphics2D.setColor(COMPLETED_BACKGROUND_COLOR);
                graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                graphics2D.setColor(LEVEL_COMPLETED_FOREGROUND_COLOR);
                graphics2D.setFont(LEVEL_TITLE_FONT);
                String endMessage = state.getCurrentLevel().getLevelName() + " COMPLETE";
                int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(endMessage) / 2);
                graphics2D.drawString(endMessage, text_start_point, 300);

                graphics2D.setFont(LEVEL_TITLE_FONT);
                String spaceMessage = "PRESS SPACE TO CONTINUE";
                text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(spaceMessage) / 2);
                graphics2D.drawString(spaceMessage, text_start_point, 400);
            }

            // +------------------------------------------------------------------------------------------------------+
            // | TOP STATUS BAR                                                                                       |
            // +------------------------------------------------------------------------------------------------------+
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillRect(0, 0, panel.getWidth(), 20);

            graphics2D.setColor(LEVEL_TIMER_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TIMER_FONT);
            String formattedDuration = String.format("%02d", (state.getCurrentLevel().getDuration() / 1000) / 60) + ":" + String.format("%02d", (state.getCurrentLevel().getDuration() / 1000) % 60);
            graphics2D.drawString(formattedDuration, panel.getWidth() - graphics2D.getFontMetrics().stringWidth(formattedDuration) - 10, 17);
            graphics2D.drawString(state.getCurrentLevel().getLevelName(), 10, 17);
        }
    }

    @Override
    public void update() {
        if (updateCount < Long.MAX_VALUE) {
            updateCount++;
        }

        state.getCurrentLevel().calcWallTouch();
        state.getCurrentLevel().calcGoalReached();
        state.getCurrentLevel().calcDuration();

        if (state.getCurrentLevel().isGoalReached()) {
            state.saveScore();
            SerializeObject.serializeToFile(state);
        }

        if (state.getCurrentLevel().getCharacter().getRectangle().contains(Mouse.mousePos.getX(), Mouse.mousePos.getY()) && Mouse.mouseButtons[MouseEvent.BUTTON1]) {
            state.getCurrentLevel().setActive();
        }

        if (state.getCurrentLevel().isActive()) {
            panel.setCursor(BLANK_CURSOR);
        } else {
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        if (state.getCurrentLevel().isActive()) {
            state.getCurrentLevel().getCharacter().getRectangle().x = (int) Mouse.mousePos.getX() - 20;
            state.getCurrentLevel().getCharacter().getRectangle().y = (int) Mouse.mousePos.getY() - 20;
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(state, panel);
        }

        if (Keyboard.keys[KeyEvent.VK_SPACE]) {
            Keyboard.keys[KeyEvent.VK_SPACE] = false;
            if (state.getCurrentLevel().isWallTouched()) {
                state.reloadLevel();
                updateCount = 0;
            } else if (state.getCurrentLevel().isGoalReached()) {
                state.getNextLevel();
                updateCount = 0;
            }
        }

        if (!panel.getBounds().contains(state.getCurrentLevel().getCharacter().getRectangle())) {
            state.getCurrentLevel().setOutOfBounds();
        }
    }
}
