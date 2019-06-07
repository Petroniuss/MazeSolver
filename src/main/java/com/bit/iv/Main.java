package com.bit.iv;

import com.bit.iv.algorithms.find.path.BreadthFirstSearch;
import com.bit.iv.algorithms.find.path.DepthFirstSearch;
import com.bit.iv.algorithms.find.path.DijsktraShortestPath;
import com.bit.iv.graph.Graph;

public class Main {

    public static void main(String[] args) {
        //If you have your own maze uncomment lines below and enter its path
        //MazeFactory mazeFactory = MazeFactory.getInstance();
        //Maze maze = mazeFactory.readFromConsole();

        //In case you want to generate random maze
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
