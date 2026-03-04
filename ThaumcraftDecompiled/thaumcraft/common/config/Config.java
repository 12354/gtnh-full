/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionHelper
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.ChestGenHooks
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.common.config;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.api.potions.PotionVisExhaust;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigRecipes;
import thaumcraft.common.config.MaterialAiry;
import thaumcraft.common.config.MaterialTaint;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.EntityFallingTaint;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.equipment.ItemElementalAxe;
import thaumcraft.common.lib.enchantment.EnchantmentHaste;
import thaumcraft.common.lib.enchantment.EnchantmentRepair;
import thaumcraft.common.lib.potions.PotionBlurredVision;
import thaumcraft.common.lib.potions.PotionDeathGaze;
import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
import thaumcraft.common.lib.potions.PotionSunScorned;
import thaumcraft.common.lib.potions.PotionThaumarhia;
import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
import thaumcraft.common.lib.potions.PotionWarpWard;
import thaumcraft.common.lib.utils.CropUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.biomes.BiomeGenEerie;
import thaumcraft.common.lib.world.biomes.BiomeGenEldritch;
import thaumcraft.common.lib.world.biomes.BiomeGenMagicalForest;
import thaumcraft.common.lib.world.biomes.BiomeGenTaint;
import thaumcraft.common.lib.world.biomes.BiomeHandler;

public class Config {
    public static Configuration config;
    public static final String CATEGORY_ENCH = "Enchantments";
    public static final String CATEGORY_ENTITIES = "Entities";
    public static final String CATEGORY_BIOMES = "Biomes";
    public static final String CATEGORY_RESEARCH = "Research";
    public static final String CATEGORY_GEN = "World_Generation";
    public static final String CATEGORY_REGEN = "World_Regeneration";
    public static final String CATEGORY_SPAWN = "Monster_Spawning";
    public static final String CATEGORY_RUNIC = "Runic_Shielding";
    public static int biomeTaintID;
    public static int biomeMagicalForestID;
    public static int biomeEerieID;
    public static int biomeEldritchID;
    public static int biomeTaintWeight;
    public static int biomeMagicalForestWeight;
    public static int taintSpreadRate;
    public static boolean taintFromFlux;
    public static boolean hardNode;
    public static boolean wuss;
    public static int dimensionOuterId;
    public static boolean championMobs;
    public static int shieldRecharge;
    public static int shieldWait;
    public static int shieldCost;
    public static boolean colorBlind;
    public static boolean shaders;
    public static boolean crooked;
    public static boolean showTags;
    public static boolean blueBiome;
    public static boolean allowMirrors;
    public static boolean dialBottom;
    public static int nodeRefresh;
    public static final float auraSize = 4.0f;
    public static boolean genAura;
    public static boolean genStructure;
    public static boolean genCinnibar;
    public static boolean genAmber;
    public static boolean genInfusedStone;
    public static boolean genTrees;
    public static boolean genTaint;
    public static boolean regenAura;
    public static boolean regenStructure;
    public static boolean regenCinnibar;
    public static boolean regenAmber;
    public static boolean regenInfusedStone;
    public static boolean regenTrees;
    public static boolean regenTaint;
    public static String regenKey;
    public static boolean wardedStone;
    public static boolean allowCheatSheet;
    public static boolean golemChestInteract;
    public static int nodeRarity;
    public static int specialNodeRarity;
    public static int notificationDelay;
    public static int notificationMax;
    public static boolean glowyTaint;
    public static int researchDifficulty;
    public static int aspectTotalCap;
    public static int golemDelay;
    public static int golemIgnoreDelay;
    public static int golemLinkQuality;
    public static boolean CwardedStone;
    public static boolean CallowCheatSheet;
    public static boolean CallowMirrors;
    public static boolean ChardNode;
    public static boolean Cwuss;
    public static int CresearchDifficulty;
    public static int CaspectTotalCap;
    public static boolean spawnAngryZombie;
    public static boolean spawnFireBat;
    public static boolean spawnTaintacle;
    public static boolean spawnWisp;
    public static boolean spawnTaintSpore;
    public static boolean spawnPech;
    public static boolean spawnElder;
    public static int potionVisExhaustID;
    public static int potionInfVisExhaustID;
    public static int potionBlurredID;
    public static int potionThaumarhiaID;
    public static int potionTaintPoisonID;
    public static int potionUnHungerID;
    public static int potionSunScornedID;
    public static int potionWarpWardID;
    public static int potionDeathGazeID;
    public static Enchantment enchHaste;
    public static Enchantment enchRepair;
    public static ArrayList<Aspect> aspectOrder;
    public static boolean foundCopperIngot;
    public static boolean foundTinIngot;
    public static boolean foundSilverIngot;
    public static boolean foundLeadIngot;
    public static boolean foundCopperOre;
    public static boolean foundTinOre;
    public static boolean foundSilverOre;
    public static boolean foundLeadOre;
    public static final Material airyMaterial;
    public static final Material fluxGoomaterial;
    public static final Material taintMaterial;

    public static void initialize(File file) {
        config = new Configuration(file);
        config.addCustomCategoryComment(CATEGORY_ENCH, "Custom enchantments");
        config.addCustomCategoryComment(CATEGORY_SPAWN, "Will these mobs spawn");
        config.addCustomCategoryComment(CATEGORY_RESEARCH, "Various research related things.");
        config.addCustomCategoryComment(CATEGORY_GEN, "Settings to turn certain world-gen on or off.");
        config.addCustomCategoryComment(CATEGORY_REGEN, "If a chunk is encountered that skipped TC worldgen, then the game will attempt to regenerate certain world features if they are set to true. CAUTION: Best used for worlds created before you added this mod, and only if you know what you are doing. Backups are advised.");
        config.addCustomCategoryComment(CATEGORY_BIOMES, "Biomes and effects");
        config.addCustomCategoryComment(CATEGORY_RUNIC, "Runic Shielding");
        config.load();
        Config.syncConfigurable();
        Property btcp = config.get(CATEGORY_BIOMES, "taint_biome_weight", 2);
        btcp.comment = "higher values increases number of taint biomes. If you are using biome addon mods you probably want to increase this weight quite a bit";
        biomeTaintWeight = btcp.getInt();
        Property biomeTaintProp = config.get(CATEGORY_BIOMES, "biome_taint", biomeTaintID);
        biomeTaintProp.comment = "Taint biome id";
        biomeTaintID = biomeTaintProp.getInt();
        if (BiomeGenBase.func_150565_n()[biomeTaintID] != null) {
            biomeTaintID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeTaintID);
            biomeTaintProp.set(biomeTaintID);
        }
        try {
            ThaumcraftWorldGenerator.biomeTaint = new BiomeGenTaint(biomeTaintID);
        }
        catch (Exception e) {
            Thaumcraft.log.fatal("Could not register Taint Biome");
        }
        Property mfcp = config.get(CATEGORY_BIOMES, "magical_forest_biome_weight", 5);
        mfcp.comment = "higher values increases number of magical forest biomes. If you are using biome addon mods you probably want to increase this weight quite a bit";
        biomeMagicalForestWeight = mfcp.getInt();
        Property biomeMFProp = config.get(CATEGORY_BIOMES, "biome_magical_forest", biomeMagicalForestID);
        biomeMFProp.comment = "Magical Forest biome id";
        biomeMagicalForestID = biomeMFProp.getInt();
        if (BiomeGenBase.func_150565_n()[biomeMagicalForestID] != null) {
            biomeMagicalForestID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeMagicalForestID);
            biomeMFProp.set(biomeMagicalForestID);
        }
        try {
            ThaumcraftWorldGenerator.biomeMagicalForest = new BiomeGenMagicalForest(biomeMagicalForestID);
        }
        catch (Exception e) {
            Thaumcraft.log.fatal("Could not register Magical Forest Biome");
        }
        Property biomeEerieProp = config.get(CATEGORY_BIOMES, "biome_eerie", biomeEerieID);
        biomeEerieProp.comment = "Eerie biome id";
        biomeEerieID = biomeEerieProp.getInt();
        if (BiomeGenBase.func_150565_n()[biomeEerieID] != null) {
            biomeEerieID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeEerieID);
            biomeEerieProp.set(biomeEerieID);
        }
        try {
            ThaumcraftWorldGenerator.biomeEerie = new BiomeGenEerie(biomeEerieID);
        }
        catch (Exception e) {
            Thaumcraft.log.fatal("Could not register Eerie Biome");
        }
        Property biomeEldritchProp = config.get(CATEGORY_BIOMES, "biome_eldritch", biomeEldritchID);
        biomeEldritchProp.comment = "Eldritch Lands biome id";
        biomeEldritchID = biomeEldritchProp.getInt();
        if (BiomeGenBase.func_150565_n()[biomeEldritchID] != null) {
            biomeEldritchID = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(biomeEldritchID);
            biomeEldritchProp.set(biomeEldritchID);
        }
        try {
            ThaumcraftWorldGenerator.biomeEldritchLands = new BiomeGenEldritch(biomeEldritchID);
        }
        catch (Exception e) {
            Thaumcraft.log.fatal("Could not register Eldritch Lands Biome");
        }
        Property dimEldritch = config.get(CATEGORY_BIOMES, "outer_lands_dim", dimensionOuterId);
        dimensionOuterId = dimEldritch.getInt();
        int encIdx = 150;
        Property enchHas = config.get(CATEGORY_ENCH, "ench_haste", encIdx++);
        enchHaste = new EnchantmentHaste(enchHas.getInt(), 3);
        ThaumcraftApi.enchantHaste = enchHas.getInt();
        Enchantment.addToBookList((Enchantment)enchHaste);
        Property enchRep = config.get(CATEGORY_ENCH, "ench_repair", encIdx++);
        enchRepair = new EnchantmentRepair(enchRep.getInt(), 2);
        ThaumcraftApi.enchantRepair = enchRep.getInt();
        Enchantment.addToBookList((Enchantment)enchRepair);
        config.save();
    }

    public static void save() {
        config.save();
    }

    public static void initPotions() {
        int customPotions = 8;
        int potionOffset = Potion.field_76425_a.length;
        int start = 0;
        Thaumcraft.log.info("Found potion array with a size of " + potionOffset);
        if (potionOffset < 128 - customPotions) {
            Thaumcraft.log.info("Extending Potion.potionTypes array to " + (potionOffset + customPotions));
            Potion[] potionTypes = new Potion[potionOffset + customPotions];
            System.arraycopy(Potion.field_76425_a, 0, potionTypes, 0, potionOffset);
            Utils.setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes", "field_76425_a", "a");
            start = potionOffset++ - 1;
        } else {
            start = -1;
        }
        start = Config.getNextPotionId(start);
        if (start >= 0) {
            potionTaintPoisonID = start;
            PotionFluxTaint.instance = new PotionFluxTaint(potionTaintPoisonID, true, 0x663377);
            PotionFluxTaint.init();
            Thaumcraft.log.info("Initializing PotionFluxTaint with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionVisExhaustID = start;
            PotionVisExhaust.instance = new PotionVisExhaust(potionVisExhaustID, true, 0x664477);
            PotionVisExhaust.init();
            Thaumcraft.log.info("Initializing PotionVisExhaust with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionInfVisExhaustID = start;
            PotionInfectiousVisExhaust.instance = new PotionInfectiousVisExhaust(potionInfVisExhaustID, true, 0x665577);
            PotionInfectiousVisExhaust.init();
            Thaumcraft.log.info("Initializing PotionInfectiousVisExhaust with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionUnHungerID = start;
            PotionUnnaturalHunger.instance = new PotionUnnaturalHunger(potionUnHungerID, true, 0x446633);
            PotionUnnaturalHunger.init();
            Thaumcraft.log.info("Initializing PotionUnnaturalHunger with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionWarpWardID = start;
            PotionWarpWard.instance = new PotionWarpWard(potionWarpWardID, false, 14742263);
            PotionWarpWard.init();
            Thaumcraft.log.info("Initializing PotionWarpWard with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionDeathGazeID = start;
            PotionDeathGaze.instance = new PotionDeathGaze(potionDeathGazeID, true, 0x664433);
            PotionDeathGaze.init();
            Thaumcraft.log.info("Initializing PotionDeathGaze with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionBlurredID = start;
            PotionBlurredVision.instance = new PotionBlurredVision(potionBlurredID, true, 0x808080);
            PotionBlurredVision.init();
            Thaumcraft.log.info("Initializing PotionBlurredVision with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionSunScornedID = start;
            PotionSunScorned.instance = new PotionSunScorned(potionSunScornedID, true, 16308330);
            PotionSunScorned.init();
            Thaumcraft.log.info("Initializing PotionSunScorned with id " + start);
        }
        if ((start = Config.getNextPotionId(start)) >= 0) {
            potionThaumarhiaID = start;
            PotionThaumarhia.instance = new PotionThaumarhia(potionThaumarhiaID, true, 0x664477);
            PotionThaumarhia.init();
            Thaumcraft.log.info("Initializing PotionThaumarhia with id " + start);
        }
    }

    static int getNextPotionId(int start) {
        if (Potion.field_76425_a != null && start > 0 && start < Potion.field_76425_a.length && Potion.field_76425_a[start] == null) {
            return start;
        }
        start = ++start < 128 ? Config.getNextPotionId(start) : -1;
        return start;
    }

    public static void syncConfigurable() {
        genAura = config.get(CATEGORY_GEN, "generate_aura_nodes", true).getBoolean(true);
        genStructure = config.get(CATEGORY_GEN, "generate_structures", true).getBoolean(true);
        genCinnibar = config.get(CATEGORY_GEN, "generate_cinnibar_ore", true).getBoolean(true);
        genAmber = config.get(CATEGORY_GEN, "generate_amber_ore", true).getBoolean(true);
        genInfusedStone = config.get(CATEGORY_GEN, "generate_infused_stone", true).getBoolean(true);
        genTrees = config.get(CATEGORY_GEN, "generate_trees", true).getBoolean(true);
        Property gt = config.get(CATEGORY_GEN, "generate_taint", genTaint);
        gt.comment = "Can taint biomes generate at worldgen";
        genTaint = gt.getBoolean(true);
        Property regKey = config.get(CATEGORY_REGEN, "regen_key", "DEFAULT");
        regKey.comment = "This key is used to keep track of which chunk have been generated/regenerated. Changing it will cause the regeneration code to run again, so only change it if you want it to happen. Useful to regen only one world feature at a time.";
        regenKey = regKey.getString();
        regenAura = config.get(CATEGORY_REGEN, "aura_nodes", false).getBoolean(false);
        regenStructure = config.get(CATEGORY_REGEN, "structures", false).getBoolean(false);
        regenCinnibar = config.get(CATEGORY_REGEN, "cinnibar_ore", false).getBoolean(false);
        regenAmber = config.get(CATEGORY_REGEN, "amber_ore", false).getBoolean(false);
        regenInfusedStone = config.get(CATEGORY_REGEN, "infused_stone", false).getBoolean(false);
        regenTrees = config.get(CATEGORY_REGEN, "trees", false).getBoolean(false);
        regenTaint = config.get(CATEGORY_REGEN, "taint", false).getBoolean(false);
        Property resDif = config.get(CATEGORY_RESEARCH, "research_difficulty", 0);
        resDif.comment = "0 = normal, -1 = easy (all research items are directly purchased with RP), 1 = Hard (all research items need to be solved via the research table)";
        CresearchDifficulty = researchDifficulty = resDif.getInt(8);
        Property aspTotCap = config.get(CATEGORY_RESEARCH, "aspect_total_cap", 100);
        aspTotCap.comment = "The total amount of RP you can have in your pool per aspect before the scanning soft cap kicks in.";
        CaspectTotalCap = aspectTotalCap = aspTotCap.getInt(100);
        spawnAngryZombie = config.get(CATEGORY_SPAWN, "spawn_angry_zombies", true).getBoolean(true);
        spawnFireBat = config.get(CATEGORY_SPAWN, "spawn_fire_bats", true).getBoolean(true);
        spawnWisp = config.get(CATEGORY_SPAWN, "spawn_wisps", true).getBoolean(true);
        spawnTaintacle = config.get(CATEGORY_SPAWN, "spawn_taintacles", true).getBoolean(true);
        spawnTaintSpore = config.get(CATEGORY_SPAWN, "spawn_taint_spores", true).getBoolean(true);
        spawnPech = config.get(CATEGORY_SPAWN, "spawn_pechs", true).getBoolean(true);
        spawnElder = config.get(CATEGORY_SPAWN, "spawn_eldercreatures", true).getBoolean(true);
        Property cm = config.get(CATEGORY_SPAWN, "champion_mobs", championMobs);
        cm.comment = "Setting this to false will disable spawning champion mobs. Even when false they will still have a greatly reduced chance of spawning in certain dangerous places.";
        championMobs = cm.getBoolean(true);
        Property am = config.get("general", "allow_mirrors", allowMirrors);
        am.comment = "Setting this to false will disable arcane mirror research and crafting recipes.";
        CallowMirrors = allowMirrors = am.getBoolean(true);
        Property cb = config.get("general", "color_blind", colorBlind);
        cb.comment = "Setting this to true will make certain colors higher contrast or darker to prevent them from being 'invisible' to color blind people.";
        colorBlind = cb.getBoolean(false);
        Property shad = config.get("general", "shaders", shaders);
        shad.comment = "This setting will disable certain thaumcraft shaders for those who experience FPS drops.";
        shaders = shad.getBoolean(false);
        Property ocd = config.get("general", "crooked", crooked);
        ocd.comment = "Hate crooked labels, kittens, puppies and all things awesome? If yes, set this to false.";
        crooked = ocd.getBoolean(true);
        Property hn = config.get("general", "hard_mode_nodes", hardNode);
        hn.comment = "Negative nodes like hungry, tainted or dark nodes will have additional, much nastier, effects.";
        ChardNode = hardNode = hn.getBoolean(true);
        Property wm = config.get("general", "wuss_mode", wuss);
        wm.comment = "Setting this to true disables Warp and similar mechanics. You wuss.";
        Cwuss = wuss = wm.getBoolean(false);
        Property dbp = config.get("general", "wand_dial_bottom", dialBottom);
        dbp.comment = "Set to true to have the wand dial display in the bottom left instead of the top left.";
        dialBottom = dbp.getBoolean(false);
        Property golDel = config.get("general", "golem_delay", golemDelay);
        golDel.comment = "How many ticks a golem waits between checking for tasks. Setting it higher will save server ticks, but will make the golems slower to react.";
        golemDelay = golDel.getInt();
        if (golemDelay < 1) {
            golemDelay = 1;
        }
        Property golIgDel = config.get("general", "golem_ignore_delay", golemIgnoreDelay);
        golIgDel.comment = "How many milliseconds a golem will ignore an item after it has failed to find a destination or use for it. Min value 1000";
        golemIgnoreDelay = golIgDel.getInt();
        if (golemIgnoreDelay < 1000) {
            golemIgnoreDelay = 1000;
        }
        Property golLinkQual = config.get("general", "golem_link_quality", golemLinkQuality);
        golLinkQual.comment = "The fx quality of the line connecting golems to marked blocks. Setting this below 4 deactives the effect entirely.";
        golemLinkQuality = golLinkQual.getInt();
        if (golemLinkQuality < 4) {
            golemLinkQuality = 0;
        }
        Property notDel = config.get("general", "notification_delay", notificationDelay);
        notDel.comment = "Determines how fast notifications scroll downwards.";
        notificationDelay = notDel.getInt();
        Property notMax = config.get("general", "notification_max", notificationMax);
        notMax.comment = "The maximum amount of notifications that are displayed onscreen.";
        notificationMax = notMax.getInt();
        Property nodRare = config.get("general", "node_rarity", nodeRarity);
        nodRare.comment = "How rare nodes are in the world. The number means there will be (on average) one node per N chunks.";
        nodeRarity = nodRare.getInt();
        Property nodSpec = config.get("general", "special_node_rarity", specialNodeRarity);
        nodSpec.comment = "The chance of a node being special (pure, dark, unstable, etc.). The number means roughly 1 in N nodes will be special, so setting the number to 5 will mean 1 in 5 nodes may be special.";
        specialNodeRarity = nodSpec.getInt();
        if (specialNodeRarity < 3) {
            specialNodeRarity = 3;
        }
        Property showtags = config.get("general", "display_aspects", false);
        showtags.comment = "Item aspects are hidden by default and pressing shift reveals them. Changing this setting to 'true' will reverse this behaviour and always display aspects unless shift is pressed.";
        showTags = showtags.getBoolean(false);
        Property cheatsheet = config.get("general", "allow_cheat_sheet", false);
        cheatsheet.comment = "Enables a version of the Thauminomicon in creative mode that grants you all the research when you first use it.";
        CallowCheatSheet = allowCheatSheet = cheatsheet.getBoolean(false);
        Property wardstone = config.get("general", "allow_warded_stone", true);
        wardstone.comment = "If set to false, warded stone, doors and glass will just be cosmetic in nature and not have its hardened properties (everyone will be able to break it with equal ease).";
        CwardedStone = wardedStone = wardstone.getBoolean(false);
        Property wiz_vil = config.get("general", "thaumcraft_villager_id", ConfigEntities.entWizardId);
        wiz_vil.comment = "Thaumcraft wizard villager id";
        ConfigEntities.entWizardId = wiz_vil.getInt();
        Property bank_vil = config.get("general", "thaumcraft_banker_id", ConfigEntities.entBankerId);
        bank_vil.comment = "Thaumcraft banker villager id";
        ConfigEntities.entBankerId = bank_vil.getInt();
        Property gci = config.get("general", "golem_chest_interact", true);
        gci.comment = "If set to true golems will attempt to play the chest opening animations and sounds whenever they interact with them.";
        golemChestInteract = gci.getBoolean(false);
        Property phblacklist = config.get("general", "portablehole_blacklist", "iron_door");
        phblacklist.comment = "This is a comma-delimited list of any block names the portable hole is not allowed to pass through.";
        String[] phbl = phblacklist.getString().split(",");
        if (phbl != null && phbl.length > 0) {
            for (String s : phbl) {
                try {
                    Block b = Block.func_149684_b((String)s);
                    if (b == null || b == Blocks.field_150350_a) continue;
                    ThaumcraftApi.portableHoleBlackList.add(b);
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
        }
        Property blueb = config.get("general", "blue_magical_forest", blueBiome);
        blueb.comment = "Set this to true to get the old blue magical forest back.";
        blueBiome = blueb.getBoolean(false);
        Property bff = config.get("general", "biome_taint_from_flux", taintFromFlux);
        bff.comment = "Can Taint be caused by flux effects.";
        taintFromFlux = bff.getBoolean(true);
        Property ts = config.get("general", "biome_taint_spread", taintSpreadRate);
        ts.comment = "The chance per block update (1 in n) of the Taint biome spreading. Setting it to 0 prevents the spread of Taint biomes.";
        taintSpreadRate = ts.getInt();
        Property glowT = config.get("general", "glowing_taint", glowyTaint);
        glowT.comment = "Setting this to false will remove the glowing purple nodules from taint fibres. This might prevent crashes some people experience and improve performance.";
        glowyTaint = glowT.getBoolean(true);
        Property rss = config.get(CATEGORY_RUNIC, "runic_recharge_speed", shieldRecharge);
        rss.comment = "How many milliseconds pass between runic shielding recharge ticks. Lower values equals faster recharge. Minimum of 500.";
        shieldRecharge = Math.max(500, rss.getInt());
        Property rsd = config.get(CATEGORY_RUNIC, "runic_recharge_delay", shieldWait);
        rsd.comment = "How many game ticks pass after a shield has been reduced to zero before it can start recharging again. Minimum of 0.";
        shieldWait = Math.max(0, rsd.getInt());
        Property rsc = config.get(CATEGORY_RUNIC, "runic_cost", shieldCost);
        rsc.comment = "How much aer and terra centi-vis (0.01 vis) it costs to reacharge a single unit of shielding. Minimum of 0.";
        shieldCost = Math.max(0, rsc.getInt());
    }

    public static void initLoot() {
        Random rand = new Random(System.currentTimeMillis());
        ItemStack amulet = new ItemStack(ConfigItems.itemAmuletVis, 1, 0);
        ItemAmuletVis ai = (ItemAmuletVis)amulet.func_77973_b();
        for (Aspect a : Aspect.getPrimalAspects()) {
            ai.addVis(amulet, a, rand.nextInt(5), true);
        }
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 1, 18), 2500, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 2, 18), 2250, 1);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 3, 18), 2000, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemEldritchObject, 1, 3), 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151156_bN), 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 10, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151045_i), 50, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 15, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151166_bC), 75, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151043_k), 100, 0, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151079_bi), 100, 0, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemResource, 1, 9), 25, 0, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), 10, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), 10, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), 10, 0);
        for (int a = 3; a <= 8; ++a) {
            ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, a), 5, 1);
            ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemBaubleBlanks, 1, a), 7, 2);
        }
        ThaumcraftApi.addLootBagItem(amulet.func_77946_l(), 6, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(ConfigItems.itemRingRunic, 1, 0), 5, 1, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 5, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 10, 1);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151062_by), 20, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 1, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 2, 1);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 1), 3, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 3, 0);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 6, 1);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151153_ao, 1, 0), 9, 2);
        ThaumcraftApi.addLootBagItem(new ItemStack(Items.field_151122_aG), 10, 0, 1, 2);
        for (int i = 0; i <= 15; ++i) {
            for (int j = 0; j <= 1; ++j) {
                int k = j == 0 ? i | 0x2000 : i | 0x4000;
                for (int l = 0; l <= 2; ++l) {
                    List list1;
                    int i1 = k;
                    if (l != 0) {
                        if (l == 1) {
                            i1 = k | 0x20;
                        } else if (l == 2) {
                            i1 = k | 0x40;
                        }
                    }
                    if ((list1 = PotionHelper.func_77917_b((int)i1, (boolean)false)) == null || list1.isEmpty()) continue;
                    ThaumcraftApi.addLootBagItem(new ItemStack((Item)Items.field_151068_bn, 1, i1), l + 1, 0, 1, 2);
                }
            }
        }
        ItemStack[] commonLoot = new ItemStack[]{new ItemStack(ConfigItems.itemLootbag, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 6)};
        ItemStack[] uncommonLoot = new ItemStack[]{new ItemStack(ConfigItems.itemLootbag, 1, 1), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 9)};
        ItemStack[] rareLoot = new ItemStack[]{new ItemStack(ConfigItems.itemLootbag, 1, 2), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack(ConfigItems.itemSwordThaumium, 1, 0), new ItemStack(ConfigItems.itemPickThaumium, 1, 0), new ItemStack(ConfigItems.itemAxeThaumium, 1, 0), new ItemStack(ConfigItems.itemHoeThaumium, 1, 0), new ItemStack(ConfigItems.itemRingRunic, 1, 0), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 4), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 5), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 6), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 7), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 8), amulet};
        for (ItemStack is : commonLoot) {
            ChestGenHooks.addItem((String)"dungeonChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 5));
            ChestGenHooks.addItem((String)"pyramidJungleChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 5));
            ChestGenHooks.addItem((String)"pyramidDesertyChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 5));
            ChestGenHooks.addItem((String)"mineshaftCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 4));
            ChestGenHooks.addItem((String)"strongholdCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 4));
            ChestGenHooks.addItem((String)"strongholdCrossing", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 4));
            ChestGenHooks.addItem((String)"strongholdLibrary", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 3, 4));
        }
        for (ItemStack is : uncommonLoot) {
            ChestGenHooks.addItem((String)"dungeonChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 4));
            ChestGenHooks.addItem((String)"pyramidJungleChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 4));
            ChestGenHooks.addItem((String)"pyramidDesertyChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 4));
            ChestGenHooks.addItem((String)"mineshaftCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 3));
            ChestGenHooks.addItem((String)"strongholdCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 3));
            ChestGenHooks.addItem((String)"strongholdCrossing", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 3));
            ChestGenHooks.addItem((String)"strongholdLibrary", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 2, 3));
        }
        ChestGenHooks.addItem((String)"strongholdLibrary", (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ConfigItems.itemResource, 1, 9), 3, 6, 20));
        for (ItemStack is : rareLoot) {
            ChestGenHooks.addItem((String)"dungeonChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"pyramidJungleChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"pyramidDesertyChest", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"mineshaftCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"strongholdCorridor", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"strongholdCrossing", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
            ChestGenHooks.addItem((String)"strongholdLibrary", (WeightedRandomChestContent)new WeightedRandomChestContent(is, 1, 1, 1));
        }
        ChestGenHooks.addItem((String)"villageBlacksmith", (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ConfigItems.itemResource, 1, 2), 1, 3, 10));
    }

    public static void initModCompatibility() {
        String[] ores;
        for (String ore : ores = OreDictionary.getOreNames()) {
            if (ore == null) continue;
            if (ore.equals("oreCopper") && OreDictionary.getOres((String)ore).size() > 0) {
                foundCopperOre = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 17), 1.0f);
                }
            }
            if (ore.equals("oreTin") && OreDictionary.getOres((String)ore).size() > 0) {
                foundTinOre = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 18), 1.0f);
                }
            }
            if (ore.equals("oreSilver") && OreDictionary.getOres((String)ore).size() > 0) {
                foundSilverOre = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 19), 1.0f);
                }
            }
            if (ore.equals("oreLead") && OreDictionary.getOres((String)ore).size() > 0) {
                foundLeadOre = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    Utils.addSpecialMiningResult(is, new ItemStack(ConfigItems.itemNugget, 1, 20), 1.0f);
                }
            }
            if (ore.equals("ingotCopper")) {
                boolean first = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    if (is.field_77994_a > 1) {
                        is.field_77994_a = 1;
                    }
                    foundCopperIngot = true;
                    CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 1), new Object[]{"#", Character.valueOf('#'), is});
                    if (!first) continue;
                    first = false;
                    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 17), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0f);
                    ConfigRecipes.oreDictRecipe(is, new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 1)});
                }
                continue;
            }
            if (ore.equals("ingotTin")) {
                boolean first = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    if (is.field_77994_a > 1) {
                        is.field_77994_a = 1;
                    }
                    foundTinIngot = true;
                    CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 2), new Object[]{"#", Character.valueOf('#'), is});
                    if (!first) continue;
                    first = false;
                    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 18), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0f);
                    ConfigRecipes.oreDictRecipe(is, new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 2)});
                }
                continue;
            }
            if (ore.equals("ingotSilver")) {
                boolean first = true;
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    if (is.field_77994_a > 1) {
                        is.field_77994_a = 1;
                    }
                    foundSilverIngot = true;
                    CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 3), new Object[]{"#", Character.valueOf('#'), is});
                    if (!first) continue;
                    first = false;
                    FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 19), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0f);
                    ConfigRecipes.oreDictRecipe(is, new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 3)});
                }
                continue;
            }
            if (ore.equals("oreUranium") || ore.equals("itemDropUranium") || ore.equals("ingotUranium")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.POISON, 2).add(Aspect.ENERGY, 2));
                }
                continue;
            }
            if (ore.equals("ingotBrass") || ore.equals("ingotBronze")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.TOOL, 1));
                }
                continue;
            }
            if (ore.equals("dustBrass") || ore.equals("dustBronze")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.TOOL, 1));
                }
                continue;
            }
            if (ore.equals("gemRuby") || ore.equals("gemGreenSapphire") || ore.equals("gemSapphire")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.GREED, 2));
                }
                continue;
            }
            if (ore.equals("woodRubber")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.TREE, 3).add(Aspect.TOOL, 1));
                }
                continue;
            }
            if (ore.equals("itemRubber")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.MOTION, 2).add(Aspect.TOOL, 2));
                }
                continue;
            }
            if (ore.equals("ingotSteel")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.METAL, 3).add(Aspect.ORDER, 1));
                }
                continue;
            }
            if (ore.equals("crystalQuartz")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ThaumcraftApi.registerObjectTag(is, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
                }
                continue;
            }
            if (ore.equals("woodLog")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ItemElementalAxe.oreDictLogs.add(Arrays.asList(Item.func_150891_b((Item)is.func_77973_b()), is.func_77960_j()));
                }
                continue;
            }
            if (!ore.equals("ingotLead")) continue;
            boolean first = true;
            for (ItemStack is : OreDictionary.getOres((String)ore)) {
                if (is.field_77994_a > 1) {
                    is.field_77994_a = 1;
                }
                foundLeadIngot = true;
                CraftingManager.func_77594_a().func_92103_a(new ItemStack(ConfigItems.itemNugget, 9, 4), new Object[]{"#", Character.valueOf('#'), is});
                if (!first) continue;
                first = false;
                FurnaceRecipes.func_77602_a().func_151394_a(new ItemStack(ConfigItems.itemNugget, 1, 20), new ItemStack(is.func_77973_b(), 2, is.func_77960_j()), 1.0f);
                ConfigRecipes.oreDictRecipe(is, new Object[]{"###", "###", "###", Character.valueOf('#'), new ItemStack(ConfigItems.itemNugget, 1, 4)});
            }
        }
        Thaumcraft.log.info("Adding entities to MFR safari net blacklist.");
        Config.registerSafariNetBlacklist(EntityGolemBase.class);
        Config.registerSafariNetBlacklist(EntityTravelingTrunk.class);
        Config.registerSafariNetBlacklist(EntityAspectOrb.class);
        Config.registerSafariNetBlacklist(EntityFallingTaint.class);
        Config.registerSafariNetBlacklist(EntityWisp.class);
        Config.registerSafariNetBlacklist(EntityPech.class);
        Config.registerSafariNetBlacklist(EntityTaintSpore.class);
        Config.registerSafariNetBlacklist(EntityEldritchGuardian.class);
        Config.registerSafariNetBlacklist(EntityEldritchWarden.class);
        Config.registerSafariNetBlacklist(EntityEldritchGolem.class);
        Config.registerSafariNetBlacklist(EntityCultistCleric.class);
        Config.registerSafariNetBlacklist(EntityCultistKnight.class);
        Config.registerSafariNetBlacklist(EntityCultistLeader.class);
        Config.registerSafariNetBlacklist(EntityCultistPortal.class);
        Config.registerSafariNetBlacklist(EntityEldritchCrab.class);
        Config.registerSafariNetBlacklist(EntityInhabitedZombie.class);
    }

    public static void registerSafariNetBlacklist(Class<?> blacklistedEntity) {
        try {
            Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
            if (registry != null) {
                Method reg = registry.getMethod("registerSafariNetBlacklist", Class.class);
                reg.invoke(registry, blacklistedEntity);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void registerBiomes() {
        BiomeDictionary.registerBiomeType((BiomeGenBase)ThaumcraftWorldGenerator.biomeTaint, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WASTELAND});
        BiomeDictionary.registerBiomeType((BiomeGenBase)ThaumcraftWorldGenerator.biomeEerie, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY});
        BiomeDictionary.registerBiomeType((BiomeGenBase)ThaumcraftWorldGenerator.biomeEldritchLands, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.END});
        BiomeDictionary.registerBiomeType((BiomeGenBase)ThaumcraftWorldGenerator.biomeMagicalForest, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST});
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WATER, 100, Aspect.WATER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.OCEAN, 120, Aspect.WATER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.RIVER, 100, Aspect.WATER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WET, 80, Aspect.WATER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.HOT, 100, Aspect.FIRE, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DESERT, 100, Aspect.FIRE, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.NETHER, 120, Aspect.FIRE, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MESA, 80, Aspect.FIRE, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DENSE, 100, Aspect.ORDER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SNOWY, 80, Aspect.ORDER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.COLD, 80, Aspect.ORDER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.FROZEN, 100, Aspect.ORDER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MUSHROOM, 140, Aspect.ORDER, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.CONIFEROUS, 100, Aspect.EARTH, true, 0.2f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.FOREST, 120, Aspect.EARTH, true, 1.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SANDY, 80, Aspect.EARTH, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.BEACH, 80, Aspect.EARTH, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SAVANNA, 80, Aspect.AIR, true, 0.2f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MOUNTAIN, 100, Aspect.AIR, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.HILLS, 120, Aspect.AIR, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.PLAINS, 80, Aspect.AIR, true, 0.2f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DRY, 80, Aspect.ENTROPY, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SPARSE, 80, Aspect.ENTROPY, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SWAMP, 120, Aspect.ENTROPY, true, 0.2f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.WASTELAND, 80, Aspect.ENTROPY, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.JUNGLE, 100, Aspect.PLANT, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.LUSH, 100, Aspect.PLANT, true, 0.5f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.MAGICAL, 100, null, true, 1.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.END, 80, Aspect.VOID, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.SPOOKY, 80, Aspect.SOUL, false, 0.0f);
        BiomeHandler.registerBiomeInfo(BiomeDictionary.Type.DEAD, 50, Aspect.DEATH, false, 0.0f);
    }

    public static void initMisc() {
        CropUtils.addStandardCrop(new ItemStack(Blocks.field_150440_ba), Short.MAX_VALUE);
        CropUtils.addStandardCrop(new ItemStack(Blocks.field_150423_aK), Short.MAX_VALUE);
        CropUtils.addStandardCrop(new ItemStack(ConfigBlocks.blockManaPod), 7);
        CropUtils.addStackedCrop(Blocks.field_150436_aH, Short.MAX_VALUE);
        CropUtils.addStackedCrop(Blocks.field_150434_aF, Short.MAX_VALUE);
        Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150366_p), new ItemStack(ConfigItems.itemNugget, 1, 16), 1.0f);
        Utils.addSpecialMiningResult(new ItemStack(Blocks.field_150352_o), new ItemStack(ConfigItems.itemNugget, 1, 31), 0.9f);
        Utils.addSpecialMiningResult(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0), new ItemStack(ConfigItems.itemNugget, 1, 21), 0.9f);
        Collection<Aspect> pa = Aspect.aspects.values();
        for (Aspect aspect : pa) {
            aspectOrder.add(aspect);
        }
    }

    static {
        biomeTaintID = 192;
        biomeMagicalForestID = 193;
        biomeEerieID = 194;
        biomeEldritchID = 195;
        biomeTaintWeight = 2;
        biomeMagicalForestWeight = 5;
        taintSpreadRate = 200;
        taintFromFlux = true;
        hardNode = true;
        wuss = false;
        dimensionOuterId = -42;
        championMobs = true;
        shieldRecharge = 2000;
        shieldWait = 80;
        shieldCost = 50;
        colorBlind = false;
        shaders = true;
        crooked = true;
        showTags = false;
        blueBiome = false;
        allowMirrors = true;
        dialBottom = false;
        nodeRefresh = 10;
        genAura = true;
        genStructure = true;
        genCinnibar = true;
        genAmber = true;
        genInfusedStone = true;
        genTrees = true;
        genTaint = true;
        regenAura = false;
        regenStructure = false;
        regenCinnibar = false;
        regenAmber = false;
        regenInfusedStone = false;
        regenTrees = false;
        regenTaint = false;
        regenKey = "DEFAULT";
        wardedStone = true;
        allowCheatSheet = true;
        golemChestInteract = true;
        nodeRarity = 36;
        specialNodeRarity = 18;
        notificationDelay = 5000;
        notificationMax = 15;
        glowyTaint = true;
        researchDifficulty = 0;
        aspectTotalCap = 100;
        golemDelay = 5;
        golemIgnoreDelay = 10000;
        golemLinkQuality = 16;
        CwardedStone = true;
        CallowCheatSheet = true;
        CallowMirrors = true;
        ChardNode = true;
        Cwuss = false;
        CresearchDifficulty = 0;
        CaspectTotalCap = 100;
        spawnAngryZombie = true;
        spawnFireBat = true;
        spawnTaintacle = true;
        spawnWisp = true;
        spawnTaintSpore = true;
        spawnPech = true;
        spawnElder = true;
        potionVisExhaustID = 18;
        potionInfVisExhaustID = 18;
        potionBlurredID = 18;
        potionThaumarhiaID = 18;
        potionTaintPoisonID = 19;
        potionUnHungerID = 17;
        potionSunScornedID = 17;
        potionWarpWardID = 23;
        potionDeathGazeID = 17;
        enchHaste = null;
        enchRepair = null;
        aspectOrder = new ArrayList();
        foundCopperIngot = false;
        foundTinIngot = false;
        foundSilverIngot = false;
        foundLeadIngot = false;
        foundCopperOre = false;
        foundTinOre = false;
        foundSilverOre = false;
        foundLeadOre = false;
        airyMaterial = new MaterialAiry(MapColor.field_151660_b);
        fluxGoomaterial = new MaterialTaint(MapColor.field_151661_c).func_76219_n();
        taintMaterial = new MaterialTaint(MapColor.field_151661_c);
    }
}
