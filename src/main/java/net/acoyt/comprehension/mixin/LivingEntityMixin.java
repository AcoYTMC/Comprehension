package net.acoyt.comprehension.mixin;

import net.acoyt.comprehension.cca.HiddenComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void livingIgnoreHiddenPlayers(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living instanceof PlayerEntity player && HiddenComponent.get(player).isHidden()) {
            cir.setReturnValue(false);
        }
    }
}
