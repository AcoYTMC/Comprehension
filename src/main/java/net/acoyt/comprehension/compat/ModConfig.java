package net.acoyt.comprehension.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    // A category in Midnight config
    private static final String client = "client";

    @Entry(category = client) // Adds to the MidnightConfig screen for your mod, has many parameters
    public static boolean enabledHidden = true; // A boolean value
}
