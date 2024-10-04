package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ColourMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            addAllTags(blockSet.getValue().Wool.get(), BlockTags.WOOL);
            addAllTags(blockSet.getValue().Carpet.get(), BlockTags.WOOL_CARPETS);
            addAllTags(blockSet.getValue().Concrete.get(), BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.CONCRETES);
            addAllTags(blockSet.getValue().ConcretePowder.get(), BlockTags.CONCRETE_POWDER, BlockTags.MINEABLE_WITH_SHOVEL);
            addAllTags(blockSet.getValue().ConcreteStair.get(), BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE);
            addAllTags(blockSet.getValue().ConcreteSlab.get(), BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE);
            addAllTags(blockSet.getValue().ConcreteWall.get(), BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE);
            addAllTags(blockSet.getValue().StainedGlass.get(), Tags.Blocks.GLASS_BLOCKS_CHEAP);
            addAllTags(blockSet.getValue().StainedGlassPane.get(), Tags.Blocks.GLASS_PANES);
        }
    }
    
    @SafeVarargs
    protected final void addAllTags(Block block, TagKey<Block>... tags) {
        for(TagKey<Block> tag : tags) {
            this.tag(tag).add(block);
        }
    }
}
