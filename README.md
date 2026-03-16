<center>
    <h1>TO-DO : Banner image</h1>
    A bunch of small interactions that add a bit more depth to the game, and make use of items and blocks that are often overlooked.
</center>

<br>

## Features

All features can be enabled, disabled, or for some set to 'only when crouching' in the config file.

### Cracking/repairing Blocks
- Click a block with a pickaxe to turn it into its cracked variant. (e.g. Stone Bricks -> Cracked Stone Bricks)
- Use clay on cracked blocks to repair them.

### Mossifying/shearing Blocks
- Click a block with shears to remove the moss from it. (e.g. Mossy Cobblestone -> Cobblestone)
- Use vines on a block to turn it into its mossy variant. (e.g. Cobblestone -> Mossy Cobblestone)

### Cauldrons
- Use Dirt on a cauldron with water to turn it to mud.
- Use Concrete Powder on a cauldron with water to harden it into a solid block.
- Use a Wet Sponge on a cauldron with lava to dry it out.

### Campfires
- Place a Wet Sponge on an active Campfire to dry it out.

### Undo-ing Paths and Farmland
- Use a shovel on a path block to turn it back into dirt.
- Use a hoe on farm land to turn it back into dirt.
- The height an entity must fall to trample farmland is increased.
  - This minimum height can be configured in the config file.

### Slime Chunks Checker
- Right-clicking a Slime Ball shows if you're inside a slime chunk using particles.
  - If in a slime chunk, green particles will appear.
  - If not in a slime chunk, only a 'Slime Block break sound' can be heard.

### Glow Berries Effect
- Eating Glow Berries gives the player or mob the Glow effect.
  - The duration of the effect can be configured in the config file.

### Item Frames Visibility
- Crouch clicking an Item Frame toggles its visibility.

### Blast Proof Tinted Glass
- Tinted Glass are blast proof, meaning they will not break from explosions.

<br>

## Resource Features

There are also some features which adds new ways of obtaining resources, these are disabled by default for balance reasons, but can be enabled in the config file.

### Environment dependent (Cobble)Stone Generation
- When lava and water meet and Cobblestone or Stone would be generated, it will instead generate a block based on its position.
  - If the block under it is Stone or Cobblestone, it will generate the default block (Stone or Cobblestone).
  - In the End, it will always generate End Stone.
  - Below Y=0, Cobbled Deepslate will generate instead of Cobblestone, and Deepslate will generate instead of Stone.
  - Between Y=50 and Y=10, there is a chance Diorite, Andesite, or Granite will generate instead of Stone.
  - The heights in between the before mentioned Y levels will have an equal chance of generating either.
  - In a Desert biome, Sandstone will generate instead of Cobblestone, and Smooth Sandstone will generate instead of Stone.
  - In a Badlands biome, Red Sandstone will generate instead of Cobblestone, and Terracotta or Smooth Red Sandstone will generate instead of Stone.
  - In a Mossy biome (Jungles and Taigas), there is a chance of Mossy Cobblestone generating instead of Cobblestone.

### Explosion Item Crushing
- Items exploded by TNT are 'crushed', meaning they will drop as their crushed variant.
  - Concrete will turn to their respective Powder form, Cobblestone will turn to Gravel, and Sandstone will turn to Sand.

### Experience Capturing
- When a player is holding an empty Bottle and picks-up experience orbs, the experience will be captured in the bottle, turning it into a Bottle o' Enchanting.
- A Bottle o' Enchanting gets created from 8 experience points and drops between 3 and 7 when thrown to the ground.

<br>

## Configuration

On its own, the mod will not create a config file.
To change settings, you can install [Fzzy Config](https://modrinth.com/mod/fzzy-config).

To load changes to the config file, you can use the vanilla ``/reload`` command.

```toml
...
# Using a shovel on a path block turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
undoPathBlock = "ENABLED"
# Using a hoe on farm land turns it into dirt. ('ENABLED', 'CROUCH_ONLY', 'DISABLED')
undoFarmland = "ENABLED"
# The minimum distance for an entity to fall to trample farmland.
fallDistanceTrampleFarmland = 8
# Right-clicking a Slime Ball shows if you're inside a slime chunk using particles. ('ENABLED', 'DISABLED')
slimeChunkChecker = "ENABLED"
# Crouch right-clicking an Item Frame toggles its visibility. ('ENABLED', 'DISABLED')
toggleItemFrameVisibility = "ENABLED"
# Eating Glow Berries gives the player or mob the Glow effect. ('ENABLED', 'DISABLED')
glowBerriesGlowEffect = "ENABLED"
# The time in seconds the Glow effect should last when eating Glow Berries.
glowBerryEffectTime = 8
# Tinted Glass are blast proof. ('ENABLED', 'DISABLED')
blastProofTintedGlass = "ENABLED"
# Items exploded by TNT are 'crushed'. Disabled by default for balance reasons. ('ENABLED', 'DISABLED')
explosionItemCrushing = "DISABLED"
...
```