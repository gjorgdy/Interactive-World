package nl.gjorgdy.interactive_world.modules;

import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import nl.gjorgdy.interactive_world.InteractiveWorld;

public class StoneGen {

    static final Map<Block, Integer> UNDERGROUND_STONES = Map.of(
            Blocks.ANDESITE, 2,
            Blocks.DIORITE, 2,
            Blocks.GRANITE, 2,
            Blocks.STONE, 4
    );
    static final Map<Block, Integer> BADLAND_STONES = Map.of(
            Blocks.SMOOTH_RED_SANDSTONE, 2,
            Blocks.TERRACOTTA, 3,
            Blocks.RED_TERRACOTTA, 1,
            Blocks.ORANGE_TERRACOTTA, 1,
            Blocks.BROWN_TERRACOTTA, 1,
            Blocks.WHITE_TERRACOTTA, 1,
            Blocks.YELLOW_TERRACOTTA, 1
    );

    public static boolean fiftyFifty(Level world) {
        return chance(world, 1, 2);
    }

    public static boolean chance(Level world, int favourable, int possible) {
        return random(world, possible) <= favourable;
    }

    public static int random(Level world, int possible) {
        return world.random.nextInt(possible + 1);
    }

    public static Block environmentBasedCobble(Level world, BlockPos pos) {
        // end dimension
        if (world.dimension().equals(Level.END)) {
            return Blocks.END_STONE;
        }
        // above ground
        else if (pos.getY() > 60 || (fiftyFifty(world) && pos.getY() > 50)) {
            return getCobblestoneForBiome(world, world.getBiome(pos));
        }
        // deep
        else if (pos.getY() < 0 || (fiftyFifty(world) && pos.getY() < 10)) {
            return Blocks.COBBLED_DEEPSLATE;
        }
        // default
        else {
            return Blocks.COBBLESTONE;
        }
    }

    public static Block getCobblestoneForBiome(Level world, Holder<Biome> biome) {
        if (biome.is(Biomes.DESERT)) {
            return Blocks.SANDSTONE;
        } else if (biome.is(BiomeTags.IS_BADLANDS)) {
            return Blocks.RED_SANDSTONE;
        } else if (biome.is(BiomeTags.IS_JUNGLE) || biome.is(BiomeTags.IS_TAIGA)) {
            return chance(world, 1, 3) ? Blocks.MOSSY_COBBLESTONE : Blocks.COBBLESTONE;
        } else {
            return Blocks.COBBLESTONE;
        }
    }

    public static BlockState replaceStone(Level world, BlockPos pos) {
        Block under = world.getBlockState(pos.offset(0, -1, 0)).getBlock();
        if (under == Blocks.STONE) {
            return Blocks.STONE.defaultBlockState();
        } else {
            return environmentBasedStone(world, pos).defaultBlockState();
        }
    }

    public static Block environmentBasedStone(Level world, BlockPos pos) {
        int y = pos.getY();
        Holder<Biome> biome = world.getBiome(pos);
        // end dimension
        if (world.dimension().equals(Level.END)) {
            return Blocks.END_STONE;
        }
        // above ground
        else if (y > 60 || (y > 50 && fiftyFifty(world))) {
            return getStoneForBiome(world, biome);
        }
        // between
        else if (y > 50) {
            return fiftyFifty(world) ? getStoneForBiome(world, biome) : getStoneForUnderground(world);
        }
        // underground
        else if (y < 50 && y >= 10) {
            return getStoneForUnderground(world);
        }
        // under between
        else if (y < 10 && y >= 0) {
            return fiftyFifty(world) ? Blocks.DEEPSLATE : getStoneForUnderground(world);
        }
        // deep
        else if (y < 0) {
            return Blocks.DEEPSLATE;
        }
        // default
        else {
            return Blocks.STONE;
        }
    }

    public static Block getStoneForBiome(Level world, Holder<Biome> biome) {
        if (biome.is(Biomes.DESERT)) {
            return Blocks.SMOOTH_SANDSTONE;
        } else if (biome.is(BiomeTags.IS_BADLANDS)) {
            return randomBlock(world, BADLAND_STONES);
        } else {
            return Blocks.STONE;
        }
    }

    public static Block getStoneForUnderground(Level world) {
        return randomBlock(world, UNDERGROUND_STONES);
    }

    private static Block randomBlock(Level world, Map<Block, Integer> blocks) {
        int r = random(world, 10);
        int c = 0;
        for (Map.Entry<Block, Integer> entry : blocks.entrySet()) {
            c += entry.getValue();
            if (r < c) {
                return entry.getKey();
            }
        }
        return (Block) blocks.keySet().toArray()[blocks.size() - 1];
    }

    public static BlockState generateObsidian(Level world) {
        if (chance(world, 1, InteractiveWorld.cryingObsidianGenerationChance)) {
            return Blocks.CRYING_OBSIDIAN.defaultBlockState();
        } else {
            return Blocks.OBSIDIAN.defaultBlockState();
        }
    }

}
