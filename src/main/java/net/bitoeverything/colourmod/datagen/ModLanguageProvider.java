package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
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
        for(PigmentColor pigment : PigmentColor.values()) {
            this.add("item.colourmod." + pigment.getSerializedName() + "_pigment", StringUtils.capitaliseAllWords(pigment.getSerializedName().replace("_", " ")) + " Pigment");
        }

        addAllBlockTranslations(ModBlocks.woolBlocks);
        addAllBlockTranslations(ModBlocks.carpetBlocks);
        addAllBlockTranslations(ModBlocks.concreteBlocks);
        addAllBlockTranslations(ModBlocks.concretePowderBlocks);

        this.add("creativetab.colourmod.mod_colored_blocks", "Mod Colored Blocks");
    }

    protected void addAllBlockTranslations(Map<DeferredBlock<Block>, PigmentColor> map) {
        for(Map.Entry<DeferredBlock<Block>, PigmentColor> element: map.entrySet()) {
            this.add("block.colourmod." + element.getKey().getRegisteredName().replace("colourmod:",""), StringUtils.capitaliseAllWords(element.getKey().getRegisteredName().replace("_", " ").replace("colourmod:","")));
        }
    }
}
