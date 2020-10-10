package fr.idarkay.morefeatures.options.mode;

import net.minecraft.client.options.ParticlesMode;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

/**
 * File <b>WeatherMode</b> located on fr.idarkay.morefeatures.options.mode
 * WeatherMode is a part of Features-mod_1.16.2.
 * <p>
 * Copyright (c) 2020 Features-mod_1.16.2.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 10/10/2020 at 12:10
 */
public enum  WeatherMode
{
    SERVER(0, "options.more_features_id.weather.server"),
    SUN(1, "options.more_features_id.weather.sun"),
    RAIN(2, "options.more_features_id.weather.rain"),
    THUNDER(3, "options.more_features_id.weather.thunder");

    private static final WeatherMode[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(WeatherMode::getId)).toArray(WeatherMode[]::new);

    private final int id;
    private final String translationKey;

    WeatherMode(int id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    public String getTranslationKey() {
        return this.translationKey;
    }

    public int getId() {
        return this.id;
    }

    public static WeatherMode byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }
}
