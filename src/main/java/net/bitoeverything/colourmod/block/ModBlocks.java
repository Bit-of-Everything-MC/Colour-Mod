package net.bitoeverything.colourmod.block;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.custom.*;
import net.bitoeverything.colourmod.item.ModItems;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ColourMod.MOD_ID);

    public static final Map<PigmentColor, PigmentBlockSet> pigmentBlocks = new TreeMap<>((o1, o2) -> {
        if(o1.getId() == o2.getId()) return 0;
        return o1.getId() > o2.getId() ? 1 : -1;
    });

    static {
        for(PigmentColor pigment : PigmentColor.values()) {
            var pigmentItem = ModItems.ITEMS.register(pigment.getSerializedName() + "_pigment",
                    () -> new PigmentItem(pigment, new Item.Properties()));

            var wool = registerBlock(pigment.getSerializedName() + "_wool",
                    () -> new ModWoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(pigment.getMapColor())));

            var carpet = registerBlock(pigment.getSerializedName() + "_carpet",
                    () -> new ModWoolCarpetBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).mapColor(pigment.getMapColor())));

            var concrete = registerBlock(pigment.getSerializedName() + "_concrete",
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).mapColor(pigment.getMapColor())));

            var concretePowder = registerBlock(pigment.getSerializedName() + "_concrete_powder",
                    () -> new ConcretePowderBlock(concrete.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).mapColor(pigment.getMapColor())));

            var concreteSlab = registerBlock(pigment.getSerializedName() + "_concrete_slab", () -> new SlabBlock(
                            BlockBehaviour.Properties.ofFullCopy(concrete.get())));

            var concreteStair = registerBlock(pigment.getSerializedName() + "_concrete_stairs", () -> new StairBlock(
                    Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(concrete.get())));

            var concreteWall = registerBlock(pigment.getSerializedName() + "_concrete_wall", () -> new WallBlock(
                    BlockBehaviour.Properties.ofFullCopy(concrete.get())));

            var stainedGlass = registerBlock(pigment.getSerializedName() + "_stained_glass",
                    () -> new ModStainedGlassBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(pigment.getMapColor())));

            var stainedGlassPane = registerBlock(pigment.getSerializedName() + "_stained_glass_pane",
                    () -> new ModStainedGlassPaneBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(pigment.getMapColor())));

            PigmentBlockSet pigmentBlockSet = new PigmentBlockSet(pigmentItem, wool, carpet, concretePowder, concrete,
                    concreteStair, concreteSlab, concreteWall, stainedGlass, stainedGlassPane);
            pigmentBlocks.put(pigment, pigmentBlockSet);
        }
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
