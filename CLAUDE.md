# Thaumcraft 4.2.3.5 Loot Bag Decompilation Notes

## Purpose

This document describes how the Thaumcraft 4.2.3.5a jar was decompiled to analyze the rare treasure bag loot table, specifically to determine the drop chance of Primordial Pearls and other items.

## Decompilation Process

### Tool Used

- **CFR (Class File Reader) v0.152** — a standalone Java decompiler
- Downloaded from: `https://github.com/leibnitz27/cfr/releases/download/0.152/cfr-0.152.jar`

### Steps

1. **Extract the jar:**
   ```bash
   mkdir -p /tmp/tc-extract
   cd /tmp/tc-extract
   jar xf Thaumcraft-1.7.10-4.2.3.5a.jar
   ```

2. **Identify relevant classes** by searching for references to `lootBagRare`, `WeightedRandomLoot`, and `generateLoot`:
   ```bash
   grep -rl "lootBagRare" --include="*.class" .
   ```
   This identified three key classes:
   - `thaumcraft/api/internal/WeightedRandomLoot.class`
   - `thaumcraft/api/ThaumcraftApi.class`
   - `thaumcraft/common/lib/utils/Utils.class`

   Additional classes were identified by tracing call chains:
   - `thaumcraft/common/items/ItemLootBag.class` — the loot bag item itself
   - `thaumcraft/common/config/Config.class` — where loot bag contents are registered
   - `thaumcraft/common/config/ConfigItems.class` — item registry

3. **Decompile each class with CFR:**
   ```bash
   java -jar cfr-0.152.jar thaumcraft/api/internal/WeightedRandomLoot.class --outputdir /tmp/tc-decompiled
   java -jar cfr-0.152.jar thaumcraft/api/ThaumcraftApi.class --outputdir /tmp/tc-decompiled
   java -jar cfr-0.152.jar thaumcraft/common/lib/utils/Utils.class --outputdir /tmp/tc-decompiled
   java -jar cfr-0.152.jar thaumcraft/common/items/ItemLootBag.class --outputdir /tmp/tc-decompiled
   java -jar cfr-0.152.jar thaumcraft/common/config/Config.class --outputdir /tmp/tc-decompiled
   java -jar cfr-0.152.jar thaumcraft/common/config/ConfigItems.class --outputdir /tmp/tc-decompiled
   ```

## Decompiled Files

All decompiled source files are stored in `ThaumcraftDecompiled/` with their original package structure preserved:

```
ThaumcraftDecompiled/
  thaumcraft/
    api/
      ThaumcraftApi.java          — addLootBagItem() method
      internal/
        WeightedRandomLoot.java   — weighted loot pool data structure
    common/
      config/
        Config.java               — loot bag item registration (weights & items)
        ConfigItems.java          — item field declarations & registry
      items/
        ItemLootBag.java          — loot bag right-click behavior
      lib/
        utils/
          Utils.java              — generateLoot() and genGear() methods
```

## Key Findings: Rare Loot Bag Mechanics

### Opening a Rare Bag

From `ItemLootBag.java` — right-clicking a rare bag (meta=2) generates **8-12 items** (`8 + rand.nextInt(5)`). Each item is produced by `Utils.generateLoot(2, rand)`.

### Per-Item Generation (`Utils.generateLoot`)

For rarity=2 (rare bag):
1. **5% chance** (`0.025 * 2 = 0.05`) to generate random enchanted gear via `genGear()` instead of drawing from the pool. If `genGear()` returns null, it recurses.
2. **95% chance** to draw from the `lootBagRare` weighted random pool.

### Rare Bag Weighted Pool (`Config.java`)

Items are registered via `ThaumcraftApi.addLootBagItem(stack, weight, bagType...)` where bagType=2 is rare.

| Item | Weight | Per-Draw Chance |
|---|---|---|
| Gold Nuggets x3 (itemResource:18) | 2000 | ~77.2% |
| Iron Ingot | 100 | ~3.9% |
| Gold Ingot | 100 | ~3.9% |
| Emerald | 75 | ~2.9% |
| Diamond | 50 | ~1.9% |
| Bauble Blanks (6 types, meta 3-8, weight 7 each) | 42 | ~1.6% |
| Enchanted Fabric (itemResource:9) | 25 | ~1.0% |
| Name Tag | 20 | ~0.8% |
| Book (randomly enchanted) | 10 | ~0.4% |
| Golden Apple | 9 | ~0.35% |
| Vis Amulet (random primal vis) | 6 | ~0.23% |
| Runic Ring | 5 | ~0.19% |
| Enchanted Golden Apple (Notch Apple) | 3 | ~0.12% |
| Potions (various types & tiers) | ~100-200 | ~4-8% |
| **Primordial Pearl** (itemEldritchObject:3) | **1** | **~0.039%** |
| **Nether Star** | **1** | **~0.039%** |

**Total pool weight: ~2,547-2,647** (varies by valid potion combinations)

### Overall Probability

- Per draw from the pool: **~1 in 2,500** chance for a Primordial Pearl
- Accounting for the 5% gear diversion: **~1 in 2,632** effective chance per item slot
- Per bag (average 10 draws): **~0.38%** chance (roughly **1 in 263 rare bags**)

### MCP Obfuscated Field Name Mappings (1.7.10)

The decompiled code uses SRG (obfuscated) names for vanilla Minecraft items. Key mappings:

| SRG Name | Minecraft Item |
|---|---|
| `field_151156_bN` | Nether Star |
| `field_151045_i` | Diamond |
| `field_151166_bC` | Emerald |
| `field_151043_k` | Iron Ingot |
| `field_151079_bi` | Gold Ingot |
| `field_151062_by` | Name Tag |
| `field_151153_ao` | Golden Apple (meta 0=normal, 1=enchanted) |
| `field_151122_aG` | Book |
| `field_151068_bn` | Potion |

### Important Caveat

These are vanilla Thaumcraft 4.2.3.5 values only. In GTNH, other mods may call `ThaumcraftApi.addLootBagItem()` to add additional items to the rare pool, which would increase the total weight and dilute the per-item probabilities accordingly.
