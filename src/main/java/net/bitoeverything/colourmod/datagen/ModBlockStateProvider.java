package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
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
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            simpleBlockWithItem(blockSet.getValue().Wool.get(), tintableCubeAll(blockSet.getValue().Wool.getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, "white_wool"));
            simpleBlockWithItem(blockSet.getValue().Carpet.get(), tintableCarpet(blockSet.getValue().Carpet.getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, "white_wool"));
            simpleBlockWithItem(blockSet.getValue().Concrete.get(), tintableCubeAll(blockSet.getValue().Concrete.getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, "white_concrete"));
            simpleBlockWithItem(blockSet.getValue().ConcretePowder.get(), tintableCubeAll(blockSet.getValue().ConcretePowder.getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, "white_concrete_powder"));
        }
    }

    public ModelFile tintableCubeAll(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_cube_all"), "all", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }

    public ModelFile tintableCarpet(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_carpet"), "wool", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }
}
