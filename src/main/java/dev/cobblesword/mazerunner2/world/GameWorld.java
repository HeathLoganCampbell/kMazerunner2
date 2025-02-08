package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.map.Map;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.*;

public class GameWorld
{
    private int seed;
    private World world;
    private Map map;

    public GameWorld(int seed)
    {
        this.seed = seed;
        this.map = new Map(this.seed, 12, 12);
        this.map.generateMap();

        WorldCreator creator = new WorldCreator("GW-" + UUID.randomUUID());
        creator.generator(new VoidChunkGenerator()); // Use custom generator
        this.world = Bukkit.createWorld(creator);
        this.world.setAutoSave(false);

        int scalar = 3;
        for (int x = 0; x < this.map.getTotalWidth(); x++) {
            for (int z = 0; z < this.map.getTotalHeight(); z++) {
                int sample = this.map.getSample(x, z);
                if(sample == PATH || sample == DEADEND)
                {
                    this.world.getBlockAt(x * scalar, 1, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                    if(sample == DEADEND)
                    {
                        this.world.getBlockAt(x * scalar, 2, z * scalar).setBlockData(Material.CHEST.createBlockData(), false);
                    }
                }

                if(sample == WALL)
                {
                    this.world.getBlockAt(x * scalar, 1, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x * scalar, 2, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x * scalar, 3, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x * scalar, 4, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x * scalar, 5, z * scalar).setBlockData(Material.STONE.createBlockData(), false);
                }

                if(sample == TEAM_BASE)
                {
                    this.world.getBlockAt(x * scalar, 1, z * scalar).setBlockData(Material.GRASS_BLOCK.createBlockData(), false);
                }
            }
        }
    }

    public void teleport(Player player)
    {
        player.setGameMode(GameMode.CREATIVE);
        player.teleport(this.world.getBlockAt(0, 20, 0).getLocation());
    }
}
