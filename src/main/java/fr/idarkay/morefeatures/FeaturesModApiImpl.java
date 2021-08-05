package fr.idarkay.morefeatures;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.idarkay.morefeatures.options.screen.FeaturesOptionsScreen;
import fr.idarkay.morefeatures.options.screen.MenuButtons;

/**
 * File <b>FeaturesModApiImpl</b> located on fr.idarkay.morefeatures
 * FeaturesModApiImpl is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 01/08/2021 at 17:31
 */
public class FeaturesModApiImpl implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    { return FeaturesOptionsScreen::new; }
}
