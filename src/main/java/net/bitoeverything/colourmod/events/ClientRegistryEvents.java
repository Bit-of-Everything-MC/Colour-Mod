package net.bitoeverything.colourmod.events;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.ModItems;

import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.pigments.PigmentItem;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

@EventBusSubscriber(value = Dist.CLIENT, modid = ColourMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for(Map.Entry<DeferredItem<Item>, PigmentColor> pigmentItem : ModItems.pigmentItems.entrySet()) {
            regPigmentColor(event, pigmentItem.getKey().get());
        }
    }

    public static void regPigmentColor(RegisterColorHandlersEvent.Item event, Item item) {
        if (item instanceof PigmentItem pigmentItem) {
            event.register(pigmentItem.getItemColor(), item);
        }
    }
}
