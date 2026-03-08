package nl.gjorgdy.interactive_world.modules;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import nl.gjorgdy.interactive_world.utils.BlockUtils;

public class Bricks {

    private static boolean setBlock(ServerLevel world, BlockPos pos, BlockState state, Player player, BlockState newState) {
        if (newState != state) {
            var oldState = world.getBlockState(pos);
            var entity = world.getBlockEntity(pos);
            world.setBlockAndUpdate(pos, newState);
            PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(world, player, pos, oldState, entity);
            return true;
        }
        return false;
    }

    public static boolean usePickaxeOnStone(ServerLevel world, BlockPos pos, BlockState state) {
        BlockState newState = crackStone(state);
        if (newState != state) {
            world.setBlockAndUpdate(pos, newState);
            return true;
        }
        return false;
    }

    public static boolean useClayOnStone(ServerLevel world, BlockPos pos, BlockState state, Player player) {
        BlockState newState = repairStone(state);
        return setBlock(world, pos, state, player, newState);
    }

    private static BlockState crackStone(BlockState blockState) {
        if (blockState.is(Blocks.STONE_BRICKS)) {
            return Blocks.CRACKED_STONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.DEEPSLATE_BRICKS)) {
            return Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.DEEPSLATE_TILES)) {
            return Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState();
        }
        if (blockState.is(Blocks.NETHER_BRICKS)) {
            return Blocks.CRACKED_NETHER_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.POLISHED_BLACKSTONE_BRICKS)) {
            return Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.INFESTED_STONE_BRICKS)) {
            return Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState();
        }
        return blockState;
    }

    private static BlockState repairStone(BlockState blockState) {
        if (blockState.is(Blocks.CRACKED_STONE_BRICKS)) {
            return Blocks.STONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.CRACKED_DEEPSLATE_BRICKS)) {
            return Blocks.DEEPSLATE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.CRACKED_DEEPSLATE_TILES)) {
            return Blocks.DEEPSLATE_TILES.defaultBlockState();
        }
        if (blockState.is(Blocks.CRACKED_NETHER_BRICKS)) {
            return Blocks.NETHER_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)) {
            return Blocks.POLISHED_BLACKSTONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.INFESTED_CRACKED_STONE_BRICKS)) {
            return Blocks.INFESTED_STONE_BRICKS.defaultBlockState();
        }
        return blockState;
    }

    public static boolean shearMoss(ServerLevel world, BlockPos pos, BlockState state, Player player) {
        BlockState newState = removeMoss(state);
        return setBlock(world, pos, state, player, newState);
    }

    private static BlockState removeMoss(BlockState blockState) {
        // stone bricks
        if (blockState.is(Blocks.MOSSY_STONE_BRICK_STAIRS)) {
            return BlockUtils.changeStairs(blockState, Blocks.STONE_BRICK_STAIRS);
        }
        if (blockState.is(Blocks.MOSSY_STONE_BRICK_SLAB)) {
            return BlockUtils.changeSlab(blockState, Blocks.STONE_BRICK_SLAB);
        }
        if (blockState.is(Blocks.MOSSY_STONE_BRICK_WALL)) {
            return BlockUtils.changeWall(blockState, Blocks.STONE_BRICK_WALL);
        }
        if (blockState.is(Blocks.MOSSY_STONE_BRICKS)) {
            return Blocks.STONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.INFESTED_MOSSY_STONE_BRICKS)) {
            return Blocks.INFESTED_STONE_BRICKS.defaultBlockState();
        }
        /// cobblestone
        if (blockState.is(Blocks.MOSSY_COBBLESTONE_STAIRS)) {
            return BlockUtils.changeStairs(blockState, Blocks.COBBLESTONE_STAIRS);
        }
        if (blockState.is(Blocks.MOSSY_COBBLESTONE_SLAB)) {
            return BlockUtils.changeSlab(blockState, Blocks.COBBLESTONE_SLAB);
        }
        if (blockState.is(Blocks.MOSSY_COBBLESTONE_WALL)) {
            return BlockUtils.changeWall(blockState, Blocks.COBBLESTONE_WALL);
        }
        if (blockState.is(Blocks.MOSSY_COBBLESTONE)) {
            return Blocks.COBBLESTONE.defaultBlockState();
        }
        return blockState;
    }

    public static boolean placeVines(ServerLevel world, BlockPos pos, BlockState state, Player player) {
        BlockState newState = addMoss(state);
        return setBlock(world, pos, state, player, newState);
    }

    private static BlockState addMoss(BlockState blockState) {
        // stone bricks
        if (blockState.is(Blocks.STONE_BRICK_STAIRS)) {
            return BlockUtils.changeStairs(blockState, Blocks.MOSSY_STONE_BRICK_STAIRS);
        }
        if (blockState.is(Blocks.STONE_BRICK_SLAB)) {
            return BlockUtils.changeSlab(blockState, Blocks.MOSSY_STONE_BRICK_SLAB);
        }
        if (blockState.is(Blocks.STONE_BRICK_WALL)) {
            return BlockUtils.changeWall(blockState, Blocks.MOSSY_STONE_BRICK_WALL);
        }
        if (blockState.is(Blocks.STONE_BRICKS)) {
            return Blocks.MOSSY_STONE_BRICKS.defaultBlockState();
        }
        if (blockState.is(Blocks.INFESTED_STONE_BRICKS)) {
            return Blocks.INFESTED_MOSSY_STONE_BRICKS.defaultBlockState();
        }
        /// cobblestone
        if (blockState.is(Blocks.COBBLESTONE_STAIRS)) {
            return BlockUtils.changeStairs(blockState, Blocks.MOSSY_COBBLESTONE_STAIRS);
        }
        if (blockState.is(Blocks.COBBLESTONE_SLAB)) {
            return BlockUtils.changeSlab(blockState, Blocks.MOSSY_COBBLESTONE_SLAB);
        }
        if (blockState.is(Blocks.COBBLESTONE_WALL)) {
            return BlockUtils.changeWall(blockState, Blocks.MOSSY_COBBLESTONE_WALL);
        }
        if (blockState.is(Blocks.COBBLESTONE)) {
            return Blocks.MOSSY_COBBLESTONE.defaultBlockState();
        }
        return blockState;
    }

}
