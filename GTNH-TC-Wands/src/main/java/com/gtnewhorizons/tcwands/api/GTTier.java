package com.gtnewhorizons.tcwands.api;

import java.util.function.Supplier;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.Materials;
import gregtech.api.util.GTModHandler;

public enum GTTier {

    // spotless:off
    LV(0, Materials.Aluminium,() -> GTModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat))),
    MV(1, Materials.StainlessSteel,() -> GTModHandler.getModItem("dreamcraft", "LichBone", 1, 0, new ItemStack(Items.carrot))),
    HV(2, Materials.EnergeticAlloy,() -> GTModHandler.getModItem("dreamcraft", "LichBone", 1, 0, new ItemStack(Items.carrot))),
    EV(3, Materials.VibrantAlloy,() -> GTModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato))),
    IV(4, Materials.TungstenSteel,() -> GTModHandler.getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato))),
    LUV(5,Materials.Enderium, () -> GTModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    ZPM(6,Materials.Oriharukon, () -> GTModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    UV(7, Materials.Osmiridium,() -> GTModHandler.getModItem("dreamcraft", "SnowQueenBlood", 1, 0, new ItemStack(Items.cake)));
    // spotless:on

    private static final GTTier[] tiers;

    static {
        final GTTier[] values = values();
        tiers = new GTTier[values.length];
        for (GTTier tier : values) {
            tiers[tier.getIndex()] = tier;
        }
    }

    private final int index;
    private final Materials material;
    private Supplier<ItemStack> wandConductorSupplier;

    GTTier(int index, Materials material, Supplier<ItemStack> wandConductorSupplier) {
        this.index = index;
        this.material = material;
        this.wandConductorSupplier = wandConductorSupplier;
    }

    public static void init() {
        for (GTTier tier : values()) {// is needed for optimization
            ItemStack conductor = tier.wandConductorSupplier.get();
            tier.wandConductorSupplier = () -> conductor;
        }
    }

    public int getIndex() {
        return index;
    }

    public Materials getMaterial() {
        return material;
    }

    public ItemStack getConductor() {
        return wandConductorSupplier.get();
    }

    /**
     * Returns the next tier. If current tier is the last, it will return itself.
     */
    public GTTier nextTier() {
        if (getIndex() >= tiers.length - 1) {
            return this;
        } else return tiers[getIndex() + 1];
    }
}
