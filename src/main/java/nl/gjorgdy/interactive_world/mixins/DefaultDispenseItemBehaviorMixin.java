package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.modules.Washing;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenseItemBehavior.class)
public interface DefaultDispenseItemBehaviorMixin {

	@Inject(method = "bootStrap", at = @At("HEAD"))
	private static void bootstrap(CallbackInfo ci) {
		if (!InteractiveWorld.allowDispenserToUseCauldron) return;
		if (InteractiveWorld.turnDirtToMudInCauldron) {
			registerCauldronBehavior(Blocks.WATER_CAULDRON, Items.DIRT, Items.MUD);
		}
		if (InteractiveWorld.hardenConcreteInCauldron) {
			Washing.forConcrete(
				(powder, concrete) -> registerCauldronBehavior(Blocks.WATER_CAULDRON, powder, concrete)
			);
		}
		if (InteractiveWorld.drySpongeInCauldron) {
			registerCauldronBehavior(Blocks.LAVA_CAULDRON, Items.WET_SPONGE, Items.SPONGE);
		}
	}

	@Unique
	private static void registerCauldronBehavior(Block cauldron, Item in, Item out) {
		DispenserBlock.registerBehavior(
				in, new CauldronDispenseItemBehavior(cauldron, in, out)
		);
	}

	class CauldronDispenseItemBehavior extends DefaultDispenseItemBehavior {

		public final Block cauldron;
		public final Item in;
		public final Item out;

		public CauldronDispenseItemBehavior(Block cauldron, Item in, Item out) {
			this.cauldron = cauldron;
			this.in = in;
			this.out = out;
		}

		@Override
		public @NonNull ItemStack execute(final @NonNull BlockSource source, final @NonNull ItemStack dispensed) {
			Position position = DispenserBlock.getDispensePosition(source);
			BlockPos target = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
			Level level = source.level();
			if (level.getBlockState(target).is(cauldron)) {
				spawnItemInCauldron(source.level(), out.asItem().getDefaultInstance(), position, target);
				dispensed.shrink(1);
				if (cauldron == Blocks.WATER_CAULDRON) {
					source.level().playSound(null, target, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundSource.BLOCKS);
				}
				if (cauldron == Blocks.LAVA_CAULDRON) {
					source.level().playSound(null, target, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON, SoundSource.BLOCKS);
				}
				return dispensed;
			}
			return super.execute(source, dispensed);
		}

		public static void spawnItemInCauldron(final Level level, @NonNull ItemStack itemStack, final Position position, final BlockPos pos) {
			if (level.getBlockEntity(pos.below()) instanceof HopperBlockEntity hopper) {
				 itemStack = HopperBlockEntity.addItem(null, hopper, itemStack, null);
				 if (itemStack.isEmpty()) return;
			}
			level.addFreshEntity(new ItemEntity(level, position.x(), position.y() - 0.25, position.z(), itemStack));
		}

	}

}
