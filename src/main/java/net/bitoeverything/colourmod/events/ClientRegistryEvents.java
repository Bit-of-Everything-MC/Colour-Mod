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

import java.util.Map;

@EventBusSubscriber(value = Dist.CLIENT, modid = ColourMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            registerPigmentItemPigmentColor(event, blockSet.getValue().Pigment.get());
            registerItemPigmentColor(event, blockSet.getValue().Wool.asItem(), blockSet.getKey(), 1.15f);
            registerItemPigmentColor(event, blockSet.getValue().Carpet.asItem(), blockSet.getKey(), 1.15f);
            registerItemPigmentColor(event, blockSet.getValue().Concrete.asItem(), blockSet.getKey(), 1.0f);
            registerItemPigmentColor(event, blockSet.getValue().ConcretePowder.asItem(), blockSet.getKey(), 1.15f);
            registerItemPigmentColor(event, blockSet.getValue().ConcreteStair.asItem(), blockSet.getKey(), 1.0f);
            registerItemPigmentColor(event, blockSet.getValue().ConcreteSlab.asItem(), blockSet.getKey(), 1.0f);
            registerItemPigmentColor(event, blockSet.getValue().ConcreteWall.asItem(), blockSet.getKey(), 1.0f);
        }
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        for(Map.Entry<PigmentColor, PigmentBlockSet> blockSet : ModBlocks.pigmentBlocks.entrySet()) {
            registerBlockPigmentColor(event, blockSet.getValue().Wool.get(), blockSet.getKey(), 1.15f);
            registerBlockPigmentColor(event, blockSet.getValue().Carpet.get(), blockSet.getKey(), 1.15f);
            registerBlockPigmentColor(event, blockSet.getValue().Concrete.get(), blockSet.getKey(), 1.0f);
            registerBlockPigmentColor(event, blockSet.getValue().ConcretePowder.get(), blockSet.getKey(), 1.15f);
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteStair.get(), blockSet.getKey(), 1.0f);
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteSlab.get(), blockSet.getKey(), 1.0f);
            registerBlockPigmentColor(event, blockSet.getValue().ConcreteWall.get(), blockSet.getKey(), 1.0f);
        }
    }

    public static void registerPigmentItemPigmentColor(RegisterColorHandlersEvent.Item event, PigmentItem item) {
        event.register(item.getItemColor(), item);
    }

    public static void registerBlockPigmentColor(RegisterColorHandlersEvent.Block event, Block block, PigmentColor pigmentColor, float multiplier) {
        if(pigmentColor != PigmentColor.OFF_WHITE) {
        event.register((state, level, pos, tintIndex) -> (PigmentColor.quartetToInt(colorShift(pigmentColor.getARGB(), multiplier,
                (q, m) -> {
                    int r = Math.min(255, Math.max(0, (int)(q.getB() * m)));
                    int g = Math.min(255, Math.max(0, (int)(q.getC() * m)));
                    int b = Math.min(255, Math.max(0, (int)(q.getD() * m)));
                    Quartet<Byte, Byte, Byte, Byte> quar = new Quartet<>((byte)((int)q.getA()), (byte)(r), (byte)(g), (byte)(b));
                    return quar;
                }))), block);
        }
    }

    public static void registerItemPigmentColor(RegisterColorHandlersEvent.Item event, Item item, PigmentColor pigmentColor, float multiplier) {
        if(pigmentColor != PigmentColor.OFF_WHITE) {
            event.register((stack, tintIndex) -> (PigmentColor.quartetToInt(colorShift(pigmentColor.getARGB(), multiplier,
                    (q, m) -> {
                        int r = Math.min(255, Math.max(0, (int)(q.getB() * m)));
                        int g = Math.min(255, Math.max(0, (int)(q.getC() * m)));
                        int b = Math.min(255, Math.max(0, (int)(q.getD() * m)));
                        return new Quartet<>((byte)(q.getA() & 0xFF), (byte)(r & 0xFF), (byte)(g & 0xFF), (byte)(b & 0xFF));
                    }))), item);
        }
    }

    public static Quartet<Byte, Byte, Byte, Byte> colorShift(Quartet<Integer, Integer, Integer, Integer> q, float m, IColorShift instance) {
        return instance.ColorShift(q, m);
    }

    public interface IColorShift {
        Quartet<Byte, Byte, Byte, Byte> ColorShift(Quartet<Integer, Integer, Integer, Integer> q, float m);
    }
}
