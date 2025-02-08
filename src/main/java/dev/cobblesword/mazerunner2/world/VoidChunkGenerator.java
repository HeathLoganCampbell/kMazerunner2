package dev.cobblesword.mazerunner2.world;

import dev.cobblesword.mazerunner2.map.Map;
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
}
