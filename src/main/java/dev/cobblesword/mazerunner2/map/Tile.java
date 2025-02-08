package dev.cobblesword.mazerunner2.map;

public abstract class Tile
{
    private int width;
    private int height;

    public Tile(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSample(int x, int y)
    {
        return -1;
    }
}
