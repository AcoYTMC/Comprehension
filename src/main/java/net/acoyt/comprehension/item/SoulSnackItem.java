package net.acoyt.comprehension.item;

import net.acoyt.comprehension.cca.HiddenComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SoulSnackItem extends Item {
    public SoulSnackItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isInSneakingPose()) {
            stack.decrement(1);
            HiddenComponent component = HiddenComponent.KEY.get(user);
            component.duration = 100;
            component.sync();

            return ActionResult.PASS;
        }
        return super.use(world, user, hand);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().formatted(Formatting.DARK_AQUA);
    }
}
