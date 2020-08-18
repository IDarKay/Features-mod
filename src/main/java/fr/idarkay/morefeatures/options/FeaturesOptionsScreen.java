package fr.idarkay.morefeatures.options;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

/**
 * File <b>FeatursOptionsScreen</b> located on fr.idarkay.breaksafe.options
 * FeatursOptionsScreen is a part of fabric-example-mod.
 * <p>
 * Copyright (c) 2020 fabric-example-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 27/07/2020 at 16:46
 */
@Environment(EnvType.CLIENT)
public class FeaturesOptionsScreen extends Screen
{
    private static final Option[] OPTIONS = new Option[] {Option.BREAK_SAFE, Option.FOG_LAVA, Option.AMBIENT_FOG, Option.HIDE_FIRE, Option.HIDE_FIRE_ONLY_ON_RESISTANCE, Option.HIDE_PUMPKIN, Option.SHOW_EFFECT_TIME, Option.LOCAL_IME};
    @Nullable private final Screen parent;
    private final FeaturesGameOptions option;

    public FeaturesOptionsScreen(@Nullable Screen parent, FeaturesGameOptions featuresGameOptions)
    {
        super(new TranslatableText("features.more_features_id.title"));
        this.passEvents = true;
        this.parent = parent;
        this.option = featuresGameOptions;
    }

    @Override
    protected void init()
    {

        int i = 0;
        Option[] options = OPTIONS;
        int length = options.length;

        for(int j = 0; j < length; ++j) {
            Option option = options[j];
            int width = this.width / 2 - 155 + i % 2 * 160;
            int height = this.height / 6 + 24 * (i >> 1);
            this.addButton(option.createButton(this.option, width, height, 150));
            ++i;
        }

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (i + 1) / 2, 200, 20, ScreenTexts.DONE,
                (buttonWidget) -> this.client.openScreen(this.parent)));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void onClose()
    {
        this.client.openScreen(parent);
    }
}
