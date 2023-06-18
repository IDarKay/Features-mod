package fr.idarkay.morefeatures.options;

import com.google.common.collect.Maps;
import fr.idarkay.morefeatures.FeaturesMod;
import fr.idarkay.morefeatures.exception.KeyBindingException;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * File <b>FeatursKeyBingings</b> located on fr.idarkay.morefeatures.options
 * FeatursKeyBingings is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 07/08/2021 at 03:13
 */
public class FeaturesKeyBindings
{
    @Nullable private final KeyBinding registered;
    private static final Map<InputUtil.Key, FeaturesKeyBindings> keyToBindings = Maps.newHashMap();
    private final Runnable onPress;
    private final int tickInterval;
    private InputUtil.Key boundKey;
    private final InputUtil.Key defaultKey;
    private int countDown = 0;
    private boolean pressed;

    public FeaturesKeyBindings(InputUtil.Type type, int code, Runnable onPress, int tickInterval)
    {
        this.registered = null;
        this.onPress = onPress;
        this.tickInterval = Math.max(1, tickInterval);
        this.boundKey = type.createFromCode(code);
        this.defaultKey = this.boundKey;
        keyToBindings.put(this.boundKey, this);
    }

    public FeaturesKeyBindings(String name, InputUtil.Type type, int code, Runnable onPress, int tickInterval)
    {
        this.registered = new KeyBinding("key." + FeaturesMod.MOD_ID + "." + name, type, code, "key.categories." + FeaturesMod.MOD_ID);
        KeyBindingHelper.registerKeyBinding(this.registered);
        this.boundKey = type.createFromCode(code);
        this.defaultKey = this.boundKey;
        this.onPress = onPress;
        this.tickInterval = Math.max(1, tickInterval);
        keyToBindings.put(this.boundKey, this);
    }

    public static void setKeyPressed(InputUtil.Key key, boolean pressed) {
        FeaturesKeyBindings keyBinding = keyToBindings.get(key);
        if (keyBinding != null && !keyBinding.isRegistered()) {
            keyBinding.setPressed(pressed);
        }
    }

    public boolean isPressed()
    {
        if (this.isRegistered()) return this.registered.isPressed();
        else return pressed;
    }

    public void setPressed(boolean pressed) {
        if (this.isRegistered()) throw new KeyBindingException("try setPressed on registered keyBinding");
        this.pressed = pressed;
    }

    public void setBoundKey(InputUtil.Key boundKey) {
        if (this.isRegistered()) throw new KeyBindingException("try setBoundKey on registered keyBinding");
        this.boundKey = boundKey;
    }

    public static void updatePressedStates() {

        for (FeaturesKeyBindings keyBinding : keyToBindings.values())
        {
            if (!keyBinding.isRegistered() && keyBinding.boundKey.getCategory() == InputUtil.Type.KEYSYM && keyBinding.boundKey.getCode() != InputUtil.UNKNOWN_KEY.getCode())
            {
                keyBinding.setPressed(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), keyBinding.boundKey.getCode()));
            }
        }
    }

    public boolean isRegistered()
    {
        return this.registered != null;
    }

    public void tick() {
        if (this.isPressed())
        {
            countDown--;
            if (countDown <= 0)
            {
                onPress.run();
                countDown = tickInterval;
            }
        }
    }

}
