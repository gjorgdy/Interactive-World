package nl.gjorgdy.interactive_world.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends Avatar {

	@Shadow
	public abstract boolean addItem(ItemStack itemStack);

	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Unique
	private int experiencePointsTowardsBottle = 0;

	@WrapMethod(method = "giveExperiencePoints")
	public void onGainExperience(int i, Operation<Void> original) {
		if (!InteractiveWorld.captureExperienceInBottles) {
			original.call(i);
			return;
		}
		var heldItem = getItemInHand(InteractionHand.MAIN_HAND);
		if (heldItem.is(Items.GLASS_BOTTLE)) {
			experiencePointsTowardsBottle += i;
		} else {
			original.call(i);
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void onSelect(CallbackInfo ci) {
		if (!InteractiveWorld.captureExperienceInBottles) return;
		var heldItem = getItemInHand(InteractionHand.MAIN_HAND);
		if (heldItem.is(Items.GLASS_BOTTLE) && experiencePointsTowardsBottle > 0) {
			while (experiencePointsTowardsBottle >= 8) {
				heldItem.shrink(1);
				addItem(Items.EXPERIENCE_BOTTLE.getDefaultInstance());
				experiencePointsTowardsBottle -= 8;
			}
		} else {
			experiencePointsTowardsBottle = 0;
		}
	}

}
