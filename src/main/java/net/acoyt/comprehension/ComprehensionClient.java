package net.acoyt.comprehension;

import net.acoyt.comprehension.init.ModBlocks;
import net.acoyt.comprehension.init.ModItems;
import net.acoyt.comprehension.init.ModParticles;
import net.fabricmc.api.ClientModInitializer;

public class ComprehensionClient implements ClientModInitializer {
    public void onInitializeClient() {
        // Client Initialization for classes
        ModBlocks.clientInit();
        ModItems.clientInit();
        ModParticles.clientInit();
    }
}
