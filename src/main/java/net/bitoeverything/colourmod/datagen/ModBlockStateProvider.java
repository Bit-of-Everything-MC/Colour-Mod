package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
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
        registerTintableCubeAllBlocksWithItems(ModBlocks.glassBlocks, "white_stained_glass");
        registerTintablePaneWithItem(ModBlocks.glassPaneBlocks, "white_stained_glass",
                "white_stained_glass_pane_top", "translucent");
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

    public void tintableCarpetWithItem(Map<PigmentColor, DeferredBlock<Block>> map, String texture) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element: map.entrySet()) {
            simpleBlockWithItem(element.getValue().get(), tintableCarpet(element.getValue().getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, texture));
        }
    }

    public void registerTintablePaneWithItem(Map<PigmentColor, DeferredBlock<Block>> map, String sideTexture, String topTexture, String renderType) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element: map.entrySet()) {
            paneBlockInternalWithRenderType((IronBarsBlock) element.getValue().get(),
                    element.getValue().getRegisteredName().replace("colourmod:",""),
                    ResourceLocation.withDefaultNamespace("block/" + sideTexture),
                    ResourceLocation.withDefaultNamespace("block/" + topTexture), renderType);
        }
    }

    private void paneBlockInternalWithRenderType(IronBarsBlock block, String baseName, ResourceLocation pane, ResourceLocation edge, String renderType) {
        ModelFile post = this.models().withExistingParent(baseName + "_post",
                this.modLoc("block/template_tint_glass_pane_post")).texture("pane", pane).texture("edge", edge).renderType(renderType);
        ModelFile side = this.models().withExistingParent(baseName + "_side",
                this.modLoc("block/template_tint_glass_pane_side")).texture("pane", pane).texture("edge", edge).renderType(renderType);
        ModelFile sideAlt = this.models().withExistingParent(baseName + "_side_alt",
                this.modLoc("block/template_tint_glass_pane_side_alt")).texture("pane", pane).texture("edge", edge).renderType(renderType);
        ModelFile noSide = this.models().withExistingParent(baseName + "_noside",
                this.modLoc("block/template_tint_glass_pane_noside")).texture("pane", pane).renderType(renderType);
        ModelFile noSideAlt = this.models().withExistingParent(baseName + "_noside_alt",
                this.modLoc("block/template_tint_glass_pane_noside_alt")).texture("pane", pane).renderType(renderType);
        this.paneBlock(block, post, side, sideAlt, noSide, noSideAlt);
    }

    public void registerTintableCubeAllBlocksWithItems(Map<PigmentColor, DeferredBlock<Block>> map, String parentTexture) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element: map.entrySet()) {
            tintableCubeAllWithItem(element.getValue(), element.getValue().getRegisteredName(),
                    ResourceLocation.DEFAULT_NAMESPACE, parentTexture);
        }
    }
}
