package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import fr.idarkay.morefeatures.PublicMinecraftClientEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements PublicMinecraftClientEditor
{

	@Shadow protected abstract void doAttack();

	@Shadow protected abstract void doItemUse();

	@Shadow @NotNull public ClientPlayerEntity player;

	@Shadow protected abstract void handleBlockBreaking(boolean bl);

	@Shadow @Nullable public Screen currentScreen;

	@Shadow @Final public GameOptions options;

	@Shadow @Final public Mouse mouse;

	@Inject(at = @At("HEAD"),  method = "doItemUse()V", cancellable = true)
	private void doItemUse(CallbackInfo info)
	{
		if(FeaturesClient.options().breakSafe)
		{
			ItemStack mainHandItem = this.player.getStackInHand(Hand.MAIN_HAND);
			ItemStack offHandItem = this.player.getStackInHand(Hand.OFF_HAND);

			if((mainHandItem != null && mainHandItem.isDamageable() &&  mainHandItem.getMaxDamage() - mainHandItem.getDamage() < FeaturesClient.options().protectDurability) || (offHandItem != null && offHandItem.isDamageable() &&  offHandItem.getMaxDamage() - offHandItem.getDamage() < FeaturesClient.options().protectDurability))
			{
				if (FeaturesClient.options().breakSafeSound)
					this.player.playSound(FeaturesClient.BREAK_SAFE_EVENT, SoundCategory.AMBIENT, 1f, 1f);
				info.cancel();
			}
		}
	}

	@Inject(method = "handleInputEvents()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z", shift = At.Shift.BEFORE, ordinal = 2), cancellable = true)
	private void handleInputEvents(CallbackInfo ci)
	{
		if(FeaturesClient.isEating && FeaturesClient.options().eatOn && (FeaturesClient.options().autoAttackActivated || FeaturesClient.options().autoMineActivated)) ci.cancel();
		else this.handleBlockBreaking(this.currentScreen == null && this.options.keyAttack.isPressed() && this.mouse.isCursorLocked());
	}

	/**
	 * use this and not reflect because code obfuscation
	 */
	@Override
	public void publicDoAttack()
	{
		doAttack();
	}

	/**
	 * use this and not reflect because code obfuscation
	 */
	@Override
	public void publicDoItemUse()
	{
		this.doItemUse();
	}

}
