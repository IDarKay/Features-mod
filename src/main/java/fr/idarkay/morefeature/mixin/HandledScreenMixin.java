package fr.idarkay.morefeature.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * File <b>HandledScreenMixin</b> located on fr.idarkay.morefeature.mixin
 * HandledScreenMixin is a part of featurs-mod.
 * <p>
 * Copyright (c) 2020 featurs-mod.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 29/07/2020 at 22:09
 */
@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T>
{
    @Shadow protected abstract boolean handleHotbarKeyPressed(int keyCode, int scanCode);

    @Shadow @Nullable protected Slot focusedSlot;

    @Shadow protected abstract void onMouseClick(Slot slot, int invSlot, int clickData, SlotActionType actionType);

    @Shadow @Final protected T handler;

    @Shadow @Nullable protected abstract Slot getSlotAt(double xPosition, double yPosition);

    protected HandledScreenMixin(Text title)
    {
        super(title);
    }

    @Inject(method = "keyPressed(III)Z", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (super.keyPressed(keyCode, scanCode, modifiers))
        {
            cir.setReturnValue(true);
        } else if (keyCode != 256 && !this.client.options.keyInventory.matchesKey(keyCode, scanCode))
        {
            this.handleHotbarKeyPressed(keyCode, scanCode);
            if (this.focusedSlot != null && this.focusedSlot.hasStack())
            {
                if (this.client.options.keyPickItem.matchesKey(keyCode, scanCode))
                {
                    this.onMouseClick(this.focusedSlot, this.focusedSlot.id, 0, SlotActionType.CLONE);
                } else if (this.client.options.keyDrop.matchesKey(keyCode, scanCode))
                {
                    boolean control = hasControlDown();
                    if(hasShiftDown() && control)
                    {
                        Item focusedType = this.focusedSlot.getStack().getItem();
                        if(!focusedType.equals(Items.AIR))
                        {
                            for (Slot slot : handler.slots)
                            {
                                if(slot.getStack().getItem().equals(focusedType))
                                {
                                    this.onMouseClick(slot, slot.id, 1, SlotActionType.THROW);
                                }
                            }
                        }
                    }
                    else this.onMouseClick(this.focusedSlot, this.focusedSlot.id, control ? 1 : 0, SlotActionType.THROW);
                }
            }
            cir.setReturnValue(true);
        } else {
            this.client.player.closeHandledScreen();
            cir.setReturnValue(true);
        }
    }


    @Inject(method = "mouseDragged(DDIDD)Z", at = @At("TAIL"))
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir)
    {
        if((button == 0 || button == 1) && hasShiftDown() && this.client.player.inventory.getCursorStack().isEmpty())
        {
            Slot slot = this.getSlotAt(mouseX, mouseY);
            if(slot != null && !slot.getStack().isEmpty())
            {
                this.onMouseClick(slot, slot.id, button, SlotActionType.QUICK_MOVE);
            }
        }
    }
}
