package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import fr.idarkay.morefeatures.options.Option;
import fr.idarkay.morefeatures.options.Options;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;

/**
 * File <b>BreakageProtectionScreen</b> located on fr.idarkay.morefeatures.options.screen
 * BreakageProtectionScreen is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 26/07/2021 at 22:32
 */
public class BreakageProtectionScreen extends FeaturesScreen
{
    private static final Option[] OPTIONS = new Option[] {Options.BREAK_SAFE, Options.PROTECT_DURABILITY, Options.PROTECT_SOUND};

    public BreakageProtectionScreen(@Nullable Screen parent, FeaturesGameOptions featuresGameOptions)
    {
        super(MenuButtons.BREAKAGE_PROTECTION_TEXT, parent, featuresGameOptions, OPTIONS, null);
    }
}
