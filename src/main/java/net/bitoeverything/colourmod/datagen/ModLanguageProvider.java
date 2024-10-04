package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.codehaus.plexus.util.StringUtils;

import java.util.Map;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, ColourMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            this.add("item.colourmod." + blockSet.getKey().getSerializedName() + "_pigment", StringUtils.capitaliseAllWords(blockSet.getKey().getSerializedName().replace("_", " ")) + " Pigment");
            addSimpleBlockTranslation(blockSet.getValue().Wool);
            addSimpleBlockTranslation(blockSet.getValue().Carpet);
            addSimpleBlockTranslation(blockSet.getValue().Concrete);
            addSimpleBlockTranslation(blockSet.getValue().ConcretePowder);
            addSimpleBlockTranslation(blockSet.getValue().ConcreteStair);
            addSimpleBlockTranslation(blockSet.getValue().ConcreteSlab);
            addSimpleBlockTranslation(blockSet.getValue().ConcreteWall);
        }

        this.add("creativetab.colourmod.mod_colored_blocks", "Mod Colored Blocks");
    }

    private void addSimpleBlockTranslation(DeferredBlock<? extends Block> block) {
        this.add("block.colourmod." + block.getRegisteredName().replace("colourmod:",""), StringUtils.capitaliseAllWords(block.getRegisteredName().replace("_", " ").replace("colourmod:","")));
    }
}
