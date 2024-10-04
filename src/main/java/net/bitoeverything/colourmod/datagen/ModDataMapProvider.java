package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
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
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            AddFurnaceFuel(blockSet.getValue().Wool, 100);
            AddFurnaceFuel(blockSet.getValue().Carpet, 67);
        }
    }
    
    protected void AddFurnaceFuel(DeferredBlock<? extends Block> block, int burnTime) {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS).add(block.getId(), new FurnaceFuel(burnTime), false);
    }
}
