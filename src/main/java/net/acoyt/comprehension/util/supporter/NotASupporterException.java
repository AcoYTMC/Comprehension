package net.acoyt.comprehension.util.supporter;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

// Crashes the player's game if being a supporter is required to launch (set via SupporterUtils.setIsSupporterRequired(<BOOLEAN>))
public class NotASupporterException extends ExceptionInInitializerError {
    public static void notASupporter(ClientPlayerEntity player) {
        if (player != null) {
            if (!SupporterUtils.isPlayerSupporter(player) && SupporterUtils.IS_SUPPORTER_REQUIRED) {
                throw new CrashException(new CrashReport("You are.... NOT, a Supporter. Consider Supporting :]", new NotASupporterException()));
            }
        }
    }
}
