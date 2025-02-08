package dev.cobblesword.mazerunner2.map.tiles;

import dev.cobblesword.mazerunner2.map.Tile;
import dev.cobblesword.mazerunner2.maze.BasicMazeGenerator;

public class MazeTile extends Tile
{
    private int seed;
    private BasicMazeGenerator basicMazeGenerator;

    public MazeTile(int seed, int width, int height)
    {
        super(width, height);

        this.seed = seed;
        basicMazeGenerator = new BasicMazeGenerator(this.seed, this.getHeight(), this.getWidth());
        basicMazeGenerator.generate();
    }

    public void regenerate(int newSeed)
    {
        this.seed = newSeed;
        basicMazeGenerator = new BasicMazeGenerator(seed, this.getHeight(), this.getWidth());
        basicMazeGenerator.generate();
    }

    public int getSample(int x, int y)
    {
        return basicMazeGenerator.getSample(x, y);
    }
}
