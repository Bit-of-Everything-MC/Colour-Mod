package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            dropSelf(blockSet.getValue().Wool.get());
            dropSelf(blockSet.getValue().Carpet.get());
            dropSelf(blockSet.getValue().Concrete.get());
            dropSelf(blockSet.getValue().ConcretePowder.get());
            dropSelf(blockSet.getValue().ConcreteStair.get());
            dropSlab(blockSet.getValue().ConcreteSlab.get());
            dropSelf(blockSet.getValue().ConcreteWall.get());
            dropWhenSilkTouch(blockSet.getValue().StainedGlass.get());
            dropWhenSilkTouch(blockSet.getValue().StainedGlassPane.get());
        }
    }
    
    private void dropSlab(Block block) {
        this.add(block, createSlabItemTable(block));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
