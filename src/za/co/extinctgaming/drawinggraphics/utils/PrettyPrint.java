package za.co.extinctgaming.drawinggraphics.utils;

public class PrettyPrint {
    public static String prettyPrintDuration(long duration) {
        return String.format("%02d", (duration / 1000) / 60) + ":" + String.format("%02d", (duration / 1000) % 60);
    }
}
