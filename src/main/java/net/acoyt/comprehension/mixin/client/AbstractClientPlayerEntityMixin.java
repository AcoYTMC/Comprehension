package net.acoyt.comprehension.mixin.client;

import com.mojang.authlib.GameProfile;
import net.acoyt.comprehension.cca.HiddenComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        // Cancels player rendering if isHidden is set to true and one of the conditions is met
        if (HiddenComponent.get(this).isHidden() && (Math.abs(MinecraftClient.getInstance().getCameraEntity().getRotationVecClient().dotProduct(this.getPos().subtract(cameraX, cameraY, cameraZ).normalize())) > 0.5 || this.isInSneakingPose() ||MinecraftClient.getInstance().options.getPerspective().isFrontView())) {
            return false;
        }
        return super.shouldRender(cameraX, cameraY, cameraZ);
    }
}
