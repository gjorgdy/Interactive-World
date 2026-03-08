package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.modules.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "finishUsingItem", at = @At("RETURN"))
    public void finishUsing(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.is(Items.GLOW_BERRIES) && InteractiveWorld.glowBerriesGlowEffect) {
            entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 160, 0, false, true, true));
        }
    }

    @Inject(method = "use", at = @At(value = "RETURN"))
    public void use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.getItemInHand(hand).is(Items.SLIME_BALL) && level instanceof ServerLevel serverWorld && InteractiveWorld.slimeChunkChecker) {
            Slime.use(serverWorld, player, hand);
        }
    }

}
