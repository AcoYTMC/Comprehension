package net.acoyt.comprehension.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class CompConfig extends MidnightConfig {
    // A category in Midnight config
    public static final String colors = "colors";

    @Entry(category = colors, isColor = true)
    public static String modNameColor = "#363255"; // The mod's modmenu name color
}
