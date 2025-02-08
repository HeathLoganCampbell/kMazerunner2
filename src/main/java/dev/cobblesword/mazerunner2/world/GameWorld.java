package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.MazeRunner2Plugin;
import dev.cobblesword.mazerunner2.map.Map;
import dev.cobblesword.mazerunner2.map.Tile;
import dev.cobblesword.mazerunner2.map.tiles.MazeTile;
import dev.cobblesword.mazerunner2.utils.FastBlockUtil;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.*;

public class GameWorld
{
    private int seed;
    private World world;
    private Map map;
    private Random random;

    public GameWorld(int seed)
    {
        this.seed = seed;
        this.map = new Map(this.seed, 5, 5);
        this.map.generateMap();
        this.random = new Random(seed);

        WorldCreator creator = new WorldCreator("GW-" + UUID.randomUUID());
        creator.generator(new VoidChunkGenerator(this.map)); // Use custom generator
        creator.keepSpawnInMemory(false);
        creator.type(WorldType.FLAT);
        creator.generateStructures(false);
        this.world = Bukkit.createWorld(creator);
        this.world.setAutoSave(false);
        this.world.setDifficulty(Difficulty.NORMAL);
        this.world.setGameRuleValue("doMobSpawning", "false");
        this.world.setGameRuleValue("randomTickSpeed", "0");
        this.world.setGameRuleValue("doDaylightCycle", "false");

        drawMapToWorld();
    }

    public void drawMapToWorld()
    {
        int wallHeight = 10;

        BlockData air = Material.AIR.createBlockData();
        BlockData stone = Material.STONE.createBlockData();
        BlockData grassBlock = Material.GRASS_BLOCK.createBlockData();
        BlockData stoneBrick = Material.STONE_BRICKS.createBlockData();
        BlockData crackedStoneBricks = Material.CRACKED_STONE_BRICKS.createBlockData();
        BlockData chest = Material.CHEST.createBlockData();

        long startChunkLoading = System.currentTimeMillis();
        for (int x = 0; x <  (this.map.getTotalWidth() * 3) >> 4; x++)
        {
            for (int z = 0; z < (this.map.getTotalHeight() * 3) >> 4; z++)
            {
                this.world.loadChunk(x, z);
            }
        }

        System.out.println("loaded chunks in " + (System.currentTimeMillis() - startChunkLoading) + "ms");

        long start = System.currentTimeMillis();
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
                            setBlock(newX, 1, newZ, stoneBrick);

                            // clear existing blocks
                            for (int i = 2; i < 12; i++)
                            {
                                setBlock(newX, i, newZ, air);
                            }
                        }
                    }

                    if(sample == DEADEND)
                    {
                        if (random.nextInt(5) == 1) {
                            setBlock(x * scalar + 1, 2, z * scalar + 1, chest);
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
                                BlockData material = stoneBrick;
                                if (random.nextInt(3) == 1) {
                                    material = crackedStoneBricks;
                                }

                                setBlock(newX, i, newZ, material);
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
                            setBlock(newX, 1, newZ, grassBlock);
                        }
                    }
                }
            }
        }

        System.out.println("Maze Generated " + (System.currentTimeMillis() - start) + "ms");
    }

    private void setBlock(int x, int y, int z, BlockData blockData)
    {
        FastBlockUtil.setBlock(this.world, x, y, z, blockData);
    }

    public void regenerateMaze()
    {
        for (int x = 0; x < this.map.getTileWidth(); x++) {
            for (int z = 0; z < this.map.getTileHeight(); z++) {
                map.regenerateTile(x,z);
            }
        }

        drawMapToWorld();

        for (int x = 0; x <  (this.map.getTotalWidth() * 3) >> 4; x++)
        {
            for (int z = 0; z < (this.map.getTotalHeight() * 3) >> 4; z++)
            {
                FastBlockUtil.recalculateChunkLighting(this.world, x, z);
                this.world.refreshChunk(x, z);
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = 0; x < (map.getTotalWidth() * 3) >> 4; x++) {
                    for (int z = 0; z < (map.getTotalHeight() * 3) >> 4; z++) {
                        world.refreshChunk(x, z);
                    }
                }

            }
        }.runTaskLater(JavaPlugin.getPlugin(MazeRunner2Plugin.class), 9);
    }

    public void teleport(Player player)
    {
        player.setGameMode(GameMode.CREATIVE);
        player.teleport(this.world.getBlockAt(0, 20, 0).getLocation());
    }
}
