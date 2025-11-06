package net.acoyt.comprehension.cca;

import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class CompComponents implements EntityComponentInitializer {
    /// Registers the component, requires listing the id in the mod metadata (under custom in your fabric.mod.json)
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        /// Registers the component for only players, as opposed to every entity, and will not copy when the player respawns
        registry.registerForPlayers(HiddenComponent.KEY, HiddenComponent::new, RespawnCopyStrategy.NEVER_COPY);

        /// Registers the component for all entities extending LivingEntity
        registry.beginRegistration(LivingEntity.class, TickingComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(TickingComponent::new);
    }
}
