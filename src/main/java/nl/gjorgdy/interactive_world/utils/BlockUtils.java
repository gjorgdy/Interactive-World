package nl.gjorgdy.interactive_world.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.WallSide;

public class BlockUtils {

    public static BlockState changeStairs(BlockState state, Block block) {
        if (state.getBlock() instanceof StairBlock && block instanceof StairBlock) {
            Direction facing = state.getValue(StairBlock.FACING);
            Half half = state.getValue(StairBlock.HALF);
            StairsShape shape = state.getValue(StairBlock.SHAPE);
            Boolean waterlogged = state.getValue(StairBlock.WATERLOGGED);
            return block.defaultBlockState()
                    .setValue(StairBlock.FACING, facing)
                    .setValue(StairBlock.HALF, half)
                    .setValue(StairBlock.SHAPE, shape)
                    .setValue(StairBlock.WATERLOGGED, waterlogged);
        }
        return state;
    }

    public static BlockState changeSlab(BlockState state, Block block) {
        if (state.getBlock() instanceof SlabBlock && block instanceof SlabBlock) {
            SlabType type = state.getValue(SlabBlock.TYPE);
            boolean waterlogged = state.getValue(SlabBlock.WATERLOGGED);
            return block.defaultBlockState()
                    .setValue(SlabBlock.TYPE, type)
                    .setValue(SlabBlock.WATERLOGGED, waterlogged);
        }
        return state;
    }

    public static BlockState changeWall(BlockState state, Block block) {
        if (state.getBlock() instanceof WallBlock && block instanceof WallBlock) {
            WallSide northShape = state.getValue(WallBlock.NORTH);
            WallSide eastShape = state.getValue(WallBlock.EAST);
            WallSide southShape = state.getValue(WallBlock.SOUTH);
            WallSide westShape = state.getValue(WallBlock.WEST);
            boolean waterlogged = state.getValue(WallBlock.WATERLOGGED);
            boolean up = state.getValue(WallBlock.UP);
            return block.defaultBlockState()
                    .setValue(WallBlock.NORTH, northShape)
                    .setValue(WallBlock.EAST, eastShape)
                    .setValue(WallBlock.SOUTH, southShape)
                    .setValue(WallBlock.WEST, westShape)
                    .setValue(WallBlock.WATERLOGGED, waterlogged)
                    .setValue(WallBlock.UP, up);
        }
        return state;
    }

    public static boolean canBeMossy(Block block) {
        return block == Blocks.COBBLESTONE
                || block == Blocks.COBBLESTONE_WALL
                || block == Blocks.COBBLESTONE_STAIRS
                || block == Blocks.COBBLESTONE_SLAB
                || block == Blocks.STONE_BRICKS
                || block == Blocks.STONE_BRICK_WALL
                || block == Blocks.STONE_BRICK_STAIRS
                || block == Blocks.STONE_BRICK_SLAB
                || block == Blocks.INFESTED_STONE_BRICKS;
    }

    public static boolean isMossy(Block block) {
        return block == Blocks.MOSSY_COBBLESTONE
                || block == Blocks.MOSSY_COBBLESTONE_WALL
                || block == Blocks.MOSSY_COBBLESTONE_STAIRS
                || block == Blocks.MOSSY_COBBLESTONE_SLAB
                || block == Blocks.MOSSY_STONE_BRICKS
                || block == Blocks.MOSSY_STONE_BRICK_WALL
                || block == Blocks.MOSSY_STONE_BRICK_STAIRS
                || block == Blocks.MOSSY_STONE_BRICK_SLAB
                || block == Blocks.INFESTED_MOSSY_STONE_BRICKS;
    }

    public static boolean canCrack(Block block) {
        return block == Blocks.STONE_BRICKS
                || block == Blocks.DEEPSLATE_BRICKS
                || block == Blocks.DEEPSLATE_TILES
                || block == Blocks.NETHER_BRICKS
                || block == Blocks.POLISHED_BLACKSTONE_BRICKS
                || block == Blocks.INFESTED_STONE_BRICKS;
    }

    public static boolean isCracked(Block block) {
        return block == Blocks.CRACKED_STONE_BRICKS
                || block == Blocks.CRACKED_DEEPSLATE_BRICKS
                || block == Blocks.CRACKED_DEEPSLATE_TILES
                || block == Blocks.CRACKED_NETHER_BRICKS
                || block == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
                || block == Blocks.INFESTED_CRACKED_STONE_BRICKS;
    }

    public static boolean isConcretePowder(Block block) {
        return block instanceof ConcretePowderBlock;
    }

}
