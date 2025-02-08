package dev.cobblesword.mazerunner2.maze.app;

import dev.cobblesword.mazerunner2.maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeGenerator extends JPanel {
    private static final int WIDTH = 120, HEIGHT = 120, CELL_SIZE = 20;

    public MazeGenerator()
    {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = WIDTH / CELL_SIZE;
        int height = HEIGHT / CELL_SIZE;
        ImageMazeRender imageMazeRender = new ImageMazeRender(CELL_SIZE, WIDTH, HEIGHT);
        Maze maze = new Maze(1, width, height, imageMazeRender);
        BufferedImage mazeImage = imageMazeRender.getMazeImage();

        g.drawImage(mazeImage, 0,  0, this);

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                int width = WIDTH / CELL_SIZE;
//                int height = HEIGHT / CELL_SIZE;
//                ImageMazeRender imageMazeRender = new ImageMazeRender(CELL_SIZE, WIDTH, HEIGHT);
//                Maze maze = new Maze(1, width, height, imageMazeRender);
//                BufferedImage mazeImage = imageMazeRender.getMazeImage();
//
//                g.drawImage(mazeImage, i * WIDTH, j * HEIGHT, this);
//            }
//        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(10 * WIDTH + 10, 10 * HEIGHT + 50);
            frame.setContentPane(new MazeGenerator());
            frame.setVisible(true);
        });
    }
}
