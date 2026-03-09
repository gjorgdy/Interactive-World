package nl.gjorgdy.interactive_world.client.conditions;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.Identifier;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import org.jspecify.annotations.NonNull;

public abstract class ConfigCondition implements ResourceCondition {

	private static MapCodec<? extends ConfigCondition> CODEC;
	private final String name;

	public ConfigCondition(String name) {
		this.name = name;
		CODEC = MapCodec.unit(this);
	}

	@Override
	public @NonNull ResourceConditionType<?> getType() {
		return ResourceConditionType.create(Identifier.parse(InteractiveWorld.MOD_ID + "_config_" + name.toLowerCase()), CODEC);
	}

}
