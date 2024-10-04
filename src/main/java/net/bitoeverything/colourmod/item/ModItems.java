package net.bitoeverything.colourmod.item;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.pigments.PigmentItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ColourMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
