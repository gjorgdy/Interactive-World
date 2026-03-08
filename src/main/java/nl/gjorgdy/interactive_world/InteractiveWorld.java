package nl.gjorgdy.interactive_world;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.loader.api.FabricLoader;
import nl.gjorgdy.interactive_world.listeners.UseBrickListener;
import nl.gjorgdy.interactive_world.listeners.UseCauldronListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InteractiveWorld implements ModInitializer {

	public static final String MOD_NAME = "Interactive World";
	public static final String MOD_ID = "interactive_world";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	public static boolean blastProofTintedGlass = false;
	public static boolean explosionItemCrushing = false;

	@Override
	public void onInitialize() {
		UseBlockCallback.EVENT.register(new UseBrickListener());
		UseBlockCallback.EVENT.register(new UseCauldronListener());

		if (FabricLoader.getInstance().isModLoaded("fzzy_config")) {
			FzzyConfig.load();
		} else {
			LOGGER.log(Level.INFO, "Fzzy Config not found, using default settings.");
		}
	}
}
