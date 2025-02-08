package dev.cobblesword.mazerunner2.map;

import dev.cobblesword.mazerunner2.map.tiles.MazeTile;
import dev.cobblesword.mazerunner2.map.tiles.TeamBaseTile;
import dev.cobblesword.mazerunner2.maze.IMazeRender;
import org.bukkit.scoreboard.Team;

import java.awt.*;
import java.util.Random;

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

    public void GenerateMap()
    {
        Random random = new Random(seed);
        this.tiles[1][1] = new TeamBaseTile(16, 16);
        this.tiles[1][3] = new TeamBaseTile(16, 16);
        this.tiles[3][1] = new TeamBaseTile(16, 16);
        this.tiles[3][3] = new TeamBaseTile(16, 16);

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
