package fr.idarkay.morefeatures.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * File <b>BackgroundRendererMixin</b> located on fr.idarkay.morefeature.mixin
 * BackgroundRendererMixin is a part of FeatursMod.
 * <p>
 * Copyright (c) 2020 FeatursMod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 22:37
 */
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin
{

//    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
//    private static void render(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci)
//    {
//        FluidState fluidState = camera.getSubmergedFluidState();
//        if(fluidState.isIn(FluidTags.LAVA))
//        {
//            RenderSystem.clearColor(1, 1, 1, 0.0F);
//            ci.cancel();
//        }
//    }

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci)
    {
        float optionValue = (float) FeaturesClient.options().lavaSmogRemover;
        if(optionValue > 0)
        {
            FluidState fluidState = camera.getSubmergedFluidState();
            if(fluidState.isIn(FluidTags.LAVA))
            {
                RenderSystem.fogStart(viewDistance * 0.75f * optionValue);
                RenderSystem.fogEnd(viewDistance * optionValue);
                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
                RenderSystem.setupNvFogDistance();

                ci.cancel();
            }
        }
    }

}
