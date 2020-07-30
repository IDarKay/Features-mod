package fr.idarkay.morefeature;

import fr.idarkay.morefeature.options.FeaturesGameOptions;
import fr.idarkay.morefeature.options.FeaturesOptionsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

import java.io.File;

@Environment(EnvType.CLIENT)
public class FeaturesClient implements ModInitializer {

	private static FeaturesGameOptions CONFIG;

	//sound
	public static final Identifier BREAK_SAFE_ID = new Identifier("more_feature_id:break_safe");
	public static final SoundEvent BREAK_SAFE_EVENT = new SoundEvent(BREAK_SAFE_ID);

	//keybinding
	public static final KeyBinding OPEN_OPTIONS_KEYS = new KeyBinding(
			"key.more_feature_id.options",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_F,
			"key.categories.misc"
			);



	@Override
	public void onInitialize() {
		
		Registry.register(Registry.SOUND_EVENT, FeaturesClient.BREAK_SAFE_ID, BREAK_SAFE_EVENT);
		KeyBindingHelper.registerKeyBinding(OPEN_OPTIONS_KEYS);

		ClientTickCallback.EVENT.register(client -> {
			while (OPEN_OPTIONS_KEYS.isPressed())
			{
				client.openScreen(new FeaturesOptionsScreen(null, options()));
			}
		});

	}

	public static FeaturesGameOptions options() {
		if (CONFIG == null) {
			CONFIG = loadConfig();
		}

		return CONFIG;
	}

	private static FeaturesGameOptions loadConfig() {
		FeaturesGameOptions config = FeaturesGameOptions.load(new File("config/more_feature_id.json"));

		return config;
	}

}
