package net.acoyt.comprehension;

import com.mojang.logging.LogUtils;
import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.comprehension.compat.ModConfig;
import net.acoyt.comprehension.init.*;
import net.acoyt.comprehension.util.supporter.SupporterUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class Comprehension implements ModInitializer {
	public static final SupporterUtils supporters = new SupporterUtils(); // The supporter list as accessible in other classes

	public static final String MOD_ID = "comprehension"; // The Mod's Identifier
	public static final Logger LOGGER = LogUtils.getLogger();

	// How fabric initializes different aspects of the mod
	public void onInitialize() {
		new Thread(supporters::fetchPlayers).start(); // Fetches player upon starting game, and at every 5 minute interval

		MidnightConfig.init(MOD_ID, ModConfig.class); // Initializes The Config for the mod

		// Initialization
		ModBlocks.init();
		ModDataComponents.init();
		ModItems.init();
		ModParticles.init();
		ModSounds.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}