package com.gtnewhorizons.tcwands.api.wandinfo;

import net.minecraft.item.ItemStack;

import com.gtnewhorizons.tcwands.api.GTTier;

import gregtech.api.enums.Materials;

public class WandDetails {

    private final String name;
    private final GTTier tier;
    private final ItemStack conductor;

    public WandDetails(String name, GTTier tier, ItemStack conductor) {
        this.name = name;
        this.tier = tier;
        this.conductor = conductor;
    }

    public ItemStack getConductor() {
        return conductor;
    }

    public String getName() {
        return name;
    }

    public Materials getMaterial() {
        return tier.getMaterial();
    }

    public GTTier getTier() {
        return tier;
    }

    public String getScrew() {
        return "screw" + this.getMaterial().mName;// FIXME check
    }
}
