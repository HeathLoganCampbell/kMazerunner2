package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.map.Map;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.PATH;
import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.WALL;

public class GameWorld
{
    private int seed;
    private World world;
    private Map map;

    public GameWorld(int seed)
    {
        this.seed = seed;
        this.map = new Map(this.seed, 10, 10);

        WorldCreator creator = new WorldCreator("GW-" + UUID.randomUUID());
        creator.generator(new VoidChunkGenerator()); // Use custom generator
        this.world = Bukkit.createWorld(creator);
        this.world.setAutoSave(false);

        for (int x = 0; x < this.map.getTotalWidth(); x++) {
            for (int z = 0; z < this.map.getTotalHeight(); z++) {
                int sample = this.map.getSample(x, z);
                if(sample == PATH)
                {
                    this.world.getBlockAt(x, 1, z).setBlockData(Material.STONE.createBlockData(), false);
                }

                if(sample == WALL)
                {
                    this.world.getBlockAt(x, 1, z).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x, 2, z).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x, 3, z).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x, 4, z).setBlockData(Material.STONE.createBlockData(), false);
                    this.world.getBlockAt(x, 5, z).setBlockData(Material.STONE.createBlockData(), false);
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
