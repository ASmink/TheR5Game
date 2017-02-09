package za.co.extinctgaming.drawinggraphics.screens;

import static za.co.extinctgaming.drawinggraphics.styling.CustomColors.*;
import static za.co.extinctgaming.drawinggraphics.styling.CustomFonts.*;

import za.co.extinctgaming.drawinggraphics.Main;
import za.co.extinctgaming.drawinggraphics.input.Keyboard;
import za.co.extinctgaming.drawinggraphics.input.Mouse;
import za.co.extinctgaming.drawinggraphics.levels.LevelOne;
import za.co.extinctgaming.drawinggraphics.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

public class MainMenu implements Screen {
    private MenuItem[] menuItems = new MenuItem[6];

    private JPanel panel;

    public MainMenu(JPanel panel) {
        this.panel = panel;
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        menuItems[0] = new MenuItem(1, "New Game", true);
        menuItems[1] = new MenuItem(2, "Resume Game", false);
        menuItems[2] = new MenuItem(6, "High Scores", false);
        menuItems[3] = new MenuItem(4, "Options", false);
        menuItems[4] = new MenuItem(5, "About", false);
        menuItems[5] = new MenuItem(3, "Quit Game", false);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(MAIN_MENU_BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        graphics2D.setColor(MAIN_MENU_TITLE_FOREGROUND_COLOR);
        graphics2D.setFont(MAIN_MENU_TITLE_FONT);
        int text_start_point = (panel.getWidth() / 2) - (graphics2D.getFontMetrics().stringWidth(Main.GAME_NAME) / 2);
        graphics2D.drawString(Main.GAME_NAME, text_start_point, 150);

        int itemYPos = 260;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isItemSelected()) {
                graphics2D.setColor(MAIN_MENU_SELECTED_ITEM_FOREGROUND_COLOR);
                graphics2D.setFont(MAIN_MENU_HOVER_ITEM_FONT);
            } else {
                graphics2D.setColor(MAIN_MENU_ITEM_FOREGROUND_COLOR);
                graphics2D.setFont(MAIN_MENU_ITEM_FONT);
            }

            FontRenderContext context = graphics2D.getFontRenderContext();
            LineMetrics ln = MAIN_MENU_HOVER_ITEM_FONT.getLineMetrics(menuItem.getItemName(), context);
            int itemWidth = graphics2D.getFontMetrics().stringWidth(menuItem.getItemName());
            int itemHeight = (int) (ln.getAscent() + ln.getDescent());

            if (menuItem.isItemSelected()) {
                graphics2D.drawImage(Main.images.getImage(Images.ImageNames.R5_COIN_FRONT_64_64), text_start_point - 70, itemYPos - (itemHeight - (int) ln.getDescent()), null);
            }
            menuItem.setArea(new Rectangle(text_start_point, (itemYPos - itemHeight) + (int) ln.getDescent(), itemWidth, itemHeight));
            graphics2D.drawString(menuItem.getItemName(), text_start_point, itemYPos);
            itemYPos += 70;
        }
    }

    @Override
    public void update() {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getArea().contains(Mouse.mousePos)) {
                for (MenuItem menuItem1 : menuItems) {
                    menuItem1.setItemSelected(false);
                }
                menuItem.setItemSelected(true);
            }
        }

        if (Mouse.mouseButtons[MouseEvent.BUTTON1]) {
            MenuItem item = getItemClickedOn();
            if (item != null) {
                executeAction(item.getId());
            }
        }

        if (Keyboard.keys[KeyEvent.VK_F4]) {
            Keyboard.keys[KeyEvent.VK_F4] = false;
            Main.activeScreen = new WallDebug(panel);
        }

        if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
            Keyboard.keys[KeyEvent.VK_ESCAPE] = false;
            Main.running = false;
        }

        if (Keyboard.keys[KeyEvent.VK_UP]) {
            Keyboard.keys[KeyEvent.VK_UP] = false;
            changeSelectedItem(false);
        }

        if (Keyboard.keys[KeyEvent.VK_DOWN]) {
            Keyboard.keys[KeyEvent.VK_DOWN] = false;
            changeSelectedItem(true);
        }

        if (Keyboard.keys[KeyEvent.VK_ENTER]) {
            Keyboard.keys[KeyEvent.VK_ENTER] = false;
            executeAction(getCurrentSelectedItem().getId());
        }
    }

    private void executeAction(int itemID) {
        switch (itemID) {
            case 1:
                Screen game = new Game(panel);
                Main.activeScreen = game;
                break;
            case 2:
                break;
            case 3:
                Main.running = false;
                break;
            case 4:
                Main.activeScreen = new Options(panel);
                break;
            case 5:
                Main.activeScreen = new About(panel);
                break;
            case 6:
                break;
        }
    }

    private MenuItem getCurrentSelectedItem() {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isItemSelected()) {
                return menuItem;
            }
        }

        return menuItems[0];
    }

    private MenuItem getItemClickedOn() {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getArea().contains(Mouse.mousePos)) {
                return menuItem;
            }
        }
        return null;
    }

    private void changeSelectedItem(boolean moveDown) {
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i].isItemSelected()) {
                if (moveDown && i != menuItems.length - 1) {
                    menuItems[i].setItemSelected(false);
                    menuItems[i + 1].setItemSelected(true);
                } else if (!moveDown && i != 0) {
                    menuItems[i].setItemSelected(false);
                    menuItems[i - 1].setItemSelected(true);
                }
                break;
            }
        }
    }

    private class MenuItem {
        private int id;
        private String itemName;
        private boolean itemSelected;
        private Rectangle area;

        private MenuItem(int id, String itemName, boolean itemSelected) {
            this.id = id;
            this.itemName = itemName;
            this.itemSelected = itemSelected;
            this.area = new Rectangle(0, 0, 0, 0);
        }

        private int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        private boolean isItemSelected() {
            return itemSelected;
        }

        private void setItemSelected(boolean itemSelected) {
            this.itemSelected = itemSelected;
        }

        public Rectangle getArea() {
            return area;
        }

        public void setArea(Rectangle area) {
            this.area = area;
        }
    }
}
