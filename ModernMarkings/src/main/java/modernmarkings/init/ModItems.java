package modernmarkings.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import modernmarkings.items.ItemBase;

public class ModItems {

    /**
     * Storing all Mod Items in an Array List
     */
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item CHALK = new ItemBase("chalk", "chalk");

    public static void registerItems() {
        for (Item item : ITEMS) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }
    }
}
