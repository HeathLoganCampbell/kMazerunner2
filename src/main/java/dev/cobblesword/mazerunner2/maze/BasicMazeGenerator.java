package dev.cobblesword.mazerunner2.maze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
// best
public class BasicMazeGenerator extends JPanel {
    public static final int DEADEND = 2;
    public static final int WALL = 1;
    public static final int PATH = 0;
    private static final int[][] DIRECTIONS = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
    private int[][] maze;
    private int rows, cols;
    private Random random;

    public BasicMazeGenerator(int seed, int rows, int cols) {
        this.random = new Random(seed);
        this.rows = rows % 2 == 0 ? rows + 1 : rows;  // Ensure odd dimensions
        this.cols = cols % 2 == 0 ? cols + 1 : cols;
        this.maze = new int[this.rows][this.cols];

        // Fill maze with walls
        for (int[] row : maze) Arrays.fill(row, WALL);
    }

    public void generate() {
        // Start at a random odd row and column
        int startRow = 1, startCol = 1;
        maze[startRow][startCol] = PATH;
        dfs(startRow, startCol, 0);
    }

    private void dfs(int r, int c, int depth) {
        Collections.shuffle(Arrays.asList(DIRECTIONS), random); // Randomize directions
        int moves = 0;
        for (int[] dir : DIRECTIONS)
        {
            int nr = r + dir[0], nc = c + dir[1];
            if (isInBounds(nr, nc))
            {
                if(isValid(nr, nc))
                {
                    maze[nr - dir[0] / 2][nc - dir[1] / 2] = PATH; // Break wall
                    maze[nr][nc] = PATH;
                    dfs(nr, nc, depth + 1);
                    moves++;
                }
            }
        }

        if(moves == 0)
        {
            maze[r][c] = DEADEND;
        }
    }

    private boolean isInBounds(int r, int c) {
        return r > 0 && c > 0 && r < rows - 1 && c < cols - 1;
    }

    private boolean isValid(int r, int c) {
        return r > 0 && c > 0 && r < rows - 1 && c < cols - 1 && maze[r][c] == WALL;
    }

    public int getSample(int x, int y)
    {
        return maze[y][x];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage bufferedImage = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < maze.length; y++)
        {
            for (int x = 0; x <  maze[y].length; x++)
            {
                int tile = maze[y][x];
                if(tile == WALL) bufferedImage.setRGB(x, y, 0x000000);
                if(tile == PATH) bufferedImage.setRGB(x, y, 0xFFFFFF);
                if(tile == DEADEND) bufferedImage.setRGB(x, y, 0xFF0000);
            }
        }
//        int x = 11, y = 11;
//        int sample = maze.getSample(x/10, y/10);
//        bufferedImage.setRGB(x, y, sample == 0 ? 0xFFFFFF : 0xFF00FF);

        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth() * 20, bufferedImage.getHeight() * 20, this);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Runner Map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            BasicMazeGenerator generator = new BasicMazeGenerator(2, 16, 16);
            frame.setContentPane(generator);
            generator.generate();
            frame.setVisible(true);
        });
    }
}
