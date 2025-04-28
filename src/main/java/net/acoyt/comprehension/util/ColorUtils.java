package net.acoyt.comprehension.util;

import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;

public class ColorUtils {
    // Takes a text and returns the same text but with the given int color.
    public static Text coloredText(Text text, int color) {
        Style style = text.getStyle().withColor(color);
        List<Text> styled = text.getWithStyle(style);
        if (styled.size() > 0) {
            return styled.get(0);
        }
        return text;
    }

    // Coverts a String hex to an int hex
    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", ""); // Remove # if present
        return Integer.parseInt(hexString, 16); // Convert to an int
    }
}
