package fr.idarkay.morefeatures;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import fr.idarkay.morefeatures.options.Options;
import fr.idarkay.morefeatures.options.screen.AutoFarmScreen;
import fr.idarkay.morefeatures.options.screen.FeaturesOptionsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

import java.io.File;

@Environment(EnvType.CLIENT)
public class FeaturesClient implements ClientModInitializer
{
	private static final String MOD_ID = FeaturesMod.MOD_ID;
	private static FeaturesGameOptions CONFIG;

	//sound
	public static final Identifier BREAK_SAFE_ID = new Identifier("more_features_id:break_safe");
	public static final SoundEvent BREAK_SAFE_EVENT = new SoundEvent(BREAK_SAFE_ID);
	public static long LOCAL_TIME = 12000;
	public static boolean isEating = false;

	private long lastInput = 0;
	private int countDown = 0;
	private long lastShown = 0;


	@Override
	public void onInitializeClient()
	{
		Registry.register(Registry.SOUND_EVENT, FeaturesClient.BREAK_SAFE_ID, BREAK_SAFE_EVENT);
		KeyBindings.init();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.isPaused()) return;
			if (KeyBindings.OPEN_OPTIONS_KEYS.isPressed())
			{
				client.setScreen(new FeaturesOptionsScreen(null, FeaturesClient.options()));
			}
			else if(FeaturesClient.options().localTime)
			{
				if (KeyBindings.ADD_LOCAL_TIME_KEYS.isPressed())
				{
					FeaturesClient.LOCAL_TIME += 500;
				}
				if (KeyBindings.REMOVE_LOCAL_TIME_KEYS.isPressed())
				{
					FeaturesClient.LOCAL_TIME -= 500;
				}
			}
			else if(System.currentTimeMillis() - lastInput > 250)
			{
				if (KeyBindings.ACTIVE_LOCAL_TIME.isPressed())
				{
					lastInput = System.currentTimeMillis();
					Options.LOCAL_IME.set(FeaturesClient.options());
				}
				else if (KeyBindings.ATTACK_START_KEY.isPressed())
				{
					lastInput = System.currentTimeMillis();
					boolean active = options().switchAttackActive();
					client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText( "message." + MOD_ID + ".attack" + (active ? "On" : "Off")), Util.NIL_UUID);
				}
				else if (KeyBindings.MINE_START_KEY.isPressed())
				{
					lastInput = System.currentTimeMillis();
					boolean active = options().switchMineActive();
					client.options.keyAttack.setPressed(false);
					client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText( "message." + MOD_ID + ".mine" + (active ? "On" : "Off")), Util.NIL_UUID);
				}
				else if (KeyBindings.CLICK_START_KEY.isPressed())
				{
					lastInput = System.currentTimeMillis();
					boolean active = options().switchClickActive();
					client.options.keyAttack.setPressed(false);
					client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText( "message." + MOD_ID + ".click" + (active ? "On" : "Off")), Util.NIL_UUID);
				}
				else if (KeyBindings.AUTO_FARM_OPTIONS_KEY.isPressed())
				{
					lastInput = System.currentTimeMillis();
					client.setScreen(new AutoFarmScreen(null, options()));
				}
			}
			if (System.currentTimeMillis() - lastShown > 3000) {
				if (options().breakSafeWarning) {
					if (!options().breakSafe) {
						lastShown = System.currentTimeMillis();
						client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText( "message." + MOD_ID + ".noBreakProtection"), Util.NIL_UUID);
					}
				}
			}
			if(options().autoAttackActivated)
			{
				countDown--;
				if (countDown > 0)
				{
					countDown = (int) options().attackCoolDown;
					if(client instanceof PublicMinecraftClientEditor)
					{
						((PublicMinecraftClientEditor) client).publicDoAttack();
					}
					else
					{
						errorMessage(client);
						return;
					}
				}
				doEat(client);
			}
			else if(options().autoMineActivated)
			{
				client.options.keyAttack.setPressed(true);
				doEat(client);
			}
			else if(options().autoClickActivated)
			{
				countDown--;
				if (countDown <= 0)
				{
					countDown = (int) options().useCoolDown;
					if (client instanceof PublicMinecraftClientEditor)
					{
						((PublicMinecraftClientEditor) client).publicDoItemUse();
					} else
					{
						errorMessage(client);
						isEating = false;
					}
				}
			}
		});
	}

	private void doEat(MinecraftClient client)
	{
		if (options().eatOn && !client.player.isSpectator() && !client.player.isCreative())
		{
			if(client.currentScreen == null || client.currentScreen.passEvents)
			{
				if(client.player.getOffHandStack().getItem().isFood())
				{
					if(client.player.getHungerManager().getFoodLevel() <= options().eatLvlLimit)
					{
						if(!client.player.isUsingItem())
						{
							if(client instanceof PublicMinecraftClientEditor)
							{
								((PublicMinecraftClientEditor) client).publicDoItemUse();
							}
							else
							{
								errorMessage(client);
								isEating = false;
								return;
							}
						}
						isEating = true;
					}
					else isEating = false;
				}
				else
				{
					client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText("message." + MOD_ID + ".noFood"), Util.NIL_UUID);
					isEating = false;
				}
			}
			else isEating = false;
		}
		else isEating = false;
	}

	private void errorMessage(MinecraftClient client)
	{
		options().autoAttackActivated = false;
		client.inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText( "message." + MOD_ID + ".error"), Util.NIL_UUID);
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
