package modernmarkings.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;
import modernmarkings.blocks.BlockBase;

public class ModFuelHandler implements IFuelHandler {

    /**
     * Return the number of ticks this fuel will burn on a furnace, or 0 if the fuel cannot be used.
     */
    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel == null) {
            return 0;
        }

        // Check if the fuel item is your custom block.
        // You should compare with the Item obtained for your block.
        if (Block.getBlockFromItem(fuel.getItem()) instanceof BlockBase) {
            // Wood plank smelts for 300 ticks, 1 plank + 1 paper = 64 markings
            // One marking smelts for floor(300/64)=4 ticks
            return 4;
        }
        return 0;
    }
}
