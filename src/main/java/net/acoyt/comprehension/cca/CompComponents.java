package net.acoyt.comprehension.cca;

import net.acoyt.comprehension.util.CompUtils;
import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class CompComponents implements EntityComponentInitializer {
    // Allows the Components to be referenced throughout different classes
    public static final ComponentKey<TickingComponent> TICKING = ComponentRegistry.getOrCreate(CompUtils.id("ticking"), TickingComponent.class);
    public static final ComponentKey<HiddenComponent> HIDDEN = ComponentRegistry.getOrCreate(CompUtils.id("hidden"), HiddenComponent.class);

    // Registers the component, requires listing the id in the mod metadata (under custom in your fabric.mod.json)
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        // Registers the component for only players, as opposed to every entity, and will not copy when the player respawns
        registry.registerForPlayers(HIDDEN, HiddenComponent::new, RespawnCopyStrategy.NEVER_COPY);
        // Registers the component for all entities extending LivingEntity
        registry.beginRegistration(LivingEntity.class, TICKING).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(TickingComponent::new);
    }
}
