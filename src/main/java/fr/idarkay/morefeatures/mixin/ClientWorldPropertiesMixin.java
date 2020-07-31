package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * File <b>PropertiesMixin</b> located on fr.idarkay.morefeature.mixin
 * PropertiesMixin is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 31/07/2020 at 00:59
 */
@Mixin(ClientWorld.Properties.class)
public abstract class ClientWorldPropertiesMixin
{


    @Shadow private long timeOfDay;


    /**
     * @author IDarKay
     * @reason for local time
     */
    @Overwrite
    public long getTimeOfDay()
    {
        if(FeaturesClient.options().localTime)
            return FeaturesClient.LOCAL_TIME;
        return this.timeOfDay;
    }

}
