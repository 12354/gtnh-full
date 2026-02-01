package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;

public class CapWrapper {

    private int costMultiplier;
    private String name;
    private ItemStack itemStack;

    public CapWrapper(String name, int costMultiplier) {
        this(name, thaumcraft.api.wands.WandCap.caps.get(name).getItem(), costMultiplier);
    }

    public CapWrapper(String name, ItemStack itemStack, int costMultiplier) {
        this.name = name;
        this.costMultiplier = costMultiplier;
        this.itemStack = itemStack;
    }

    public String getName() {
        return name;
    }

    public int getCostMultiplier() {
        return costMultiplier;
    }

    public ItemStack getItem() {
        return itemStack;
    }
}
