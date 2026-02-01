package de.keridos.floodlights.item.itemBlock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * Created by Keridos on 14.07.2015. This Class
 */
public class ItemBlockSmallElectricMetaBlock extends ItemBlockWithMetadata {

    private static final String[] subNames = { "smallFluorescent", "squareFluorescent" };

    public ItemBlockSmallElectricMetaBlock(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + subNames[itemStack.getItemDamage()];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean flagIn) {
        super.addInformation(stack, player, tooltip, flagIn);

        if (stack.hasTagCompound()) {
            tooltip.add(StatCollector.translateToLocal("gui.floodlights:tooltipConfigured"));
        }
    }
}
