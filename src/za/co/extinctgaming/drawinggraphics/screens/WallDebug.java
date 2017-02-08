package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.entities.Wall;

import javax.swing.*;
import java.awt.*;

public class WallDebug implements Screen {
    private JPanel panel;

    private Wall wall;
    private int[] xCoor = {0, 0, 190, 190, 385, 385, 820, 820, 1279, 1279, 1280, 1280};
    private int[] yCoor = {720, 400, 400, 310, 310, 620, 620, 180, 180, 120, 120, 720};


    public WallDebug(JPanel panel) {
        this.panel = panel;
        this.wall = new Wall(new Polygon(xCoor, yCoor, xCoor.length), null);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(Color.BLUE);
        graphics2D.fill(wall.getPolygon());

        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 14));
        for (int i = 0; i < xCoor.length; i++) {
            graphics2D.drawString(xCoor[i] + ":" + yCoor[i], xCoor[i], yCoor[i]);
        }
    }

    @Override
    public void update() {

    }
}
