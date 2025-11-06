package net.acoyt.comprehension.cca;

import net.acoyt.comprehension.Comprehension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class TickingComponent implements CommonTickingComponent, AutoSyncedComponent {
    public static final ComponentKey<TickingComponent> KEY = ComponentRegistry.getOrCreate(Comprehension.id("ticking"), TickingComponent.class);
    private final LivingEntity living;
    public int duration;

    public TickingComponent(LivingEntity living) {
        this.living = living;
    }

    public void sync() {
        KEY.sync(this.living);
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
        this.duration = nbt.getInt("duration", 0);
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("duration", this.duration);
    }
}
