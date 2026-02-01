package team.chisel.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class BlockCarvableGlowie extends BlockCarvable {

    public BlockCarvableGlowie(Material material) {
        super(material);
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }
}
