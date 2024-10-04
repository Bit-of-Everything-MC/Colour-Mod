package net.bitoeverything.colourmod.item;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColourMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MOD_COLORED_BLOCKS_TAB = CREATIVE_MODE_TABS.register("mod_colored_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.pigmentBlocks.get(PigmentColor.FOREST_GREEN).Pigment.get()))
                    .title(Component.translatable("creativetab.colourmod.mod_colored_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for(PigmentBlockSet blockSet : ModBlocks.pigmentBlocks.values()) {
                            AddBlock(blockSet.Wool.get(), output);
                            AddBlock(blockSet.Carpet.get(), output);
                            AddBlock(blockSet.Concrete.get(), output);
                            AddBlock(blockSet.ConcretePowder.get(), output);
                            AddBlock(blockSet.ConcreteStair.get(), output);
                            AddBlock(blockSet.ConcreteSlab.get(), output);
                            AddBlock(blockSet.ConcreteWall.get(), output);
                            AddBlock(blockSet.StainedGlass.get(), output);
                            AddBlock(blockSet.StainedGlassPane.get(), output);
                            AddItem(blockSet.Pigment.get(), output);
                        }
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static <T extends Block> void AddBlock(T block, CreativeModeTab.Output output) {
        output.accept(block);
    }
    
    public static <T extends Item> void AddItem(T item, CreativeModeTab.Output output) {
        output.accept(item);
    }
}
