package dev.cobblesword.mazerunner2.utils;


import net.minecraft.core.BlockPosition;
import net.minecraft.server.level.LightEngineThreaded;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.ChunkCoordIntPair;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.Chunk;
import net.minecraft.world.level.lighting.LightEngine;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_21_R3.block.data.CraftBlockData;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;

public class FastBlockUtil
{
    public static void setBlock(World world, int x, int y, int z, BlockData bd) {
        CraftBlockData craft = (CraftBlockData) bd;
        net.minecraft.world.level.World nmsWorld = ((CraftWorld) world).getHandle();
        Chunk nmsChunk = nmsWorld.d(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        nmsChunk.a(bp, craft.getState(), false);
    }

    public static void recalculateChunkLighting(World bukkitWorld, int chunkX, int chunkZ) {
        WorldServer nmsWorld = ((CraftWorld) bukkitWorld).getHandle();
        LightEngineThreaded lightEngine = nmsWorld.m().a();
        lightEngine.a(chunkX, chunkZ);
        lightEngine.b(new ChunkCoordIntPair(chunkX, chunkZ));
        lightEngine.a(new ChunkCoordIntPair(chunkX, chunkZ), true);
//        lightEngine.b(new ChunkCoordIntPair(chunkX, chunkZ));
//        lightEngine.updateChunkStatus(chunk, false); // Forces light recalculation
    }
}
