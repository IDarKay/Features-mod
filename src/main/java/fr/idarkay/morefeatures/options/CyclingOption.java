package fr.idarkay.morefeatures.options;

import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * File <b>CyclingOption</b> located on fr.idarkay.morefeatures.options
 * CyclingOption is a part of Features-mod_1.16.2.
 * <p>
 * Copyright (c) 2020 Features-mod_1.16.2.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 10/10/2020 at 11:59
 */
public class CyclingOption extends Option
{

    private final BiConsumer<FeaturesGameOptions, Integer> setter;
    private final BiFunction<FeaturesGameOptions, CyclingOption, Text> messageProvider;

    protected CyclingOption(MutableText prefix,  BiConsumer<FeaturesGameOptions, Integer> setter, BiFunction<FeaturesGameOptions, CyclingOption, Text> messageProvider)
    {
        super(prefix);
        this.setter = setter;
        this.messageProvider = messageProvider;
    }

    public void cycle(FeaturesGameOptions options, int amount) {
        this.setter.accept(options, options.weatherMode.getId() + amount);
        options.writeChanges();
    }

    public AbstractButtonWidget createButton(FeaturesGameOptions options, int x, int y, int width) {
        return new ButtonWidget(x, y, width, 20,  this.getMessage(options), (button) -> {
            this.cycle(options, 1);
            button.setMessage(this.getMessage(options));
        });
    }

    public Text getMessage(FeaturesGameOptions options) {
        return getDisplayPrefix().copy().append(this.messageProvider.apply(options,this));
    }
}
