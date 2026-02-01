package com.gtnewhorizons.tcwands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizons.tcwands.api.TCWandAPI;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = TCWandsMod.MODID,
        name = TCWandsMod.NAME,
        version = TCWandsMod.VERSION,
        dependencies = TCWandsMod.DEPENDENCIES)
public class TCWandsMod {

    public static final String MODID = "gtnhtcwands";
    public static final String NAME = "GTNH-TC-Wands";
    public static final String VERSION = "GRADLETOKEN_VERSION";
    public static final String DEPENDENCIES = "required-after:Thaumcraft;"
            /* + "required-after:dreamcraft;" */// will be automatically uncommented while building
            + "required-after:gregtech;"
            + "required-after:TwilightForest;"
            + "after:ForbiddenMagic;"
            + "after:TaintedMagic;"
            + "after:BloodArsenal;"
            + "after:thaumicbases;"
            + "after:ThaumicExploration;"
            + "after:ThaumicTinkerer;"
            + "after:ChromatiCraft;";
    /** ChromatiCraft is non-supported content. if this ever errors out in some way feel free to remove this. */

    public static final Logger LOGGER = LogManager.getLogger(NAME);
    @Mod.Instance
    public static TCWandsMod instance;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        TCWandAPI.addRegistry(new GTWandRegistry());
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        TCWandAPI.init();
    }
}
