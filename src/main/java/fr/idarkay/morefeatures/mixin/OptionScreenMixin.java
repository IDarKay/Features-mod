package fr.idarkay.morefeatures.mixin;

import fr.idarkay.morefeatures.FeaturesClient;
import fr.idarkay.morefeatures.options.FeaturesOptionsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
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
        if(MinecraftClient.getInstance().world != null)
        {
            // down Done button
            this.buttons.get(this.buttons.size() - 1).y = this.height / 6 + 192;

            this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, new LiteralText("Features"), (buttonWidget) -> {
                this.client.openScreen(new FeaturesOptionsScreen(this, FeaturesClient.options()));
            }));
        }
    }
}
