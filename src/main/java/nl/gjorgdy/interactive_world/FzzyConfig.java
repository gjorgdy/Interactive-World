package nl.gjorgdy.interactive_world;

import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.config.Config;
import net.minecraft.resources.Identifier;

@IgnoreVisibility
public class FzzyConfig extends Config {

    static {
        ConfigApi.event().onSyncServer((a, b) -> FzzyConfig.load());
        ConfigApi.event().onSyncClient((a, b) -> FzzyConfig.load());
    }

    public static void load() {
        var config = ConfigApiJava.registerAndLoadConfig(FzzyConfig::new);
    }

    private FzzyConfig() {
        super(Identifier.fromNamespaceAndPath(InteractiveWorld.MOD_ID, "config"));
    }
}
