package net.acoyt.comprehension.util;

import net.acoyt.comprehension.Comprehension;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// A class for references and other stuff basic utilities. Only because putting Comprehension.id(<VALUE>) is so long
public class CompUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(Comprehension.MOD_ID); // How the Mod adds information to Minecraft's log file

    // An alternative to constantly putting Identifier.of(Comprehension.MOD_ID, <VALUE>)
    public static Identifier id(String path) {
        return Identifier.of(Comprehension.MOD_ID, path);
    }
}
