/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.internal.DummyInternalMethodHandler;
import thaumcraft.api.internal.IInternalMethodHandler;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ThaumcraftApi {
    public static Item.ToolMaterial toolMatThaumium = EnumHelper.addToolMaterial((String)"THAUMIUM", (int)3, (int)400, (float)7.0f, (float)2.0f, (int)22);
    public static Item.ToolMaterial toolMatVoid = EnumHelper.addToolMaterial((String)"VOID", (int)4, (int)150, (float)8.0f, (float)3.0f, (int)10);
    public static Item.ToolMaterial toolMatElemental = EnumHelper.addToolMaterial((String)"THAUMIUM_ELEMENTAL", (int)3, (int)1500, (float)10.0f, (float)3.0f, (int)18);
    public static ItemArmor.ArmorMaterial armorMatThaumium = EnumHelper.addArmorMaterial((String)"THAUMIUM", (int)25, (int[])new int[]{2, 6, 5, 2}, (int)25);
    public static ItemArmor.ArmorMaterial armorMatSpecial = EnumHelper.addArmorMaterial((String)"SPECIAL", (int)25, (int[])new int[]{1, 3, 2, 1}, (int)25);
    public static ItemArmor.ArmorMaterial armorMatThaumiumFortress = EnumHelper.addArmorMaterial((String)"FORTRESS", (int)40, (int[])new int[]{3, 7, 6, 3}, (int)25);
    public static ItemArmor.ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial((String)"VOID", (int)10, (int[])new int[]{3, 7, 6, 3}, (int)10);
    public static ItemArmor.ArmorMaterial armorMatVoidFortress = EnumHelper.addArmorMaterial((String)"VOIDFORTRESS", (int)18, (int[])new int[]{4, 8, 7, 4}, (int)10);
    public static int enchantFrugal;
    public static int enchantPotency;
    public static int enchantWandFortune;
    public static int enchantHaste;
    public static int enchantRepair;
    public static ArrayList<Block> portableHoleBlackList;
    public static IInternalMethodHandler internalMethods;
    public static ArrayList<IScanEventHandler> scanEventhandlers;
    public static ArrayList<EntityTags> scanEntities;
    private static ArrayList craftingRecipes;
    private static HashMap<Object, ItemStack> smeltingBonus;
    private static HashMap<int[], Object[]> keyCache;
    public static ConcurrentHashMap<List, AspectList> objectTags;
    public static ConcurrentHashMap<List, int[]> groupedObjectTags;
    private static HashMap<Object, Integer> warpMap;

    public static void registerScanEventhandler(IScanEventHandler scanEventHandler) {
        scanEventhandlers.add(scanEventHandler);
    }

    public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT ... nbt) {
        scanEntities.add(new EntityTags(entityName, aspects, nbt));
    }

    public static void addSmeltingBonus(ItemStack in, ItemStack out) {
        smeltingBonus.put(Arrays.asList(in.func_77973_b(), in.func_77960_j()), new ItemStack(out.func_77973_b(), 0, out.func_77960_j()));
    }

    public static void addSmeltingBonus(String in, ItemStack out) {
        smeltingBonus.put(in, new ItemStack(out.func_77973_b(), 0, out.func_77960_j()));
    }

    public static ItemStack getSmeltingBonus(ItemStack in) {
        ItemStack out = smeltingBonus.get(Arrays.asList(in.func_77973_b(), in.func_77960_j()));
        if (out == null) {
            out = smeltingBonus.get(Arrays.asList(in.func_77973_b(), Short.MAX_VALUE));
        }
        if (out == null) {
            String od = OreDictionary.getOreName((int)OreDictionary.getOreID((ItemStack)in));
            out = smeltingBonus.get(od);
        }
        return out;
    }

    public static List getCraftingRecipes() {
        return craftingRecipes;
    }

    public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe) {
        ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
        craftingRecipes.add(r);
        return r;
    }

    public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object ... recipe) {
        ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
        craftingRecipes.add(r);
        return r;
    }

    public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, ItemStack input, ItemStack[] recipe) {
        if (!(result instanceof ItemStack) && !(result instanceof Object[])) {
            return null;
        }
        InfusionRecipe r = new InfusionRecipe(research, result, instability, aspects, input, recipe);
        craftingRecipes.add(r);
        return r;
    }

    public static InfusionEnchantmentRecipe addInfusionEnchantmentRecipe(String research, Enchantment enchantment, int instability, AspectList aspects, ItemStack[] recipe) {
        InfusionEnchantmentRecipe r = new InfusionEnchantmentRecipe(research, enchantment, instability, aspects, recipe);
        craftingRecipes.add(r);
        return r;
    }

    public static InfusionRecipe getInfusionRecipe(ItemStack res) {
        for (Object r : ThaumcraftApi.getCraftingRecipes()) {
            if (!(r instanceof InfusionRecipe) || !(((InfusionRecipe)r).getRecipeOutput() instanceof ItemStack) || !((ItemStack)((InfusionRecipe)r).getRecipeOutput()).func_77969_a(res)) continue;
            return (InfusionRecipe)r;
        }
        return null;
    }

    public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags) {
        CrucibleRecipe rc = new CrucibleRecipe(key, result, catalyst, tags);
        ThaumcraftApi.getCraftingRecipes().add(rc);
        return rc;
    }

    public static CrucibleRecipe getCrucibleRecipe(ItemStack stack) {
        for (Object r : ThaumcraftApi.getCraftingRecipes()) {
            if (!(r instanceof CrucibleRecipe) || !((CrucibleRecipe)r).getRecipeOutput().func_77969_a(stack)) continue;
            return (CrucibleRecipe)r;
        }
        return null;
    }

    public static CrucibleRecipe getCrucibleRecipeFromHash(int hash) {
        for (Object r : ThaumcraftApi.getCraftingRecipes()) {
            if (!(r instanceof CrucibleRecipe) || ((CrucibleRecipe)r).hash != hash) continue;
            return (CrucibleRecipe)r;
        }
        return null;
    }

    public static Object[] getCraftingRecipeKey(EntityPlayer player, ItemStack stack) {
        int[] key = new int[]{Item.func_150891_b((Item)stack.func_77973_b()), stack.func_77960_j()};
        if (keyCache.containsKey(key)) {
            if (keyCache.get(key) == null) {
                return null;
            }
            if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), (String)keyCache.get(key)[0])) {
                return keyCache.get(key);
            }
            return null;
        }
        for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values()) {
            for (ResearchItem ri : rcl.research.values()) {
                if (ri.getPages() == null) continue;
                for (int a = 0; a < ri.getPages().length; ++a) {
                    ResearchPage page = ri.getPages()[a];
                    if (page.recipe != null && page.recipe instanceof CrucibleRecipe[]) {
                        CrucibleRecipe[] crs;
                        for (CrucibleRecipe cr : crs = (CrucibleRecipe[])page.recipe) {
                            if (!cr.getRecipeOutput().func_77969_a(stack)) continue;
                            keyCache.put(key, new Object[]{ri.key, a});
                            if (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), ri.key)) continue;
                            return new Object[]{ri.key, a};
                        }
                        continue;
                    }
                    if (page.recipeOutput == null || stack == null || !page.recipeOutput.func_77969_a(stack)) continue;
                    keyCache.put(key, new Object[]{ri.key, a});
                    if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), ri.key)) {
                        return new Object[]{ri.key, a};
                    }
                    return null;
                }
            }
        }
        keyCache.put(key, null);
        return null;
    }

    public static boolean exists(Item item, int meta) {
        AspectList tmp = objectTags.get(Arrays.asList(item, meta));
        if (tmp == null) {
            tmp = objectTags.get(Arrays.asList(item, Short.MAX_VALUE));
            if (meta == Short.MAX_VALUE && tmp == null) {
                int index = 0;
                do {
                    tmp = objectTags.get(Arrays.asList(item, index));
                } while (++index < 16 && tmp == null);
            }
            if (tmp == null) {
                return false;
            }
        }
        return true;
    }

    public static void registerObjectTag(ItemStack item, AspectList aspects) {
        if (aspects == null) {
            aspects = new AspectList();
        }
        try {
            objectTags.put(Arrays.asList(item.func_77973_b(), item.func_77960_j()), aspects);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects) {
        if (aspects == null) {
            aspects = new AspectList();
        }
        try {
            objectTags.put(Arrays.asList(item.func_77973_b(), meta[0]), aspects);
            for (int m : meta) {
                groupedObjectTags.put(Arrays.asList(item.func_77973_b(), m), meta);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void registerObjectTag(String oreDict, AspectList aspects) {
        ArrayList ores;
        if (aspects == null) {
            aspects = new AspectList();
        }
        if ((ores = OreDictionary.getOres((String)oreDict)) != null && ores.size() > 0) {
            for (ItemStack ore : ores) {
                try {
                    objectTags.put(Arrays.asList(ore.func_77973_b(), ore.func_77960_j()), aspects);
                }
                catch (Exception e) {}
            }
        }
    }

    public static void registerComplexObjectTag(ItemStack item, AspectList aspects) {
        if (!ThaumcraftApi.exists(item.func_77973_b(), item.func_77960_j())) {
            AspectList tmp = ThaumcraftApiHelper.generateTags(item.func_77973_b(), item.func_77960_j());
            if (tmp != null && tmp.size() > 0) {
                for (Aspect tag : tmp.getAspects()) {
                    aspects.add(tag, tmp.getAmount(tag));
                }
            }
            ThaumcraftApi.registerObjectTag(item, aspects);
        } else {
            AspectList tmp = ThaumcraftApiHelper.getObjectAspects(item);
            for (Aspect tag : aspects.getAspects()) {
                tmp.merge(tag, tmp.getAmount(tag));
            }
            ThaumcraftApi.registerObjectTag(item, tmp);
        }
    }

    public static void addWarpToItem(ItemStack craftresult, int amount) {
        warpMap.put(Arrays.asList(craftresult.func_77973_b(), craftresult.func_77960_j()), amount);
    }

    public static void addWarpToResearch(String research, int amount) {
        warpMap.put(research, amount);
    }

    public static int getWarp(Object in) {
        if (in == null) {
            return 0;
        }
        if (in instanceof ItemStack && warpMap.containsKey(Arrays.asList(((ItemStack)in).func_77973_b(), ((ItemStack)in).func_77960_j()))) {
            return warpMap.get(Arrays.asList(((ItemStack)in).func_77973_b(), ((ItemStack)in).func_77960_j()));
        }
        if (in instanceof String && warpMap.containsKey((String)in)) {
            return warpMap.get((String)in);
        }
        return 0;
    }

    public static void addLootBagItem(ItemStack item, int weight, int ... bagTypes) {
        if (bagTypes == null || bagTypes.length == 0) {
            WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
        } else {
            block5: for (int rarity : bagTypes) {
                switch (rarity) {
                    case 0: {
                        WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
                        continue block5;
                    }
                    case 1: {
                        WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight));
                        continue block5;
                    }
                    case 2: {
                        WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
                    }
                }
            }
        }
    }

    static {
        portableHoleBlackList = new ArrayList();
        internalMethods = new DummyInternalMethodHandler();
        scanEventhandlers = new ArrayList();
        scanEntities = new ArrayList();
        craftingRecipes = new ArrayList();
        smeltingBonus = new HashMap();
        keyCache = new HashMap();
        objectTags = new ConcurrentHashMap();
        groupedObjectTags = new ConcurrentHashMap();
        warpMap = new HashMap();
    }

    public static class EntityTags {
        public String entityName;
        public EntityTagsNBT[] nbts;
        public AspectList aspects;

        public EntityTags(String entityName, AspectList aspects, EntityTagsNBT ... nbts) {
            this.entityName = entityName;
            this.nbts = nbts;
            this.aspects = aspects;
        }
    }

    public static class EntityTagsNBT {
        public String name;
        public Object value;

        public EntityTagsNBT(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }
}
