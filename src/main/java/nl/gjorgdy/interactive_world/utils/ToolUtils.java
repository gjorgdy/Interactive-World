package nl.gjorgdy.interactive_world.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ToolUtils {

    public static boolean isPickaxe(ItemStack item) {
        return
            item.is(Items.WOODEN_PICKAXE)
            || item.is(Items.STONE_PICKAXE)
            || item.is(Items.IRON_PICKAXE)
            || item.is(Items.GOLDEN_PICKAXE)
            || item.is(Items.DIAMOND_PICKAXE)
            || item.is(Items.NETHERITE_PICKAXE);
    }

}
