package com.bit.iv;

import com.bit.iv.algorithm.BreadthFirstSearch;
import com.bit.iv.algorithm.DepthFirstSearch;
import com.bit.iv.algorithm.DijsktraShortestPath;

public class Main {

    public static void main(String[] args) {
        MazeFactory mazeFactory = MazeFactory.getInstance();
        //Maze maze = mazeFactory.readFromConsole();
        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.ask();

        Graph graph = maze.getGraph();
        maze.saveFoundPath("generated_bfs.png", graph.findPath(new BreadthFirstSearch()));
        graph.refresh();
        maze.saveFoundPath("generated_dfs.png", graph.findPath(new DepthFirstSearch()));
        graph.refresh();
        maze.saveFoundPath("generated_dijsktra.png", graph.findPath(new DijsktraShortestPath()));
        graph.refresh();

    }
}
