package com.gtnewhorizons.tcwands;

import static com.gtnewhorizons.tcwands.api.GTTier.*;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.tcwands.api.GTTier;
import com.gtnewhorizons.tcwands.api.IWandRegistry;
import com.gtnewhorizons.tcwands.api.TCWandAPI;
import com.gtnewhorizons.tcwands.api.WandRecipeCreator;
import com.gtnewhorizons.tcwands.api.wrappers.CapWrapper;

import gregtech.api.util.GTModHandler;

public class GTWandRegistry implements IWandRegistry {

    @Override
    public void register() {
        new WandRecipeCreator("wood").regWandRecipe(
                0,
                5,
                GTModHandler.getModItem("Forestry", "oakStick", 1, 0, new ItemStack(Items.stick)),
                GTTier.LV).regSceptreRecipe(2.0F);
        new WandRecipeCreator("greatwood").regWandRecipe(20, 5, MV).regSceptreRecipe(2F).regUpwardStaffRecipe(75, 15)
                .regStaffSceptreRecipe(1.4F);
        new WandRecipeCreator("reed").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("blaze").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("obsidian").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("ice").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("quartz").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("bone").regWandRecipe(60, 10, HV).regSceptreRecipe(1.4F).regUpwardStaffRecipe(125, 15)
                .regStaffSceptreRecipe(1.5F);
        new WandRecipeCreator("silverwood").regWandRecipe(115, 15, EV).regSceptreRecipe(1.25F)
                .regUpwardStaffRecipe(165, 15).regStaffSceptreRecipe(1.75F);
        new WandRecipeCreator("primal").regStaffRecipe(175, 20, IV).regStaffSceptreRecipe(1.25F);

        TCWandAPI.regCap(new CapWrapper("iron", 0));
        TCWandAPI.regCap(new CapWrapper("copper", 1));
        TCWandAPI.regCap(new CapWrapper("gold", 2));
        TCWandAPI.regCap(new CapWrapper("silver", 3));
        TCWandAPI.regCap(new CapWrapper("thaumium", 5));
        TCWandAPI.regCap(new CapWrapper("void", 7));

        if (CompatibleMods.FORBIDDEN_MAGIC.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Forbidden Magic. Applying GTNH Recipes...");
            new WandRecipeCreator("profane").regWandRecipe(25, 5, HV).regSceptreRecipe(2F);
            new WandRecipeCreator("tainted").regWandRecipe(175, 15, IV).regSceptreRecipe(1.4F);
            new WandRecipeCreator("blood").regWandRecipe(115, 15, EV).regSceptreRecipe(1.35F)
                    .regUpwardStaffRecipe(150, 15).regStaffSceptreRecipe(1.2F);
            new WandRecipeCreator("infernal").regWandRecipe(165, 15, IV).regSceptreRecipe(1.35F);
            new WandRecipeCreator("livingwood").regWandRecipe(105, 15, EV).regSceptreRecipe(1.35F);
            new WandRecipeCreator("dreamwood").regWandRecipe(115, 15, EV).regSceptreRecipe(1.3F)
                    .regUpwardStaffRecipe(150, 15).regStaffSceptreRecipe(1.2F);
            new WandRecipeCreator("witchwood").regWandRecipe(115, 15, EV).regSceptreRecipe(1.3F)
                    .regUpwardStaffRecipe(150, 15).regStaffSceptreRecipe(1.2F);

            TCWandAPI.regCap(new CapWrapper("manasteel", 5));
            TCWandAPI.regCap(new CapWrapper("terrasteel", 1));
            TCWandAPI.regCap(new CapWrapper("elementium", 7));
            TCWandAPI.regCap(new CapWrapper("vinteum", 5));
            TCWandAPI.regCap(new CapWrapper("alchemical", 6));
        }

        if (CompatibleMods.THAUMIC_TINKERER.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Thaumic Tinkerer. Applying GTNH Recipes...");
            new WandRecipeCreator("ICHORCLOTH").regWandRecipe(250, 25, UV).regSceptreRecipe(1.25F);

            TCWandAPI.regCap(new CapWrapper("ICHOR", 8));
        }

        if (CompatibleMods.BLOOD_ARSENAL.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Blood Arsenal. Applying GTNH Recipes...");
            new WandRecipeCreator("blood_wood").regWandRecipe(110, 15, EV).regSceptreRecipe(1.2F)
                    .regUpwardStaffRecipe(145, 20).regStaffSceptreRecipe(1.6F);

            TCWandAPI.regCap(new CapWrapper("blood_iron", 6));
        }

        if (CompatibleMods.TAINTED_MAGIC.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Tainted Magic. Applying GTNH Patches and Recipes...");
            // Override Tainted Magic Caps
            TCWandAPI.makeCap(
                    "shadowcloth",
                    GTModHandler.getModItem("TaintedMagic", "ItemWandCap", 1, 3),
                    0.85F,
                    7,
                    new ResourceLocation("taintedmagic", "textures/models/ModelWAND_CAP_SHADOW_CLOTH.png"));
            TCWandAPI.makeCap(
                    "crimsoncloth",
                    GTModHandler.getModItem("TaintedMagic", "ItemWandCap", 1, 2),
                    0.80F,
                    9,
                    new ResourceLocation("taintedmagic", "textures/models/ModelWAND_CAP_CRIMSON_CLOTH.png"));

            new WandRecipeCreator("warpwood").regWandRecipe(190, 15, LUV).regSceptreRecipe(1.2F)
                    .regUpwardStaffRecipe(220, 25).regStaffSceptreRecipe(1.2F);

            TCWandAPI.regCap(new CapWrapper("cloth", 3));
            TCWandAPI.regCap(new CapWrapper("crimsoncloth", 6));
            TCWandAPI.regCap(new CapWrapper("shadowcloth", 5));
            TCWandAPI.regCap(new CapWrapper("shadowmetal", 8));
        }

        if (CompatibleMods.THAUMIC_BASES.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Thaumic Bases. Applying GTNH Recipes...");
            new WandRecipeCreator("tbthaumium").regWandRecipe(75, 10, HV).regSceptreRecipe(1.5F);
            new WandRecipeCreator("tbvoid").regWandRecipe(175, 15, EV).regSceptreRecipe(1.2F);

            TCWandAPI.regCap(new CapWrapper("thauminite", 6));
        }

        if (CompatibleMods.THAUMIC_EXPLORATION.isPresent()) {
            TCWandsMod.LOGGER.info("Detected Thaumic Exploration. Applying GTNH Recipes...");
            new WandRecipeCreator("AMBER").regWandRecipe(20, 5, MV).regSceptreRecipe(2F).regUpwardStaffRecipe(75, 15)
                    .regStaffSceptreRecipe(1.4F);
            new WandRecipeCreator("TRANSMUTATION").regWandRecipe(50, 10, HV).regSceptreRecipe(1.5F)
                    .regUpwardStaffRecipe(125, 15).regStaffSceptreRecipe(1.5F);
            new WandRecipeCreator("NECROMANCER").regStaffRecipe(150, 15, EV).regStaffSceptreRecipe(1.5F);

            TCWandAPI.regCap(new CapWrapper("SOJOURNER", 5));
            TCWandAPI.regCap(new CapWrapper("MECHANIST", 5));
        }
    }
}
