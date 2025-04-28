package net.acoyt.comprehension.init;

import net.acoyt.comprehension.util.CompUtils;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface SoundInit {
    Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();

    // Method 1
    static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(CompUtils.id(name));
        SOUND_EVENTS.put(soundEvent, CompUtils.id(name));
        return soundEvent;
    }

    static void init() {
        SOUND_EVENTS.keySet().forEach((soundEvent) -> {
            Registry.register(Registries.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent);
        });
    }
}
