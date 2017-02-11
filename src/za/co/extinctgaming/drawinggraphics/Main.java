package za.co.extinctgaming.drawinggraphics;

import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;

import za.co.extinctgaming.drawinggraphics.core.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;
import za.co.extinctgaming.drawinggraphics.resources.Images;
import za.co.extinctgaming.drawinggraphics.screens.*;
import za.co.extinctgaming.drawinggraphics.styling.CustomColors;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class Main extends JPanel implements Runnable {
    public static final String GAME_NAME = "The R5 Game";
    public static final int GAME_VERSION_MAJOR = 0;
    public static final int GAME_VERSION_MINOR = 1;
    public static final int GAME_VERSION_BABY = 0;

    public static Images images;

    public static boolean debug = false;
    public static Screen activeScreen = null;
    public static boolean running;
    public static boolean paused;

    private int window_width = 1280;
    private int window_height = 720;

    private Thread thread;
    private JFrame frame;

    private long fpsNow;
    private int framesCount = 0;
    private int framesCountAvg = 0;
    private long framesTimer = 0;

    private long tickNow;
    private int ticksCount = 0;
    private int ticksCountAvg = 0;
    private long ticksTimer = 0;

    private Main() {
        running = true;
        paused = false;

        Mouse mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        loadResources();
        createFrame();
        createThread();

        activeScreen = new MainMenu(this);
    }

    private void loadResources() {
        try {
            images = new Images();
        } catch (IOException e) {
            System.err.println("Could not Load Resources: " + e.getMessage());
            System.exit(1);
        }
    }

    private void createFrame() {
        frame = new JFrame();

        Keyboard keyboard = new Keyboard();
        frame.addKeyListener(keyboard);

        frame.getContentPane().add(this);

        frame.setSize(window_width, window_height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createThread() {
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void update() {
        if (activeScreen != null) {
            activeScreen.update();
        }
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        if (activeScreen != null) {
            activeScreen.draw(graphics2D);
        }

        if (debug) {
            graphics2D.setColor(CustomColors.DEBUG_BACKGROUND_COLOR);
            graphics2D.fillRect(5, 5, 170, 69);

            graphics2D.setFont(DEBUG_FONT);
            graphics2D.setColor(DEBUG_FOREGROUND_COLOR);
            Locale.setDefault(new Locale("pl", "PL"));

            String formattedFPS = "FPS: " + String.format("%,d", framesCountAvg);
            String formattedTickRate = "TICK RATE: " + String.format("%,d", ticksCountAvg);
            String formattedMousePos = "MOUSE X-POS: " + (int) Mouse.mousePos.getX() + ", Y-POS: " + (int) Mouse.mousePos.getY();
            String formattedGameVersion = "VERSION: " + GAME_VERSION_MAJOR + "." + GAME_VERSION_MINOR + "." + GAME_VERSION_BABY;
            String formattedGameState;

            if (paused) {
                formattedGameState = "GAME STATE: PAUSED";
            } else {
                formattedGameState = "GAME STATE: RUNNING";
            }

            graphics2D.drawString(formattedFPS, 10, 20);
            graphics2D.drawString(formattedTickRate, 10, 32);
            graphics2D.drawString(formattedMousePos, 10, 44);
            graphics2D.drawString(formattedGameVersion, 10, 56);
            graphics2D.drawString(formattedGameState, 10, 68);
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double ticks = 120D;
        double ns = 1000000000 / ticks;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tickNow = System.currentTimeMillis();
                ticksCount++;
                if (tickNow - ticksTimer > 1000) {
                    ticksTimer = tickNow;
                    ticksCountAvg = ticksCount;
                    ticksCount = 0;
                }
                if (!paused) {
                    update();
                }
                delta--;
            }

            // DRAW FPS:
            fpsNow = System.currentTimeMillis();
            framesCount++;
            if (fpsNow - framesTimer > 1000) {
                framesTimer = fpsNow;
                framesCountAvg = framesCount;
                framesCount = 0;
            }

            repaint();
        }
        System.exit(0);

    }

    public static void main(String[] args) {
        new Main();
    }
}
