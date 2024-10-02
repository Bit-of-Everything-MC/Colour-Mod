package net.bitoeverything.colourmod.item.pigments;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

public enum PigmentColor implements StringRepresentable {
    OFF_WHITE(0, "off_white", MapColor.TERRACOTTA_WHITE, "FFFFE4"),
    SLATE_GRAY(1, "slate_gray", MapColor.TERRACOTTA_LIGHT_BLUE, "657B90"),
    LIGHT_BROWN(2, "light_brown", MapColor.TERRACOTTA_LIGHT_GRAY, "9F6F55"),
    LIGHT_RED(3, "light_red", MapColor.TERRACOTTA_PINK, "FF7979"),
    MAROON(4, "maroon", MapColor.CRIMSON_NYLIUM, "4C1019"),
    SCARLET(5, "scarlet", MapColor.FIRE, "E02D00"),
    DARK_ORANGE(6, "dark_orange", MapColor.TERRACOTTA_ORANGE, "8B3800"),
    LIGHT_ORANGE(7, "light_orange", MapColor.COLOR_ORANGE, "FFBF6D"),
    LIGHT_YELLOW(8, "light_yellow", MapColor.SAND, "FFEB78"),
    FOREST_GREEN(9, "forest_green", MapColor.GRASS, "1A861A"),
    MINT(10, "mint", MapColor.EMERALD, "88FFB4"),
    TURQUOISE(11, "turquoise", MapColor.WARPED_STEM, "39E2AF"),
    NAVY(12, "navy", MapColor.TERRACOTTA_BLUE, "1A005E"),
    INDIGO(13, "indigo", MapColor.TERRACOTTA_BLUE, "35008A"),
    ROYAL_PURPLE(14, "royal_purple", MapColor.COLOR_PURPLE, "480096"),
    LIGHT_PURPLE(15, "light_purple", MapColor.COLOR_PURPLE, "C37EFF");

    private final int id;
    private final String name;
    private final MapColor mapColor;
    private final int color;

    PigmentColor(int id, String name, MapColor mapColor, String hexColor) {
        this.id = id;
        this.name = name;
        this.mapColor = mapColor;
        this.color = (int)Long.parseLong("FF" + hexColor, 16);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }

    public int getColor() {
        return this.color;
    }
}
