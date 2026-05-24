package nl.gjorgdy.interactive_world.modules;

import kotlin.Pair;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Washing {

	@Unique
	private static final List<Pair<Item, Item>> concrete = new ArrayList<>(){
		{
			add(new Pair<>(Items.BLACK_CONCRETE_POWDER, Items.BLACK_CONCRETE));
			add(new Pair<>(Items.BLUE_CONCRETE_POWDER, Items.BLUE_CONCRETE));
			add(new Pair<>(Items.BROWN_CONCRETE_POWDER, Items.BROWN_CONCRETE));
			add(new Pair<>(Items.CYAN_CONCRETE_POWDER, Items.CYAN_CONCRETE));
			add(new Pair<>(Items.GRAY_CONCRETE_POWDER, Items.GRAY_CONCRETE));
			add(new Pair<>(Items.GREEN_CONCRETE_POWDER, Items.GREEN_CONCRETE));
			add(new Pair<>(Items.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE));
			add(new Pair<>(Items.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE));
			add(new Pair<>(Items.LIME_CONCRETE_POWDER, Items.LIME_CONCRETE));
			add(new Pair<>(Items.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_CONCRETE));
			add(new Pair<>(Items.ORANGE_CONCRETE_POWDER, Items.ORANGE_CONCRETE));
			add(new Pair<>(Items.PINK_CONCRETE_POWDER, Items.PINK_CONCRETE));
			add(new Pair<>(Items.RED_CONCRETE_POWDER, Items.RED_CONCRETE));
			add(new Pair<>(Items.WHITE_CONCRETE_POWDER, Items.WHITE_CONCRETE));
			add(new Pair<>(Items.YELLOW_CONCRETE_POWDER, Items.YELLOW_CONCRETE));
			add(new Pair<>(Items.PURPLE_CONCRETE_POWDER, Items.PURPLE_CONCRETE));
		}
	};

	public static void forConcrete(BiConsumer<Item, Item> consumer) {
		concrete.forEach(pair -> consumer.accept(pair.getFirst(), pair.getSecond()));
	}

}
