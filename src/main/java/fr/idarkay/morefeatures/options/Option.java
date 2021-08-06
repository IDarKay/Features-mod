package fr.idarkay.morefeatures.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ClickableWidget;
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
