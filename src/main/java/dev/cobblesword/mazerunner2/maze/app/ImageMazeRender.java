package dev.cobblesword.mazerunner2.maze.app;

import dev.cobblesword.mazerunner2.maze.IMazeRender;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageMazeRender implements IMazeRender
{
    private int cellSize;
    private int width, height;
    private BufferedImage mazeImage;

    public ImageMazeRender(int cellSize, int width, int height) {
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        this.mazeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getMazeImage() {
        return mazeImage;
    }

    @Override
    public void render(int mazeWidth, int mazeHeight, boolean[][][] walls, boolean[][] deadEnds)
    {
        Graphics2D g = mazeImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        for (int r = 0; r < mazeHeight; r++) {
            for (int c = 0; c < mazeWidth; c++) {
                int x = c * cellSize, y = r * cellSize;
                if (walls[r][c][0]) g.fillRect(x, y, cellSize, cellSize / 4); // Top
                if (walls[r][c][1]) g.fillRect(x + cellSize - cellSize / 4, y, cellSize / 4, cellSize); // Right
                if (walls[r][c][2]) g.fillRect(x, y + cellSize - cellSize / 4, cellSize, cellSize / 4); // Bottom
                if (walls[r][c][3]) g.fillRect(x, y, cellSize / 4, cellSize); // Left
                if(deadEnds[r][c])
                {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.BLACK);
                }
            }
        }
        g.dispose();
    }
}
