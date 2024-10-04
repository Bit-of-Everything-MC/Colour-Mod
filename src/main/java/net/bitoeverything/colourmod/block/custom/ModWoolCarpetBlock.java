package net.bitoeverything.colourmod.block.custom;

import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModWoolCarpetBlock extends CarpetBlock {
    private PigmentColor pigmentColor;

    public ModWoolCarpetBlock(PigmentColor color, Properties properties) {
        super(properties);
        this.pigmentColor = color;
    }

    public PigmentColor getPigmentColor() {
        return pigmentColor;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
    }
}
