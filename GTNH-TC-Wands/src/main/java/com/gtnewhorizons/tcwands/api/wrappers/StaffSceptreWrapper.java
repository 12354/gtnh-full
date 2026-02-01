package com.gtnewhorizons.tcwands.api.wrappers;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

public class StaffSceptreWrapper extends SceptreWrapper {

    public StaffSceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
        super(wandDetails, wandProps, sceptreCostMultiplier);
    }

    @Override
    public String getRodName() {
        return super.getRodName() + "_staff";
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.STAFF_SCEPTRE;
    }
}
