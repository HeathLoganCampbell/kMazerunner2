package dev.cobblesword.mazerunner2.utils;


import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.Chunk;
import net.minecraft.world.level.chunk.ChunkSection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_21_R3.util.CraftMagicNumbers;
import net.minecraft.server.level.WorldServer;

import java.util.concurrent.CompletableFuture;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_21_R3.block.data.CraftBlockData;

public class FastBlockUtil
{
    private static final BlockData AIR_BLOCKDATA = Bukkit.createBlockData(Material.AIR);
    protected static final IBlockData AIR = ((CraftBlockData) AIR_BLOCKDATA).getState();

    public static org.bukkit.block.Block setBlock(Location location, BlockData bd) {
        org.bukkit.block.Block block = location.getBlock();
        // Set the block data - default is AIR
        CraftBlockData craft = (CraftBlockData) bd;
        net.minecraft.world.level.World nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        Chunk nmsChunk = nmsWorld.d(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        // Setting the block to air before setting to another state prevents some console errors
        nmsChunk.a(bp, AIR, false);
        nmsChunk.a(bp, craft.getState(), false);
        block.setBlockData(bd, false);
        return block;
    }
}
