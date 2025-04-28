package net.acoyt.comprehension.mixin.modmenu;

import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.comprehension.Comprehension;
import net.acoyt.comprehension.compat.CompConfig;
import net.acoyt.comprehension.util.ColorUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class ModMenuEntryMixin extends Screen {
    @Shadow private ModListEntry selected;
    @Shadow private int rightPaneX;

    protected ModMenuEntryMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ModListEntry selectedEntry = this.selected;
        if (selectedEntry != null) {
            Mod mod = selectedEntry.getMod();
            int imageOffset = 36;
            Text name = Text.literal(mod.getTranslatedName());
            StringVisitable trimmedName = name;
            int maxNameWidth = this.width - (this.rightPaneX + imageOffset);
            if (this.textRenderer.getWidth(name) > maxNameWidth) {
                StringVisitable ellipsis = StringVisitable.plain("...");
                trimmedName = StringVisitable.concat(this.textRenderer.trimToWidth(name, maxNameWidth = this.textRenderer.getWidth(ellipsis)), ellipsis);
            }

            if (Comprehension.MOD_ID.equals(mod.getId())) {
                // Modify the text rendering with a new color
                context.drawText(this.textRenderer, Language.getInstance().reorder(trimmedName), this.rightPaneX + imageOffset, 49, ColorUtils.convertToHex(CompConfig.modNameColor), true);

                // Renders a texture next to the name
                //context.drawTexture(RenderLayer::getGuiTexturedOverlay, AcornLib.id("acorn.png"), this.rightPaneX + imageOffset + 44, 46, 0, 0 , 13, 13, 13, 13);
            }
        }
    }
}
