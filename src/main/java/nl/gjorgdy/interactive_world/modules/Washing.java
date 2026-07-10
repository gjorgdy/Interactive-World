package nl.gjorgdy.interactive_world.modules;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ColorCollection;

import java.util.function.BiConsumer;

public class Washing {

	public static void forConcrete(BiConsumer<Item, Item> consumer) {
		ColorCollection.zipApply(Items.CONCRETE_POWDER, Items.CONCRETE, consumer);
	}

}
