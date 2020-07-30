package fr.idarkay.morefeature.mixin;

import fr.idarkay.morefeature.FeaturesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.security.CodeSource;

/**
 * File <b>InGameOverlayRendererMixin</b> located on fr.idarkay.morefeature.mixin
 * InGameOverlayRendererMixin is a part of features-mod.
 * <p>
 * Copyright (c) 2020 features-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 28/07/2020 at 19:51
 */
@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin
{
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderFireOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo ci)
    {
        if(FeaturesClient.options().hide_fire)
        {
            ci.cancel();
        }
    }
}
