package fr.idarkay.morefeatures.options;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.idarkay.morefeatures.options.mode.WeatherMode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;

/**
 * File <b>FeaturesGameOptions</b> located on fr.idarkay.breaksafe.options
 * FeaturesGameOptions is a part of fabric-example-mod.
 * <p>
 * Copyright (c) 2020 fabric-example-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 17:17
 */
@Environment(EnvType.CLIENT)
public class FeaturesGameOptions
{

    public double lavaFogRemover = 0.0d;
    public double ambientFogRemover = 0.0d;
    public boolean breakSafe = true;
    public boolean hideFire = false;
    public boolean hideFireOnlyResistance = false;
    public boolean hidePumpkin = false;
    public boolean effectTime = true;
    public boolean localTime = false;
    public WeatherMode weatherMode = WeatherMode.SERVER;

    private File saveFile;

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.PRIVATE)
            .create();

    public static FeaturesGameOptions load(File file)
    {
        FeaturesGameOptions config;

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                config = gson.fromJson(reader, FeaturesGameOptions.class);
            } catch (IOException e) {
                throw new RuntimeException("Could not parse config", e);
            }

        } else {
            config = new FeaturesGameOptions();
        }
        config.saveFile = file;

        return config;
    }

    public void writeChanges()
    {
        File dir = this.saveFile.getParentFile();

        if (!dir.exists())
        {
            if (!dir.mkdirs())
            {
                throw new RuntimeException("Could not create parent directories");
            }
        } else if (!dir.isDirectory())
        {
            throw new RuntimeException("The parent file is not a directory");
        }

        try (FileWriter writer = new FileWriter(this.saveFile)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException("Could not save configuration file", e);
        }
    }

}
