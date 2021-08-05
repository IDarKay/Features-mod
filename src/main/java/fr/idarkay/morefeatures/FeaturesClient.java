package fr.idarkay.morefeatures;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;

@Environment(EnvType.CLIENT)
public class FeaturesClient implements ClientModInitializer
{

	private static FeaturesGameOptions CONFIG;

	//sound
	public static final Identifier BREAK_SAFE_ID = new Identifier("more_features_id:break_safe");
	public static final SoundEvent BREAK_SAFE_EVENT = new SoundEvent(BREAK_SAFE_ID);
	public static long LOCAL_TIME = 12000;




	@Override
	public void onInitializeClient()
	{
		Registry.register(Registry.SOUND_EVENT, FeaturesClient.BREAK_SAFE_ID, BREAK_SAFE_EVENT);
		KeyBindings.init();
	}

	public static FeaturesGameOptions options() {
		if (CONFIG == null) {
			CONFIG = loadConfig();
		}

		return CONFIG;
	}

	private static FeaturesGameOptions loadConfig() {

		return FeaturesGameOptions.load(new File("config/more_features_id.json"));
	}

}
