package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * File <b>ClientPlayerInteractionM</b> located on fr.idarkay.breaksafe.mixin
 * ClientPlayerInteractionM is a part of fabric-example-mod.
 * <p>
 * Copyright (c) 2020 fabric-example-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 01:31
 */
@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin
{

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "attackBlock", at = @At("HEAD"),  cancellable = true)
    public void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> ci)
    {
        if(FeaturesClient.options().breakSafe)
        {
            ItemStack mainHandItem = this.client.player.getStackInHand(Hand.MAIN_HAND);
            if(mainHandItem != null && mainHandItem.isDamageable() &&  mainHandItem.getMaxDamage() - mainHandItem.getDamage() < 2)
            {
                this.client.player.playSound(FeaturesClient.BREAK_SAFE_EVENT, SoundCategory.AMBIENT, 1f, 1f);
                ci.setReturnValue(false);
            }
        }
    }

    @Inject(method =  "attackEntity", at = @At("HEAD"), cancellable = true)
    public void attackEntity(PlayerEntity player, Entity target, CallbackInfo ci)
    {
        if(FeaturesClient.options().breakSafe)
        {
            ItemStack mainHandItem = this.client.player.getStackInHand(Hand.MAIN_HAND);
            if(mainHandItem != null && mainHandItem.isDamageable() &&  mainHandItem.getMaxDamage() - mainHandItem.getDamage() < 2)
            {
                this.client.player.playSound(FeaturesClient.BREAK_SAFE_EVENT, SoundCategory.AMBIENT, 1f, 1f);
                ci.cancel();
            }
        }
    }

}
