package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import fr.idarkay.morefeatures.options.Option;
import fr.idarkay.morefeatures.options.Options;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;

/**
 * File <b>AutoFarmScreen</b> located on fr.idarkay.morefeatures.options.screen
 * AutoFarmScreen is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 06/08/2021 at 20:23
 */
public class AutoFarmScreen extends FeaturesScreen
{
    private static final Option[] OPTIONS = new Option[] {Options.ATTACK_COOL_DOWN, Options.USE_COOL_DOWN, Options.EAT_AT, Options.EAT,
            Options.ACTIVATED, Options.AUTO_MINE_ACTIVATED, Options.AUTO_CLICK_ACTIVATED};

    public AutoFarmScreen(@Nullable Screen parent, FeaturesGameOptions featuresGameOptions)
    {
        super(MenuButtons.BREAKAGE_PROTECTION_TEXT, parent, featuresGameOptions, OPTIONS, null);
    }
}
