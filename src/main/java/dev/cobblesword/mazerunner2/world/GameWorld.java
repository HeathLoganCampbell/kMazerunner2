package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.map.Map;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Random;
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
        Random random = new Random(seed);

        WorldCreator creator = new WorldCreator("GW-" + UUID.randomUUID());
        creator.generator(new VoidChunkGenerator(this.map)); // Use custom generator
        this.world = Bukkit.createWorld(creator);
        this.world.setAutoSave(false);
        this.world.setDifficulty(Difficulty.NORMAL);
//        this.world.setGameRuleValue("doDaylightCycle", "false");
        int wallHeight = 10;

        int scalar = 3;
        for (int x = 0; x < this.map.getTotalWidth(); x++) {
            System.out.println("Maze Generated " + Math.round(((float) x / this.map.getTotalWidth()) * 100) + "%");
            for (int z = 0; z < this.map.getTotalHeight(); z++) {
                int sample = this.map.getSample(x, z);
                if(sample == PATH || sample == DEADEND)
                {
                    for (int dx = 0; dx < scalar; dx++) {
                        for (int dz = 0; dz < scalar; dz++) {
                            int newX = x * scalar + dx;
                            int newZ = z * scalar + dz;
                            this.world.getBlockAt(newX, 1, newZ).setType(Material.STONE, false);
                        }
                    }

                    if(sample == DEADEND)
                    {
                        if (random.nextInt(5) == 1) {
                            this.world.getBlockAt(x * scalar + 1, 2, z * scalar + 1).setType(Material.CHEST, false);
                        }
                    }
                }

                if(sample == WALL)
                {
                    for (int dx = 0; dx < scalar; dx++) {
                        for (int dz = 0; dz < scalar; dz++) {
                            int newX = x * scalar + dx;
                            int newZ = z * scalar + dz;
                            for (int i = 0; i < wallHeight; i++) {
                                Material material = Material.STONE_BRICKS;
                                if (random.nextInt(3) == 1) {
                                    material = Material.CRACKED_STONE_BRICKS;
                                }

                                this.world.getBlockAt(newX, i, newZ).setType(material, false);
                            }
                        }
                    }
                }

                if(sample == TEAM_BASE)
                {
                    for (int dx = 0; dx < scalar; dx++) {
                        for (int dz = 0; dz < scalar; dz++) {
                            int newX = x * scalar + dx;
                            int newZ = z * scalar + dz;
                            this.world.getBlockAt(newX, 1, newZ).setType(Material.GRASS_BLOCK, false);
                        }
                    }
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
