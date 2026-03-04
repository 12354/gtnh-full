# GTNH Complete Dungeon Loot Guide

Comprehensive analysis of ALL dungeon loot systems across every mod in GregTech New Horizons, compiled from source code analysis of the full GTNH repository.

## Table of Contents

1. [Overview](#overview)
2. [Vanilla Structure Chests (ChestGenHooks)](#1-vanilla-structure-chests-via-chestgenhooks)
3. [Twilight Forest Structures](#2-twilight-forest-structures-tftreasuretable)
4. [Roguelike Dungeons](#3-roguelike-dungeons)
5. [Galacticraft / Amunra Space Dungeons](#4-galacticraft--amunra-space-dungeons)
6. [Hardcore Ender Expansion (End Structures)](#5-hardcore-ender-expansion-end-structures)
7. [Other Loot Systems](#6-other-loot-systems)
8. [Best Sources for Rare Items](#best-sources-for-rare-items)

---

## Overview

GTNH has **6 major loot systems** and **15+ mods** that contribute items to dungeon chests:

| System | Mods Contributing |
|---|---|
| ChestGenHooks (Forge) | GT5, Thaumcraft, Botania, HEE, EnderIO, EMT, AE2, OMT, AdventureBackpack, IguanaTweaks, Forestry, MagicBees, Railcraft |
| TFTreasure | Twilight Forest (heavily modified by GTNH) |
| Roguelike Dungeons | Own weighted loot system with 5 difficulty levels |
| Galacticraft/Amunra | Uses ChestGenHooks.DUNGEON_CHEST + custom treasure chests |
| HEE WeightedLootList | Custom End dimension structure loot |
| EnhancedLootBags | XML-based loot bag system (58+ bag types) |

---

## 1. Vanilla Structure Chests (via ChestGenHooks)

The Forge `ChestGenHooks` system is the primary way mods inject items into vanilla Minecraft structure chests. There are **10 standard chest categories** plus several mod-added custom categories.

### Standard Chest Categories

| Category | Structure |
|---|---|
| `dungeonChest` | Vanilla mob spawner dungeons |
| `bonusChest` | World spawn bonus chest |
| `mineshaftCorridor` | Abandoned mineshafts |
| `strongholdCorridor` | Stronghold hallways |
| `strongholdCrossing` | Stronghold intersections |
| `strongholdLibrary` | Stronghold libraries |
| `pyramidDesertChest` | Desert temples |
| `pyramidJungleChest` | Jungle temples |
| `pyramidJungleDispenser` | Jungle temple trap dispensers |
| `villageBlacksmith` | Village blacksmith chests |

### Custom Chest Categories (Mod-Added)

| Category | Mod |
|---|---|
| `naturalist:chest` | Forestry (bee houses in world) |
| `railcraft:workshop` | Railcraft (village workshop) |
| `towerChestContents` | Witching Gadgets |
| `tinkerHouseChest` | Tinkers' Construct (village smeltery) |
| `tinkerHousePatterns` | Tinkers' Construct (village smeltery patterns) |
| `composting` | Et-Futurum Requiem |

---

### GregTech 5 Loot Additions

**Source:** `GT5-Unofficial/src/main/java/gregtech/loaders/postload/BookAndLootLoader.java`

GT5 is the largest single contributor to vanilla dungeon loot. It also increases loot quantities by 30-100% when `mIncreaseDungeonLoot` is enabled.

#### Loot Count Increases (when enabled)

| Category | Extra Min | Extra Max |
|---|---|---|
| bonusChest | +4 | +8 |
| dungeonChest | +3 | +6 |
| pyramidDesertChest | +4 | +8 |
| pyramidJungleChest | +8 | +16 |
| pyramidJungleDispenser | +1 | +2 |
| mineshaftCorridor | +2 | +4 |
| villageBlacksmith | +6 | +12 |
| strongholdCrossing | +4 | +8 |
| strongholdCorridor | +3 | +6 |
| strongholdLibrary | +8 | +16 |

#### dungeonChest Items

| Item | Stack | Weight |
|---|---|---|
| Purple Drink Bottle | 8-16 | 80 |
| Silver Ingot | 1-6 | 120 |
| Bronze Ingot | 1-6 | 60 |
| Steel Ingot | 1-6 | 60 |
| Manganese Ingot | 1-6 | 60 |
| Red Garnet | 1-6 | 40 |
| Yellow Garnet | 1-6 | 40 |
| Neodymium Dust | 1-6 | 40 |
| Chrome Dust | 1-3 | 40 |
| Lead Ingot | 1-6 | 30 |
| Emerald | 1-6 | 20 |
| Ruby | 1-6 | 20 |
| Sapphire | 1-6 | 20 |
| Green Sapphire | 1-6 | 20 |
| Olivine | 1-6 | 20 |
| Holy Water Bottle | 4-8 | 20 |
| Damascus Steel Ingot | 1-6 | 10 |

#### pyramidDesertChest Items

| Item | Stack | Weight |
|---|---|---|
| Silver Ingot | 4-16 | 12 |
| Platinum Ingot | 2-8 | 4 |
| Red Garnet | 2-8 | 4 |
| Yellow Garnet | 2-8 | 4 |
| Holy Water Bottle | 4-8 | 2 |
| Ruby | 2-8 | 2 |
| Sapphire | 2-8 | 2 |
| Green Sapphire | 2-8 | 2 |
| Olivine | 2-8 | 2 |

#### pyramidJungleChest Items

| Item | Stack | Weight |
|---|---|---|
| Bronze Ingot | 4-16 | 12 |
| Ancient Gold Coins | 16-64 | 10 |
| Red Garnet | 2-8 | 4 |
| Yellow Garnet | 2-8 | 4 |
| Ruby | 2-8 | 2 |
| Sapphire | 2-8 | 2 |
| Green Sapphire | 2-8 | 2 |
| Olivine | 2-8 | 2 |
| **ZPM Fully Charged** | **1** | **1 (EXTREMELY RARE)** |

#### mineshaftCorridor Items

| Item | Stack | Weight |
|---|---|---|
| Silver Ingot | 1-4 | 12 |
| Bronze Ingot | 1-4 | 6 |
| Steel Ingot | 1-4 | 6 |
| Red Garnet | 1-4 | 4 |
| Yellow Garnet | 1-4 | 4 |
| Lead Ingot | 1-4 | 3 |
| Emerald | 1-4 | 2 |
| Ruby | 1-4 | 2 |
| Sapphire | 1-4 | 2 |
| Green Sapphire | 1-4 | 2 |
| Olivine | 1-4 | 2 |

#### villageBlacksmith Items

| Item | Stack | Weight |
|---|---|---|
| Steel Ingot | 4-12 | 12 |
| Bronze Ingot | 4-12 | 12 |
| Brass Ingot | 4-12 | 12 |
| Manganese Ingot | 2-8 | 12 |
| Chrome Dust | 1-4 | 6 |
| Neodymium Dust | 2-8 | 6 |
| Damascus Steel Ingot | 2-8 | 4 |
| **McGuffium-239** | **1** | **1 (EXTREMELY RARE)** |

#### strongholdCrossing Items

| Item | Stack | Weight |
|---|---|---|
| McGuffium-239 | 1 | 10 |
| Holy Water Bottle | 4-8 | 6 |

#### bonusChest Items

| Item | Stack | Weight |
|---|---|---|
| Purple Drink Bottle | 8-16 | 2 |

#### pyramidJungleDispenser Items

| Item | Stack | Weight |
|---|---|---|
| Fire Charges | 2-8 | 30 |

---

### Thaumcraft Loot Additions

**Source:** `ThaumcraftDecompiled/thaumcraft/common/config/Config.java`

Thaumcraft adds items to ALL vanilla chest categories (except villageBlacksmith which gets special treatment).

#### Common Tier (all vanilla chests except blacksmith)

| Item | Stack | Weight |
|---|---|---|
| Thaumcraft Loot Bag (Common) | 1-3 | 5 |
| Aspect Dust | 1-3 | 5 |
| Amber | 1-3 | 5 |

#### Uncommon Tier (all vanilla chests except blacksmith)

| Item | Stack | Weight |
|---|---|---|
| Thaumcraft Loot Bag (Uncommon) | 1-2 | 4 |
| Baubles (6 types) | 1-2 | 4 |
| Vis Cubes | 1-2 | 4 |

#### Rare Tier (all vanilla chests except blacksmith)

| Item | Stack | Weight |
|---|---|---|
| **Thaumcraft Loot Bag (Rare)** | **1** | **1** |
| Thaumonomicon | 1 | 1 |
| Thaumium Sword | 1 | 1 |
| Thaumium Pickaxe | 1 | 1 |
| Thaumium Axe | 1 | 1 |
| Thaumium Hoe | 1 | 1 |
| Runic Ring | 1 | 1 |
| Baubles (4 types) | 1 | 1 |

#### Special Entries

| Item | Category | Stack | Weight |
|---|---|---|---|
| Enchanted Fabric | strongholdLibrary | 3-6 | 20 |
| Aspect Dust | villageBlacksmith | 1-3 | 10 |

---

### Botania Loot Additions

**Source:** `Botania/src/main/java/vazkii/botania/common/core/handler/ChestGenHandler.java`

| Item | Categories | Stack | Weight |
|---|---|---|---|
| Mana Resource | dungeonChest | 1-5 | 9 |
| Mana Resource | strongholdCorridor | 1 | 8 |
| Botania Lexicon | bonusChest | 1 | 7 |
| Black Lotus | dungeonChest | 1 | 6 |
| Black Lotus | strongholdCorridor, pyramidDesert/Jungle, mineshaft, blacksmith | 1 | 6 |
| Botania Lexicon | dungeonChest | 1 | 6 |
| Mana Bottle | dungeonChest | 1 | 5 |
| **Overgrowth Seed** | **dungeonChest, strongholdCorridor, pyramidDesert/Jungle, mineshaft** | **1** | **2** |
| Black Lotus | bonusChest | 1 | 1 |

---

### Hardcore Ender Expansion Loot Additions

**Source:** `Hardcore-Ender-Expansion/src/main/java/chylex/hee/world/loot/WorldLoot.java`

| Item | Categories | Stack | Weight |
|---|---|---|---|
| Adventurer's Diary | strongholdLibrary | 1 | 8 |
| Knowledge Fragments | dungeonChest, pyramids, blacksmith, strongholds | 1 | 6-9 |
| Adventurer's Diary | strongholdCorridor, mineshaft, pyramids | 1 | 5 |
| Temple Caller | mineshaft, dungeonChest, pyramids, blacksmith, strongholds | 1 | 1 |

---

### EnderIO Loot Additions

**Source:** `EnderIO/src/main/java/crazypants/enderio/item/darksteel/DarkSteelItems.java`

| Item | Categories | Stack | Weight |
|---|---|---|---|
| Dark Steel Sword | dungeonChest, blacksmith | 1 | 5 |
| Dark Steel Boots | dungeonChest, blacksmith | 1 | 5 |
| Dark Steel Sword | pyramidDesert/Jungle | 1 | 4 |

---

### Electro-Magic Tools Loot Additions

**Source:** `Electro-Magic-Tools/src/main/java/emt/util/EMTDungeonChestGenerator.java`

| Item | Category | Weight |
|---|---|---|
| Tainted Thor Hammer | dungeonChest | Configurable |
| One Ring | dungeonChest | Configurable |

Both are conditional on config settings.

---

### Other ChestGenHooks Contributors

| Mod | Items | Categories | Weight |
|---|---|---|---|
| **Applied Energistics 2** | Certus Quartz Crystal (1-4) | mineshaftCorridor | 2 |
| **Open Modular Turrets** | Disposable Turret, Turret Base, Lever (1-2) | mineshaft, dungeon, strongholdCorridor | 15 |
| **Adventure Backpack 2** | Iron Golem Backpack | blacksmith | 2 |
| **Adventure Backpack 2** | Bat Backpack | dungeon (2), mineshaft (12) | varies |
| **Adventure Backpack 2** | Pigman Backpack | pyramidDesert | 12 |
| **Adventure Backpack 2** | Standard Backpack (0-1) | bonusChest | 5 |
| **Iguana Tweaks TConstruct** | Random TC Weapons | all major categories | 2-10 |
| **Forestry** | Steadfast Bee Drone | dungeonChest | varies |
| **Forestry** | Bees, combs, grafter, saplings | naturalist:chest | 4-8 |
| **Magic Bees** | Oblivion Hive Frame | strongholdCorridor (18), Library (23) | varies |
| **Railcraft** | Coal Fuel, tools | railcraft:workshop, mineshaft, blacksmith | varies |

---

## 2. Twilight Forest Structures (TFTreasureTable)

**Source:** `twilightforest/src/main/java/twilightforest/TFTreasureTable.java`
**GTNH Modifications:** `NewHorizonsCoreMod/src/main/java/com/dreammaster/TwilightForest/TF_Loot_Chests.java`

Twilight Forest has its own weighted loot system with rarity tiers (useless, common, uncommon, rare, ultrarare) across 22+ structure types. GTNH heavily modifies these tables.

### Structure Loot Summary

#### Hollow Hills (hill1, hill2, hill3)
- **Vanilla TF:** Iron, wheat, string, bread, gunpowder, arrows, gold, picks, transformation powder, diamonds, steeleaf
- **GTNH Adds:** Thaumium dusts, Bronze ingots, Nickel ingots (hill1); Manganese, Antimony, Thaumcraft shards, Aluminum dusts (hill2/3)

#### Hedge Maze
- Planks, mushrooms, wheat, string, melon, shears, saddle, bow, diamond hoe, golden apple

#### Labyrinth (Minotaur Maze)
- **Rooms:** Iron ingot, maze wafers, steeleaf armor/weapons, charm of keeping
- **Dead Ends:** Sticks, coal, arrows, paper, leather, mushroom stew, blaze rods
- **Vault:** Iron, emeralds, maze wafers, ironwood, potions, steeleaf armor with enchantments, ender chest, **Maze Breaker Pick (Efficiency 4, Unbreaking 3, Fortune 2)**

#### Lich Tower
- **Library:** Thaumcraft candles, stainless steel dusts, potions (GTNH)
- **Boss Room:** **Thaumcraft Loot Bags (Common w:2, Uncommon w:4, Rare w:6)**, Dezil's Marshmallow (GTNH)

#### Dark Tower (Ur-Ghast)
- **Cache:** Sticks, charcoal, arrows, experiment 115, redstone, iron, ironwood, diamonds, steeleaf
- **Key Rooms:** Iron, experiment 115, gunpowder, redstone, glowstone, steeleaf armor, enchanted books (Feather Falling 3, Knockback 2, Efficiency 3)
- **Boss:** Carminite, fiery tears, trophies
- **Basement:** Stainless steel, void ingots, Thaumcraft Nitor/Alumentum, potions (GTNH)

#### Stronghold (Knight Phantom)
- **Cache/Rooms:** Sticks, coal, arrows, maze wafers, iron/ironwood/steeleaf/knight metal, enchanted bows/swords
- **Boss:** Knightly weapons (sword, pick, axe), phantom armor with enchantments, trophies

#### Aurora Palace (Snow Queen)
- High-tier weapons, armor, potions

#### Troll Cave
- **Vault:** Troll-themed items

---

## 3. Roguelike Dungeons

**Source:** `Roguelike-Dungeons/src/main/java/greymerk/roguelike/treasure/loot/`

Roguelike Dungeons generates large procedural underground dungeons with a sophisticated 5-level difficulty system and 16+ loot categories.

### Loot Categories

| Category | Description |
|---|---|
| WEAPON | Enchanted swords and bows (quality scales with level) |
| ARMOUR | Full enchanted armor sets |
| ORE | Diamonds, emeralds, gold, iron, coal |
| TOOL | Enchanted pickaxes and shovels |
| POTION | Various potions |
| FOOD | Bread, apples, mushroom stew |
| ENCHANTBOOK | Randomly enchanted books |
| ENCHANTBONUS | Bonus enchanted items |
| SUPPLY | Torches, ladders, utility items |
| MUSIC | Music discs |
| SMITHY | Smithing materials |
| SPECIAL | Specialty items |
| REWARD | High-value reward items |
| STARTER | Basic starting items |
| JUNK | Common/trash items |
| BLOCK | Various building blocks |

### Ore Rates by Dungeon Level

| Level | Diamond | Emerald | Gold Ingot | Iron Ingot | Coal |
|---|---|---|---|---|---|
| 4 (deepest) | 1 (w:1) | 1 (w:2) | 3-5 (w:3) | 1-6 (w:5x) | — |
| 3 | 1-4 (w:1) | 1 (w:1) | 1-5 (w:3) | 1-4 (w:10) | 2-5 |
| 2 | 1-4 | — | 1-4 | 1-3 | 1-4 |
| 1 | 1-4 | — | 1-3 | 1-2 | 1-3 |
| 0 (shallowest) | 1-4 | — | 1 | 1 | 1-2 |

### Room Types with Chests
- 20+ room types with varying numbers of chests
- Reward rooms with special loot
- Citadel boss structure with treasure
- Nether Brick Fortress variant

---

## 4. Galacticraft / Amunra Space Dungeons

### Moon Dungeons
**Source:** `Galacticraft/src/main/java/micdoodle8/mods/galacticraft/core/world/gen/dungeon/`

- **Treasure Rooms:** Tier 1 treasure chests
- **Chest Rooms:** Populates from standard `ChestGenHooks.DUNGEON_CHEST` (includes ALL GT/Thaumcraft/Botania items)
- **Boss Rooms:** Boss-specific loot (Skeleton Boss, Creeper Boss generate chests)

### Mars Dungeons
**Source:** `Galacticraft/src/main/java/micdoodle8/mods/galacticraft/planets/mars/world/gen/dungeon/`

- Similar to Moon, uses standard dungeon chest pool
- Higher-tier treasure chests

### Amunra Pyramids
**Source:** `amunra/src/main/java/de/katzenpapst/amunra/world/mapgen/pyramid/`

- Custom pyramid structures with `LOOT_CATEGORY_BASIC` and `LOOT_CATEGORY_BOSS` categories
- Chest rooms and boss rooms with custom loot
- Uses FillChest populator system

---

## 5. Hardcore Ender Expansion (End Structures)

**Source:** `Hardcore-Ender-Expansion/src/main/java/chylex/hee/world/`

HEE uses its own `WeightedLootList` system for End dimension structures:

| Structure | Type | Loot System |
|---|---|---|
| Tower | `ComponentTower` | Custom `lootTower` weighted list |
| Laboratory | `LaboratoryContent` | Custom lab loot |
| Hidden Cellar | `StructureHiddenCellar` | Cellar loot |
| Silverfish Dungeon | `StructureSilverfishDungeon` | Dungeon loot |
| Ravaged Dungeon | `RavagedDungeonPlacer` | Ravaged dungeon weighted list |
| Sanctuary | `ComponentSanctuary` | Sanctuary loot |
| Blob Features | `BlobPopulatorChest` | Chest-based blob loot via ITileEntityGenerator |

---

## 6. Other Loot Systems

### Blood Magic - Demon Village
**Source:** `BloodMagic/src/main/java/WayofTime/alchemicalWizardry/common/demonVillage/loot/DemonVillageLootRegistry.java`

- Pulls items from dungeonChest + pyramidDesertChest pools
- Adds Blood Magic base items (meta 28-29, w:5 each)

### EnhancedLootBags (XML-Based System)
**Source:** `EnhancedLootBags/src/main/java/eu/usrv/enhancedlootbags/core/LootGroupsHandler.java`
**GTNH Recipes:** `NewHorizonsCoreMod/src/main/java/com/dreammaster/scripts/ScriptEnhancedLootBags.java`

- **58+ loot bag types** (meta 1-58) configured via `LootBags.xml`
- Each bag has: name, rarity, min/max items, weighted drops
- Fortune enchantment scaling: reduces trash weight by ~33% per Fortune level
- Bags can be enchanted with Knockback III to remove trash drops entirely
- Covers tech items: IC2 components, GT motors, Thaumcraft materials, Forestry parts, Railcraft tracks
- Assembler recipes in `AssemblerRecipes.java` (lines 7990-8059)

### LootGames
**Source:** `LootGames/src/main/java/ru/timeconqueror/lootgames/`

- Generates custom dungeons with mini-game-based loot
- Separate from standard chest generation
- Own marker and block system

### Tinker's Construct Village
- Custom `tinkerHouseChest` and `tinkerHousePatterns` chest categories
- Tool workshop and smeltery village components

### Witching Gadgets
- Custom `towerChestContents` category
- Village Photoshop component with custom loot

### TooMuchLoot Framework
**Source:** `TooMuchLoot/src/main/java/dmillerw/tml/`

- Dynamic loot table modification framework (loads from XML)
- Can modify any ChestGenHooks loot table at runtime

---

## Best Sources for Rare Items

| Target Item | Best Source | Notes |
|---|---|---|
| **ZPM (Fully Charged)** | Jungle Temple chests | GT5, weight 1 — extremely rare |
| **McGuffium-239** | Stronghold Crossing (w:10) or Village Blacksmith (w:1) | GT5 |
| **Thaumcraft Rare Loot Bag** | TF Lich Tower Boss Room (w:6) | GTNH addition; also champion mob kills |
| **Primordial Pearl** | Inside Thaumcraft Rare Loot Bags | ~1 in 263 rare bags (~0.38% per bag) |
| **Overgrowth Seed** | Any vanilla dungeon (w:2) | Botania |
| **One Ring** | Dungeon chests | EMT, configurable weight |
| **Tainted Thor Hammer** | Dungeon chests | EMT, configurable weight |
| **Platinum Ingot** | Desert Temple (w:4) | GT5 |
| **Damascus Steel Ingot** | Dungeon chests (w:10) or Village Blacksmith (w:4) | GT5 |
| **Enchanted Gear** | Roguelike Dungeons (level 3-4) or TF Labyrinth Vault | Best gear at deeper levels |
| **Steadfast Bee Drone** | Dungeon chests | Forestry |
| **Oblivion Hive Frame** | Stronghold Library (w:23) | Magic Bees |
| **Temple Caller** | Various vanilla chests (w:1) | HEE |
| **Maze Breaker Pick** | TF Labyrinth Vault | Efficiency 4/Unbreaking 3/Fortune 2 |
| **Certus Quartz** | Mineshaft chests (w:2) | AE2 |
| **Ancient Gold Coins** | Jungle Temple (16-64, w:10) | GT5 |

---

## Source Files Reference

| File | Contents |
|---|---|
| `GT5-Unofficial/.../BookAndLootLoader.java` | GT5 dungeon loot registrations |
| `ThaumcraftDecompiled/.../Config.java` | Thaumcraft loot bag registrations |
| `Botania/.../ChestGenHandler.java` | Botania dungeon loot |
| `Hardcore-Ender-Expansion/.../WorldLoot.java` | HEE dungeon loot |
| `EnderIO/.../DarkSteelItems.java` | EnderIO dungeon loot |
| `Electro-Magic-Tools/.../EMTDungeonChestGenerator.java` | EMT dungeon loot |
| `NewHorizonsCoreMod/.../TF_Loot_Chests.java` | GTNH Twilight Forest modifications |
| `NewHorizonsCoreMod/.../ScriptEnhancedLootBags.java` | Enhanced Loot Bag recipes |
| `Roguelike-Dungeons/.../treasure/loot/` | Roguelike Dungeons loot system |
| `Galacticraft/.../world/gen/dungeon/` | Space dungeon generation |
| `twilightforest/.../TFTreasureTable.java` | TF treasure definitions |
| `EnhancedLootBags/.../LootGroupsHandler.java` | Enhanced Loot Bags XML system |
| `BloodMagic/.../DemonVillageLootRegistry.java` | Blood Magic demon village loot |
| `Forestry/.../PluginApiculture.java` | Forestry bee loot |
| `MagicBees/.../Config.java` | Magic Bees dungeon loot |
| `Railcraft/.../LootPlugin.java` | Railcraft dungeon loot |
| `OpenModularTurrets/.../DungeonLootHandler.java` | OMT dungeon loot |
| `AdventureBackpack2/.../ModWorldGen.java` | Adventure Backpack loot |
| `IguanaTweaksTConstruct/.../IguanaWorldGen.java` | Iguana Tweaks weapon loot |
| `Applied-Energistics-2-Unofficial/.../Registration.java` | AE2 dungeon loot |
