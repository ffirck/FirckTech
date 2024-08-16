package com.firck.fircktech.block.entity;

import com.firck.fircktech.FirckTech;
import com.firck.fircktech.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<BurnerOreGrinderBlockEntity> BURNER_ORE_GRINDER;
    public static BlockEntityType<BurnerOreWasherBlockEntity> BURNER_ORE_WASHER;

    public static BlockEntityType<BurnerCentrifugeBlockEntity> BURNER_CENTRIFUGE;

    public static void registerBlockEntities(){
        BURNER_ORE_GRINDER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(FirckTech.MOD_ID, "burner_ore_grinder"),
                FabricBlockEntityTypeBuilder.create(BurnerOreGrinderBlockEntity::new,
                        ModBlocks.BURNER_ORE_GRINDER).build(null));
        BURNER_ORE_WASHER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(FirckTech.MOD_ID, "burner_ore_washer"),
                FabricBlockEntityTypeBuilder.create(BurnerOreWasherBlockEntity::new,
                        ModBlocks.BURNER_ORE_WASHER).build(null));
        BURNER_CENTRIFUGE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(FirckTech.MOD_ID, "burner_centrifuge"),
                FabricBlockEntityTypeBuilder.create(BurnerCentrifugeBlockEntity::new,
                        ModBlocks.BURNER_CENTRIFUGE).build(null));
    }
}
