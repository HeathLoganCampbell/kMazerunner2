package dev.cobblesword.mazerunner2.map.tiles;

import dev.cobblesword.mazerunner2.map.Tile;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.TEAM_BASE;

public class TeamBaseTile extends Tile
{

    public TeamBaseTile(int width, int height) {
        super(width, height);
    }

    public int getSample(int x, int y)
    {
        return TEAM_BASE;
    }
}
