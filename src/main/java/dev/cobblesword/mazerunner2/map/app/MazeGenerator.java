package dev.cobblesword.mazerunner2.map.app;

import dev.cobblesword.mazerunner2.map.Map;
import dev.cobblesword.mazerunner2.map.Tile;
import dev.cobblesword.mazerunner2.map.tiles.MazeTile;
import dev.cobblesword.mazerunner2.maze.BasicMazeGenerator;
import dev.cobblesword.mazerunner2.maze.Maze;
import dev.cobblesword.mazerunner2.maze.app.ImageMazeRender;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static dev.cobblesword.mazerunner2.maze.BasicMazeGenerator.*;

public class MazeGenerator extends JPanel {
    private int tileSize = 16;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Map map = new Map(1, 5, 5);
        map.generateMap();
        BufferedImage bufferedImage = new BufferedImage(map.getTileWidth() * tileSize, map.getTileHeight() * tileSize, BufferedImage.TYPE_INT_RGB);

        for (int tileY = 0; tileY < map.getTotalHeight(); tileY++)
        {
            for (int tileX = 0; tileX < map.getTotalWidth(); tileX++)
            {
                int sample1 =  map.getSample(tileX, tileY);
                if(sample1 == WALL) bufferedImage.setRGB(tileX, tileY, 0x000000);
                if(sample1 == PATH) bufferedImage.setRGB(tileX, tileY, 0xFFFFFF);
                if(sample1 == DEADEND) bufferedImage.setRGB(tileX, tileY, 0xFF0000);
                if(sample1 == TEAM_BASE) bufferedImage.setRGB(tileX, tileY, 0x0000FF);
            }
        }

        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth() * 6, bufferedImage.getHeight() * 6, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Runner Map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setContentPane(new MazeGenerator());
            frame.setVisible(true);
        });
    }
}
