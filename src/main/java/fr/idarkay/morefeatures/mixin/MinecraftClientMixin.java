package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{

	@Shadow @NotNull public ClientPlayerEntity player;

	@Inject(at = @At("HEAD"),  method = "doItemUse()V", cancellable = true)
	private void doItemUse(CallbackInfo info)
	{
		if(FeaturesClient.options().breakSafe)
		{
			ItemStack mainHandItem = this.player.getStackInHand(Hand.MAIN_HAND);
			ItemStack offHandItem = this.player.getStackInHand(Hand.OFF_HAND);

			if((mainHandItem != null && mainHandItem.isDamageable() &&  mainHandItem.getMaxDamage() - mainHandItem.getDamage() < 2) || (offHandItem != null && offHandItem.isDamageable() &&  offHandItem.getMaxDamage() - offHandItem.getDamage() < 2 ))
			{
				this.player.playSound(FeaturesClient.BREAK_SAFE_EVENT, SoundCategory.AMBIENT, 1f, 1f);
				info.cancel();
			}
		}

	}

}
