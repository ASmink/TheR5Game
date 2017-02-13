package za.co.extinctgaming.drawinggraphics.utils;

import za.co.extinctgaming.drawinggraphics.Main;

import java.io.*;

public class SerializeObject {

    public static void serializeToFile(Object object) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(Main.GAME_HOME_PATH + "\\" + Main.GAME_DIR_PATH + "\\" + Main.GAME_STATE_SERIALIZABLE_PATH);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object deserializeFromFile() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object readObject = null;

        try {
            fileInputStream = new FileInputStream(Main.GAME_HOME_PATH + "\\" + Main.GAME_DIR_PATH + "\\" + Main.GAME_STATE_SERIALIZABLE_PATH);
            objectInputStream = new ObjectInputStream(fileInputStream);
            readObject = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("No Game State Found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return readObject;
    }
}
