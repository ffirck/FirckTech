package com.firck.fircktech.screen;

import com.firck.fircktech.FirckTech;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.GameRenderer;

public class BurnerOreGrinderScreen extends HandledScreen<BurnerOreGrinderScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(FirckTech.MOD_ID, "textures/gui/burner_ore_grinder_gui.png");
    public BurnerOreGrinderScreen(BurnerOreGrinderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(matrices, x, y);

        renderFuelArrow(matrices, x, y);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 72, y + 23, 176, 14, handler.getScaledProgress(), 16);
        }
    }

    private void renderFuelArrow(MatrixStack matrices, int x, int y) {
        if(handler.isBurning()) {
            drawTexture(matrices, x + 50, y + 42 + (14-handler.getScaledFuelProgress()), 176, 14-handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
