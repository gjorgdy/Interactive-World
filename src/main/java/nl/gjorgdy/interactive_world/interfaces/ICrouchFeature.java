package nl.gjorgdy.interactive_world.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface ICrouchFeature {

	boolean enabled(Player player);

	ICrouchFeature enabledModule = player -> true;
	ICrouchFeature crouchOnlyModule = Entity::isCrouching;
	ICrouchFeature disabledModule = player -> false;

}
