package dev.cobblesword.mazerunner2.world;

import org.bukkit.Material;

public class BlockChangeJob
{
    private int x, y, z;
    private Material material;

    public BlockChangeJob(int x, int y, int z, Material material) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = material;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Material getMaterial() {
        return material;
    }
}
