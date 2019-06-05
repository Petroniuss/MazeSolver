package com.bit.iv;

import com.bit.iv.algorithm.DivisionAlgorithm;
import com.bit.iv.algorithm.MazeGeneratorAlgorithm;
import com.bit.iv.algorithm.RandomizedKruskal;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class MazeGenerator {

    private Scanner scanner;
    private MazeHandler handler;

    public MazeGenerator() {
        scanner = new Scanner(System.in);
        handler = new MazeHandler();
    }

    public Maze ask() {
        System.out.println("Enter size of a maze: ");
        int n = scanner.nextInt();
        Maze maze = generateMaze(new RandomizedKruskal(), n);
        return maze;
    }

    public Maze generateMaze(MazeGeneratorAlgorithm algorithm, int n) {
        boolean[][] grid = algorithm.generateMaze(n);
        BufferedImage img = ImageProcessor.createImage(grid, n);
        return handler.getGraph(grid, n, n, img);
    }

}
