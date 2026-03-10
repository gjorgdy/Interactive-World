package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrame.class)
public abstract class ItemFrameMixin extends HangingEntity {

	@Shadow
	public abstract ItemStack getItem();

	@Unique
	private boolean hasFlippedVisibility = false;

	protected ItemFrameMixin(EntityType<? extends HangingEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "interact", at = @org.spongepowered.asm.mixin.injection.At("HEAD"), cancellable = true)
	public void onInteract(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
		if (!InteractiveWorld.toggleItemFrameVisibility) return;
		if (player.isCrouching() && !hasFlippedVisibility && !getItem().isEmpty()) {
			hasFlippedVisibility = true;
			this.setInvisible(!this.isInvisible());
			cir.setReturnValue(InteractionResult.SUCCESS);
			cir.cancel();
		}
	}

	@Inject(method = "setItem(Lnet/minecraft/world/item/ItemStack;Z)V", at = @At("TAIL"))
	public void onEmptyFrame(ItemStack itemStack, boolean bl, CallbackInfo ci) {
		if (!InteractiveWorld.toggleItemFrameVisibility) return;
		if (itemStack.isEmpty() && this.isInvisible() && !hasFlippedVisibility) {
			hasFlippedVisibility = true;
			this.setInvisible(false);
		}
	}

	@Override
	public void setInvisible(boolean bl) {
		super.setInvisible(bl);
		if (!InteractiveWorld.toggleItemFrameVisibility) return;
		if (hasFlippedVisibility && level() instanceof ServerLevel serverLevel) {
			var position = this.position().relative(getDirection(), 0.5);
			serverLevel.sendParticles(
				ParticleTypes.WHITE_SMOKE,
				position.x, position.y, position.z,
				5, 0.2F, 0.2F, 0.2F, 0.0F
			);
			serverLevel.playSound(
				this, this.getX(), this.getY(), this.getZ(),
				bl ? SoundEvents.ITEM_FRAME_REMOVE_ITEM : SoundEvents.ITEM_FRAME_ADD_ITEM,
				this.getSoundSource(), 0.25F, 1.0F
			);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!InteractiveWorld.toggleItemFrameVisibility) return;
		hasFlippedVisibility = false;
	}
}
