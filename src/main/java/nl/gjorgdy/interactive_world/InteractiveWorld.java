package nl.gjorgdy.interactive_world;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.loader.api.FabricLoader;
import nl.gjorgdy.interactive_world.client.conditions.SpongeCampfireCondition;
import nl.gjorgdy.interactive_world.config.FzzyConfig;
import nl.gjorgdy.interactive_world.interfaces.ICrouchFeature;
import nl.gjorgdy.interactive_world.listeners.UseBrickListener;
import nl.gjorgdy.interactive_world.listeners.UseCauldronListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static nl.gjorgdy.interactive_world.interfaces.ICrouchFeature.*;

public class InteractiveWorld implements ModInitializer {

	public static final String MOD_NAME = "Interactive World";
	public static final String MOD_ID = "interactive_world";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static ICrouchFeature crackBlocks = enabledModule;
	public static ICrouchFeature shearMossyBlocks = enabledModule;
	public static ICrouchFeature undoPathBlocks = enabledModule;
	public static ICrouchFeature undoFarmland = enabledModule;

	public static boolean turnDirtToMudInCauldron = true;
	public static boolean hardenConcreteInCauldron = true;
	public static boolean drySpongeInCauldron = true;
	public static boolean drySpongeOnCampfire = true;
	public static boolean mossifyBlocks = true;
	public static boolean repairCrackedBlocks = true;
	public static boolean slimeChunkChecker = true;
	public static boolean glowBerriesGlowEffect = true;
	public static boolean blastProofTintedGlass = true;

	public static int fallDistanceTrampleFarmland = 8;
	public static int glowBerryEffectTimeTicks = 160;

	// disabled by default for balance reasons - purely in the mod for parity reasons
	public static boolean explosionItemCrushing = false;

	@Override
	public void onInitialize() {
		UseBlockCallback.EVENT.register(new UseBrickListener());
		UseBlockCallback.EVENT.register(new UseCauldronListener());

		ResourceConditions.register(new SpongeCampfireCondition().getType());

		if (FabricLoader.getInstance().isModLoaded("fzzy_config")) {
			FzzyConfig.load();
		} else {
			LOGGER.log(Level.INFO, "Fzzy Config not found, using default settings.");
		}
	}
}
