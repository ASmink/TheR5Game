package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomCursors.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game implements Screen {

    private long updateCount = 0;

    private int charYPos = 200;
    private int charXPos = 200;

    private JPanel panel;

    private Polygon topWall;
    private Polygon bottomWall;
    private Rectangle character;
    private Rectangle finnish;


    int[] xPointsTop = {0, 0, 130, 130, 445, 445, 760, 760, 1280, 1280};
    int[] yPointsTop = {0, 340, 340, 250, 250, 560, 560, 120, 120, 0};

    int[] xPointsBotttom = {0, 0, 190, 190, 385, 385, 820, 820, 1280, 1280};
    int[] yPointsBotttom = {720, 400, 400, 310, 310, 620, 620, 180, 180, 720};

    int characterXPos = 0;
    int characterYPos = 345;

    private boolean boom = false;
    private boolean yeay = false;

    public Game(JPanel panel) {
        this.panel = panel;
        panel.setCursor(BLANK_CURSOR);
        topWall = new Polygon(xPointsTop, yPointsTop, xPointsTop.length);
        bottomWall = new Polygon(xPointsBotttom, yPointsBotttom, xPointsBotttom.length);
        character = new Rectangle(characterXPos, characterYPos, 40, 40);
        finnish = new Rectangle(1220, 120, 50, 60);
        //System.out.println(character.getX);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(LEVEL_ONE_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        if (updateCount < 90) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE") / 2);
            graphics2D.drawString("LEVEL ONE", text_start_point, 350);
        } else if (boom) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("BOOOM") / 2);
            graphics2D.drawString("BOOOM", text_start_point, 350);
        } else if (yeay) {
            graphics2D.setColor(LEVEL_TITLE_FOREGROUND_COLOR);
            graphics2D.setFont(LEVEL_TITLE_FONT);
            int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth("LEVEL ONE COMPLETE") / 2);
            graphics2D.drawString("LEVEL ONE COMPLETE", text_start_point, 350);
        } else {

            graphics2D.setColor(Color.cyan);
            if (topWall != null) {
                graphics2D.fill(topWall);
            }

            if (bottomWall != null) {
                graphics2D.fill(bottomWall);
            }
            graphics2D.setColor(LEVEL_ONE_CHAR_COLOR);
            if (character != null) {
                character.x = (int) Mouse.mousePos.getX() - 20;
                character.y = (int) Mouse.mousePos.getY() - 20;
                graphics2D.fill(character);
            }

            graphics2D.setColor(Color.magenta);
            if (finnish != null) {
                graphics2D.fill(finnish);
            }
        }
    }

    @Override
    public void update() {
        if (updateCount < Long.MAX_VALUE) {
            updateCount++;
        }

        if (updateCount > 300 && (topWall.intersects(character) || bottomWall.intersects(character))) {
            boom = true;
        }

        if (character.contains(Mouse.mousePos) && Mouse.mouseButtons[MouseEvent.BUTTON1]) {
            character.x = (int) Mouse.mousePos.getX();
            character.y = (int) Mouse.mousePos.getY();
        }

        if (character.intersects(finnish)) {
            yeay = true;
        }

        if (Keyboard.keys[KeyEvent.VK_W]) {
            if (charYPos > 0) {
                charYPos -= 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_S]) {
            if (charYPos < panel.getHeight()) {
                charYPos += 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_A]) {
            if (charXPos > 0) {
                charXPos -= 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_D]) {
            if (charXPos < panel.getWidth()) {
                charXPos += 2;
            }
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(panel);
        }
    }
}
