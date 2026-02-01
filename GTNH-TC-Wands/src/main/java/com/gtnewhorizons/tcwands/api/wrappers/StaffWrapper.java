package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

import thaumcraft.common.config.ConfigItems;

public class StaffWrapper extends AbstractWandWrapper {

    public StaffWrapper(WandDetails details, WandProps props) {
        super(details, props);
    }

    @Override
    public Object[] genRecipe(CapWrapper cap) {
        return new Object[] { "MSC", "SRS", "CSM", 'R', getCraftingRod(), 'M', getDetails().getConductor(), 'S',
                getDetails().getScrew(), 'C', cap.getItem(), 'P', new ItemStack(ConfigItems.itemResource, 1, 15) // Primal
                                                                                                                 // Charm
        };
    }

    @Override
    public String getRodName() {
        return super.getRodName() + "_staff";
    }

    @Override
    public String getDefaultResearchName() {
        return "ROD_greatwood_staff";
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.STAFF;
    }
}
