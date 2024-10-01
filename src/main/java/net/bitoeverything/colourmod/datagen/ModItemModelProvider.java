package net.bitoeverything.colourmod.datagen;

import net.bitoeverything.colourmod.ColourMod;
import net.bitoeverything.colourmod.item.pigments.PigmentColor;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ColourMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(PigmentColor pigment : PigmentColor.values()) {
            basicItem(ResourceLocation.fromNamespaceAndPath(ColourMod.MOD_ID, pigment.getSerializedName() + "_pigment"),
                    ColourMod.MOD_ID, "template_pigment");
        }
    }

    public ItemModelBuilder basicItem(ResourceLocation item, String namespace, String texture) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(namespace, "item/" + texture));
    }
}
