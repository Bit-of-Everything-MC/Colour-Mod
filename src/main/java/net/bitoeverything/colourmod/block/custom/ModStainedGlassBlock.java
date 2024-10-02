package net.bitoeverything.colourmod.block.custom;

import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.world.level.block.TransparentBlock;

public class ModStainedGlassBlock extends TransparentBlock {
    private final PigmentColor pigmentColor;

    public ModStainedGlassBlock(PigmentColor color, Properties properties) {
        super(properties);
        this.pigmentColor = color;
    }

    public PigmentColor getPigmentColor() {
        return pigmentColor;
    }
}
