package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.GameState;

import javax.swing.*;
import java.awt.*;

public class Screen {
    GameState state;
    JPanel panel;

    Screen(GameState state, JPanel panel) {
        this.state = state;
        this.panel = panel;
    }

    public void draw(Graphics2D graphics2D) {
        // Override With Drawing Logic
    }

    public void update() {
        // Override With Update Logic
    }
}
