package dev.cobblesword.mazerunner2.maze;

public interface IMazeRender
{
    public void render(int mazeWidth, int mazeHeight, boolean[][][] walls, boolean[][] deadEnds);
}
