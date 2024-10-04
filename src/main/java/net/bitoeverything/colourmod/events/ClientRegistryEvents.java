package net.bitoeverything.colourmod.events;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.PigmentBlockSet;
import net.bitoeverything.colourmod.item.ModItems;

import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.pigments.PigmentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import oshi.util.tuples.Quartet;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(value = Dist.CLIENT, modid = ColourMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            registerPigmentItemPigmentColor(event, blockSet.getValue().Pigment.get());
            registerItemPigmentColor(event, blockSet.getValue().Wool.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().Carpet.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().Concrete.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().ConcretePowder.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().ConcreteStair.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().ConcreteSlab.asItem(), blockSet.getKey());
            registerItemPigmentColor(event, blockSet.getValue().ConcreteWall.asItem(), blockSet.getKey());
        }
        
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            registerBlockPigmentColor(event, blockSet.getValue().Wool.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().Carpet.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().Concrete.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().ConcretePowder.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteStair.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteSlab.get(), blockSet.getKey());
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteWall.get(), blockSet.getKey());
        }
    }

    public static void registerPigmentItemPigmentColor(RegisterColorHandlersEvent.Item event, PigmentItem item) {
        event.register(item.getItemColor(), item);
    }

    public static void registerItemPigmentColor(RegisterColorHandlersEvent.Item event, Item item, PigmentColor pigmentColor) {
        event.register((stack, tintIndex) -> pigmentColor.getColor(), item);
    }

    public static void registerBlockPigmentColor(RegisterColorHandlersEvent.Block event, Block block, PigmentColor pigmentColor) {
        event.register((state, level, pos, tintIndex) -> pigmentColor.getColor(), block);
    }
}

