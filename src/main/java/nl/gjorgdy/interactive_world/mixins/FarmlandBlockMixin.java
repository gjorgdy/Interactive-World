package nl.gjorgdy.interactive_world.mixins;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @WrapWithCondition(
        method = "fallOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;D)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FarmlandBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V")
    )
    public boolean onLandedUpon(Entity sourceEntity, BlockState state, Level level, BlockPos pos, @Local(argsOnly = true, name = "fallDistance") double fallDistance) {
        return InteractiveWorld.fallDistanceTrampleFarmland == 0
                || fallDistance > InteractiveWorld.fallDistanceTrampleFarmland;
    }

}
