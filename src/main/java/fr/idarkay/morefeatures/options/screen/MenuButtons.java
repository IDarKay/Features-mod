package fr.idarkay.morefeatures.options.screen;

import net.minecraft.text.LiteralText;
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

    public static Text LIGHT_ITEM_TEXT = new TranslatableText("over_light.more_features_id.title");
    public static Text FEATURES_TEXT = new TranslatableText("features.more_features_id.title");
    public static Text BREAKAGE_PROTECTION_TEXT = new TranslatableText("breakSafe.more_features_id.title");

    public static MenuButton LIGHT_ITEM = new MenuButton(LIGHT_ITEM_TEXT, LightItemOptionsScreen::new);
    public static MenuButton FEATURES = new MenuButton(FEATURES_TEXT, FeaturesOptionsScreen::new);
    public static MenuButton BREAKAGE_PROTECTION = new MenuButton(BREAKAGE_PROTECTION_TEXT, BreakageProtectionScreen::new);
}
