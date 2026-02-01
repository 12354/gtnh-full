package net.blay09.mods.cookingforblockheads.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

import invtweaks.api.container.ChestContainer;

@ChestContainer
public class ContainerCounter extends ContainerWithInventory {

    public ContainerCounter(InventoryPlayer inventoryPlayer, IInventory counterInventory) {
        super(inventoryPlayer, counterInventory);
        counterInventory.openInventory();
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        inventory.closeInventory();
    }
}
