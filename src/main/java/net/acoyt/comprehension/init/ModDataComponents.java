package net.acoyt.comprehension.init;

import com.mojang.serialization.Codec;
import net.acoyt.comprehension.Comprehension;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface ModDataComponents {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    ComponentType<Integer> TEST_INT = create("test_int", builder -> builder.codec(Codec.INT));
    ComponentType<Unit> TEST_UNIT = createComponent("test_unit", new ComponentType.Builder<Unit>().codec(Unit.CODEC).build());

    // Method 1 (much preferred)
    static <T> ComponentType<T> create(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Comprehension.id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    // Method 2
    private static <T extends ComponentType<?>> T createComponent(String name, T componentType) {
        COMPONENTS.put(componentType, Comprehension.id(name));
        return componentType;
    }

    static void init() {
        COMPONENTS.keySet().forEach((component) -> {
            Registry.register(Registries.DATA_COMPONENT_TYPE, COMPONENTS.get(component), component);
        });
    }
}
