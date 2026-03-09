package nl.gjorgdy.interactive_world.client.conditions;

import net.minecraft.resources.RegistryOps;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.jspecify.annotations.Nullable;

public class SpongeCampfireCondition extends ConfigCondition {

	public SpongeCampfireCondition() {
		super("drySpongeOnCampfire");
	}

	@Override
	public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfoLookup) {
		return InteractiveWorld.drySpongeOnCampfire;
	}

}
