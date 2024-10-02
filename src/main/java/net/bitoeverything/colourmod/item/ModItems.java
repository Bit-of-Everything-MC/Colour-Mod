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
    public static final Map<PigmentColor, DeferredItem<Item>> pigmentItems = new TreeMap<>((o1, o2) -> {
        if(o1.getId() == o2.getId()) return 0;
        return o1.getId() > o2.getId() ? 1 : -1;
    });

    static {
        for(PigmentColor pigment : PigmentColor.values()) {
            pigmentItems.put(pigment, ITEMS.register(pigment.getSerializedName() + "_pigment",
                    () -> new PigmentItem(pigment, new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
