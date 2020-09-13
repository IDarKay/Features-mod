package fr.idarkay.morefeatures.mixin;

import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

/**
 * File <b>OptionMixin</b> located on fr.idarkay.morefeatures.mixin
 * OptionMixin is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 08/09/2020 at 23:26
 */
@Mixin(Option.class)
public abstract class OptionMixin
{
    @Mutable
    @Shadow
    @Final
    public static final DoubleOption GAMMA;

    @Shadow protected abstract Text method_30501(Text text);

    static {
        GAMMA = new DoubleOption("options.gamma", 0.0D, 5.0D, 0.0F, (gameOptions) -> {
            return gameOptions.gamma;
        }, (gameOptions, double_) -> {
            gameOptions.gamma = double_;
        }, (gameOptions, doubleOption) -> {
            double d = doubleOption.get(gameOptions);
//            double d = doubleOption.getRatio(doubleOption.get(gameOptions));
            if (d == 0.0D) {
                return getText("options.gamma", new TranslatableText("options.gamma.min"));
            } else {
                return getText2("options.gamma", (int)(d * 100.0D));
            }
        });
    }

    private static Text getText(String prefix, Text text) {
        return new TranslatableText("options.generic_value", new TranslatableText(prefix), text);
    }

    private static Text getText2(String prefix, int i) {
        return new TranslatableText("options.percent_add_value", new TranslatableText(prefix), i);
    }

}
