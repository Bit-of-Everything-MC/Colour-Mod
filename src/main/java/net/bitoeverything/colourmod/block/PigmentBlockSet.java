package net.bitoeverything.colourmod.block;

import net.bitoeverything.colourmod.block.custom.ModWoolBlock;
import net.bitoeverything.colourmod.block.custom.ModWoolCarpetBlock;
import net.bitoeverything.colourmod.item.pigments.PigmentItem;
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
    
    public PigmentBlockSet(DeferredItem<PigmentItem> pigment,
                           DeferredBlock<ModWoolBlock> wool, DeferredBlock<ModWoolCarpetBlock> carpet,
                           DeferredBlock<ConcretePowderBlock> concretePowder, DeferredBlock<Block> concrete,
                           DeferredBlock<StairBlock> concreteStair, DeferredBlock<SlabBlock> concreteSlab, DeferredBlock<WallBlock> concreteWall
                           ) {
        Pigment = pigment;
        Wool = wool;
        Carpet = carpet;
        Concrete = concrete;
        ConcretePowder = concretePowder;
        ConcreteStair = concreteStair;
        ConcreteSlab = concreteSlab;
        ConcreteWall = concreteWall;
    }
}
