package net.bitoeverything.colourmod.block;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.custom.ModWoolBlock;
import net.bitoeverything.colourmod.block.custom.ModWoolCarpetBlock;
import net.bitoeverything.colourmod.item.ModItems;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ColourMod.MOD_ID);
    public static final Map<DeferredBlock<Block>, PigmentColor> woolBlocks = new HashMap<>();
    public static final Map<DeferredBlock<Block>, PigmentColor> carpetBlocks = new HashMap<>();
    public static final Map<DeferredBlock<Block>, PigmentColor> concreteBlocks = new HashMap<>();
    public static final Map<DeferredBlock<Block>, PigmentColor> concretePowderBlocks = new HashMap<>();

    static {
        for(PigmentColor pigment : PigmentColor.values()) {
            woolBlocks.put(registerBlock(pigment.getSerializedName() + "_wool",
                    () -> new ModWoolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(pigment.getMapColor()))), pigment);

            carpetBlocks.put(registerBlock(pigment.getSerializedName() + "_carpet",
                    () -> new ModWoolCarpetBlock(pigment, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).mapColor(pigment.getMapColor()))), pigment);

            DeferredBlock<Block> currentConcrete = registerBlock(pigment.getSerializedName() + "_concrete",
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).mapColor(pigment.getMapColor())));
            concreteBlocks.put(currentConcrete, pigment);

            concretePowderBlocks.put(registerBlock(pigment.getSerializedName() + "_concrete_powder",
                    () -> new ConcretePowderBlock(currentConcrete.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).mapColor(pigment.getMapColor()))), pigment);
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
