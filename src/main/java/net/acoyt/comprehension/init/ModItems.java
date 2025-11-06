package net.acoyt.comprehension.init;

import net.acoyt.comprehension.Comprehension;
import net.acoyt.comprehension.item.SoulSnackItem;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public interface ModItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    Item HAMBURGER = createItem("hamburger", new Item(new Item.Settings()
            .food(new FoodComponent(11, 0.8f, false))
            .registryKey(keyOf("hamburger")))); // Method 1

    Item SOUL_SNACK = create("soul_snack", SoulSnackItem::new, new Item.Settings()
            .food(new FoodComponent(4, 3, false))); // Method 2

    Item FORK = create("fork", new Item(new Item.Settings()
            .registryKey(keyOf("fork")))); // Method 3

    /**
     * Item Creation Method 1
     * Requires assigning a registry key via
     * .registryKey(RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(<ITEM_IDENTIFIER>)))
     * at the end of the Item.Settings()
     */
    private static <T extends Item> T createItem(String name, T item) {
        ITEMS.put(item, Comprehension.id(name));
        return item;
    }

    /**
     * Item Creation Method 2 (preferred)
     * Doesn't Require assigning a registry key
     */
    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(name)), factory, settings);
    }

    /**
     * Item Creation Method 3
     * Requires assigning a registry key via
     * .registryKey(RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(<ITEM_IDENTIFIER>)))
     * at the end of the Item.Settings()
     */
    static Item create(String name, Item item) {
        return Registry.register(Registries.ITEM, Comprehension.id(name), item);
    }

    // Alternative for putting RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(<ITEM_IDENTIFIER>)) at the end of the Item.Settings()
    static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Comprehension.id(id));
    }

    static void init() {
        ITEMS.keySet().forEach((item) -> {
            Registry.register(Registries.ITEM, ITEMS.get(item), item);
        });
    }

    static void clientInit() {
        //
    }
}
