package net.acoyt.comprehension.init;

import net.acoyt.comprehension.util.CompUtils;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ComponentInit {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    // Method 1
    private static <T extends ComponentType<?>> T createComponent(String name, T componentType) {
        COMPONENTS.put(componentType, CompUtils.id(name));
        return componentType;
    }

    static void init() {
        COMPONENTS.keySet().forEach((component) -> {
            Registry.register(Registries.DATA_COMPONENT_TYPE, COMPONENTS.get(component), component);
        });
    }
}
