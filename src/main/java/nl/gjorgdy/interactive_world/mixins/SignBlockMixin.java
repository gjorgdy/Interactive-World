package nl.gjorgdy.interactive_world.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SignBlock.class)
public class SignBlockMixin {

	@WrapOperation(method = "useWithoutItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SignBlock;openTextEdit(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/entity/SignBlockEntity;Z)V"))
	public void useWithoutItem(SignBlock instance, Player player, SignBlockEntity sign, boolean isFrontText, Operation<Void> original) {
		if (!InteractiveWorld.clickThroughWallSign || !sign.getBlockState().is(BlockTags.WALL_SIGNS)) {
			original.call(instance, player, sign, isFrontText);
			return;
		}
		if (!player.isCrouching() && isFrontText) {
			var direction = sign.getBlockState().getValue(WallSignBlock.FACING).getOpposite();
			var wallPos = sign.getBlockPos().relative(direction);
			var result = ((ServerPlayer) player).gameMode.useItemOn(
				(ServerPlayer) player,
				player.level(),
				ItemStack.EMPTY,
				player.getUsedItemHand(),
				new BlockHitResult(player.position(), direction, wallPos, false)
			);
			if (result != InteractionResult.PASS) return;
		}
		original.call(instance, player, sign, isFrontText);
	}

}
