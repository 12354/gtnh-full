package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;

public abstract class AbstractWandWrapper {

    private WandDetails wandDetails;
    private WandProps wandProps;

    private String customResearchName;
    /**
     * Item that will be used in recipe at the place of rod.
     */
    private ItemStack craftingRod;

    public AbstractWandWrapper(WandDetails wandDetails, WandProps wandProps) {
        this.wandDetails = wandDetails;
        this.wandProps = wandProps;

        WandRod wandRod = WandRod.rods.get(getRodName());

        if (wandRod == null) throw new NullPointerException(
                "Can't find provided wand rod with id: " + getRodName()
                        + ". Be careful to register your custom rod before creating recipes.");

        this.craftingRod = wandRod.getItem();
    }

    public ItemStack getItem(CapWrapper cap) {
        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);

        NBTTagCompound wandNbt = writeNBT(cap);

        wand.setTagCompound(wandNbt);
        wand.setItemDamage(getRecipeCost(cap));

        return wand;
    }

    protected NBTTagCompound writeNBT(CapWrapper cap) {
        NBTTagCompound wandNbt = new NBTTagCompound();
        wandNbt.setString("rod", getRodName());
        wandNbt.setString("cap", cap.getName());

        return wandNbt;
    }

    public int getRecipeCost(CapWrapper cap) {
        return wandProps.getBaseCost() + wandProps.getCapCost() * cap.getCostMultiplier();
    }

    public abstract Object[] genRecipe(CapWrapper cap);

    public WandProps getProps() {
        return wandProps;
    }

    public WandDetails getDetails() {
        return wandDetails;
    }

    protected abstract String getDefaultResearchName();

    @NotNull
    public abstract WandType getType();

    public String getResearchName() {
        return customResearchName != null ? customResearchName : getDefaultResearchName();
    }

    public void setCustomResearchName(String customResearchName) {
        this.customResearchName = customResearchName;
    }

    public void setCustomCraftingRod(ItemStack rod) {
        this.craftingRod = rod;
    }

    public ItemStack getCraftingRod() {
        return craftingRod;
    }

    public String getRodName() {
        return wandDetails.getName();
    }
}
