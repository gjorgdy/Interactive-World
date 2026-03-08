package nl.gjorgdy.interactive_world.modules;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class Slime {

    public static void use(ServerLevel world, Player player, InteractionHand hand) {
        if (isSlimeChunk(world, world.getChunk(player.blockPosition()))) {
            world.sendParticles(
                    ParticleTypes.ITEM_SLIME,
                    player.getX(),
                    player.getY() + 0.5,
                    player.getZ(),
                    100,
                    0.5,
                    0.5,
                    0.5,
                    1
            );
            world.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.SLIME_BLOCK_PLACE,
                    SoundSource.BLOCKS,
                    2,
                    2
            );
        } else {
            world.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.SLIME_BLOCK_BREAK,
                    SoundSource.BLOCKS,
                    1,
                    0.25f
            );
        }
        player.swing(hand, true);
        player.getCooldowns().addCooldown(player.getItemInHand(hand), 40);
    }

    public static boolean isSlimeChunk(Level world, ChunkAccess chunk) {
        return WorldgenRandom.seedSlimeChunk(chunk.getPos().x, chunk.getPos().z, ((WorldGenLevel) world).getSeed(), 987234911L).nextInt(10) == 0;
    }

}
