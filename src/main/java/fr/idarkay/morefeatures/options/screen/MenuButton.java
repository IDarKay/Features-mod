package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * File <b>SubMenueButton</b> located on fr.idarkay.morefeatures.options.screen
 * SubMenueButton is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 26/07/2021 at 21:46
 */
public class MenuButton
{

    private final Text name;
    private final BiFunction<Screen, FeaturesGameOptions, Screen> creator;

    public MenuButton(Text name, BiFunction<Screen, FeaturesGameOptions, Screen> creator)
    {
        this.name = name;
        this.creator = creator;
    }

    public ClickableWidget createButton(@Nullable Screen parent, FeaturesGameOptions options, int x, int y, int width)
    {
        return new ButtonWidget(x, y, width, 20, this.name, button -> MinecraftClient.getInstance().setScreen(creator.apply(parent, options)));
    }

}
