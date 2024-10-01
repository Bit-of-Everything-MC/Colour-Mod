package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ColourMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerTintableCubeAllBlocksWithItems(ModBlocks.woolBlocks, "white_wool");
        tintableCarpetWithItem(ModBlocks.carpetBlocks, "white_wool");
        registerTintableCubeAllBlocksWithItems(ModBlocks.concreteBlocks, "white_concrete");
        registerTintableCubeAllBlocksWithItems(ModBlocks.concretePowderBlocks, "white_concrete_powder");
    }

    public void tintableCubeAllWithItem(DeferredBlock<?> deferredBlock, String name, String namespace, String texture) {
        simpleBlockWithItem(deferredBlock.get(), tintableCubeAll(name, namespace, texture));
    }

    public ModelFile tintableCubeAll(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_cube_all"), "all", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }

    public ModelFile tintableCarpet(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_carpet"), "wool", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }

    public void tintableCarpetWithItem(Map<DeferredBlock<Block>, PigmentColor> map, String texture) {
        for(Map.Entry<DeferredBlock<Block>, PigmentColor> element: map.entrySet()) {
            simpleBlockWithItem(element.getKey().get(), tintableCarpet(element.getKey().getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, texture));
        }
    }

    public void registerTintableCubeAllBlocksWithItems(Map<DeferredBlock<Block>, PigmentColor> map, String parentTexture) {
        for(Map.Entry<DeferredBlock<Block>, PigmentColor> element: map.entrySet()) {
            tintableCubeAllWithItem(element.getKey(), element.getKey().getRegisteredName(),
                    ResourceLocation.DEFAULT_NAMESPACE, parentTexture);
        }
    }
}
