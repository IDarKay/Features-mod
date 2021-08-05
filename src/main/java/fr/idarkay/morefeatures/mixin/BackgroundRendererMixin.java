package fr.idarkay.morefeatures.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
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
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci)
    {
        float lavaFogRemoverValue = Math.min((float) FeaturesClient.options().lavaFogRemover, 1.0F);

        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        if(cameraSubmersionType == CameraSubmersionType.LAVA)
        {
            if(lavaFogRemoverValue > 0)
            {
                RenderSystem.setShaderFogStart(viewDistance * 0.75f * lavaFogRemoverValue);
                RenderSystem.setShaderFogEnd(viewDistance * lavaFogRemoverValue);
//                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
//                RenderSystem.setupNvFogDistance();

                ci.cancel();
            }
            return;
        }

        float netherFogRemoverValue = Math.min((float) FeaturesClient.options().ambientFogRemover, 1.0F);
        if(thickFog && netherFogRemoverValue > 0)
        {
            RenderSystem.setShaderFogStart(Math.min(viewDistance * 0.5F * netherFogRemoverValue * 2.0F, viewDistance * 0.90F));
            RenderSystem.setShaderFogEnd(Math.min(viewDistance, viewDistance * 0.75F * netherFogRemoverValue * 2.0F));
//            RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
//            RenderSystem.setupNvFogDistance();

            ci.cancel();
        }
    }
}
