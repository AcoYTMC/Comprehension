package net.acoyt.comprehension;

import net.acoyt.comprehension.init.BlockInit;
import net.acoyt.comprehension.init.ItemInit;
import net.acoyt.comprehension.util.supporter.NotASupporterException;
import net.acoyt.comprehension.util.supporter.SupporterUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.client.network.ClientPlayerEntity;

public class ComprehensionClient implements ClientModInitializer {
    public void onInitializeClient() {
        // Client Initialization for classes
        BlockInit.clientInit();
        ItemInit.clientInit();

        // Registers the supporter list and 'NotASupporterException'
        ClientEntityEvents.ENTITY_LOAD.register((entity, clientWorld) -> {
            if (entity instanceof ClientPlayerEntity player) {
                SupporterUtils.list = SupporterUtils.fetchPlayers();
                NotASupporterException.notASupporter(player);
            }
        });
    }
}
