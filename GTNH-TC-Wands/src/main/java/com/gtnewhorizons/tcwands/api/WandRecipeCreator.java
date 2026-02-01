package com.gtnewhorizons.tcwands.api;

import net.minecraft.item.ItemStack;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import com.gtnewhorizons.tcwands.api.wrappers.*;

/**
 * This class allows to register wand recipes easier.
 */
public class WandRecipeCreator {

    private String name;
    private WandDetails wandDetails = null;
    private WandDetails staffDetails = null;
    private WandProps wandProps = null;
    private WandProps staffProps = null;

    private String customResearchName = null;
    private ItemStack customCraftingRod = null;

    /**
     * @param name the name of rod material. Shouldn't contain _staff postfix, it will be substituted automatically if
     *             you register a staff or staff-sceptre recipe.
     */
    public WandRecipeCreator(String name) {
        this.name = name;
    }

    /**
     * Applies custom research name to the next registered wand,staff, etc recipe.
     *
     * @param customResearchName name to be applied
     */
    public WandRecipeCreator applyCustomResearchName(String customResearchName) {
        this.customResearchName = customResearchName;

        return this;
    }

    /**
     * Registers wand recipe with provided base, cap cost and GT tier. Conductor will be got from tier.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, GTTier tier) {
        return regWandRecipe(baseCost, capCost, tier, tier.getConductor());
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier and conductor.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, GTTier tier, ItemStack conductor) {
        this.wandProps = new WandProps(baseCost, capCost);
        this.wandDetails = new WandDetails(name, tier, conductor);
        regWandWrapper(new WandWrapper(wandDetails, wandProps));
        return this;
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier and custom crafting rod. Conductor will be got from
     * tier.
     *
     * @param customCraftingRod item that will be used in wand and ALSO IN SCEPTRE recipes instead of default rod.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, ItemStack customCraftingRod, GTTier tier) {
        return regWandRecipe(baseCost, capCost, customCraftingRod, tier, tier.getConductor());
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier, conductor and custom crafting rod.
     *
     * @param customCraftingRod item that will be used in wand and ALSO IN SCEPTRE recipes instead of default rod.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, ItemStack customCraftingRod, GTTier tier,
            ItemStack conductor) {
        this.customCraftingRod = customCraftingRod;
        return regWandRecipe(baseCost, capCost, tier, conductor);
    }

    /**
     * Register staff recipe, where its GT level and conductor depends on wand.
     */
    public WandRecipeCreator regWandDepStaffRecipe(int baseCost, int capCost) {
        if (wandDetails == null) {
            throw new IllegalStateException(
                    "You can't use regStaffRecipe(int baseCost, int capCost) without calling regWandRecipe(...).");
        }

        this.staffProps = new WandProps(baseCost, capCost);
        regWandWrapper(new StaffWrapper(wandDetails, staffProps));
        return this;
    }

    /**
     * Register staff recipe with provided base, cap cost and with level one higher tier and its standard conductor.
     */
    public WandRecipeCreator regUpwardStaffRecipe(int baseCost, int capCost) {
        GTTier tier = wandDetails.getTier().nextTier();
        return regStaffRecipe(baseCost, capCost, tier);
    }

    /**
     * Register staff recipe with provided base, cap cost and GT tier. Conductor will be got from tier.
     */
    public WandRecipeCreator regStaffRecipe(int baseCost, int capCost, GTTier tier) {
        return regStaffRecipe(baseCost, capCost, tier, tier.getConductor());
    }

    /**
     * Register staff recipe with provided base, cap cost, GT tier and conductor.
     */
    public WandRecipeCreator regStaffRecipe(int baseCost, int capCost, GTTier tier, ItemStack conductor) {
        this.staffProps = new WandProps(baseCost, capCost);
        this.staffDetails = new WandDetails(name, tier, conductor);
        regWandWrapper(new StaffWrapper(staffDetails, staffProps));
        return this;
    }

    /**
     * Register sceptre recipe, where sceptre characteristics are got from registered wand. Mustn't be called before any
     * {@link #regWandRecipe} method.
     *
     * @param sceptreCostMultiplier multiplier that will be applied on total recipe cost.
     */
    public WandRecipeCreator regSceptreRecipe(float sceptreCostMultiplier) {
        if (wandDetails == null || wandProps == null) {
            throw new IllegalStateException(
                    "You can't use regSceptreRecipe(...) without calling regWandRecipe(...) method.");
        }

        SceptreWrapper sceptreWrapper = new SceptreWrapper(wandDetails, wandProps, sceptreCostMultiplier);
        regWandWrapper(sceptreWrapper);
        return this;
    }

    /**
     * Register sceptre recipe, where sceptre characteristics are got from registered wand. Mustn't be called before any
     * {@link #regStaffRecipe} method.
     *
     * @param sceptreCostMultiplier multiplier that will be applied on total recipe cost.
     */
    public WandRecipeCreator regStaffSceptreRecipe(float sceptreCostMultiplier) {
        if (staffDetails == null || staffProps == null) {
            throw new IllegalStateException(
                    "You can't use regStaffSceptreRecipe(...) without calling regStaffRecipe(...) method.");
        }
        SceptreWrapper sceptreWrapper = new StaffSceptreWrapper(staffDetails, staffProps, sceptreCostMultiplier);
        regWandWrapper(sceptreWrapper);
        return this;
    }

    private void regWandWrapper(AbstractWandWrapper wandWrapper) {
        applyCustomizations(wandWrapper);
        TCWandAPI.regWandWrapper(wandWrapper);
    }

    private void applyCustomizations(AbstractWandWrapper wandWrapper) {
        if (customResearchName != null) {
            wandWrapper.setCustomResearchName(customResearchName);
            customResearchName = null;
        }

        if (wandWrapper.getType() == WandType.WAND || wandWrapper.getType() == WandType.SCEPTRE) {
            if (customCraftingRod != null) {
                wandWrapper.setCustomCraftingRod(customCraftingRod);
            }
        }
    }
}
