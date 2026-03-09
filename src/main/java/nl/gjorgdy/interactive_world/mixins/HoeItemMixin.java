package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class HoeItemMixin {

    @Inject(at = @At("HEAD"), method="useOn", cancellable = true)
    public void onUse(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        var world = context.getLevel();
        var pos = context.getClickedPos();
        var player = context.getPlayer();
        if (world.getBlockState(pos).is(Blocks.FARMLAND) && InteractiveWorld.undoFarmland.enabled(player)) {
            if (world.getBlockState(pos.above()).isAir()) {
                world.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
                world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1f, 0.5f);
                if (player != null) {
                    context.getItemInHand().hurtAndBreak(1, player, context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                    player.swing(context.getHand(), true);
                }
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

}
