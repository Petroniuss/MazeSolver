package com.bit.iv;

import java.io.IOException;
import java.util.Scanner;

public class MazeFactory {

    private final static String TEST_MAZE_URL = "maze50.png";
    private static MazeFactory mazeFactory;
    private final MazeHandler mazeLoader;

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
            return mazeLoader.loadMaze(TEST_MAZE_URL);
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
