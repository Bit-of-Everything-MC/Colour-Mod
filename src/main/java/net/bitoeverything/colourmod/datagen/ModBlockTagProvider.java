package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
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
        addAllTags(ModBlocks.woolBlocks, BlockTags.WOOL);
        addAllTags(ModBlocks.carpetBlocks, BlockTags.WOOL_CARPETS);
        addAllTags(ModBlocks.concreteBlocks, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.CONCRETES);
        addAllTags(ModBlocks.concretePowderBlocks, BlockTags.CONCRETE_POWDER, BlockTags.MINEABLE_WITH_SHOVEL);
    }

    @SafeVarargs
    protected final void addAllTags(Map<PigmentColor, DeferredBlock<Block>> map, TagKey<Block>... tags) {
        for(DeferredBlock<Block> element : map.values()) {
            for(TagKey<Block> tag : tags) {
                this.tag(tag).add(element.get());
            }
        }
    }
}
