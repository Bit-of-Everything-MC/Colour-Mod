package net.bitoeverything.colourmod.block.custom;

import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.world.level.block.IronBarsBlock;

public class ModStainedGlassPaneBlock extends IronBarsBlock {
    private final PigmentColor pigmentColor;

    public ModStainedGlassPaneBlock(PigmentColor color, Properties properties) {
        super(properties);
        this.pigmentColor = color;
    }

    public PigmentColor getPigmentColor() {
        return pigmentColor;
    }
}
