package net.bitoeverything.colourmod.events;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
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

import java.util.Map;

@EventBusSubscriber(value = Dist.CLIENT, modid = ColourMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for(Map.Entry<DeferredItem<Item>, PigmentColor> pigmentItem : ModItems.pigmentItems.entrySet()) {
            registerPigmentItemPigmentColor(event, (PigmentItem)pigmentItem.getKey().get());
        }

        registerAllItemPigmentColors(event, ModBlocks.woolBlocks);
        registerAllItemPigmentColors(event, ModBlocks.concreteBlocks);
        registerAllItemPigmentColors(event, ModBlocks.concretePowderBlocks);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        registerAllBlockPigmentColors(event, ModBlocks.woolBlocks);
        registerAllBlockPigmentColors(event, ModBlocks.concreteBlocks);
        registerAllBlockPigmentColors(event, ModBlocks.concretePowderBlocks);
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

    public static void registerAllBlockPigmentColors(RegisterColorHandlersEvent.Block event, Map<DeferredBlock<Block>, PigmentColor> map) {
        for(Map.Entry<DeferredBlock<Block>, PigmentColor> element : map.entrySet()) {
            registerBlockPigmentColor(event, element.getKey().get(), element.getValue());
        }
    }

    public static void registerAllItemPigmentColors(RegisterColorHandlersEvent.Item event, Map<DeferredBlock<Block>, PigmentColor> map) {
        for(Map.Entry<DeferredBlock<Block>, PigmentColor> element : map.entrySet()) {
            registerItemPigmentColor(event, element.getKey().asItem(), element.getValue());
        }
    }
}
