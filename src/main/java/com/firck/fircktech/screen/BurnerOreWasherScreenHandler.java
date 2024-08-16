package com.firck.fircktech.screen;

import com.firck.fircktech.screen.slot.OutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BurnerOreWasherScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public BurnerOreWasherScreenHandler(int syncId, PlayerInventory inv){
        this(syncId, inv, new SimpleInventory(4), new ArrayPropertyDelegate(4));
    }
    public BurnerOreWasherScreenHandler(int syncId, PlayerInventory playerInv, Inventory inv, PropertyDelegate delegate) {
        super(ModScreenHandlers.BURNER_ORE_WASHER_SCREEN_HANDLER, syncId);
        checkSize(inv, 4);
        this.inventory = inv;
        inv.onOpen(playerInv.player);
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0, 44, 19));
        this.addSlot(new Slot(inventory, 1, 66, 19));
        this.addSlot(new Slot(inventory, 2, 55, 54));
        this.addSlot(new OutputSlot(playerInv.player, inventory, 3, 112, 19));

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);

        addProperties(delegate);

    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean isBurning() {
        return this.propertyDelegate.get(2) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 18;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelTime = this.propertyDelegate.get(2);
        int maxFuelTime = this.propertyDelegate.get(3);
        int fuelArrowSize = 14;

        return maxFuelTime != 0 && fuelTime != 0 ? fuelTime * fuelArrowSize / maxFuelTime : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot invSlot = this.slots.get(slot);
        if (invSlot != null && invSlot.hasStack()) {
            ItemStack originalStack = invSlot.getStack();
            newStack = originalStack.copy();
            if (slot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                invSlot.setStack(ItemStack.EMPTY);
            } else {
                invSlot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }

    }

}

