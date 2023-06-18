package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.options.FeaturesKeyBindings;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * File <b>MouseMixin</b> located on fr.idarkay.morefeatures.mixin
 * MouseMixin is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 07/08/2021 at 04:18
 */
@Mixin(Mouse.class)
public abstract class MouseMixin
{
    @Inject(method = "lockCursor()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;updatePressedStates()V", shift = At.Shift.AFTER))
    public void lockCursor(CallbackInfo ci)
    {
        FeaturesKeyBindings.updatePressedStates();
    }
}
