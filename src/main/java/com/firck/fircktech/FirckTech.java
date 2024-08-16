package com.firck.fircktech;

import com.firck.fircktech.block.ModBlocks;
import com.firck.fircktech.block.entity.ModBlockEntities;
import com.firck.fircktech.item.ModItemGroup;
import com.firck.fircktech.item.ModItems;
import com.firck.fircktech.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirckTech implements ModInitializer {
    public static final String MOD_ID = "fircktech";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize(){
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerAllScreenHandlers();
        ModItemGroup.initialize();
    }

}
