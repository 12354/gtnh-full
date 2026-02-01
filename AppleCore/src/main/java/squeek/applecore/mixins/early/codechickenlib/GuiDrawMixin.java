package squeek.applecore.mixins.early.codechickenlib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import codechicken.lib.gui.GuiDraw;
import squeek.applecore.client.TooltipOverlayHandler;

@Mixin(GuiDraw.class)
public class GuiDrawMixin {

    @Inject(method = "drawTooltipBox(IIIIIIII)V", at = @At("HEAD"), remap = false)
    private static void onDrawTooltipBox(int x, int y, int w, int h, int bgStart, int bgEnd, int borderStart,
            int borderEnd, CallbackInfo callbackInfo) {
        TooltipOverlayHandler.toolTipX = x;
        TooltipOverlayHandler.toolTipY = y;
        TooltipOverlayHandler.toolTipW = w;
        TooltipOverlayHandler.toolTipH = h;
    }
}
