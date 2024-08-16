package com.firck.fircktech.block.entity;

import com.firck.fircktech.block.ModBlocks;
import com.firck.fircktech.item.ModItemGroup;
import com.firck.fircktech.item.ModItems;
import com.firck.fircktech.screen.BurnerOreGrinderScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.firck.fircktech.block.custom.BurnerOreGrinder.LIT;
import static com.firck.fircktech.block.custom.BurnerOreGrinder.ON;
import static net.minecraft.block.entity.AbstractFurnaceBlockEntity.createFuelTimeMap;

public class BurnerOreGrinderBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inv = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 400;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public BurnerOreGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BURNER_ORE_GRINDER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return BurnerOreGrinderBlockEntity.this.progress;
                    case 1: return BurnerOreGrinderBlockEntity.this.maxProgress;
                    case 2: return BurnerOreGrinderBlockEntity.this.fuelTime;
                    case 3: return BurnerOreGrinderBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: BurnerOreGrinderBlockEntity.this.progress = value; break;
                    case 1: BurnerOreGrinderBlockEntity.this.maxProgress = value; break;
                    case 2: BurnerOreGrinderBlockEntity.this.fuelTime = value; break;
                    case 3: BurnerOreGrinderBlockEntity.this.maxFuelTime = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inv;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Burner Ore Grinder");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new BurnerOreGrinderScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
        return slot==2;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inv);
        nbt.putInt("burner_ore_grinder.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inv);
        progress = nbt.getInt("burner_ore_grinder.progress");
    }

    private void resetProgress(){
        this.progress = 0;
    }

    public static boolean isFuel(ItemStack stack) {
        return createFuelTimeMap().containsKey(stack.getItem());
    }

    public static int getBurnTime(ItemStack stack) {
        return createFuelTimeMap().get(stack.getItem());
    }
    public static void tick(World world, BlockPos blockPos, BlockState blockState, BurnerOreGrinderBlockEntity entity) {
        if(world.isClient()) return;

        if(hasRecipe(entity)){

            if(entity.fuelTime > 0) {
                entity.progress++;
                entity.fuelTime-=1;
                world.setBlockState(blockPos, blockState.with(ON, true).with(LIT, true));
            }else{
                if (entity.getStack(0).getCount() > 0) {
                    if (isFuel(entity.getStack(0))) {
                        entity.maxFuelTime = getBurnTime(entity.getStack(0));
                        entity.fuelTime = entity.maxFuelTime;

                        entity.removeStack(0, 1);
                    } else {
                        if(entity.progress>0) entity.progress-=1;
                        world.setBlockState(blockPos, blockState.with(LIT, false).with(ON, false));
                    }
                } else {
                    if(entity.progress>0) entity.progress-=1;
                    world.setBlockState(blockPos, blockState.with(LIT, false).with(ON, false));
                }
            }

            markDirty(world, blockPos, blockState);
            if(entity.progress >= entity.maxProgress){
                craftItem(entity);
            }

        } else {
            entity.resetProgress();
            if(entity.fuelTime>0){
                entity.fuelTime-=1;
                world.setBlockState(blockPos, blockState.with(LIT, true).with(ON, false));
            } else {
                world.setBlockState(blockPos, blockState.with(LIT, false).with(ON, false));
            }
            markDirty(world, blockPos, blockState);
        }
    }

    private static void craftItem(BurnerOreGrinderBlockEntity entity){
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)){
            entity.removeStack(1, 1);
            entity.setStack(2, new ItemStack(ModItems.HIGHLY_IMPURE_GRAPHITE, entity.getStack(2).getCount() + 3));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(BurnerOreGrinderBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        boolean hasOreInFirstSlot = entity.getStack(1).getItem() == ModBlocks.GRAPHITE_ORE.asItem()
                || entity.getStack(1).getItem() == ModBlocks.DEEPSLATE_GRAPHITE_ORE.asItem();

        return hasOreInFirstSlot && isOutputFreeForSelectAmount(inventory, 3)
                && isOutputFree(inventory, ModItems.HIGHLY_IMPURE_GRAPHITE);
    }

    public boolean isValid(int slot, ItemStack stack) {
        return slot != 2;
    }
    private static boolean isOutputFree(SimpleInventory inv, Item i){
        return inv.getStack(2).getItem() == i.asItem() || inv.getStack(2).isEmpty();
    }
    private static boolean isOutputFreeForSelectAmount(SimpleInventory inv, int count){
        return inv.getStack(2).getMaxCount() >= inv.getStack(2).getCount() + count;
    }
}
