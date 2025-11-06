package net.acoyt.comprehension.cca;

import net.acoyt.comprehension.Comprehension;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class HiddenComponent implements CommonTickingComponent, AutoSyncedComponent {
    public static final ComponentKey<HiddenComponent> KEY = ComponentRegistry.getOrCreate(Comprehension.id("hidden"), HiddenComponent.class);
    private final PlayerEntity player;
    public int duration;

    public HiddenComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void tick() {
        if (this.duration > 0) {
            this.duration--;
            if (this.duration == 0) {
                this.sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.getInt("duration");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("duration", this.duration);
    }
}
