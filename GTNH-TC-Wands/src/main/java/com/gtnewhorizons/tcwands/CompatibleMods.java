package com.gtnewhorizons.tcwands;

import cpw.mods.fml.common.Loader;

public enum CompatibleMods {

    FORBIDDEN_MAGIC("ForbiddenMagic"),
    THAUMIC_TINKERER("ThaumicTinkerer"),
    BLOOD_ARSENAL("BloodArsenal"),
    TAINTED_MAGIC("TaintedMagic"),
    THAUMIC_BASES("thaumicbases"),
    THAUMIC_EXPLORATION("ThaumicExploration");

    private String modID;

    CompatibleMods(String modID) {
        this.modID = modID;
    }

    public String getModID() {
        return modID;
    }

    public boolean isPresent() {
        return Loader.isModLoaded(getModID());
    }
}
