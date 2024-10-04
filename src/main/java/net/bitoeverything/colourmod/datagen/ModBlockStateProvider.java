package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.block.custom.ModStainedGlassPaneBlock;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.IronBarsBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
            simpleBlockWithItem(blockSet.getValue().StainedGlass.get(), tintableCubeAllWithRenderType(blockSet.getValue().StainedGlass.getRegisteredName(), ResourceLocation.DEFAULT_NAMESPACE, "white_stained_glass", "translucent"));
            tintablePane(blockSet.getValue().StainedGlassPane.get(), blockSet.getValue().StainedGlassPane.getRegisteredName(), "white_stained_glass", "white_stained_glass_pane_top", "translucent");
        }
    }

    public ModelFile tintableCubeAll(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_cube_all"), "all", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }

    public ModelFile tintableCubeAllWithRenderType(String name, String namespace, String texture, String renderType) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_cube_all"), "all", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture)).renderType(renderType);
    }

    public ModelFile tintableCarpet(String name, String namespace, String texture) {
        return this.models().singleTexture(name, ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, "template_tint_carpet"), "wool", ResourceLocation.fromNamespaceAndPath(namespace, "block/" + texture));
    }

    public void tintablePane(ModStainedGlassPaneBlock block, String name, String sideTexture, String topTexture, String renderType) {
            paneBlockInternalWithRenderType(block, name.replace("colourmod:",""), ResourceLocation.withDefaultNamespace("block/" + sideTexture), ResourceLocation.withDefaultNamespace("block/" + topTexture), renderType);
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
}
