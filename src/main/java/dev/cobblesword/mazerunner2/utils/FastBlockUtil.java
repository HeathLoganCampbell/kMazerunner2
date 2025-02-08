package dev.cobblesword.mazerunner2.utils;


import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_21_R3.block.data.CraftBlockData;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;

public class FastBlockUtil
{
    private static final BlockData AIR_BLOCKDATA = Bukkit.createBlockData(Material.AIR);
    protected static final IBlockData AIR = ((CraftBlockData) AIR_BLOCKDATA).getState();

    public static void setBlock(World world, int x, int y, int z, BlockData bd) {
//        org.bukkit.block.Block block = location.getBlock();
        // Set the block data - default is AIR
        CraftBlockData craft = (CraftBlockData) bd;
        net.minecraft.world.level.World nmsWorld = ((CraftWorld) world).getHandle();
        Chunk nmsChunk = nmsWorld.d(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        // Setting the block to air before setting to another state prevents some console errors
        nmsChunk.a(bp, AIR, false);
        nmsChunk.a(bp, craft.getState(), false);
//        block.setBlockData(bd, false);
    }
//
//    public void blah(int x, int y, int z)
//    {
//        World w = Bukkit.getWorld(worldIdToName.get(worldname);
//
//        CraftWorld craftWorld = (CraftWorld) w;
//        WorldServer nmsWorld = craftWorld.getHandle();
//
//        String blockDataString = bukkitBlockDataAsString;
//        IBlockData blockData;
//
//        if (blockDataString == null) {
//            blockData = ((CraftBlockData) bukkitMaterial.createBlockData()).getState();
//        } else {
//            blockData = ((CraftBlockData) bukkitBlockData.getState();
//        }
//
//        int chunkX = x>> 4;
//        int chunkZ = z>> 4;
//        Chunk chunk = nmsWorld.d(chunkX, chunkZ);
//
//        BlockPosition blockPos = new BlockPosition(x& 15, y, z & 15);
//        chunk.setBlockState(blockPos, blockData, false, false);
//    }

//    public IBlockData convertMaterialToIBlockData(Material material) {
//        Block nmsBlock = CraftMagicNumbers.getBlock(material);
//        return (nmsBlock != null) ? nmsBlock.defaultBlockState() : Blocks.AIR.defaultBlockState();
//    }
//
//    public void update(org.bukkit.World bukkitWorld, World nmsWorld, List<BlockChangeJob> blockChangeJobList) {
//
//        WorldServer worldServer = ((CraftWorld) bukkitWorld).getHandle();
//        ChunkProviderServer chunkProviderServer = worldServer.m();
//        for (BlockChangeJob blockChangeJob : blockChangeJobList) {
//            Chunk chunk = chunkProviderServer.a(blockChangeJob.getX() >> 4, blockChangeJob.getZ() >> 4);
//            chunk.setBlockState(Blocks.AIR.getDefaultState(), null, false, true);
//        }

        //update lights
//        LightEngineThreaded engine = chunkProviderServer.a();
//        for (BlockPosition pos : modified.keySet()) {
//            engine.a(pos);
//        }

//        for (Chunk chunk : chunks) {
//            chunk
//        }
//    }
}
