package nl.gjorgdy.interactive_world.client;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.client.conditions.SpongeCampfireCondition;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DrySpongeRecipeGenerator extends FabricRecipeProvider {
	public DrySpongeRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
		return new RecipeProvider(provider, recipeOutput) {
			@Override
			public void buildRecipes() {
				var conditionalOutput = withConditions(output, new SpongeCampfireCondition());
				SimpleCookingRecipeBuilder.campfireCooking(
						Ingredient.of(Items.WET_SPONGE),
						RecipeCategory.MISC,
						Items.SPONGE,
						0.35f,
						320
				)
				.unlockedBy(getHasName(Items.WET_SPONGE), has(Items.WET_SPONGE))
				.save(conditionalOutput);

			}
		};
	}

	@Override
	public @NonNull String getName() {
		return InteractiveWorld.MOD_ID;
	}
}
