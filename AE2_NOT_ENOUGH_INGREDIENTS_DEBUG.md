# AE2 "Not Enough Ingredients" — Diagnosis & Root Causes

## Your Symptoms

- Requesting **1 item** → crafting plan shows all ingredients available (green), but fails with "not enough ingredients" when you click Start
- Requesting **2 items** → AE2 crafts the sub-components for one, first one succeeds, second fails with "not enough ingredients"
- Happens **randomly across all recipes**, not specific to any one item

## What "Not Enough Ingredients" Actually Means

AE2 crafting works in two phases:

1. **Planning phase** (when you see the crafting confirmation screen): AE2 does a *read-only scan* of everything visible in the network. It counts items across all storage — ME drives, storage buses, interface inventories — and shows you green checkmarks.

2. **Execution phase** (when you click Start): AE2 tries to *actually extract* those items from storage into the crafting CPU. If extraction fails for any item, you get **"not enough ingredients"** even though the planning screen said everything was available.

**The error means: "I can see the items, but I can't pull them out."**

## The #1 Most Likely Cause: Storage Buses

### The Bug

This is a **confirmed, well-documented bug** in the GTNH fork of AE2.

When items are stored in external inventories (Storage Drawers, barrels, Deep Storage Units, chests, etc.) connected to your ME network via **Storage Buses**, the crafting planner can see and count those items during the planning phase, but the crafting system **fails to extract them** during execution.

This is tracked in multiple GitHub issues:

- **[GTNH #18009](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/18009)** — "Simulated (missing) items in AE2 crafts doesn't consumed from storage busses." Items show in the network but don't get consumed by the craft. If you manually extract and re-insert the item via the terminal, it works.

- **[AE2 #8511](https://github.com/AppliedEnergistics/Applied-Energistics-2/issues/8511)** — Crafting plan miscounts items on External Storage Buses. Example: 64 copper ingots in a chest with storage bus → crafting plan claims 576 are available.

- **[AE2 #1868](https://github.com/AppliedEnergistics/Applied-Energistics-2/issues/1868)** — Storage buses + compacting drawers + drawer controllers: crafting window shows items available, button is clickable, but nothing happens. Works fine when items are on ME drives.

### Why "Request 2" Reveals the Problem

When you request 2 robot arms:
1. AE2's planner sees it needs sub-components and can't reliably count external storage → decides to **craft** them
2. Freshly crafted items go into the **ME drive storage** (not back into drawers)
3. First robot arm succeeds because it uses the freshly-crafted items from ME drives
4. Second robot arm fails because it tries to use the *original* items that are on storage buses → extraction fails → "not enough ingredients"

This is the smoking gun that your items are visible but not extractable.

### How to Confirm

1. Move ALL the ingredients for one robot arm directly onto **ME storage drives** (take them out of drawers/external storage)
2. Request 1 robot arm
3. If it works → your storage buses are the problem

## Other Possible Causes

### 2. Items Stuck in ME Interface Buffers

If a previous crafting job left items buffered inside an ME Interface (waiting to push into a busy/full machine), those items appear as "available" to the network but are actually committed to that interface's pending operation.

**Check:** Open every ME Interface GUI. If any have items sitting in them, clear them out or wait for the machine to accept them.

### 3. Channel Issues

If an interface or storage bus intermittently loses its channel (8/8 on normal cable, 32/32 on dense cable), it can see items one moment and not the next. This would explain the "random" nature.

**Check:** Use a Network Tool on your ME Controller. Verify all devices have active channels. Look for cables at capacity.

### 4. NBT Mismatches (GregTech-Specific)

GregTech items sometimes carry invisible NBT data (charge level, damage values). The terminal displays them as the same item, but the crafting system treats them as different items.

**Check:** Can you manually stack a freshly-crafted version of the ingredient with the stored version? If not, they have different NBT and you need to re-encode your patterns.

### 5. ME Interface Priority Bug (Open Issue)

**[AE2-Unofficial #951](https://github.com/GTNewHorizons/Applied-Energistics-2-Unofficial/issues/951)** — ME Interface priorities are non-functional in the GTNH fork. If you have two processing patterns that can make the same intermediate item, AE2 may pick the wrong one (including one that creates a circular dependency), causing "Missing: Item X" even though a simpler recipe exists.

**Check:** Do you have multiple patterns that output the same item? If so, remove the more complex one or consolidate to a single pattern per output.

### 6. Crafting CPU Size Limits

**[GTNH #18412](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/18412)** — Very large crafting requests can cause the Start button to silently fail. This depends on request size, not recipe complexity.

**Check:** Does the craft work if you request a smaller quantity?

## Fixes & Workarounds

### Immediate Fix
**Move crafting ingredients onto ME Drives.** Don't rely on storage bus-connected external inventories for autocrafting. Use drawers/barrels for bulk overflow storage, but keep autocrafting materials on cells.

### Long-Term Best Practices

1. **Use ME Level Maintainers** to keep common sub-components (motors, pistons, circuits, cables, rods) pre-stocked in ME storage. This avoids deep recursive crafting trees entirely.

2. **Enable Blocking Mode** on interfaces attached to machines. This prevents recipe conflicts and item buffering issues.

3. **Flatten crafting trees** with "fake patterns" — instead of letting AE2 recursively craft Motor → Rod + Cable → ..., create flat patterns that go from raw materials directly to the final product.

4. **Avoid Storage Buses on Drawer Controllers** — the GTNH wiki specifically warns against this as it causes lag and extraction issues. Use individual storage buses on individual drawers if needed.

5. **Keep crafting requests reasonable** — break large batch requests into smaller ones (e.g., 16 at a time instead of 64).

6. **Update your GTNH pack** — several of these bugs were fixed in late 2024/early 2025 patches to Applied-Energistics-2-Unofficial (PRs #627, #645).

## Relevant GTNH GitHub Issues

| Issue | Description | Status |
|-------|-------------|--------|
| [GTNH #18009](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/18009) | Storage bus items not consumed by crafts | **Fixed** (AE2-Unofficial #627, Dec 2024) |
| [GTNH #18079](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/18079) | Large crafts fail to start, can void items | **Fixed** (AE2-Unofficial #645, Jan 2025) |
| [GTNH #19722](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/19722) | Autocrafting doesn't properly complete all sub-crafts | Open (Ready for Developer) |
| [GTNH #18412](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/18412) | Start button stops working on large requests | Open |
| [AE2-Unofficial #951](https://github.com/GTNewHorizons/Applied-Energistics-2-Unofficial/issues/951) | ME Interface priority ignored (wrong pattern selected) | Open |
| [GTNH #21793](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack/issues/21793) | Fluid crafts stuck, ingredients partially submitted | Open |
| [AE2 #8511](https://github.com/AppliedEnergistics/Applied-Energistics-2/issues/8511) | External Storage Bus causes item miscounting | Upstream bug |

## Quick Diagnostic Checklist

- [ ] Do you use Storage Buses to connect drawers/barrels/external inventories? → **Move items to ME drives**
- [ ] Are any ME Interfaces holding items in their buffer slots? → **Clear them**
- [ ] Do all your interfaces and buses have active channels? → **Check with Network Tool**
- [ ] Do stored items stack with freshly-crafted versions? → **Re-encode patterns if not**
- [ ] Do you have duplicate patterns for the same output item? → **Remove duplicates**
- [ ] Are you on the latest GTNH nightly? → **Update for bug fixes**
- [ ] Does the craft work with a smaller request size? → **Break into batches**
