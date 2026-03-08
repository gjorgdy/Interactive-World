package nl.gjorgdy.interactive_world.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import nl.gjorgdy.interactive_world.interfaces.IConcretePowderBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ConcretePowderBlock.class)
public class ConcretePowderBlockMixin implements IConcretePowderBlock {

    @Shadow @Final private Block concrete;

    @Override
    public Block interactiveWorld$getHardenedState() {
        return this.concrete;
    }

    @Override
    public Item interactiveWorld$getHardenedStateItem() {
        return interactiveWorld$getHardenedState().asItem();
    }

    @Override
    public ItemStack interactiveWorld$getHardenedStateDefaultItemStack() {
        return interactiveWorld$getHardenedStateItem().getDefaultInstance();
    }

}
