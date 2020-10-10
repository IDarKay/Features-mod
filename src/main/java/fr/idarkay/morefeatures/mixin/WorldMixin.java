package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * File <b>WorldMiwin</b> located on fr.idarkay.morefeatures.mixin
 * WorldMiwin is a part of Features-mod_1.16.2.
 * <p>
 * Copyright (c) 2020 Features-mod_1.16.2.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 10/10/2020 at 19:33
 */
@Mixin(World.class)
public abstract class WorldMixin
{

    @Shadow protected float rainGradientPrev;

    @Shadow protected float rainGradient;

    @Shadow protected float thunderGradientPrev;

    @Shadow protected float thunderGradient;

    /**
     * @author IDarKay
     * @reason  create local weather
     */
    @Overwrite
    public float getThunderGradient(float delta) {
        switch (FeaturesClient.options().weatherMode)
        {
            case SERVER: return MathHelper.lerp(delta, this.thunderGradientPrev, this.thunderGradient) * this.getRainGradient(delta);
            case THUNDER: return 1.0f;
            default: return 0.0f;
        }
    }

    /**
     * @author IDarKay
     * @reason  create local weather
     */
    @Overwrite
    public float getRainGradient(float delta) {
        switch (FeaturesClient.options().weatherMode)
        {
            case SERVER: return MathHelper.lerp(delta, this.rainGradientPrev, this.rainGradient);
            case THUNDER:
            case RAIN: return 1.0f;
            default: return 0.0f;
        }
    }

}
