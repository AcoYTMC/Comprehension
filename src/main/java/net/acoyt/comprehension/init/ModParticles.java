package net.acoyt.comprehension.init;

import net.acoyt.comprehension.Comprehension;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModParticles {
    Map<ParticleType<?>, Identifier> PARTICLES = new LinkedHashMap<>();

    SimpleParticleType TEST = create("test", FabricParticleTypes.simple(true));

    // Method 1
    private static <T extends ParticleType<?>> T create(String name, T particle) {
        PARTICLES.put(particle, Comprehension.id(name));
        return particle;
    }

    static void init() {
        PARTICLES.keySet().forEach(particle -> {
            Registry.register(Registries.PARTICLE_TYPE, PARTICLES.get(particle), particle);
        });
    }

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(TEST, SweepAttackParticle.Factory::new);
    }
}
