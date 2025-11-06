package net.acoyt.comprehension.init;

import net.acoyt.comprehension.Comprehension;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public interface ModBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    // Method 1
    Block CHEESE = createBlock("cheese", new Block(AbstractBlock.Settings.copy(Blocks.SPONGE).registryKey(keyOf("cheese"))), true);

    // Method 2

    // Method 3

    /**
     * Block Creation Method 1
     * Requires assigning a registry key via
     * .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Comprehension.id(<ITEM_IDENTIFIER>)))
     * at the end of the AbstractBlock.Settings()
     */
    private static <T extends Block> T createBlock(String name, T block, boolean createItem) {
        BLOCKS.put(block, Comprehension.id(name));
        if (createItem) {
            ModItems.ITEMS.put(new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(name)))), Comprehension.id(name));
        }

        return block;
    }

    /**
     * Block Creation Method 2
     * Doesn't require assigning a registry key
     * at the end of the AbstractBlock.Settings()
     * Creates no BlockItem
     */
    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Comprehension.id(name)), factory, settings);
    }

    /**
     * Block Creation Method 3
     * Doesn't require assigning a registry key
     * at the end of the AbstractBlock.Settings()
     * Creates a BlockItem
     */
    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        ModItems.create(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings().useBlockPrefixedTranslationKey());
        return block;
    }

    // Alternative for putting RegistryKey.of(RegistryKeys.BLOCK, Comprehension.id(<ITEM_IDENTIFIER>)) at the end of the AbstractBlock.Settings()
    static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Comprehension.id(id));
    }

    static void init() {
        BLOCKS.keySet().forEach((block) -> {
            Registry.register(Registries.BLOCK, BLOCKS.get(block), block);
        });
    }

    static void clientInit() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), new Block[]{
                //
        });
    }
}
