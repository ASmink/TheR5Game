package za.co.extinctgaming.drawinggraphics.input;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.screens.Clock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public static boolean[] keys = new boolean[128];

    public Keyboard() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F3) {
            Main.debug = !Main.debug;
        }

        if (e.getKeyCode() == KeyEvent.VK_PAUSE) {
            Main.paused = !Main.paused;
        }

        try {
            keys[e.getKeyCode()] = true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("The following key code is to large for the array: " + e.getKeyChar() + " - " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            keys[e.getKeyCode()] = false;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("The following key code is to large for the array: " + e.getKeyChar() + " - " + e.getKeyCode());
        }
    }
}
