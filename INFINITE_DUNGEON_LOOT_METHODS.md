# Every Automated Way to Get Infinite Dungeon Loot in GTNH

This document catalogs every method in the GTNH codebase that draws from the vanilla `dungeonChest` (`ChestGenHooks.DUNGEON_CHEST`) loot table, or from related dungeon-style loot tables, and can be automated for infinite drops.

---

## 1. Botania — Loonium Flower (DIRECT DUNGEON LOOT)

**Source:** `Botania/src/main/java/vazkii/botania/common/block/subtile/functional/SubTileLoonuim.java`

**Mechanism:** Every 200 ticks (10 seconds), if it has 35,000 mana and no redstone signal, it calls:
```java
ChestGenHooks.getOneItem(ChestGenHooks.DUNGEON_CHEST, rand)
```
and drops the item as an EntityItem within a 3-block radius.

**Automation:** Fully automatable. Supply mana via Mana Spreaders, collect items with hoppers/Item Conduits/vacuum hoppers. The flower directly pulls from `DUNGEON_CHEST` every 10 seconds indefinitely.

**Rate:** 1 dungeon loot item per 10 seconds, limited only by mana supply.

**Blacklist:** Items in `BotaniaAPI.looniumBlacklist` are excluded; if drawn, the flower re-rolls.

---

## 2. BetterQuesting — Loot Chest Item (meta 103) (DIRECT DUNGEON LOOT)

**Source:** `BetterQuesting/src/main/java/bq_standard/items/ItemLootChest.java`

**Mechanism:** When right-clicked (damage value 103), generates 1-8 items from a configurable loot table. Default loot table is `"dungeonChest"`:
```java
String loottable = (stack.getTagCompound() != null && stack.getTagCompound()
    .hasKey("loottable", 8)) ? stack.getTagCompound().getString("loottable") : "dungeonChest";
for (int n = 1 + player.getRNG().nextInt(7); n > 0; n--) {
    loot.add(new BigItemStack(ChestGenHooks.getOneItem(loottable, player.getRNG())));
}
```

**Automation:** Can be automated if you can auto-right-click the item (e.g., Autonomous Activator, MFR Auto-Activator). However, these are typically quest rewards and not infinitely obtainable unless quest repeats are configured. The NBT `loottable` tag can be set to any `ChestGenHooks` category.

**Key detail:** The item itself is consumed on use (stackSize--), so you need a supply of them.

---

## 3. Random Things — Creative Chest Generator (DIRECT DUNGEON LOOT)

**Source:** `Random-Things/src/main/java/lumien/randomthings/Items/ItemCreativeChestGenerator.java`

**Mechanism:** When used on a block (right-click), places a vanilla chest and fills it with loot from a selectable `ChestGenHooks` category (shift-right-click cycles categories). `DUNGEON_CHEST` is one of the available categories.
```java
ChestCategory category = ChestCategory.values()[...];
WeightedRandomChestContent.generateChestContents(rng,
    ChestGenHooks.getItems(category.getName(), rng),
    (IInventory) worldObj.getTileEntity(posX, posY, posZ),
    ChestGenHooks.getCount(category.getName(), rng));
```

**Automation:** Creative-only item. If available (cheats/creative), fully automatable — auto-use, auto-break, auto-collect.

---

## 4. LootGames — Dungeon Minigames (CONFIGURABLE DUNGEON LOOT)

**Source:** `LootGames/src/main/java/ru/timeconqueror/lootgames/api/util/RewardUtils.java`

**Mechanism:** When minigames are completed (e.g., Minesweeper), the mod spawns chests filled with items from configurable loot tables. The loot tables include `DUNGEON_CHEST`, `MINESHAFT_CORRIDOR`, `PYRAMID_JUNGLE_CHEST`, and `STRONGHOLD_CORRIDOR` (defined in `LootTables.java`).
```java
WeightedRandomChestContent[] randomLoot = ChestGenHooks.getItems(lootTable, RandHelper.RAND);
WeightedRandomChestContent.generateChestContents(RandHelper.RAND, randomLoot, chestTile, count);
```

**Automation:** Not easily automated — requires completing minigames. Structures spawn naturally underground during worldgen.

---

## 5. KubaTech — Extreme Entity Crusher (EEC) (MOB DROPS INCLUDING DUNGEON LOOT ITEMS)

**Source:** `GT5-Unofficial/src/main/java/kubatech/tileentity/gregtech/multiblock/MTEExtremeEntityCrusher.java` and `kubatech/loaders/MobHandlerLoader.java`

**Mechanism:** A GregTech multiblock machine that takes a Powered Spawner, spawns mobs virtually, and collects their drops. Key features:
- Processes all registered mob drops (from MobsInfo registry)
- Supports Looting enchantment on weapons (up to MAX_LOOTING_LEVEL)
- Can produce Infernal Mobs drops (configurable toggle) including Elite/Ultra/Infernal loot
- Supports batch mode (16x output)
- Player-only drops are included at a configurable modifier rate

**Dungeon Loot Connection:** Many items from the `DUNGEON_CHEST` table are also mob drops. More importantly, mobs killed by the EEC drop Thaumcraft loot bags (via the Salis-Arcana fake player mixin — see #6), which themselves contain dungeon-style loot.

**Automation:** Fully automated GregTech multiblock. Supply power (EV+), insert a Powered Spawner, optionally insert a looting sword. Outputs to Output Bus.

---

## 6. Salis-Arcana — Fake Player Loot Bag Drops (THAUMCRAFT LOOT BAGS FROM AUTOMATED KILLS)

**Source:** `Salis-Arcana/src/main/java/dev/rndmorris/salisarcana/mixins/late/thaumcraft/common/lib/events/MixinEventHandlerEntity_LootBagFakePlayer.java`

**Mechanism:** A mixin that modifies Thaumcraft's `EventHandlerEntity.livingDrops()` method. In vanilla Thaumcraft, loot bags do NOT drop when a mob is killed by a FakePlayer. This mixin changes the `fakeplayer` check variable to always return `false`, meaning:

```java
@ModifyVariable(method = "livingDrops", at = @At("STORE"), name = "fakeplayer")
private static boolean fakePlayersDropLootBags(boolean original) {
    return false;
}
```

**Effect:** When enabled (`SalisConfig.features.fakePlayersDropLootbags`), ALL automated mob killing methods (EEC, mob grinders, auto-activators, etc.) will cause Thaumcraft loot bags to drop just as if a real player killed the mob.

**Automation:** This is the KEY enabler for infinite Thaumcraft loot bags from any automated mob farm.

---

## 7. Thaumcraft — Loot Bags (Common/Uncommon/Rare) (SEPARATE LOOT POOL)

**Source:** `ThaumcraftDecompiled/thaumcraft/common/items/ItemLootBag.java` and `ThaumcraftDecompiled/thaumcraft/common/lib/utils/Utils.java`

**Mechanism:** Right-clicking a loot bag generates 8-12 items from Thaumcraft's own weighted loot pools (separate from vanilla `DUNGEON_CHEST` but conceptually similar "dungeon loot"):
- Meta 0 = Common bag → draws from `WeightedRandomLoot.lootBagCommon`
- Meta 1 = Uncommon bag → draws from `WeightedRandomLoot.lootBagUncommon`
- Meta 2 = Rare bag → draws from `WeightedRandomLoot.lootBagRare`

Each draw has a 2.5% × rarity chance to generate random enchanted gear via `genGear()` instead.

**Automation:** Can be auto-opened with Autonomous Activator / similar. Combined with #6 (Salis-Arcana mixin), you get infinite loot bags from mob farms which can then be auto-opened.

---

## 8. EnhancedLootBags — GTNH Custom Loot Bag System (GTNH-SPECIFIC LOOT POOLS)

**Source:** `EnhancedLootBags/src/main/java/eu/usrv/enhancedlootbags/core/items/ItemLootBag.java`

**Mechanism:** GTNH's primary loot bag system. Right-clicking opens a bag and generates items from a configurable JSON-based loot pool system (not vanilla `DUNGEON_CHEST`). Features:
- Weighted random drops from custom loot groups
- Fortune enchantment support (Fortune III = 100% better drops when `CombineWithTrash` is true)
- Limited drops per player
- Tiered bags (Basic through UV tier, plus themed bags: Magic, Space, Bees, Food, etc.)

**Automation:**
- Bags themselves come from quest rewards, mob drops, world exploration
- Can be auto-opened with an Autonomous Activator
- **Can be tier-upgraded via GregTech Assembler recipes** (3 lower → 1 higher tier), defined in `NewHorizonsCoreMod/.../AssemblerRecipes.java`
- Assembler tier upgrade chains: Basic→Steam→LV→MV→HV→EV→IV→LuV→ZPM→UV

---

## 9. Gadomancy — Fake Loot Bag (REDIRECTED TO PACKAGE SYSTEM)

**Source:** `Gadomancy/src/main/java/makeo/gadomancy/common/items/ItemFakeLootbag.java`

**Mechanism:** Extends Thaumcraft's `ItemLootBag` but overrides `onItemRightClick` to redirect to `RegisteredItems.itemPackage.onItemRightClick()`. This is Gadomancy's own package system rather than Thaumcraft's loot pool.

**Automation:** Depends on how Gadomancy packages work. Not a direct source of dungeon loot.

---

## 10. Infernal Mobs — Elite/Ultra/Infernal Drops (CONFIGURABLE DROP LISTS)

**Source:** `Infernal-Mobs/src/main/java/atomicstryker/infernalmobs/common/InfernalMobsCore.java`

**Mechanism:** When an Infernal Mob dies, `dropLootForEnt()` is called, which runs `dropRandomEnchantedItems()`. This drops randomly enchanted items from three configurable drop lists:
- `dropIdListElite` (≤5 modifiers)
- `dropIdListUltra` (6-10 modifiers)
- `dropIdListInfernal` (11+ modifiers)

Items are enchanted with strength proportional to modifier count.

**Automation:** Automated via EEC (Extreme Entity Crusher, see #5) which has an "infernal drops" toggle. Also automated via any mob farm + Salis-Arcana fake player mixin.

---

## 11. Mods That ADD Items to DUNGEON_CHEST (Expanding the Loot Pool)

These mods all call `ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, ...)` to add their items to the dungeon chest loot table. This means the Loonium (#1) and any other `DUNGEON_CHEST` consumer will include these items:

| Mod | Items Added | Source File |
|-----|-------------|-------------|
| **Botania** | Manasteel, Lexicon, Mana Bottle, Black Lotus, Overgrowth Seed | `ChestGenHandler.java` |
| **Blood Magic** | 7 Blood Magic items (slates, orbs, etc.) | `AlchemicalWizardry.java` |
| **Electro-Magic Tools** | EMT-specific items | `EMTDungeonChestGenerator.java` |
| **EnderIO** | Dark Steel Sword, Boots, Capacitors, Travel Staff, etc. | `EnderIO.java`, `DarkSteelItems.java` |
| **Et Futurum** | Otherside Record, Beetroot Seeds | `EtFuturum.java`, `ModRecipes.java` |
| **Forestry** | Forestry bee-related items | `PluginApiculture.java` |
| **GregTech** | Holy Water, Purple Drink, Coins, Books | `BookAndLootLoader.java` |
| **GT++ (Forestry Bees)** | Frame items | `FRItemRegistry.java` |
| **Hardcore Ender Expansion** | End-related items | `WorldLoot.java` |
| **IguanaTweaks TConstruct** | Mob heads, TConstruct items | Various |
| **OpenBlocks** | Multiple OpenBlocks items | `Config.java` |
| **OpenModularTurrets** | Turret components | `DungeonLootHandler.java` |
| **Random Things** | Whitestone (uncharged) | `ItemWhiteStone.java` |
| **Adventure Backpack** | Random backpacks | `ModWorldGen.java` |
| **Railcraft** | Railcraft items | `LootPlugin.java` |
| **TinkersConstruct** | TConstruct materials | `TinkerWorld.java` |
| **TooMuchLoot** | Configurable JSON-defined items | `ChestLootLoader.java` |
| **WitchingGadgets** | Witchery/Thaumcraft items | `WGContent.java` |

---

## 12. GT++ Fish Trap (FISHING LOOT, NOT DUNGEON)

**Source:** `GT5-Unofficial/src/main/java/gtPlusPlus/core/tileentities/general/TileEntityFishTrap.java`

**Mechanism:** A passive block that generates fish/junk loot when surrounded by water. Uses its own hardcoded loot table (not `DUNGEON_CHEST`), but generates items infinitely and automatically.

**Automation:** Fully passive — place in water, extract with hoppers/pipes.

**Note:** This is fishing loot, not dungeon loot, but included for completeness.

---

## 13. Galacticraft — Space Dungeon Chests (ONE-TIME WORLDGEN)

**Source:** `Galacticraft/.../RoomChestsMoon.java`, `RoomChestsMars.java`

**Mechanism:** Moon and Mars dungeons generate chests with loot from `ChestGenHooks.DUNGEON_CHEST` during world generation. Not automatable — one-time worldgen.

---

## 14. TooMuchLoot — Loot Table Override System (META-SYSTEM)

**Source:** `TooMuchLoot/src/main/java/dmillerw/tml/TooMuchLoot.java`

**Mechanism:** Allows complete replacement of all `ChestGenHooks` loot tables via JSON config files in `config/TooMuchLoot/loot/`. This affects ALL consumers of `ChestGenHooks` including Loonium. Not a loot source itself, but a way to customize what all dungeon loot sources produce.

---

## 15. Twilight Forest — Treasure Chests (SEPARATE LOOT SYSTEM WITH TC LOOT BAGS)

**Source:** `NewHorizonsCoreMod/.../TF_Loot_Chests.java`

**Mechanism:** GTNH adds custom items to Twilight Forest treasure chests via the `TFTreasure` system. Notably, **Thaumcraft Loot Bags are added to tower_room chests**:
- Common Loot Bag (meta 0) in tower common loot
- Uncommon Loot Bag (meta 1) in tower uncommon loot
- Rare Loot Bag (meta 2) in tower rare loot

**Automation:** Not automatable — one-time worldgen exploration.

---

## Summary: Best Automated Infinite Dungeon Loot Methods

### Tier 1: Direct DUNGEON_CHEST access (infinite, automatable)
1. **Loonium** — 1 item / 10 sec, directly from `DUNGEON_CHEST`, fully automatable with mana
2. **BQ Loot Chest (meta 103)** — 1-8 items per use from `dungeonChest`, requires item supply

### Tier 2: Indirect via mob farming (infinite, automatable)
3. **EEC (Extreme Entity Crusher)** — Automated mob killing, produces all mob drops including Thaumcraft loot bags (with Salis-Arcana mixin enabled)
4. **Any mob grinder + Salis-Arcana mixin** — Conventional mob farms now drop Thaumcraft loot bags thanks to the fake player mixin
5. **Auto-open loot bags** — Use Autonomous Activator on Thaumcraft/Enhanced loot bags from mob farms

### Tier 3: Loot bag upgrading (multiplier)
6. **GT Assembler loot bag upgrade** — Convert 3 lower-tier Enhanced Loot Bags into 1 higher-tier bag

### Tier 4: Creative/admin only
7. **Creative Chest Generator** — Infinite dungeon chests, creative-only item
