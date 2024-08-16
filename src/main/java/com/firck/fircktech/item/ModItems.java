package com.firck.fircktech.item;

import com.firck.fircktech.FirckTech;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //region Graphite
    public static final Item HIGHLY_IMPURE_GRAPHITE = registerItem("highly_impure_graphite",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_RAW_MATERIALS);
    public static final Item IMPURE_GRAPHITE = registerItem("impure_graphite",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_RAW_MATERIALS);
    public static final Item PURE_GRAPHITE = registerItem("pure_graphite",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_RAW_MATERIALS);
    public static final Item RAW_GRAPHITE = registerItem("raw_graphite",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_RAW_MATERIALS);
    public static final Item SHARD_GRAPHITE = registerItem("graphite_shard",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_RAW_MATERIALS);
    public static final Item INGOT_GRAPHITE = registerItem("graphite_ingot",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_INGOTS);
    public static final Item ROD_GRAPHITE = registerItem("graphite_rod",
            new Item(new FabricItemSettings()), ModItemGroup.FIRCKTECH_MATERIAL_PRODUCTS);
    //endregion

    private static Item registerItem(String s, Item i, ItemGroup g){
        registerItemGroups(g, i);
        return Registry.register(Registries.ITEM, new Identifier(FirckTech.MOD_ID, s), i);
    }

    public static void registerItemGroups(ItemGroup g, Item i) {
        if(g == ModItemGroup.FIRCKTECH_INGOTS)
            ModItemGroup.ENTRIES_INGOTS.add(new ItemStack(i));
        else if(g == ModItemGroup.FIRCKTECH_RAW_MATERIALS)
            ModItemGroup.ENTRIES_RAW.add(new ItemStack(i));
        else if(g == ModItemGroup.FIRCKTECH_MATERIAL_PRODUCTS)
            ModItemGroup.ENTRIES_PRODUCTS.add(new ItemStack(i));
        else if(g == ModItemGroup.FIRCKTECH_BLOCKS)
            ModItemGroup.ENTRIES_BLOCKS.add(new ItemStack(i));
        else if(g == ModItemGroup.FIRCKTECH_ORES)
            ModItemGroup.ENTRIES_ORES.add(new ItemStack(i));
        else if(g == ModItemGroup.FIRCKTECH_MACHINES)
            ModItemGroup.ENTRIES_MACHINES.add(new ItemStack(i));
        else return;
    }

    public static void registerModItems() {
        FirckTech.LOGGER.debug("items are being loaded for " + FirckTech.MOD_ID);
    }
}
