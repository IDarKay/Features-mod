package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import fr.idarkay.morefeatures.options.Option;
import fr.idarkay.morefeatures.options.Options;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

/**
 * File <b>LightItemOptionsScreen</b> located on fr.idarkay.morefeatures.options.screen
 * LightItemOptionsScreen is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 26/07/2021 at 21:40
 */
public class LightItemOptionsScreen extends FeaturesScreen
{
    private static final Option[] OPTIONS = new Option[]{Options.LIGHT_SAME_ITEM, Options.LIGHT8SAME_ITEM_RED,
            Options.LIGHT8SAME_ITEM_GREEN, Options.LIGHT8SAME_ITEM_BLUE, Options.LIGHT8SAME_ITEM_ALPHA};

    public LightItemOptionsScreen(@Nullable Screen parent, FeaturesGameOptions featuresGameOptions)
    {
        super(MenuButtons.LIGHT_ITEM_TEXT, parent, featuresGameOptions, OPTIONS, null);
    }
}
