/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.block.IGrowable
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.BonemealEvent
 */
package thaumcraft.common.lib.utils;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.equipment.ItemElementalAxe;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXVisDrain;
import thaumcraft.common.lib.network.misc.PacketBiomeChange;

public class Utils {
    public static HashMap<List<Object>, ItemStack> specialMiningResult = new HashMap();
    public static HashMap<List<Object>, Float> specialMiningChance = new HashMap();
    public static final String[] colorNames = new String[]{"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
    public static final int[] colors = new int[]{0xF0F0F0, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 0x434343, 0xA0A0A0, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 0x1E1B1B};
    public static HashMap<WorldCoordinates, Long> effectBuffer = new HashMap();

    public static boolean isChunkLoaded(World world, int x, int z) {
        int xx = x >> 4;
        int zz = z >> 4;
        return world.func_72863_F().func_73149_a(xx, zz);
    }

    public static boolean useBonemealAtLoc(World world, EntityPlayer player, int x, int y, int z) {
        IGrowable igrowable;
        Block block = world.func_147439_a(x, y, z);
        BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        if (event.getResult() == Event.Result.ALLOW) {
            return true;
        }
        if (block instanceof IGrowable && (igrowable = (IGrowable)block).func_149851_a(world, x, y, z, world.field_72995_K)) {
            if (!world.field_72995_K && igrowable.func_149852_a(world, world.field_73012_v, x, y, z)) {
                igrowable.func_149853_b(world, world.field_73012_v, x, y, z);
            }
            return true;
        }
        return false;
    }

    public static boolean hasColor(byte[] colors) {
        for (byte col : colors) {
            if (col < 0) continue;
            return true;
        }
        return false;
    }

    public static int getFirstUncoveredY(World world, int par1, int par2) {
        int var3 = 5;
        while (!world.func_147437_c(par1, var3 + 1, par2)) {
            ++var3;
        }
        return var3;
    }

    public static boolean isEETransmutionItem(Item item) {
        try {
            String itemClass = "com.pahimar.ee3.item.ITransmutationStone";
            Class<?> ee = Class.forName(itemClass);
            if (ee.isAssignableFrom(item.getClass())) {
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        AbstractInterruptibleChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            ((FileChannel)destination).transferFrom(source, 0L, source.size());
        }
        finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public static int getFirstUncoveredBlockHeight(World world, int par1, int par2) {
        int var3;
        for (var3 = 10; !world.func_147437_c(par1, var3 + 1, par2) || var3 > 250; ++var3) {
        }
        return var3;
    }

    public static void addSpecialMiningResult(ItemStack in, ItemStack out, float chance) {
        specialMiningResult.put(Arrays.asList(in.func_77973_b(), in.func_77960_j()), out);
        specialMiningChance.put(Arrays.asList(in.func_77973_b(), in.func_77960_j()), Float.valueOf(chance));
    }

    public static ItemStack findSpecialMiningResult(ItemStack is, float chance, Random rand) {
        ItemStack dropped = is.func_77946_l();
        float r = rand.nextFloat();
        List<Object> ik = Arrays.asList(is.func_77973_b(), is.func_77960_j());
        if (specialMiningResult.containsKey(ik) && r <= chance * specialMiningChance.get(ik).floatValue()) {
            dropped = specialMiningResult.get(ik).func_77946_l();
            dropped.field_77994_a *= is.field_77994_a;
        }
        return dropped;
    }

    public static float clamp_float(float par0, float par1, float par2) {
        return par0 < par1 ? par1 : (par0 > par2 ? par2 : par0);
    }

    public static void setBiomeAt(World world, int x, int z, BiomeGenBase biome) {
        if (biome == null) {
            return;
        }
        Chunk chunk = world.func_72938_d(x, z);
        byte[] array = chunk.func_76605_m();
        array[(z & 0xF) << 4 | x & 0xF] = (byte)(biome.field_76756_M & 0xFF);
        chunk.func_76616_a(array);
        if (!world.field_72995_K) {
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketBiomeChange(x, z, (short)biome.field_76756_M), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)x, (double)world.func_72976_f(x, z), (double)z, 32.0));
        }
    }

    public static boolean isWoodLog(IBlockAccess world, int x, int y, int z) {
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (bi == Blocks.field_150350_a) {
            return false;
        }
        if (bi.canSustainLeaves(world, x, y, z)) {
            return true;
        }
        return ItemElementalAxe.oreDictLogs.contains(Arrays.asList(bi, md));
    }

    public static void resetFloatCounter(EntityPlayerMP player) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, (Object)player.field_71135_a, (Object)0, (String[])new String[]{"floatingTickCount", "field_147365_f"});
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static boolean getBit(int value, int bit) {
        return (value & 1 << bit) != 0;
    }

    public static int setBit(int value, int bit) {
        return value | 1 << bit;
    }

    public static int clearBit(int value, int bit) {
        return value & ~(1 << bit);
    }

    public static int toggleBit(int value, int bit) {
        return value ^ 1 << bit;
    }

    public static byte pack(boolean[] vals) {
        byte result = 0;
        for (boolean bit : vals) {
            result = (byte)(result << 1 | (bit ? 1 : 0) & 1);
        }
        return result;
    }

    public static boolean[] unpack(byte val) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; ++i) {
            result[i] = (byte)(val >> 7 - i & 1) == 1;
        }
        return result;
    }

    public static Object getNBTDataFromId(NBTTagCompound nbt, byte id, String key) {
        switch (id) {
            case 1: {
                return nbt.func_74771_c(key);
            }
            case 2: {
                return nbt.func_74765_d(key);
            }
            case 3: {
                return nbt.func_74762_e(key);
            }
            case 4: {
                return nbt.func_74763_f(key);
            }
            case 5: {
                return Float.valueOf(nbt.func_74760_g(key));
            }
            case 6: {
                return nbt.func_74769_h(key);
            }
            case 7: {
                return nbt.func_74770_j(key);
            }
            case 8: {
                return nbt.func_74779_i(key);
            }
            case 9: {
                return nbt.func_150295_c(key, 10);
            }
            case 10: {
                return nbt.func_74781_a(key);
            }
            case 11: {
                return nbt.func_74759_k(key);
            }
        }
        return null;
    }

    public static void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color) {
        WorldCoordinates wc = new WorldCoordinates(x, y, z, dim);
        Long time = System.currentTimeMillis();
        Random rand = new Random(time);
        if (effectBuffer.containsKey(wc)) {
            if (effectBuffer.get(wc) < time) {
                effectBuffer.remove(wc);
            }
        } else {
            effectBuffer.put(wc, time + 500L + (long)rand.nextInt(100));
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXVisDrain(x, y, z, x2, y2, z2, color), new NetworkRegistry.TargetPoint(dim, (double)x, (double)y, (double)z, 64.0));
        }
    }

    public static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value, String ... fieldNames) {
        Field field = ReflectionHelper.findField(classToAccess, (String[])ObfuscationReflectionHelper.remapFieldNames((String)classToAccess.getName(), (String[])fieldNames));
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
            field.set(instance, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLyingInCone(double[] x, double[] t, double[] b, float aperture) {
        double[] axisVect;
        boolean isInInfiniteCone;
        double halfAperture = aperture / 2.0f;
        double[] apexToXVect = Utils.dif(t, x);
        boolean bl = isInInfiniteCone = Utils.dotProd(apexToXVect, axisVect = Utils.dif(t, b)) / Utils.magn(apexToXVect) / Utils.magn(axisVect) > Math.cos(halfAperture);
        if (!isInInfiniteCone) {
            return false;
        }
        boolean isUnderRoundCap = Utils.dotProd(apexToXVect, axisVect) / Utils.magn(axisVect) < Utils.magn(axisVect);
        return isUnderRoundCap;
    }

    public static double dotProd(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }

    public static double[] dif(double[] a, double[] b) {
        return new double[]{a[0] - b[0], a[1] - b[1], a[2] - b[2]};
    }

    public static double magn(double[] a) {
        return Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
    }

    public static Vec3 calculateVelocity(Vec3 from, Vec3 to, double heightGain, double gravity) {
        double endGain = to.field_72448_b - from.field_72448_b;
        double horizDist = Math.sqrt(Utils.distanceSquared2d(from, to));
        double gain = heightGain;
        double maxGain = gain > endGain + gain ? gain : endGain + gain;
        double a = -horizDist * horizDist / (4.0 * maxGain);
        double b = horizDist;
        double c = -endGain;
        double slope = -b / (2.0 * a) - Math.sqrt(b * b - 4.0 * a * c) / (2.0 * a);
        double vy = Math.sqrt(maxGain * gravity);
        double vh = vy / slope;
        double dx = to.field_72450_a - from.field_72450_a;
        double dz = to.field_72449_c - from.field_72449_c;
        double mag = Math.sqrt(dx * dx + dz * dz);
        double dirx = dx / mag;
        double dirz = dz / mag;
        double vx = vh * dirx;
        double vz = vh * dirz;
        return Vec3.func_72443_a((double)vx, (double)vy, (double)vz);
    }

    public static double distanceSquared2d(Vec3 from, Vec3 to) {
        double dx = to.field_72450_a - from.field_72450_a;
        double dz = to.field_72449_c - from.field_72449_c;
        return dx * dx + dz * dz;
    }

    public static double distanceSquared3d(Vec3 from, Vec3 to) {
        double dx = to.field_72450_a - from.field_72450_a;
        double dy = to.field_72448_b - from.field_72448_b;
        double dz = to.field_72449_c - from.field_72449_c;
        return dx * dx + dy * dy + dz * dz;
    }

    public static ItemStack generateLoot(int rarity, Random rand) {
        ItemStack is = null;
        if (rarity > 0 && rand.nextFloat() < 0.025f * (float)rarity) {
            is = Utils.genGear(rarity, rand);
            if (is == null) {
                is = Utils.generateLoot(rarity, rand);
            }
        } else {
            switch (rarity) {
                default: {
                    is = ((WeightedRandomLoot)WeightedRandom.func_76271_a((Random)rand, WeightedRandomLoot.lootBagCommon)).item;
                    break;
                }
                case 1: {
                    is = ((WeightedRandomLoot)WeightedRandom.func_76271_a((Random)rand, WeightedRandomLoot.lootBagUncommon)).item;
                    break;
                }
                case 2: {
                    is = ((WeightedRandomLoot)WeightedRandom.func_76271_a((Random)rand, WeightedRandomLoot.lootBagRare)).item;
                }
            }
        }
        if (is.func_77973_b() == Items.field_151122_aG) {
            EnchantmentHelper.func_77504_a((Random)rand, (ItemStack)is, (int)((int)(5.0f + (float)rarity * 0.75f * (float)rand.nextInt(18))));
        }
        return is.func_77946_l();
    }

    private static ItemStack genGear(int rarity, Random rand) {
        Item item;
        ItemStack is = null;
        int quality = rand.nextInt(2);
        if (rand.nextFloat() < 0.2f) {
            ++quality;
        }
        if (rand.nextFloat() < 0.15f) {
            ++quality;
        }
        if (rand.nextFloat() < 0.1f) {
            ++quality;
        }
        if (rand.nextFloat() < 0.095f) {
            ++quality;
        }
        if (rand.nextFloat() < 0.095f) {
            ++quality;
        }
        if ((item = Utils.getGearItemForSlot(rand.nextInt(5), quality)) == null) {
            return null;
        }
        is = new ItemStack(item, 1, rand.nextInt(1 + item.func_77612_l() / 6));
        if (rand.nextInt(4) < rarity) {
            EnchantmentHelper.func_77504_a((Random)rand, (ItemStack)is, (int)((int)(5.0f + (float)rarity * 0.75f * (float)rand.nextInt(18))));
        }
        return is.func_77946_l();
    }

    private static Item getGearItemForSlot(int slot, int quality) {
        switch (slot) {
            case 4: {
                if (quality == 0) {
                    return Items.field_151024_Q;
                }
                if (quality == 1) {
                    return Items.field_151169_ag;
                }
                if (quality == 2) {
                    return Items.field_151020_U;
                }
                if (quality == 3) {
                    return Items.field_151028_Y;
                }
                if (quality == 4) {
                    return ConfigItems.itemHelmetThaumium;
                }
                if (quality == 5) {
                    return Items.field_151161_ac;
                }
                if (quality == 6) {
                    return ConfigItems.itemHelmetVoid;
                }
            }
            case 3: {
                if (quality == 0) {
                    return Items.field_151027_R;
                }
                if (quality == 1) {
                    return Items.field_151171_ah;
                }
                if (quality == 2) {
                    return Items.field_151023_V;
                }
                if (quality == 3) {
                    return Items.field_151030_Z;
                }
                if (quality == 4) {
                    return ConfigItems.itemChestThaumium;
                }
                if (quality == 5) {
                    return Items.field_151163_ad;
                }
                if (quality == 6) {
                    return ConfigItems.itemChestVoid;
                }
            }
            case 2: {
                if (quality == 0) {
                    return Items.field_151026_S;
                }
                if (quality == 1) {
                    return Items.field_151149_ai;
                }
                if (quality == 2) {
                    return Items.field_151022_W;
                }
                if (quality == 3) {
                    return Items.field_151165_aa;
                }
                if (quality == 4) {
                    return ConfigItems.itemLegsThaumium;
                }
                if (quality == 5) {
                    return Items.field_151173_ae;
                }
                if (quality == 6) {
                    return ConfigItems.itemLegsVoid;
                }
            }
            case 1: {
                if (quality == 0) {
                    return Items.field_151021_T;
                }
                if (quality == 1) {
                    return Items.field_151151_aj;
                }
                if (quality == 2) {
                    return Items.field_151029_X;
                }
                if (quality == 3) {
                    return Items.field_151167_ab;
                }
                if (quality == 4) {
                    return ConfigItems.itemBootsThaumium;
                }
                if (quality == 5) {
                    return Items.field_151175_af;
                }
                if (quality == 6) {
                    return ConfigItems.itemBootsVoid;
                }
            }
            case 0: {
                if (quality == 0) {
                    return Items.field_151036_c;
                }
                if (quality == 1) {
                    return Items.field_151040_l;
                }
                if (quality == 2) {
                    return Items.field_151006_E;
                }
                if (quality == 3) {
                    return Items.field_151010_B;
                }
                if (quality == 4) {
                    return ConfigItems.itemSwordThaumium;
                }
                if (quality == 5) {
                    return Items.field_151048_u;
                }
                if (quality != 6) break;
                return ConfigItems.itemSwordVoid;
            }
        }
        return null;
    }
}
