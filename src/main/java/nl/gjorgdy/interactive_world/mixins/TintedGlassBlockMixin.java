package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.TransparentBlock;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TintedGlassBlock.class)
public class TintedGlassBlockMixin extends TransparentBlock {

    public TintedGlassBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    public float getExplosionResistance() {
        return InteractiveWorld.blastProofTintedGlass ? 1200.0F : super.getExplosionResistance();
    }
}
