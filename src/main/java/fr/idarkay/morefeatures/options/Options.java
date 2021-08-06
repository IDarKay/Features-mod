package fr.idarkay.morefeatures.options;

import fr.idarkay.morefeatures.FeaturesMod;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

/**
 * File <b>Options</b> located on fr.idarkay.morefeatures.options
 * Options is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 06/08/2021 at 20:46
 */
public abstract class Options
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
    public static final DoubleOption EAT_AT = new DoubleOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".eatAt"),
            0.0d, 19.0d, 0.5f,
            options -> options.eatLvlLimit,
            (options, aDouble) -> options.eatLvlLimit = aDouble,
            (options, doubleOption) -> new LiteralText(String.valueOf(doubleOption.get(options)))
    );
    public static final DoubleOption ATTACK_COOL_DOWN = new DoubleOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".attackCoolDown"),
            2.0d, 1200.0d, 2.0f,
            options -> options.attackCoolDown,
            (options, aDouble) -> options.attackCoolDown = aDouble,
            (options, doubleOption) -> new LiteralText (((double) doubleOption.get(options) / 20.0d) + " seconds" )
    );
    public static final DoubleOption USE_COOL_DOWN = new DoubleOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".useCoolDown"),
            1.0d, 1200.0d, 1.0f,
            options -> options.useCoolDown,
            (options, aDouble) -> options.useCoolDown = aDouble,
            (options, doubleOption) -> new LiteralText (((double) doubleOption.get(options) / 20.0d) + " seconds" )
    );
    public static final BooleanOption EAT = new BooleanOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".eat"),
            options -> options.eatOn,
            (options, aBoolean) -> options.eatOn = aBoolean
    );
    public static final BooleanOption ACTIVATED = new BooleanOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".activated"),
            options -> options.autoAttackActivated,
            (options, aBoolean) -> options.autoAttackActivated = aBoolean
    );
    public static final BooleanOption AUTO_MINE_ACTIVATED = new BooleanOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".autoMine"),
            options -> options.autoMineActivated,
            (options, aBoolean) -> options.autoMineActivated = aBoolean
    );
    public static final BooleanOption AUTO_CLICK_ACTIVATED = new BooleanOption(
            new TranslatableText("options." + FeaturesMod.MOD_ID + ".autoCLick"),
            options -> options.autoClickActivated,
            (options, aBoolean) -> options.autoClickActivated = aBoolean
    );
}
