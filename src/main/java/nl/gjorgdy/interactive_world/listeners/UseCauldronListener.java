package nl.gjorgdy.interactive_world.listeners;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nl.gjorgdy.interactive_world.utils.ItemUtils;
import nl.gjorgdy.interactive_world.utils.PlayerUtils;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class UseCauldronListener implements UseBlockCallback {

	@Override
	public @NonNull InteractionResult interact(Player player, Level level, @NonNull InteractionHand interactionHand, BlockHitResult blockHitResult) {
		BlockState blockState = level.getBlockState(blockHitResult.getBlockPos());
		ItemStack itemStack = player.getItemInHand(interactionHand);

		if (interactionHand == InteractionHand.MAIN_HAND && player.getOffhandItem().getItem() instanceof BlockItem) return InteractionResult.PASS;
		if (interactionHand == InteractionHand.OFF_HAND && player.getMainHandItem().getItem() instanceof BlockItem) return InteractionResult.PASS;

		if (!player.isShiftKeyDown() && ItemUtils.isConcretePowder(itemStack.getItem()) && blockState.is(Blocks.WATER_CAULDRON)) {
			if (level.isClientSide()) return PlayerUtils.clientSwingHand(player, interactionHand, blockHitResult);
			return cauldronWash(player, interactionHand, level, blockHitResult.getBlockPos(), ItemUtils::hardenConcretePowder);
		}
		// concrete powder on cauldron
		else if (!player.isShiftKeyDown() && ItemUtils.canBecomeMud(itemStack) && blockState.is(Blocks.WATER_CAULDRON)) {
			if (level.isClientSide()) return PlayerUtils.clientSwingHand(player, interactionHand, blockHitResult);
			return cauldronWash(player, interactionHand, level, blockHitResult.getBlockPos(), stack -> Items.MUD.getDefaultInstance());
		}
		return InteractionResult.PASS;
	}

	private InteractionResult cauldronWash(Player player, InteractionHand hand, Level world, BlockPos blockPos, Function<ItemStack, ItemStack> itemStackConsumer) {
		ItemStack stackInHand = player.getItemInHand(hand);
		if (player.getCooldowns().isOnCooldown(stackInHand)) return InteractionResult.FAIL;
		ItemStack resultStack = itemStackConsumer.apply(stackInHand);
		world.playSound(null, blockPos, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundSource.BLOCKS);
		player.getCooldowns().addCooldown(stackInHand, 8);
		stackInHand.consume(1, player);
		player.swing(hand, true);
		if (!player.addItem(resultStack)) {
			Block.popResource(world, blockPos, resultStack);
		}
		return InteractionResult.SUCCESS;
	}
}
