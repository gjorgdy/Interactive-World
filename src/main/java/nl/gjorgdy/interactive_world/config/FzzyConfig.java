package nl.gjorgdy.interactive_world.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.event.api.v2.OnUpdateServerListener;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedNumber;
import net.minecraft.resources.Identifier;
import nl.gjorgdy.interactive_world.InteractiveWorld;
import nl.gjorgdy.interactive_world.interfaces.ICrouchFeature;

@IgnoreVisibility
public class FzzyConfig extends Config {

    static {
        ConfigApi.event().onUpdateServer((OnUpdateServerListener) ((a, b, c) -> FzzyConfig.load()));
        ConfigApi.event().onSyncServer((a, b) -> FzzyConfig.load());
        ConfigApi.event().onUpdateClient((a, b) -> FzzyConfig.load());
        ConfigApi.event().onSyncClient((a, b) -> FzzyConfig.load());
    }

    public static void load() {
        var config = ConfigApiJava.registerAndLoadConfig(FzzyConfig::new);
        // modules with a crouch-only option
        InteractiveWorld.undoPathBlocks = configToModule(config.undoPathBlock);
        InteractiveWorld.undoFarmland = configToModule(config.undoFarmland);
        InteractiveWorld.crackBlocks = configToModule(config.crackBlocks);
        InteractiveWorld.shearMossyBlocks = configToModule(config.shearMossyBlocks);
        // regular on/off modules
        InteractiveWorld.blastProofTintedGlass = configToBoolean(config.blastProofTintedGlass);
        InteractiveWorld.explosionItemCrushing = configToBoolean(config.explosionItemCrushing);
        InteractiveWorld.slimeChunkChecker = configToBoolean(config.slimeChunkChecker);
        InteractiveWorld.glowBerriesGlowEffect = configToBoolean(config.glowBerriesGlowEffect);
        InteractiveWorld.mossifyBlocks = configToBoolean(config.mossifyBlocks);
        InteractiveWorld.repairCrackedBlocks = configToBoolean(config.repairCrackedBlocks);
        InteractiveWorld.turnDirtToMudInCauldron = configToBoolean(config.turnDirtToMudInCauldron);
        InteractiveWorld.hardenConcreteInCauldron = configToBoolean(config.hardenConcreteInCauldron);
        InteractiveWorld.drySpongeInCauldron = configToBoolean(config.drySpongeInCauldron);
        // value settings
        InteractiveWorld.fallDistanceTrampleFarmland = config.fallDistanceTrampleFarmland.get();
        InteractiveWorld.glowBerryEffectTimeTicks = config.glowBerryEffectTime.get() * 20;
    }

    private static ICrouchFeature configToModule(ValidatedEnum<CrouchFeatureState> config) {
        return switch (config.get()) {
            case ENABLED -> ICrouchFeature.enabledModule;
            case CROUCH_ONLY -> ICrouchFeature.crouchOnlyModule;
            case DISABLED -> ICrouchFeature.disabledModule;
        };
    }

    private static boolean configToBoolean(ValidatedEnum<FeatureState> config) {
        return config.get() == FeatureState.ENABLED;
    }

    private FzzyConfig() {
        super(Identifier.fromNamespaceAndPath(InteractiveWorld.MOD_ID, "config"));
    }

    @Comment("Clicking a block with a pickaxe turns it into its cracked variant. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')")
    private ValidatedEnum<CrouchFeatureState> crackBlocks = new ValidatedEnum<>(CrouchFeatureState.ENABLED, ValidatedEnum.WidgetType.CYCLING);

    @Comment("Using clay on cracked blocks repairs them. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> repairCrackedBlocks = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Using shears on a mossy block removes the moss from it. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')")
    private ValidatedEnum<CrouchFeatureState> shearMossyBlocks = new ValidatedEnum<>(CrouchFeatureState.ENABLED, ValidatedEnum.WidgetType.CYCLING);

    @Comment("Using vines on a block turns it into its mossy variant. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> mossifyBlocks = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Using Dirt on a cauldron with water turns it to mud. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> turnDirtToMudInCauldron = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Using Concrete Powder on a cauldron with water hardens it. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> hardenConcreteInCauldron = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Using a Wet Sponge on a cauldron with lava dries it. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> drySpongeInCauldron = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Using a shovel on a path block turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')")
    private ValidatedEnum<CrouchFeatureState> undoPathBlock = new ValidatedEnum<>(CrouchFeatureState.ENABLED, ValidatedEnum.WidgetType.CYCLING);

    @Comment("Using a shovel on farm land turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')")
    private ValidatedEnum<CrouchFeatureState> undoFarmland = new ValidatedEnum<>(CrouchFeatureState.ENABLED, ValidatedEnum.WidgetType.CYCLING);

    @Comment("The minimum distance for an entity to fall to trample farmland.")
    private ValidatedInt fallDistanceTrampleFarmland = new ValidatedInt(8, 64, 0, ValidatedNumber.WidgetType.TEXTBOX_WITH_BUTTONS);

    @Comment("Tinted Glass are blast proof. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> blastProofTintedGlass = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Items exploded by TNT are 'crushed'. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> explosionItemCrushing = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Right-clicking a Slime Ball shows if you're inside a slime chunk using particles. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> slimeChunkChecker = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("Eating Glow Berries gives the player or mob the Glow effect. ('ENABLED', 'DISABLED')")
    private ValidatedEnum<FeatureState> glowBerriesGlowEffect = new ValidatedEnum<>(FeatureState.ENABLED);

    @Comment("The time in seconds the Glow effect should last when eating Glow Berries.")
    private ValidatedInt glowBerryEffectTime = new ValidatedInt(8, 3600, 1, ValidatedNumber.WidgetType.TEXTBOX_WITH_BUTTONS);

}
