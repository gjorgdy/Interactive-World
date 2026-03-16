package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.modules.StoneGen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(LiquidBlock.class)
public class FluidBlockMixin {

    @ModifyArgs(method = "shouldSpreadLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 0))
    private void generateCobble(Args args, Level world, BlockPos pos, BlockState fluidBlockState) {
        if (!InteractiveWorld.environmentDependentStoneGeneration) return;

        BlockState block = args.get(1);
        BlockState newBlock;
        // if vanilla would generate obsidian
        if (block.is(Blocks.OBSIDIAN) && InteractiveWorld.cryingObsidianGeneration) {
            newBlock = StoneGen.generateObsidian(world);
        // if vanilla would generate cobblestone
        } else if (block.is(Blocks.COBBLESTONE) && InteractiveWorld.environmentDependentStoneGeneration) {
            var blockBelow = world.getBlockState(pos.below());
            if (blockBelow.is(Blocks.COBBLESTONE)) {
                newBlock = Blocks.COBBLESTONE.defaultBlockState();
            } else {
                newBlock = StoneGen.environmentBasedCobble(world, pos).defaultBlockState();
            }
        } else {
            return;
        }
        args.set(1, newBlock);
    }

}
