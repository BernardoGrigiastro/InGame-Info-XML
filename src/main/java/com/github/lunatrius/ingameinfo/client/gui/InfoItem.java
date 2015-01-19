package com.github.lunatrius.ingameinfo.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class InfoItem extends Info {
    private final static RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private final ItemStack itemStack;
    private final boolean large;
    private final int size;

    public InfoItem(ItemStack itemStack) {
        this(itemStack, false);
    }

    public InfoItem(ItemStack itemStack, boolean large) {
        this(itemStack, large, 0, 0);
    }

    public InfoItem(ItemStack itemStack, boolean large, int x, int y) {
        super(x, y);
        this.itemStack = itemStack;
        this.large = large;
        this.size = large ? 16 : 8;
        if (large) {
            this.y = -4;
        }
    }

    @Override
    public void drawInfo() {
        if (this.itemStack != null && this.itemStack.getItem() != null) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            GL11.glTranslatef(getX(), getY(), 0);
            if (!this.large) {
                GL11.glScalef(0.5f, 0.5f, 0.5f);
            }

            final float zLevel = renderItem.zLevel;
            renderItem.zLevel = 300;
            renderItem.renderItemAndEffectIntoGUI(this.itemStack, 0, 0);
            renderItem.zLevel = zLevel;

            if (!this.large) {
                GL11.glScalef(2.0f, 2.0f, 2.0f);
            }
            GL11.glTranslatef(-getX(), -getY(), 0);

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    @Override
    public int getWidth() {
        return this.itemStack != null && this.itemStack.getItem() != null ? this.size : 0;
    }

    @Override
    public int getHeight() {
        return this.itemStack != null && this.itemStack.getItem() != null ? this.size : 0;
    }

    @Override
    public String toString() {
        return String.format("InfoItem{itemStack: %s, x: %d, y: %d, offsetX: %d, offsetY: %d, children: %s}", this.itemStack, this.x, this.y, this.offsetX, this.offsetY, this.children);
    }
}
