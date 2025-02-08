package dev.cobblesword.mazerunner2.map;

import dev.cobblesword.mazerunner2.map.tiles.MazeTile;
import dev.cobblesword.mazerunner2.map.tiles.TeamBaseTile;
import dev.cobblesword.mazerunner2.maze.IMazeRender;
import org.bukkit.Warning;
import org.bukkit.scoreboard.Team;

import java.awt.*;
import java.util.Random;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.*;

public class Map
{
    private int tileWidth;
    private int tileHeight;
    private Tile[][] tiles;
    private int seed;

    public Map(int seed, int tileWidth, int tileHeight)
    {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tiles = new Tile[tileWidth][tileHeight];
        this.seed = seed;
    }

    public void generateMap()
    {
        Random random = new Random(seed);

        this.tiles[(this.tileWidth / 6) * 3][(this.tileHeight / 6) * 3] = new TeamBaseTile(16, 16);
        this.tiles[(this.tileWidth / 6) * 3][(this.tileHeight / 6) * 5] = new TeamBaseTile(16, 16);
        this.tiles[(this.tileWidth / 6) * 5][(this.tileHeight / 6) * 3] = new TeamBaseTile(16, 16);
        this.tiles[(this.tileWidth / 6) * 5][(this.tileHeight / 6) * 5] = new TeamBaseTile(16, 16);

        for (int i = 0; i < this.tileHeight; i++)
        {
            for (int j = 0; j < this.tileWidth; j++)
            {
                if(this.tiles[i][j] == null)
                {
                    this.tiles[i][j] = new MazeTile(random.nextInt(), 16, 16);
                }
            }
        }
    }

    public int getSample(int x, int y)
    {
        int tileX = x / 16;
        int tileY = y / 16;

        Tile tile = this.getTile(tileX, tileY);
        if(tile == null) return -1;
        int xFrac = x % tile.getWidth();
        int yFrac = y % tile.getHeight();
        int sample = tile.getSample(xFrac, yFrac);

        // Connect tiles
        if(tileX != 0  && tile instanceof MazeTile)
        {
            if(xFrac == tile.getWidth() / 2 && yFrac == 0 ) sample = PATH;
            if(xFrac == tile.getWidth() / 2 && yFrac == 1 ) sample = PATH;
        }

        if(tileY != 0 && tile instanceof MazeTile)
        {
            if(xFrac == 0 && yFrac == tile.getHeight() / 2 ) sample = PATH;
            if(xFrac == 1 && yFrac == tile.getHeight() / 2 ) sample = PATH;
        }

        return sample;
    }

    public int getTotalWidth()
    {
        return this.tileWidth * 16;
    }

    public int getTotalHeight()
    {
        return this.tileHeight * 16;
    }

    public int getTileWidth()
    {
        return this.tileWidth;
    }

    public int getTileHeight()
    {
        return this.tileHeight;
    }

    public Tile getTile(int x, int y)
    {
       return this.tiles[x][y];
    }
}
