package net.bitoeverything.colouredmod.datagen;

import net.bitoeverything.colouredmod.ColouredMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ColouredMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
