package nl.gjorgdy.interactive_world.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import nl.gjorgdy.interactive_world.interfaces.IConcretePowderBlock;

public class ItemUtils {

    public static ItemStack hardenConcretePowder(final ItemStack stack) {
        if (Block.byItem(stack.getItem()) instanceof IConcretePowderBlock IConcretePowderBlock) {
            return IConcretePowderBlock.interactiveWorld$getHardenedStateDefaultItemStack();
        }
        return stack;
    }

    public static ItemStack drySponge(final ItemStack stack) {
        if (stack.is(Items.WET_SPONGE)) {
            return Items.SPONGE.getDefaultInstance();
        }
        return stack;
    }

    public static boolean canBecomeMud(final ItemStack stack) {
        return stack.is(Items.DIRT) || stack.is(Items.COARSE_DIRT)  || stack.is(Items.ROOTED_DIRT);
    }

    public static boolean isConcretePowder(Item item) {
        return BlockUtils.isConcretePowder(Block.byItem(item));
    }

}
