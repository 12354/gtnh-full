/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.Utils;

public class ItemLootBag
extends Item {
    public IIcon[] icon = new IIcon[3];

    public ItemLootBag() {
        this.func_77625_d(16);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:lootbag");
        this.icon[1] = ir.func_94245_a("thaumcraft:lootbagunc");
        this.icon[2] = ir.func_94245_a("thaumcraft:lootbagrare");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon[par1];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
        par3List.add(new ItemStack((Item)this, 1, 2));
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        switch (stack.func_77960_j()) {
            case 1: {
                return EnumRarity.uncommon;
            }
            case 2: {
                return EnumRarity.rare;
            }
        }
        return EnumRarity.common;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(stack, player, list, par4);
        list.add(StatCollector.func_74838_a((String)"tc.lootbag"));
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            int q = 8 + world.field_73012_v.nextInt(5);
            for (int a = 0; a < q; ++a) {
                ItemStack is = Utils.generateLoot(stack.func_77960_j(), world.field_73012_v);
                if (is == null) continue;
                world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, is.func_77946_l()));
            }
            world.func_72956_a((Entity)player, "thaumcraft:coins", 0.75f, 1.0f);
        }
        --stack.field_77994_a;
        return stack;
    }
}
