<center>
    <h1>TO-DO : Banner image</h1>
    Ever wanted to experience the thrill of being a fireman in Minecraft? We got the bars and ladders to start!
</center>

<br>

## About

<br>

## Configuration

On its own, the mod will not create a config file.
To change settings, you can install [Fzzy Config](https://modrinth.com/mod/fzzy-config).

To load changes to the config file, you can use the vanilla ``/reload`` command.

```toml
# Clicking a block with a pickaxe turns it into its cracked variant. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
crackBlocks = "ENABLED"
# Using clay on cracked blocks repairs them. ('ENABLED', 'DISABLED')
repairCrackedBlocks = "ENABLED"
# Using shears on a mossy block removes the moss from it. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
shearMossyBlocks = "ENABLED"
# Using vines on a block turns it into its mossy variant. ('ENABLED', 'DISABLED')
mossifyBlocks = "ENABLED"
# Using Dirt on a cauldron with water turns it to mud. ('ENABLED', 'DISABLED')
turnDirtToMudInCauldron = "ENABLED"
# Using Concrete Powder on a cauldron with water hardens it. ('ENABLED', 'DISABLED')
hardenConcreteInCauldron = "ENABLED"
# Using a Wet Sponge on a cauldron with lava dries it. ('ENABLED', 'DISABLED')
drySpongeInCauldron = "ENABLED"
# Placing a Wet Sponge on an active Campfire dries it. ('ENABLED', 'DISABLED')
drySpongeOnCampfire = "ENABLED"
# Using a shovel on a path block turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
undoPathBlock = "ENABLED"
# Using a hoe on farm land turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
undoFarmland = "ENABLED"
# The minimum distance for an entity to fall to trample farmland.
fallDistanceTrampleFarmland = 8
# Right-clicking a Slime Ball shows if you're inside a slime chunk using particles. ('ENABLED', 'DISABLED')
slimeChunkChecker = "ENABLED"
# Eating Glow Berries gives the player or mob the Glow effect. ('ENABLED', 'DISABLED')
glowBerriesGlowEffect = "ENABLED"
# The time in seconds the Glow effect should last when eating Glow Berries.
glowBerryEffectTime = 8
# Tinted Glass are blast proof. ('ENABLED', 'DISABLED')
blastProofTintedGlass = "ENABLED"
# Items exploded by TNT are 'crushed'. Disabled by default for balance reasons. ('ENABLED', 'DISABLED')
explosionItemCrushing = "DISABLED"
```