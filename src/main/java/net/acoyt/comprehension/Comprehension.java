package net.acoyt.comprehension;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.comprehension.compat.CompConfig;
import net.acoyt.comprehension.init.*;
import net.fabricmc.api.ModInitializer;

public class Comprehension implements ModInitializer {
	public static final String MOD_ID = "comprehension"; // The Mod's Identifier

	// How fabric initializes different aspects of the mod
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, CompConfig.class); // Initializes The Config for the mod

		// Initialization
		BlockInit.init();
		ComponentInit.init();
		ItemInit.init();
		ParticleInit.init();
		SoundInit.init();
	}
}