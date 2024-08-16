package com.firck.fircktech.client;

import com.firck.fircktech.screen.BurnerOreGrinderScreen;
import com.firck.fircktech.screen.BurnerOreWasherScreen;
import com.firck.fircktech.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FirckTechClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.BURNER_ORE_GRINDER_SCREEN_HANDLER, BurnerOreGrinderScreen::new);
        HandledScreens.register(ModScreenHandlers.BURNER_ORE_WASHER_SCREEN_HANDLER, BurnerOreWasherScreen::new);
    }
}
