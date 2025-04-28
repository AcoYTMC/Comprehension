package net.acoyt.comprehension.init;

import net.acoyt.comprehension.util.CompUtils;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ParticleInit {
    Map<ParticleType<?>, Identifier> PARTICLE_TYPES = new LinkedHashMap<>();

    // Method 1
    static SimpleParticleType create(String name, ParticleFactoryRegistry.PendingParticleFactory<SimpleParticleType> constructor) {
        SimpleParticleType particle = FabricParticleTypes.simple(true);
        PARTICLE_TYPES.put(particle, CompUtils.id(name));
        ParticleFactoryRegistry.getInstance().register(particle, constructor);
        return particle;
    }

    static void init() {
        PARTICLE_TYPES.keySet().forEach((particleType) -> {
            Registry.register(Registries.PARTICLE_TYPE, PARTICLE_TYPES.get(particleType), particleType);
        });
    }
}
