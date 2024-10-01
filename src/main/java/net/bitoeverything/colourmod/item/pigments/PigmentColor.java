package net.bitoeverything.colourmod.item.pigments;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

public enum PigmentColor implements StringRepresentable {
    MAROON("maroon", MapColor.CRIMSON_NYLIUM, "4C1019"),
    DARK_ORANGE("dark_orange", MapColor.FIRE, "8B3800"),
    LIGHT_YELLOW("light_yellow", MapColor.SAND, "FFEAA2"),
    MINT("mint", MapColor.EMERALD, "C7FFCC"),
    FOREST_GREEN("forest_green", MapColor.GRASS, "1A861A"),
    TURQUOISE("turquoise", MapColor.WARPED_STEM, "39E2AF"),
    INDIGO("indigo", MapColor.TERRACOTTA_BLUE, "1A005E"),
    ROYAL_PURPLE("royal_purple", MapColor.COLOR_PURPLE, "480096");

    private final String name;
    private final MapColor mapColor;
    private final int color;

    PigmentColor(String name, MapColor mapColor, String hexColor) {
        this.name = name;
        this.mapColor = mapColor;
        this.color = (int)Long.parseLong("FF" + hexColor, 16);
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
