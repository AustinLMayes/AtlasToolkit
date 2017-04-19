package net.avicus.toolkit.utils;

import org.bukkit.util.Vector;

public class XMLUtils {

    public static String generateAttribute(String name, Object value, boolean addSpace) {
        return name + "=\"" + value + "\"" + (addSpace ? " " : "");
    }

    public static String vectorToString(Vector vec, boolean allowDoubles) {
        String res = vec.getBlockX() + "," + vec.getBlockY() + "," + vec.getBlockZ();

        if (!allowDoubles)
            res = res.replace(",0", "");
        return res;
    }

    public static String vector2DToString(Vector vec, boolean allowDoubles) {
        String res = vec.getBlockX() + "," + vec.getBlockZ();

        if (!allowDoubles)
            res = res.replace(",0", "");
        return res;
    }

    public static String weVectorToString(com.sk89q.worldedit.Vector vec, boolean allowDoubles) {
        String res = vec.getX() + "," + vec.getY() + "," + vec.getZ();
        if (!allowDoubles)
            res = res.replace(",0", "");
        return res;
    }
}
