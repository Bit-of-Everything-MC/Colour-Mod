package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, ColourMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for(PigmentColor pigment : PigmentColor.values()) {
            this.add("item.colourmod." + pigment.getSerializedName() + "_pigment", StringUtils.capitaliseAllWords(pigment.getSerializedName().replace("_", " ")) + " Pigment");
        }
        this.add("creativetab.colourmod.mod_colored_blocks", "Mod Colored Blocks");
    }
}
