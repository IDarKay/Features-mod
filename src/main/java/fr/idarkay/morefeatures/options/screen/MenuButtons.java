package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.FeaturesMod;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

/**
 * File <b>MenuButtons</b> located on fr.idarkay.morefeatures.options.screen
 * MenuButtons is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 26/07/2021 at 22:09
 */
public abstract class MenuButtons
{
    private static final String MOD_ID = FeaturesMod.MOD_ID;
    
    public static Text LIGHT_ITEM_TEXT = new TranslatableText("over_light." + MOD_ID + ".title");
    public static Text FEATURES_TEXT = new TranslatableText("features." + MOD_ID + ".title");
    public static Text BREAKAGE_PROTECTION_TEXT = new TranslatableText("breakSafe." + MOD_ID + ".title");
    public static Text AUTO_FARM_TEXT = new TranslatableText("auto_farm." + MOD_ID + ".title");

    public static MenuButton LIGHT_ITEM = new MenuButton(LIGHT_ITEM_TEXT, LightItemOptionsScreen::new);
    public static MenuButton FEATURES = new MenuButton(FEATURES_TEXT, FeaturesOptionsScreen::new);
    public static MenuButton BREAKAGE_PROTECTION = new MenuButton(BREAKAGE_PROTECTION_TEXT, BreakageProtectionScreen::new);
    public static MenuButton AUTO_FARM = new MenuButton(AUTO_FARM_TEXT, AutoFarmScreen::new);
}
