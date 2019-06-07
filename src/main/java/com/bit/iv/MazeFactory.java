package com.bit.iv;

import java.io.IOException;
import java.util.Scanner;

public class MazeFactory {

    private static MazeFactory mazeFactory;
    private final MazeHandler mazeLoader;
    private static final String SAMPLE_MAZE = "sample_maze.png";

    public static MazeFactory getInstance() {
        if(mazeFactory == null){
            mazeFactory = new MazeFactory();
        }
        return mazeFactory;
    }

    private MazeFactory() {
        this.mazeLoader = new MazeHandler();
    }

    public Maze getTestMaze() {
        try {
            return mazeLoader.loadMaze(SAMPLE_MAZE);
        } catch (IOException e) {
            System.out.println("hardcoded stuff doesn't work");
        }
        return null;
    }

    public Maze readFromConsole() {
        String path = null;
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        try {
            return mazeLoader.loadMaze(path);
        } catch (IOException e) {
            System.out.println("Invalid path");
            return readFromConsole();
        }
    }

}
