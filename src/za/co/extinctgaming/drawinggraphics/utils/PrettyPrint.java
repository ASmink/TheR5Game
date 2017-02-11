package za.co.extinctgaming.drawinggraphics.utils;

/**
 * Created by andre on 2017/02/11.
 */
public class PrettyPrint {
    public static String prettyPrintDuration(long duration) {
        return String.format("%02d", (duration / 1000) / 60) + ":" + String.format("%02d", (duration / 1000) % 60);
    }
}
