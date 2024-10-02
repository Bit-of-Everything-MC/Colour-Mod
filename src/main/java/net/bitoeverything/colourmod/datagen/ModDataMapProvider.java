package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        gatherAllForFurnaceFuel(ModBlocks.woolBlocks, 100);
        gatherAllForFurnaceFuel(ModBlocks.carpetBlocks, 67);
    }

    protected void gatherAllForFurnaceFuel(Map<PigmentColor, DeferredBlock<Block>> map, int burnTime) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element: map.entrySet()) {
            this.builder(NeoForgeDataMaps.FURNACE_FUELS).add(element.getValue().getId(), new FurnaceFuel(burnTime), false);
        }
    }
}
