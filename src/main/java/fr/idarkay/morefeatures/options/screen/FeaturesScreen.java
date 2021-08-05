package fr.idarkay.morefeatures.options.screen;

import fr.idarkay.morefeatures.options.FeaturesGameOptions;
import fr.idarkay.morefeatures.options.Option;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

/**
 * File <b>FeatursScreen</b> located on fr.idarkay.morefeatures.options.screen
 * FeatursScreen is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 26/07/2021 at 21:40
 */
public abstract class FeaturesScreen extends Screen
{

    @Nullable protected final Screen parent;
    protected final FeaturesGameOptions option;
    protected final Option[] options;
    protected final MenuButton[] subMenu;


    protected FeaturesScreen(Text title, @Nullable Screen parent, FeaturesGameOptions featuresGameOptions, Option[] options, MenuButton[] subMenu)
    {
        super(title);
        this.options = options;
        this.subMenu = subMenu == null ? new MenuButton[0] : subMenu;
        this.passEvents = true;
        this.parent = parent;
        this.option = featuresGameOptions;
    }

    @Override
    protected void init()
    {
        int i = 0;
        int subMenuLength = this.subMenu.length;
        int optionLength = this.options.length;

        for(int j = 0; j < optionLength + subMenuLength; ++j) {
            int width = this.width / 2 - 155 + i % 2 * 160;
            int height = this.height / 6 + 24 * (i >> 1);
            if (j < subMenuLength)
            {
                MenuButton menu = this.subMenu[j];
                this.addDrawableChild(menu.createButton(this, this.option, width, height, 150));
            }
            else
            {
                Option option = this.options[j - subMenuLength];
                this.addDrawableChild(option.createButton(this.option, width, height, 150));
            }
            ++i;
        }
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (i + 1) / 2, 200, 20, ScreenTexts.DONE,
                (buttonWidget) -> this.client.setScreen(this.parent)));
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
        this.client.setScreen(parent);
    }

}
