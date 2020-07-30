package fr.idarkay.morefeature.options;

import fr.idarkay.morefeature.FeaturesClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
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
    private static final Option[] OPTIONS = new Option[] {Option.BREAK_SAFE, Option.SMOG_LAVA, Option.HIDE_FIRE, Option.HIDE_PUMPKIN, Option.SHOW_EFFECT_TIME};
    @Nullable private final Screen parent;
    private final FeaturesGameOptions option;

    public FeaturesOptionsScreen(@Nullable Screen parent, FeaturesGameOptions featuresGameOptions)
    {
        super(new TranslatableText("features.title"));
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
            this.addButton(option.createButton(FeaturesClient.options(), width, height, 150));
            ++i;
        }

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (i + 1) / 2, 200, 20, ScreenTexts.DONE,
                (buttonWidget) -> this.client.openScreen(this.parent)));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
//        if(BreakSafe.OPEN_OPTIONS_KEYS.matchesKey(keyCode, scanCode))
//        {
//            this.client.openScreen((Screen)null);
//            this.client.mouse.lockCursor();
//            return true;
//        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose()
    {
        this.client.openScreen(parent);
    }
}
