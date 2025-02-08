package dev.cobblesword.mazerunner2.maze;

import java.awt.*;
import java.util.Random;

public class Maze
{
    private boolean[][] visited;
    private boolean[][][] walls;
    private Random random;
    private IMazeRender render;
    private int width;
    private int height;
    private boolean[][] deadEnds;

    public Maze(int seed, int width, int height, IMazeRender render)
    {
        random = new Random(seed);
        visited = new boolean[height][width];
        walls = new boolean[height][width][4];
        deadEnds = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                deadEnds[i][j] = true;
            }
        }

        this.width = width;
        this.height = height;

        this.render = render;
        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++)
                walls[r][c] = new boolean[]{ true, true, true, true }; // ?? Right ?? left
        generateMaze(0, 0);
        walls[(height-1) / 2][0][3] = false; // Left
        walls[(height-1) / 2][(width-1)][1] = false; // Right

        walls[0][(width-1) / 2][0] = false; // Top
        walls[(height-1)][(width-1) / 2][2] = false; // Bottom

        deadEnds[(height-1) / 2][0] = false;
        deadEnds[(height-1) / 2][(width-1)] = false;
        deadEnds[0][(width-1) / 2] = false;
        deadEnds[(height-1)][(width-1) / 2] = false;

        this.render.render(width, height, walls, deadEnds);
    }

    private void generateMaze(int r, int c) {
        visited[r][c] = true;
        int[] directions = {0, 1, 2, 3};
        shuffleArray(directions);
        for (int dir : directions) {
            int nr = r, nc = c;
            switch (dir) {
                case 0: nr--; break; // Up
                case 1: nc++; break; // Right
                case 2: nr++; break; // Down
                case 3: nc--; break; // Left
            }

            if (nr >= 0 && nr < this.height && nc >= 0 && nc < this.width) {
                if(!visited[nr][nc])
                {
                    walls[r][c][dir] = false;
                    walls[nr][nc][(dir + 2) % 4] = false;
                    generateMaze(nr, nc);
                    deadEnds[r][c] = false;
                }
            }
        }
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public int getSample(int x, int y)
    {
        // 1 2 3
        // 4 5 6
        // 7 8 9
        // corners are probably always filled in

        // (0, 0), (0, 1), (0, 2)
        // (1, 0), (1, 1), (1, 2)
        // (2, 0), (2, 1), (2, 2)
        int subX = x / 3, subY = y / 3;
        int fracX = x % 3, fracY = y % 3;

        if(fracX == 1 && fracY == 0) return walls[subY][subX][0] ? 1 : 0; // top
        if(fracX == 0 && fracY == 1) return walls[subY][subX][3] ? 1 : 0; // left
        if(fracX == 1 && fracY == 2) return walls[subY][subX][2] ? 1 : 0; // bottom
        if(fracX == 2 && fracY == 1) return walls[subY][subX][1] ? 1 : 0; // right

        if(fracX == 1 && fracY == 1) return deadEnds[subY][subX] ? 2 : 0;
        return 1;
    }
}
