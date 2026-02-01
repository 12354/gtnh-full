package net.blay09.mods.cookingforblockheads.container.slot;

import net.blay09.mods.cookingforblockheads.client.ClientProxy;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotOvenTool extends Slot {

    private final int iconIndex;

    public SlotOvenTool(IInventory inventory, int id, int x, int y, int iconIndex) {
        super(inventory, id, x, y);
        this.iconIndex = iconIndex;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return ClientProxy.ovenToolIcons[iconIndex];
    }
}
