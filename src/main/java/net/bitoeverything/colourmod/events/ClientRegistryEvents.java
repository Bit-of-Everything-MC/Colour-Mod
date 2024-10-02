package net.bitoeverything.colourmod.events;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.block.ModBlocks;
import net.bitoeverything.colourmod.block.custom.ModWoolBlock;
import net.bitoeverything.colourmod.block.custom.ModWoolCarpetBlock;
import net.bitoeverything.colourmod.item.ModItems;

import net.bitoeverything.colourmod.item.custom.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.custom.pigments.PigmentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.spongepowered.asm.mixin.Interface;
import oshi.util.tuples.Quartet;

import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(value = Dist.CLIENT, modid = ColourMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for(Map.Entry<PigmentColor, DeferredItem<Item>> pigmentItem : ModItems.pigmentItems.entrySet()) {
            registerPigmentItemPigmentColor(event, (PigmentItem)pigmentItem.getValue().get());
        }

        registerAllItemPigmentColors(event, ModBlocks.woolBlocks, 1.15f);
        registerAllItemPigmentColors(event, ModBlocks.carpetBlocks, 1.15f);
        registerAllItemPigmentColors(event, ModBlocks.concreteBlocks, 1.0f);
        registerAllItemPigmentColors(event, ModBlocks.concretePowderBlocks, 1.15f);
        registerAllItemPigmentColors(event, ModBlocks.glassBlocks, 1.0f);
        registerAllItemPigmentColors(event, ModBlocks.glassPaneBlocks, 1.0f);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        registerAllBlockPigmentColors(event, ModBlocks.woolBlocks, 1.15f);
        registerAllBlockPigmentColors(event, ModBlocks.carpetBlocks, 1.15f);
        registerAllBlockPigmentColors(event, ModBlocks.concreteBlocks, 1.0f);
        registerAllBlockPigmentColors(event, ModBlocks.concretePowderBlocks, 1.15f);
        registerAllBlockPigmentColors(event, ModBlocks.glassBlocks, 1.0f);
        registerAllBlockPigmentColors(event, ModBlocks.glassPaneBlocks, 1.0f);
    }

    public static void registerPigmentItemPigmentColor(RegisterColorHandlersEvent.Item event, PigmentItem item) {
        event.register(item.getItemColor(), item);
    }

    public static void registerBlockPigmentColor(RegisterColorHandlersEvent.Block event, Block block, PigmentColor pigmentColor, float multiplier) {
        event.register((state, level, pos, tintIndex) -> (PigmentColor.quartetToInt(colorShift(pigmentColor.getARGB(), multiplier,
                (q, m) -> {
                    int r = Math.min(255, Math.max(0, (int)(q.getB() * m)));
                    int g = Math.min(255, Math.max(0, (int)(q.getC() * m)));
                    int b = Math.min(255, Math.max(0, (int)(q.getD() * m)));
                    Quartet<Byte, Byte, Byte, Byte> quar = new Quartet<>((byte)((int)q.getA()), (byte)(r), (byte)(g), (byte)(b));
                    return quar;
                }))), block);
    }

    public static void registerAllBlockPigmentColors(RegisterColorHandlersEvent.Block event, Map<PigmentColor, DeferredBlock<Block>> map, float multiplier) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element : map.entrySet()) {
            if(element.getKey() == PigmentColor.EGGSHELL) {
                registerBlockPigmentColor(event, element.getValue().get(), element.getKey(), 1.0f);
            } else {
                registerBlockPigmentColor(event, element.getValue().get(), element.getKey(), multiplier);
            }
        }
    }

    public static void registerItemPigmentColor(RegisterColorHandlersEvent.Item event, Item item, PigmentColor pigmentColor, float multiplier) {
        event.register((stack, tintIndex) -> (PigmentColor.quartetToInt(colorShift(pigmentColor.getARGB(), multiplier,
                (q, m) -> {
                    int r = Math.min(255, Math.max(0, (int)(q.getB() * m)));
                    int g = Math.min(255, Math.max(0, (int)(q.getC() * m)));
                    int b = Math.min(255, Math.max(0, (int)(q.getD() * m)));
                    return new Quartet<>((byte)(q.getA() & 0xFF), (byte)(r & 0xFF), (byte)(g & 0xFF), (byte)(b & 0xFF));
                }))), item);
    }

    public static void registerAllItemPigmentColors(RegisterColorHandlersEvent.Item event, Map<PigmentColor, DeferredBlock<Block>> map, float multiplier) {
        for(Map.Entry<PigmentColor, DeferredBlock<Block>> element : map.entrySet()) {
            if(element.getKey() == PigmentColor.EGGSHELL) {
                registerItemPigmentColor(event, element.getValue().asItem(), element.getKey(), 1.0f);
            } else {
                registerItemPigmentColor(event, element.getValue().asItem(), element.getKey(), multiplier);
            }
        }
    }

    public static Quartet<Byte, Byte, Byte, Byte> colorShift(Quartet<Integer, Integer, Integer, Integer> q, float m, IColorShift instance) {
        return instance.ColorShift(q, m);
    }

    public interface IColorShift {
        Quartet<Byte, Byte, Byte, Byte> ColorShift(Quartet<Integer, Integer, Integer, Integer> q, float m);
    }
}
