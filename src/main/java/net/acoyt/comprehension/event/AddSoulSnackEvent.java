package net.acoyt.comprehension.event;

import net.acoyt.comprehension.init.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class AddSoulSnackEvent implements LootTableEvents.Modify {
    private static final Identifier SOUL_SAND_ID = Identifier.ofVanilla("blocks/soul_sand");

    @Override
    public void modifyLootTable(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registry) {
        if (SOUL_SAND_ID.equals(key.getValue())) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1, 1)) // 1, 1 Ratio for drops
                    .conditionally(RandomChanceLootCondition.builder(0.10f)) // 10% chance to drop
                    .with(ItemEntry.builder(ModItems.SOUL_SNACK).build()); // Drops Soul Snack

            tableBuilder.pool(poolBuilder.build());
        }
    }
}
