package nl.gjorgdy.interactive_world.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Redirect(method = "hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;onDestroyed(Lnet/minecraft/world/entity/item/ItemEntity;)V"))
    public void onDamage(ItemStack stack, ItemEntity entity, @Local(argsOnly = true) DamageSource source) {
        if (InteractiveWorld.explosionItemCrushing && (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION))) {
            var crushed = crush(entity.getRandom(), stack);
            if (crushed.isPresent()) {
                Block.popResource(entity.level(), entity.blockPosition(), crushed.get());
                return;
            }
        }
        stack.onDestroyed(entity);
    }

    @Unique
    private static Optional<ItemStack> crush(RandomSource random, ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (item == Items.BLACK_CONCRETE) {
            var stack = Items.BLACK_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.LIGHT_BLUE_CONCRETE) {
            var stack = Items.LIGHT_BLUE_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.BLUE_CONCRETE) {
            var stack = Items.BLUE_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.CYAN_CONCRETE) {
            var stack = Items.CYAN_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.LIME_CONCRETE) {
            var stack = Items.LIME_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.GREEN_CONCRETE) {
            var stack = Items.GREEN_CONCRETE.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.YELLOW_CONCRETE) {
            var stack = Items.YELLOW_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.ORANGE_CONCRETE) {
            var stack = Items.ORANGE_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.BROWN_CONCRETE) {
            var stack = Items.BROWN_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.RED_CONCRETE) {
            var stack = Items.RED_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.PURPLE_CONCRETE) {
            var stack = Items.PURPLE_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.MAGENTA_CONCRETE) {
            var stack = Items.MAGENTA_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.PINK_CONCRETE) {
            var stack = Items.PINK_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.WHITE_CONCRETE) {
            var stack = Items.WHITE_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.LIGHT_GRAY_CONCRETE) {
            var stack = Items.LIGHT_GRAY_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        else if (item == Items.GRAY_CONCRETE) {
            var stack = Items.GRAY_CONCRETE_POWDER.getDefaultInstance();
            stack.setCount(itemStack.getCount());
            return Optional.of(stack);
        }
        // convert
        else if (item == Items.SANDSTONE) {
            var stack = Items.SAND.getDefaultInstance();
            stack.setCount(getAmount(random, itemStack.getCount()));
            return Optional.of(stack);
        }
        else if (item == Items.RED_SANDSTONE) {
            var stack = Items.RED_SAND.getDefaultInstance();
            stack.setCount(getAmount(random, itemStack.getCount()));
            return Optional.of(stack);
        }
        else if (item == Items.COBBLESTONE) {
            var stack = Items.GRAVEL.getDefaultInstance();
            stack.setCount(getAmount(random, itemStack.getCount()));
            return Optional.of(stack);
        }
        return Optional.empty();
    }

    @Unique
    private static int getAmount(RandomSource random, int input) {
        // calculate amount
        int amount  = 0;
        for (int i = 0; i < input; i++) {
            amount += random.nextInt(2) + 1;
        }
        return amount;
    }

}
