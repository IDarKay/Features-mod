package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import fr.idarkay.morefeatures.options.screen.FeaturesOptionsScreen;
import fr.idarkay.morefeatures.options.screen.MenuButtons;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * File <b>OptionScreenMixin</b> located on fr.idarkay.breaksafe.mixin
 * OptionScreenMixin is a part of fabric-example-mod.
 * <p>
 * Copyright (c) 2020 fabric-example-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 16:40
 */
@Mixin(OptionsScreen.class)
public abstract class OptionScreenMixin extends Screen
{
    protected OptionScreenMixin(Text title)
    {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    protected void init(CallbackInfo ci)
    {
        if(this.client != null && this.client.world != null)
        {
            this.addDrawableChild(MenuButtons.FEATURES.createButton(this, FeaturesClient.options(), this.width / 2 - 155, this.height / 6 + 144 - 6, 150));
        }
    }
}
