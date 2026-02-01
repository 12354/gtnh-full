package net.blay09.mods.cookingforblockheads.compat;

import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenStorageProvider;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class SimpleStorageProvider implements IKitchenStorageProvider {

    private final IInventory inventory;

    public SimpleStorageProvider(TileEntity tileEntity) {
        this.inventory = (IInventory) tileEntity;
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
}
