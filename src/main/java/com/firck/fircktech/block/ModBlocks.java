package com.firck.fircktech.block;

import com.firck.fircktech.FirckTech;
import com.firck.fircktech.block.custom.BurnerCentrifuge;
import com.firck.fircktech.block.custom.BurnerOreGrinder;
import com.firck.fircktech.block.custom.BurnerOreWasher;
import com.firck.fircktech.item.ModItemGroup;
import com.firck.fircktech.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block GRAPHITE_BLOCK = registerBlock("graphite_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(5f, 4f).requiresTool()),
            ModItemGroup.FIRCKTECH_BLOCKS);

    public static final Block GRAPHITE_ORE = registerBlock("graphite_ore",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(3f, 3f).requiresTool()),
            ModItemGroup.FIRCKTECH_ORES);

    public static final Block DEEPSLATE_GRAPHITE_ORE = registerBlock("deepslate_graphite_ore",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(3f, 3f).requiresTool()),
            ModItemGroup.FIRCKTECH_ORES);


    // machines

    public static final Block BURNER_ORE_GRINDER = registerBlock("burner_ore_grinder",
            new BurnerOreGrinder(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL)
                    .strength(5f, 2137f).requiresTool().luminance(state -> state.get(BurnerOreGrinder.LIT) ? 15 : 0)),
                    ModItemGroup.FIRCKTECH_MACHINES);

    public static final Block BURNER_ORE_WASHER = registerBlock("burner_ore_washer",
            new BurnerOreWasher(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL)
                    .strength(5f, 2137f).requiresTool().luminance(state -> state.get(BurnerOreGrinder.LIT) ? 15 : 0)),
                    ModItemGroup.FIRCKTECH_MACHINES);

    public static final Block BURNER_CENTRIFUGE = registerBlock("burner_centrifuge",
            new BurnerCentrifuge(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL)
                    .strength(5f, 2137f).requiresTool().luminance(state -> state.get(BurnerCentrifuge.LIT) ? 15 : 0)),
                    ModItemGroup.FIRCKTECH_MACHINES);

    //multiblock components

    public static final Block BURNER_MACHINE_CASING = registerBlock("burner_machine_casing",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(5f, 2137f).requiresTool()), ModItemGroup.FIRCKTECH_BLOCKS);

    private static Block registerBlock(String s, Block b, ItemGroup g){
        registerBlockItem(s, b);
        registerItemGroups(g, b.asItem());
        return Registry.register(Registries.BLOCK, new Identifier(FirckTech.MOD_ID, s), b);
    }

    private static Item registerBlockItem(String s, Block b){
        return Registry.register(Registries.ITEM, new Identifier(FirckTech.MOD_ID, s),
                new BlockItem(b, new Item.Settings()));
    }
    public static void registerModBlocks(){
        FirckTech.LOGGER.debug("blocks are being loaded for " + FirckTech.MOD_ID);
    }

    public static void registerItemGroups(ItemGroup g, Item i) {
        if(i != null && i != Items.AIR) {
            if (g == ModItemGroup.FIRCKTECH_INGOTS)
                ModItemGroup.ENTRIES_INGOTS.add(new ItemStack(i));
            else if (g == ModItemGroup.FIRCKTECH_RAW_MATERIALS)
                ModItemGroup.ENTRIES_RAW.add(new ItemStack(i));
            else if (g == ModItemGroup.FIRCKTECH_MATERIAL_PRODUCTS)
                ModItemGroup.ENTRIES_PRODUCTS.add(new ItemStack(i));
            else if (g == ModItemGroup.FIRCKTECH_BLOCKS)
                ModItemGroup.ENTRIES_BLOCKS.add(new ItemStack(i));
            else if (g == ModItemGroup.FIRCKTECH_ORES)
                ModItemGroup.ENTRIES_ORES.add(new ItemStack(i));
            else if (g == ModItemGroup.FIRCKTECH_MACHINES)
                ModItemGroup.ENTRIES_MACHINES.add(new ItemStack(i));
        }
    }

}
