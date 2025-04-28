package net.acoyt.comprehension.init;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableInit {
    private static final Identifier SOUL_SAND_ID = Identifier.ofVanilla("blocks/soul_sand");

    static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (SOUL_SAND_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 1)) // 1, 1 Ratio for drops
                        .conditionally(RandomChanceLootCondition.builder(0.10f)) // 10% chance to drop
                        .with(ItemEntry.builder(ItemInit.SOUL_SNACK).build()); // Drops Soul Snack

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
