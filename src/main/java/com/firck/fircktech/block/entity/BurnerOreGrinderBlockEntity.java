package com.firck.fircktech.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ManualOreGrinderBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inv = DefaultedList.ofSize(2, ItemStack.EMPTY);
    public ManualOreGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MANUAL_ORE_GRINDER, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inv;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Manual Ore Grinder");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return
    }
}
