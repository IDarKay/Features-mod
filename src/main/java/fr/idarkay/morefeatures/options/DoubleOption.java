package fr.idarkay.morefeatures.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * File <b>DoubleOption</b> located on fr.idarkay.morefeature.options
 * DoubleOption is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 23:46
 */
@Environment(EnvType.CLIENT)
public class DoubleOption extends Option
{

    private final double min;
    private final double max;
    private final float step;
    private final Function<FeaturesGameOptions, Double> getter;
    private final BiConsumer<FeaturesGameOptions, Double> setter;
    private final BiFunction<FeaturesGameOptions, DoubleOption, Text> displayStringGetter;

    public DoubleOption(MutableText prefix,
                        double min,
                        double max,
                        float step,
                        Function<FeaturesGameOptions, Double> getter,
                        BiConsumer<FeaturesGameOptions, Double> setter,
                        BiFunction<FeaturesGameOptions, DoubleOption, Text> displayStringGetter)
    {
        super(prefix);
        this.min = min;
        this.max = max;
        this.step = step;
        this.getter = getter;
        this.setter = setter;
        this.displayStringGetter = displayStringGetter;
    }

    public double getRatio(double value) {
        return MathHelper.clamp((this.adjust(value) - this.min) / (this.max - this.min), 0.0D, 1.0D);
    }

    public double getValue(double ratio) {
        return this.adjust(MathHelper.lerp(MathHelper.clamp(ratio, 0.0D, 1.0D), this.min, this.max));
    }

    private double adjust(double value) {
        if (this.step > 0.0F) {
            value = (double)(this.step * (float)Math.round(value / (double)this.step));
        }

        return MathHelper.clamp(value, this.min, this.max);
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public void set(FeaturesGameOptions options, double value)
    {
        setter.accept(options, value);
        options.writeChanges();
    }

    public double get(FeaturesGameOptions options)
    {
        return getter.apply(options);
    }

    public Text getDisplayString(FeaturesGameOptions options) {
        return getDisplayPrefix().copy().append(this.displayStringGetter.apply(options,this));
    }

    @Override
    public ClickableWidget createButton(FeaturesGameOptions options, int x, int y, int width)
    {
        return new DoubleSliderWidget(options, x, y, width, 20, this);
    }
}
