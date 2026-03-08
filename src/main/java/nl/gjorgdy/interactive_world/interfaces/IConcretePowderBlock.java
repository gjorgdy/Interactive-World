package nl.gjorgdy.interactive_world.interfaces;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public interface IConcretePowderBlock {

    Block interactiveWorld$getHardenedState();
    Item interactiveWorld$getHardenedStateItem();
    ItemStack interactiveWorld$getHardenedStateDefaultItemStack();

}
