package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.options.FeaturesKeyBindings;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * File <b>KeyBoardMixin</b> located on fr.idarkay.morefeatures.mixin
 * KeyBoardMixin is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 07/08/2021 at 04:10
 */
@Mixin(Keyboard.class)
public abstract class KeyBoardMixin
{
    @Inject(method = "onKey(JIIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V", shift = At.Shift.AFTER, ordinal = 2))
    public void onKeyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci)
    {
        InputUtil.Key key2 = InputUtil.fromKeyCode(key, scancode);
        FeaturesKeyBindings.setKeyPressed(key2, true);
    }

    @Inject(method = "onKey(JIIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V", shift = At.Shift.AFTER, ordinal = 0))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci)
    {
        InputUtil.Key key2 = InputUtil.fromKeyCode(key, scancode);
        FeaturesKeyBindings.setKeyPressed(key2, false);
    }

    @Inject(method = "onKey(JIIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V", shift = At.Shift.AFTER, ordinal = 1))
    public void onKeyWithF3(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci)
    {
        InputUtil.Key key2 = InputUtil.fromKeyCode(key, scancode);
        FeaturesKeyBindings.setKeyPressed(key2, false);
    }

}
