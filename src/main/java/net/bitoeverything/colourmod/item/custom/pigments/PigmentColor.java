package net.bitoeverything.colourmod.item.custom.pigments;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import oshi.util.tuples.Quartet;

import java.util.function.IntFunction;

public enum PigmentColor implements StringRepresentable {
    EGGSHELL(0, "eggshell", MapColor.TERRACOTTA_WHITE, "FFFFE4"),
    SLATE_GRAY(1, "slate_gray", MapColor.TERRACOTTA_LIGHT_BLUE, "657B90"),
    LIGHT_BROWN(2, "light_brown", MapColor.TERRACOTTA_LIGHT_GRAY, "9F6F55"),
    LIGHT_RED(3, "light_red", MapColor.TERRACOTTA_PINK, "FF7979"),
    SCARLET(4, "scarlet", MapColor.FIRE, "E02D00"),
    MAROON(5, "maroon", MapColor.CRIMSON_NYLIUM, "4C1019"),
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
    private final String hexColor;
    private final int color;
    private static final IntFunction<PigmentColor> BY_ID = ByIdMap.continuous(PigmentColor::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<PigmentColor> CODEC = StringRepresentable.fromEnum(PigmentColor::values);
    public static final StreamCodec<ByteBuf, PigmentColor> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, PigmentColor::getId);

    PigmentColor(int id, String name, MapColor mapColor, String hexColor) {
        this.id = id;
        this.name = name;
        this.mapColor = mapColor;
        this.hexColor = "FF" + hexColor;
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

    public Quartet<Integer, Integer, Integer, Integer> getARGB() {
        return new Quartet<>(Integer.parseInt(this.hexColor.substring(0, 2), 16),
                Integer.parseInt(this.hexColor.substring(2, 4), 16),
                Integer.parseInt(this.hexColor.substring(4, 6), 16),
                Integer.parseInt(this.hexColor.substring(6, 8), 16));
    }

    public static int quartetToInt(Quartet<Byte, Byte, Byte, Byte> quartet) {
        long unsigned = ((((quartet.getA() & 0xFF << 8 | quartet.getB() & 0xFF) << 8 | quartet.getC() & 0xFF) << 8) | quartet.getD() & 0xFF);
        return (int)unsigned;
    }
}
