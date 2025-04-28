package net.acoyt.comprehension.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class HiddenComponent implements CommonTickingComponent, AutoSyncedComponent {
    private final PlayerEntity player;
    private int hideDuration;
    private boolean isHidden;

    public HiddenComponent(PlayerEntity player) {
        this.player = player;
    }

    public static HiddenComponent get(PlayerEntity player) {
        return (HiddenComponent) CompComponents.HIDDEN.get(player);
    }

    public void tick() {
        if (isHidden()) {
            hideDuration = getHideDuration() - 1;
        }

        if (hideDuration > 0) {
            setHidden(true);
        }

        if (hideDuration == 0) {
            setHidden(false);
        }
    }

    public int getHideDuration() {
        return this.hideDuration;
    }

    public void setHideDuration(int hideDuration) {
        this.hideDuration = hideDuration;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.getInt("hideDuration");
        nbt.getBoolean("isHidden");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("hideDuration", this.hideDuration);
        nbt.putBoolean("isHidden", this.isHidden);
    }
}
