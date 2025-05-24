package net.acoyt.comprehension.cca;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class TickingComponent implements CommonTickingComponent, AutoSyncedComponent {
    private final LivingEntity living;
    private int tickDuration;
    private boolean isTicking;

    public TickingComponent(LivingEntity living) {
        this.living = living;
    }

    public static TickingComponent get(LivingEntity living) {
        return (TickingComponent) CompComponents.TICKING.get(living);
    }

    public void tick() {
        if (isTicking()) {
            tickDuration = getTickDuration() - 1;
        }

        if (tickDuration > 0) {
            setTicking(true);
        }

        if (tickDuration == 0) {
            setTicking(false);
        }
    }

    public int getTickDuration() {
        return this.tickDuration;
    }

    public void setTickDuration(int tickDuration) {
        this.tickDuration = tickDuration;
    }

    public boolean isTicking() {
        return this.isTicking;
    }

    public void setTicking(boolean ticking) {
        this.isTicking = ticking;
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.tickDuration = nbt.getInt("tickDuration", 0);
        this.isTicking = nbt.getBoolean("isTicking", false);
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("tickDuration", this.tickDuration);
        nbt.putBoolean("isTicking", this.isTicking);
    }
}
