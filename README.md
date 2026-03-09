<center>
    <h1>TO-DO : Banner image</h1>
    A bunch of small interactions that add a bit more depth to the game, and make use of items and blocks that are often overlooked.
</center>

<br>

## Features

All features can be enabled, disabled, or for some set to 'only when crouching' in the config file.

### Cracked Blocks
- Click a block with a pickaxe to turn it into its cracked variant. (e.g. Stone Bricks -> Cracked Stone Bricks)
- Use clay on cracked blocks to repair them.

### Mossy Blocks
- Click a block with shears to remove the moss from it. (e.g. Mossy Cobblestone -> Cobblestone)
- Use vines on a block to turn it into its mossy variant. (e.g. Cobblestone -> Mossy Cobblestone)

### Cauldron Interactions
- Use Dirt on a cauldron with water to turn it to mud.
- Use Concrete Powder on a cauldron with water to harden it into a solid block.
- Use a Wet Sponge on a cauldron with lava to dry it out.

### Campfire Interactions/Recipes
- Place a Wet Sponge on an active Campfire to dry it out.

### Path and Farmland Interactions
- Use a shovel on a path block to turn it back into dirt.
- Use a hoe on farm land to turn it back into dirt.
- The height an entity must fall to trample farmland is increased.
  - This minimum height can be configured in the config file.

### Slime Chunk 'Checker'
- Right-clicking a Slime Ball shows if you're inside a slime chunk using particles.
  - If in a slime chunk, green particles will appear.
  - If not in a slime chunk, only a 'Slime Block break sound' can be heard.

### Glow Berries Glow Effect
- Eating Glow Berries gives the player or mob the Glow effect.
  - The duration of the effect can be configured in the config file.

### Blast Proof Tinted Glass
- Tinted Glass are blast proof, meaning they will not break from explosions.

### Explosion Item Crushing
- Items exploded by TNT are 'crushed', meaning they will drop as their crushed variant.
  - Concrete will turn to their respective Powder form, Cobblestone will turn to Gravel, and Sandstone will turn to Sand.
  - This is disabled by default for balance reasons, as it can be used to easily obtain large amounts of certain items.

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