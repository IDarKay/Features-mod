package fr.idarkay.morefeature.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;

/**
 * File <b>Option</b> located on fr.idarkay.morefeature.options
 * Option is a part of features-mod.
 * <p>
 * Copyright (c) 2020 features-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 23:47
 */
@Environment(EnvType.CLIENT)
public abstract class Option
{

    public static final BooleanOption BREAK_SAFE = new BooleanOption(
            new LiteralText("Break Safe: "),
            options -> options.breakSafe,
            (options, aBoolean) -> options.breakSafe = aBoolean
    );
    public static final BooleanOption HIDE_FIRE = new BooleanOption(
            new LiteralText("Hide fire effect: "),
            options -> options.hide_fire,
            (options, aBoolean) -> options.hide_fire = aBoolean
    );
    public static final BooleanOption HIDE_PUMPKIN = new BooleanOption(
            new LiteralText("Hide head pumpkin effect: "),
            options -> options.hide_pumpkin,
            (options, aBoolean) -> options.hide_pumpkin = aBoolean
    );
    public static final BooleanOption SHOW_EFFECT_TIME = new BooleanOption(
            new LiteralText("Show time below effect: "),
            options -> options.effect_time,
            (options, aBoolean) -> options.effect_time = aBoolean
    );
    public static final DoubleOption SMOG_LAVA = new DoubleOption(
            new LiteralText("Remove lava fog: "),
            0.0d, 1.0d, 0.0f,
            options -> options.lavaSmogRemover,
            (options, aDouble) -> options.lavaSmogRemover = aDouble,
            (options, doubleOption) ->
            {
                double  ratio = doubleOption.getRatio(doubleOption.get(options));
                if(ratio <= 0.0D)
                    return ScreenTexts.OFF;
                else return new LiteralText (((int) (ratio * 100.0D)) + "%");
            }
    );

    protected final MutableText prefix;

    protected Option(MutableText prefix)
    {
        this.prefix = prefix;
    }

    public abstract AbstractButtonWidget createButton(FeaturesGameOptions options, int x, int y, int width);

    public MutableText getDisplayPrefix() {
        return prefix;
    }
}
