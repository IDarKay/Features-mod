package fr.idarkay.morefeature.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.function.*;

/**
 * File <b>BooleanOption</b> located on fr.idarkay.morefeature.options
 * BooleanOption is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 28/07/2020 at 00:06
 */
@Environment(EnvType.CLIENT)
public class BooleanOption extends Option
{

    private final Predicate<FeaturesGameOptions> getter;
    private final BiConsumer<FeaturesGameOptions, Boolean> setter;

    protected BooleanOption(MutableText prefix, Predicate<FeaturesGameOptions> getter, BiConsumer<FeaturesGameOptions, Boolean> setter)
    {
        super(prefix);
        this.getter = getter;
        this.setter = setter;
    }

    public void set(FeaturesGameOptions options, boolean value)
    {
        setter.accept(options, value);
        options.writeChanges();
    }

    public void set(FeaturesGameOptions options)
    {
        set(options, !get(options));
    }

    public boolean get(FeaturesGameOptions options)
    {
        return getter.test(options);
    }

    @Override
    public AbstractButtonWidget createButton(FeaturesGameOptions options, int x, int y, int width)
    {
        return new ButtonWidget(x, y, width, 20, getDisplayString(options), button ->
        {
            set(options);
            button.setMessage(getDisplayString(options));
        });
    }

    public Text getDisplayString(FeaturesGameOptions options) {
        return this.getDisplayPrefix().copy().append(ScreenTexts.getToggleText(this.get(options)));
    }

}
