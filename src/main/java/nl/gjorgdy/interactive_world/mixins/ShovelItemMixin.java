package nl.gjorgdy.interactive_world.mixins;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {

    @Inject(method = "useOn", at = @At(value = "RETURN"))
    public void useOnBlock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (cir.getReturnValue() == InteractionResult.PASS && context.getClickedFace() != Direction.DOWN) {
            Level world = context.getLevel();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.is(Blocks.DIRT_PATH)) {

                Player playerEntity = context.getPlayer();
                BlockState dirtState = Blocks.DIRT.defaultBlockState();
                Block.pushEntitiesUp(blockState, dirtState, world, blockPos);
                world.setBlock(blockPos, dirtState, 11);
                world.playSound(null, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 0.75F);
                world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(playerEntity, dirtState));
	            assert context.getPlayer() != null;
	            PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(world, context.getPlayer(), context.getClickedPos(), blockState, null);


                if (playerEntity != null) {
                    playerEntity.swing(context.getHand(), true);
                    context.getItemInHand().hurtAndBreak(1, playerEntity, context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                }
            }
        }
    }

}
