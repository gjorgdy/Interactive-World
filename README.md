<center>
    <h1>TO-DO : Banner image</h1>
    A bunch of small interactions that add a bit more depth to the game, and make use of items and blocks that are often overlooked.
</center>

<br>

## Features

All features can be enabled, disabled, or for some set to 'only when crouching' in the config file.

### Cracking/Repairing Blocks
- Click a block with a pickaxe to turn it into its cracked variant. (e.g. Stone Bricks -> Cracked Stone Bricks)
- Use clay on cracked blocks to repair them.

### Mossifying/Shearing Blocks
- Click a block with shears to remove the moss from it. (e.g. Mossy Cobblestone -> Cobblestone)
- Use vines on a block to turn it into its mossy variant. (e.g. Cobblestone -> Mossy Cobblestone)

### Cauldron Washing
- Use Dirt on a cauldron with water to turn it to mud.
- Use Concrete Powder on a cauldron with water to harden it into a solid block.
- Use a Wet Sponge on a cauldron with lava to dry it.
- Dispensers pointed into cauldrons can also be used to perform these interactions.
  - Putting a hopper under a cauldron instantly collects the washed items, preventing sponges from burning in the lava.

### Campfires
- Place a Wet Sponge on an active Campfire to dry it.

### Undoing Paths and Farmland
- Use a shovel on a path block to turn it back into dirt.
- Use a hoe on farmland to turn it back into dirt.
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

## Configuration

On its own, the mod will not create a config file.
To change settings, you can install [Fzzy Config](https://modrinth.com/mod/fzzy-config).

To load changes to the config file, you can use the vanilla ``/reload`` command.
> Some config options do require a full client/server restart to take effect, and will be noted as such in the config file.