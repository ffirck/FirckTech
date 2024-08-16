package com.firck.fircktech.item;

import com.firck.fircktech.FirckTech;
import com.firck.fircktech.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemGroup {

    public static final ArrayList<ItemStack> ENTRIES_INGOTS = new ArrayList<>();
    public static final ArrayList<ItemStack> ENTRIES_RAW = new ArrayList<>();
    public static final ArrayList<ItemStack> ENTRIES_PRODUCTS = new ArrayList<>();
    public static final ArrayList<ItemStack> ENTRIES_BLOCKS = new ArrayList<>();
    public static final ArrayList<ItemStack> ENTRIES_ORES = new ArrayList<>();
    public static final ArrayList<ItemStack> ENTRIES_MACHINES = new ArrayList<>();

    public static final ItemGroup FIRCKTECH_INGOTS =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.INGOT_GRAPHITE))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_ingots")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_INGOTS))).build();

    public static final ItemGroup FIRCKTECH_RAW_MATERIALS =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.IMPURE_GRAPHITE))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_raw_materials")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_RAW))).build();

    public static final ItemGroup FIRCKTECH_MATERIAL_PRODUCTS =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ROD_GRAPHITE))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_material_products")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_PRODUCTS))).build();

    public static final ItemGroup FIRCKTECH_BLOCKS =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.GRAPHITE_BLOCK))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_blocks")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_BLOCKS))).build();

    public static final ItemGroup FIRCKTECH_ORES =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.GRAPHITE_ORE))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_ores")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_ORES))).build();

    public static final ItemGroup FIRCKTECH_MACHINES =
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.BURNER_ORE_GRINDER))
                    .displayName(Text.translatable("itemGroup.fircktech.fircktech_machines")).entries(((displayContext, entries) ->
                            entries.addAll(ENTRIES_MACHINES))).build();

    public static void initialize(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_ingots"), FIRCKTECH_INGOTS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_materials"), FIRCKTECH_RAW_MATERIALS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_products"), FIRCKTECH_MATERIAL_PRODUCTS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_blocks"), FIRCKTECH_BLOCKS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_ores"), FIRCKTECH_ORES);
        Registry.register(Registries.ITEM_GROUP, new Identifier(FirckTech.MOD_ID, "itemgroup_machines"), FIRCKTECH_MACHINES);
    }

}
