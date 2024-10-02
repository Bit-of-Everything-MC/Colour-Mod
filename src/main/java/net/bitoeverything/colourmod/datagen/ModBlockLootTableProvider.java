package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelfBlocks(ModBlocks.woolBlocks);
        dropSelfBlocks(ModBlocks.carpetBlocks);
        dropSelfBlocks(ModBlocks.concreteBlocks);
        dropSelfBlocks(ModBlocks.concretePowderBlocks);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected void dropSelfBlocks(Map<PigmentColor, DeferredBlock<Block>> map) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element: map.entrySet()) {
            dropSelf(element.getValue().get());
        }
    }
}
