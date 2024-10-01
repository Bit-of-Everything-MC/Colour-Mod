package net.bitoeverything.colourmod.item;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.bitoeverything.colourmod.item.pigments.PigmentItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ColourMod.MOD_ID);
    public static final Map<DeferredItem<Item>, PigmentColor> pigmentItems = new HashMap<>();

    static {
        for(PigmentColor pigment : PigmentColor.values()) {
            pigmentItems.put(ITEMS.register(pigment.getSerializedName() + "_pigment",
                    () -> new PigmentItem(pigment, new Item.Properties())), pigment);
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
