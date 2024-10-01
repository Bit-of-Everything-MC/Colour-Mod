package net.bitoeverything.colourmod.item;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColourMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MOD_COLORED_BLOCKS_TAB = CREATIVE_MODE_TABS.register("mod_colored_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.pigmentItems.keySet().iterator().next().get()))
                    .title(Component.translatable("creativetab.colourmod.mod_colored_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for(Map.Entry<DeferredItem<Item>, PigmentColor> pigmentItem : ModItems.pigmentItems.entrySet()) {
                            output.accept(pigmentItem.getKey().get());
                        }
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
