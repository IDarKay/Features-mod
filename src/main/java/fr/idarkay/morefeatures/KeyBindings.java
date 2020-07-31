package fr.idarkay.morefeatures;

import fr.idarkay.morefeatures.options.FeaturesOptionsScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

/**
 * File <b>KeyBindings</b> located on fr.idarkay.morefeatures
 * KeyBindings is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 31/07/2020 at 01:44
 */
public abstract class KeyBindings
{

    private static final KeyBinding OPEN_OPTIONS_KEYS = new KeyBinding(
            "key.more_features_id.options",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F,
            "key.categories.more_features_id"
    );
    private static final KeyBinding ADD_LOCAL_TIME_KEYS = new KeyBinding(
            "key.more_features_id.addTime",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_ADD,
            "key.categories.more_features_id"
    );
    private static final KeyBinding REMOVE_LOCAL_TIME_KEYS = new KeyBinding(
            "key.more_features_id.removeTime",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.categories.more_features_id"
    );

    public static void init()
    {
        KeyBindingHelper.registerKeyBinding(OPEN_OPTIONS_KEYS);
        KeyBindingHelper.registerKeyBinding(ADD_LOCAL_TIME_KEYS);
        KeyBindingHelper.registerKeyBinding(REMOVE_LOCAL_TIME_KEYS);

        ClientTickCallback.EVENT.register(client -> {
            if (OPEN_OPTIONS_KEYS.isPressed())
            {
                client.openScreen(new FeaturesOptionsScreen(null, FeaturesClient.options()));
            }
            if(FeaturesClient.options().localTime)
            {
                if (ADD_LOCAL_TIME_KEYS.isPressed())
                {
                    FeaturesClient.LOCAL_TIME += 500;
                }
                if (REMOVE_LOCAL_TIME_KEYS.isPressed())
                {
                    FeaturesClient.LOCAL_TIME -= 500;
                }
            }

        });
    }

}
