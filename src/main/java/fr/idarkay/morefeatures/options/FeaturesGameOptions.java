package fr.idarkay.morefeatures.options;

import com.google.gson.*;
import fr.idarkay.morefeatures.annotation.Exclude;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    public boolean breakSafeSound = true;
    public boolean hideFire = false;
    public boolean hideFireOnlyResistance = false;
    public boolean hidePumpkin = false;
    public boolean effectTime = true;
    public boolean localTime = false;
    public boolean renderBeaconBeam = true;
    public boolean lightSameItem = true;
    public int rLightSameItem = 0xFF;
    public int gLightSameItem = 0x0;
    public int bLightSameItem = 0x0;
    public int aLightSameItem = 0xA0;
    public int protectDurability = 10;

    // auto_farm
    public boolean eatOn = true;
//    public boolean autoUseActivated = false;
    public double eatLvlLimit = 16.0f;
    public double attackCoolDown = 40.0f;
    public double useCoolDown = 10.0f;

    @Exclude public boolean autoAttackActivated = false;
    @Exclude public boolean autoMineActivated = false;
    @Exclude public boolean autoClickActivated = false;

    @Exclude private File saveFile;

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .setExclusionStrategies(new ExcludeExclusionStrategy())
            .create();

    public int getLightSameItemColor()
    {
        return (aLightSameItem % 256) << 24 | (rLightSameItem % 256) << 16 | (gLightSameItem % 256) << 8 | (bLightSameItem % 256);
    }

    public boolean switchAttackActive()
    {
        this.autoAttackActivated = !this.autoAttackActivated;
        if (this.autoAttackActivated)
        {
            this.autoMineActivated = false;
            this.autoClickActivated = false;
        }
        return autoAttackActivated;
    }

    public boolean switchMineActive()
    {
        this.autoMineActivated = !this.autoMineActivated;
        if (this.autoMineActivated)
        {
            this.autoClickActivated = false;
            this.autoAttackActivated = false;
        }
        return autoMineActivated;
    }

    public boolean switchClickActive()
    {
        this.autoClickActivated = !this.autoClickActivated;
        if (this.autoClickActivated)
        {
            this.autoMineActivated = false;
            this.autoAttackActivated = false;
        }
        return autoClickActivated;
    }

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

    static class ExcludeExclusionStrategy implements ExclusionStrategy
    {
        @Override
        public boolean shouldSkipField(FieldAttributes field)
        {
            return field.getAnnotation(Exclude.class) != null;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz)
        {
            return false;
        }
    }

}
