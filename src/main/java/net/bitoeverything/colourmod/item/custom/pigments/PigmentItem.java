package net.bitoeverything.colourmod.item.custom.pigments;

import com.google.common.collect.Maps;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;

import java.util.Map;

public class PigmentItem extends Item {
    private static final Map<PigmentColor, PigmentItem> ITEM_BY_COLOR = Maps.newEnumMap(PigmentColor.class);
    private final PigmentColor pigmentColor;

    public PigmentItem(PigmentColor color, Properties properties) {
        super(properties);
        this.pigmentColor = color;
        ITEM_BY_COLOR.put(color, this);
    }

    public ItemColor getItemColor() {
        return (pStack, pTintIndex) -> this.pigmentColor.getColor();
    }

    public PigmentColor getPigmentColor() {
        return this.pigmentColor;
    }

    public static PigmentItem byColor(PigmentColor color) {
        return ITEM_BY_COLOR.get(color);
    }
}
