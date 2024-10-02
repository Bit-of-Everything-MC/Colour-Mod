package net.bitoeverything.colourmod.block;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.custom.*;
import net.bitoeverything.colourmod.item.ModItems;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ColourMod.MOD_ID);
    public static final Map<PigmentColor, DeferredBlock<Block>> woolBlocks = new TreeMap<>(ModBlocks::compare);
    public static final Map<PigmentColor, DeferredBlock<Block>> carpetBlocks = new TreeMap<>(ModBlocks::compare);
    public static final Map<PigmentColor, DeferredBlock<Block>> concreteBlocks = new TreeMap<>(ModBlocks::compare);
    public static final Map<PigmentColor, DeferredBlock<Block>> concretePowderBlocks = new TreeMap<>(ModBlocks::compare);
    public static final Map<PigmentColor, DeferredBlock<Block>> glassBlocks = new TreeMap<>(ModBlocks::compare);
    public static final Map<PigmentColor, DeferredBlock<Block>> glassPaneBlocks = new TreeMap<>(ModBlocks::compare);

    static {
        for(PigmentColor pigment : PigmentColor.values()) {
            woolBlocks.put(pigment, registerBlock(pigment.getSerializedName() + "_wool",
                    () -> new ModWoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(pigment.getMapColor()))));

            carpetBlocks.put(pigment, registerBlock(pigment.getSerializedName() + "_carpet",
                    () -> new ModWoolCarpetBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).mapColor(pigment.getMapColor()))));

            DeferredBlock<Block> currentConcrete = registerBlock(pigment.getSerializedName() + "_concrete",
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).mapColor(pigment.getMapColor())));
            concreteBlocks.put(pigment, currentConcrete);

            concretePowderBlocks.put(pigment, registerBlock(pigment.getSerializedName() + "_concrete_powder",
                    () -> new ConcretePowderBlock(currentConcrete.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).mapColor(pigment.getMapColor()))));

            glassBlocks.put(pigment, registerBlock(pigment.getSerializedName() + "_stained_glass",
                    () -> new ModStainedGlassBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(pigment.getMapColor()))));

            glassPaneBlocks.put(pigment, registerBlock(pigment.getSerializedName() + "_stained_glass_pane",
                    () -> new ModStainedGlassPaneBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(pigment.getMapColor()))));
        }
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerOnlyBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static int compare(PigmentColor o1, PigmentColor o2) {
        if(o1.getId() == o2.getId()) return 0;
        return o1.getId() > o2.getId() ? 1 : -1;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
