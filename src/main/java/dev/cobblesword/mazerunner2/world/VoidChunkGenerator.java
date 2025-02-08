package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.map.Map;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import java.util.Random;

public class VoidChunkGenerator extends ChunkGenerator {
    private Map map;

    public VoidChunkGenerator(Map map)
    {
        this.map = map;
    }

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData)
    {
        // Do nothing, as we want an empty void world
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        // Since this is a void world, the server will spend 3 seconds
        // searching for a good spawn location, but then give up cause it's all void
        // If a spawn is already set, it won't search
        return world.getBlockAt(0, 10, 0).getLocation();
    }
}
