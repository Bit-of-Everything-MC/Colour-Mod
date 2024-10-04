package net.bitoeverything.colourmod.block;

import net.bitoeverything.colourmod.block.custom.ModStainedGlassBlock;
import net.bitoeverything.colourmod.block.custom.ModStainedGlassPaneBlock;
import net.bitoeverything.colourmod.block.custom.ModWoolBlock;
import net.bitoeverything.colourmod.block.custom.ModWoolCarpetBlock;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentItem;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class PigmentBlockSet {
    public DeferredItem<PigmentItem> Pigment;
    
    public DeferredBlock<ModWoolBlock> Wool;
    public DeferredBlock<ConcretePowderBlock> ConcretePowder;
    public DeferredBlock<Block> Concrete;
    public DeferredBlock<ModWoolCarpetBlock> Carpet;
    public DeferredBlock<StairBlock> ConcreteStair;
    public DeferredBlock<SlabBlock> ConcreteSlab;
    public DeferredBlock<WallBlock> ConcreteWall;
    public DeferredBlock<ModStainedGlassBlock> StainedGlass;
    public DeferredBlock<ModStainedGlassPaneBlock> StainedGlassPane;
    
    public PigmentBlockSet(DeferredItem<PigmentItem> pigment,
                           DeferredBlock<ModWoolBlock> wool, DeferredBlock<ModWoolCarpetBlock> carpet,
                           DeferredBlock<ConcretePowderBlock> concretePowder, DeferredBlock<Block> concrete,
                           DeferredBlock<StairBlock> concreteStair, DeferredBlock<SlabBlock> concreteSlab,
                           DeferredBlock<WallBlock> concreteWall, DeferredBlock<ModStainedGlassBlock> stainedGlass,
                           DeferredBlock<ModStainedGlassPaneBlock> stainedGlassPane) {
        Pigment = pigment;
        Wool = wool;
        Carpet = carpet;
        Concrete = concrete;
        ConcretePowder = concretePowder;
        ConcreteStair = concreteStair;
        ConcreteSlab = concreteSlab;
        ConcreteWall = concreteWall;
        StainedGlass = stainedGlass;
        StainedGlassPane = stainedGlassPane;
    }
}
