package fr.idarkay.morefeatures.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;

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
            new TranslatableText("options.more_features_id.breakSafe"),
            options -> options.breakSafe,
            (options, aBoolean) -> options.breakSafe = aBoolean
    );
    public static final BooleanOption LIGHT_SAME_ITEM = new BooleanOption(
            new TranslatableText("options.more_features_id.lightSameItem"),
            options -> options.lightSameItem,
            (options, aBoolean) -> options.lightSameItem = aBoolean
    );
    public static final BooleanOption HIDE_FIRE = new BooleanOption(
            new TranslatableText("options.more_features_id.fireEffect"),
            options -> options.hideFire,
            (options, aBoolean) -> options.hideFire = aBoolean
    );
    public static final BooleanOption HIDE_FIRE_ONLY_ON_RESISTANCE = new BooleanOption(
            new TranslatableText("options.more_features_id.fireEffectOnResistance"),
            options -> options.hideFireOnlyResistance,
            (options, aBoolean) -> options.hideFireOnlyResistance = aBoolean
    );
    public static final BooleanOption HIDE_PUMPKIN = new BooleanOption(
            new TranslatableText("options.more_features_id.hidePumpkin"),
            options -> options.hidePumpkin,
            (options, aBoolean) -> options.hidePumpkin = aBoolean
    );
    public static final BooleanOption SHOW_EFFECT_TIME = new BooleanOption(
            new TranslatableText("options.more_features_id.potionTime"),
            options -> options.effectTime,
            (options, aBoolean) -> options.effectTime = aBoolean
    );
    public static final BooleanOption PROTECT_SOUND = new BooleanOption(
            new TranslatableText("options.more_features_id.protectSound"),
            options -> options.breakSafeSound,
            (options, aBoolean) -> options.breakSafeSound = aBoolean
    );
    public static final BooleanOption LOCAL_IME = new BooleanOption(
            new TranslatableText("options.more_features_id.localTime"),
            options -> options.localTime,
            (options, aBoolean) -> options.localTime = aBoolean
    );
    public static final BooleanOption RENDER_BEACON_BEAM = new BooleanOption(
            new TranslatableText("options.more_features_id.renderBeaconBeam"),
            options -> options.renderBeaconBeam,
            (options, aBoolean) -> options.renderBeaconBeam = aBoolean
    );
    public static final DoubleOption FOG_LAVA = new DoubleOption(
            new TranslatableText("options.more_features_id.removeLavaFog"),
            0.0d, 1.0d, 0.0f,
            options -> options.lavaFogRemover,
            (options, aDouble) -> options.lavaFogRemover = aDouble,
            (options, doubleOption) ->
            {
                double  ratio = doubleOption.getRatio(doubleOption.get(options));
                if(ratio <= 0.0D)
                    return ScreenTexts.OFF;
                else return new LiteralText (((int) (ratio * 100.0D)) + "%");
            }
    );
    public static final DoubleOption AMBIENT_FOG = new DoubleOption(
            new TranslatableText("options.more_features_id.removeAmbientFog"),
            0.0d, 1.0d, 0.0f,
            options -> options.ambientFogRemover,
            (options, aDouble) -> options.ambientFogRemover = aDouble,
            (options, doubleOption) ->
            {
                double  ratio = doubleOption.getRatio(doubleOption.get(options));
                if(ratio <= 0.0D)
                    return ScreenTexts.OFF;
                else return new LiteralText (((int) (ratio * 100.0D)) + "%");
            }
    );
    public static final DoubleOption LIGHT8SAME_ITEM_RED = new DoubleOption(
            new TranslatableText("options.more_features_id.red"),
            0.0d, 255.0d, 1.0f,
            options -> (double) options.rLightSameItem,
            (options, aDouble) -> options.rLightSameItem = aDouble.intValue(),
            (options, doubleOption) -> new LiteralText ( String.valueOf((int) doubleOption.get(options)))
    );
    public static final DoubleOption LIGHT8SAME_ITEM_GREEN = new DoubleOption(
            new TranslatableText("options.more_features_id.green"),
            0.0d, 255.0d, 1.0f,
            options -> (double) options.gLightSameItem,
            (options, aDouble) -> options.gLightSameItem = aDouble.intValue(),
            (options, doubleOption) -> new LiteralText ( String.valueOf((int) doubleOption.get(options)))
    );
    public static final DoubleOption LIGHT8SAME_ITEM_BLUE = new DoubleOption(
            new TranslatableText("options.more_features_id.blue"),
            0.0d, 255.0d, 1.0f,
            options -> (double) options.bLightSameItem,
            (options, aDouble) -> options.bLightSameItem = aDouble.intValue(),
            (options, doubleOption) -> new LiteralText ( String.valueOf((int) doubleOption.get(options)))
    );
    public static final DoubleOption LIGHT8SAME_ITEM_ALPHA = new DoubleOption(
            new TranslatableText("options.more_features_id.alpha"),
            0.0d, 255.0d, 1.0f,
            options -> (double) options.aLightSameItem,
            (options, aDouble) -> options.aLightSameItem = aDouble.intValue(),
            (options, doubleOption) -> new LiteralText ( String.valueOf((int) doubleOption.get(options)))
    );
    public static final DoubleOption PROTECT_DURABILITY = new DoubleOption(
            new TranslatableText("options.more_features_id.protectDurability"),
            5.0d, 100.0d, 1.0f,
            options -> (double) options.protectDurability,
            (options, aDouble) -> options.protectDurability = aDouble.intValue(),
            (options, doubleOption) -> new LiteralText ( String.valueOf((int) doubleOption.get(options)))
    );

    protected final MutableText prefix;

    protected Option(MutableText prefix)
    {
        this.prefix = prefix;
    }

    public abstract ClickableWidget createButton(FeaturesGameOptions options, int x, int y, int width);

    public MutableText getDisplayPrefix() {
        return prefix;
    }
}
