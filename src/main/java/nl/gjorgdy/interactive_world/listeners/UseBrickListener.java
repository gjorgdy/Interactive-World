package nl.gjorgdy.interactive_world.listeners;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.modules.Bricks;
import nl.gjorgdy.interactive_world.utils.BlockUtils;
import nl.gjorgdy.interactive_world.utils.PlayerUtils;
import nl.gjorgdy.interactive_world.utils.ToolUtils;
import org.jspecify.annotations.NonNull;

import java.util.Random;

public class UseBrickListener implements UseBlockCallback {

    @Override
    public @NonNull InteractionResult interact(Player player, Level world, @NonNull InteractionHand hand, BlockHitResult hitResult) {
        BlockState blockState = world.getBlockState(hitResult.getBlockPos());
        ItemStack itemStack = player.getItemInHand(hand);

        if (hand == InteractionHand.MAIN_HAND && player.getOffhandItem().getItem() instanceof BlockItem) return InteractionResult.PASS;
        if (hand == InteractionHand.OFF_HAND && player.getMainHandItem().getItem() instanceof BlockItem) return InteractionResult.PASS;

        // shears on mossy block
        if (itemStack.is(Items.SHEARS) && BlockUtils.isMossy(blockState.getBlock())) {
            if (!InteractiveWorld.shearMossyBlocks.enabled(player)) return InteractionResult.PASS;
            if (!(world instanceof ServerLevel serverWorld)) return PlayerUtils.clientSwingHand(player, hand, hitResult);
            if (Bricks.shearMoss(serverWorld, hitResult.getBlockPos(), blockState, player)) {
                if (new Random().nextInt(4) > 1) {
                    Block.popResource(world, hitResult.getBlockPos().relative(hitResult.getDirection()), Items.VINE.getDefaultInstance());
                }
                world.playSound(null, hitResult.getBlockPos(), SoundEvents.BOGGED_SHEAR, SoundSource.BLOCKS);
                itemStack.hurtWithoutBreaking(1, player);
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        // vines on block
        else if (!player.isShiftKeyDown() && itemStack.is(Items.VINE) && BlockUtils.canBeMossy(blockState.getBlock())) {
            if (!InteractiveWorld.mossifyBlocks) return InteractionResult.PASS;
            if (!(world instanceof ServerLevel serverWorld)) return PlayerUtils.clientSwingHand(player, hand, hitResult);
            if (Bricks.placeVines(serverWorld, hitResult.getBlockPos(), blockState, player)) {
                itemStack.consume(1, player);
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        // pickaxe on crackable block
        else if (ToolUtils.isPickaxe(itemStack) && BlockUtils.canCrack(blockState.getBlock())) {
            if (!InteractiveWorld.crackBlocks.enabled(player)) return InteractionResult.PASS;
            if (!(world instanceof ServerLevel serverWorld)) return PlayerUtils.clientSwingHand(player, hand, hitResult);
            if (Bricks.usePickaxeOnStone(serverWorld, hitResult.getBlockPos(), blockState)) {
                world.playSound(null, hitResult.getBlockPos(), SoundEvents.DEEPSLATE_BRICKS_BREAK, SoundSource.BLOCKS);
                itemStack.hurtWithoutBreaking(1, player);
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        // clay ball on cracked block
        else if (itemStack.getItem().equals(Items.CLAY_BALL) && BlockUtils.isCracked(blockState.getBlock())) {
            if (!InteractiveWorld.repairCrackedBlocks) return InteractionResult.PASS;
            if (!(world instanceof ServerLevel serverWorld)) return PlayerUtils.clientSwingHand(player, hand, hitResult);
            if (Bricks.useClayOnStone(serverWorld, hitResult.getBlockPos(), blockState, player)) {
                world.playSound(null, hitResult.getBlockPos(), SoundEvents.DEEPSLATE_BRICKS_PLACE, SoundSource.BLOCKS);
                itemStack.consume(1, player);
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
