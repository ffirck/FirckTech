package com.firck.fircktech.screen.slot;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

public class BurnerOreGrinderOutputSlot extends Slot {
    private final PlayerEntity player;
    private int amount;

    public BurnerOreGrinderOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
    }

    public boolean canInsert(ItemStack stack) {
        return false;
    }

}
