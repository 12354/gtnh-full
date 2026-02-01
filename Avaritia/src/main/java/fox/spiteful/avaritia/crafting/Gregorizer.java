package fox.spiteful.avaritia.crafting;

import cpw.mods.fml.common.Loader;
import fox.spiteful.avaritia.Config;

public class Gregorizer {

    public static int modifier = 0;
    public static int multiplier = 0;

    public static void balance() {
        if (Loader.isModLoaded("Thaumcraft")) modifier += 0;
        if (Loader.isModLoaded("TConstruct") || Loader.isModLoaded("HydCraft")) modifier += 0;
        if (Loader.isModLoaded("ThermalExpansion") || Loader.isModLoaded("TSteelworks")
                || Loader.isModLoaded("IC2")
                || Loader.isModLoaded("ThaumicTinkerer"))
            modifier += 0;
        if (Loader.isModLoaded("technom")) modifier += 0;
        if (Loader.isModLoaded("magicalcrops")) multiplier += 0;
        if (Loader.isModLoaded("AgriCraft")) multiplier += 0;
        if (Loader.isModLoaded("MineFactoryReloaded")) multiplier += 0;
        if (Loader.isModLoaded("BigReactors")) modifier += 0;
        if (Loader.isModLoaded("EE3")) multiplier += 0;
        else if (Loader.isModLoaded("ProjectE")) multiplier += 0;
        if (Loader.isModLoaded("Botania")) modifier += 0;
        if (Loader.isModLoaded("ExtraUtilities")) modifier += 0;
        if (Loader.isModLoaded("appliedenergistics2")) modifier += 0;
        if (Loader.isModLoaded("ImmersiveEngineering")) modifier += 0;
        if (Loader.isModLoaded("Mekanism")) {
            modifier += 0;
            multiplier += 0;
        }
        if (Loader.isModLoaded("Torcherino")) multiplier += 0;
        if (Loader.isModLoaded("DraconicEvolution")) {
            modifier += 0;
            multiplier += 0;
        }

        modifier = Math.max(modifier + Config.modifier, 0);
        multiplier = Math.max(multiplier + Config.multiplier, 0);
    }

    public static int balanceCost(int cost) {
        return (cost + modifier) * multiplier;
    }

}
