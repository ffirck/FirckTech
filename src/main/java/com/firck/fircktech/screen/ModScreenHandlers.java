package com.firck.fircktech.screen;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers{
    public static ScreenHandlerType<BurnerOreGrinderScreenHandler> BURNER_ORE_GRINDER_SCREEN_HANDLER;
    public static ScreenHandlerType<BurnerOreWasherScreenHandler> BURNER_ORE_WASHER_SCREEN_HANDLER;
    public static ScreenHandlerType<BurnerCentrifugeScreenHandler> BURNER_CENTRIFUGE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers(){
        BURNER_ORE_GRINDER_SCREEN_HANDLER = new ScreenHandlerType<>(BurnerOreGrinderScreenHandler::new, FeatureSet.empty());
        BURNER_ORE_WASHER_SCREEN_HANDLER = new ScreenHandlerType<>(BurnerOreWasherScreenHandler::new, FeatureSet.empty());
        BURNER_CENTRIFUGE_SCREEN_HANDLER = new ScreenHandlerType<>(BurnerCentrifugeScreenHandler::new, FeatureSet.empty());
    }
}
