package za.co.extinctgaming.drawinggraphics.screens;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.GameState;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock extends Screen {
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public Clock(GameState state, JPanel panel) {
        super(state, panel);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(new Font("OCR A Extended", Font.BOLD, 170));
        String time = timeFormat.format(new Date());
        int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(time) / 2);
        graphics2D.drawString(time, text_start_point, 350);

        graphics2D.setFont(new Font("OCR A Extended", Font.PLAIN, 80));
        String date = dateFormat.format(new Date());
        text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(date) / 2);
        graphics2D.drawString(date, text_start_point, 450);
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.activeScreen = new MainMenu(state, panel);
        }
    }
}
