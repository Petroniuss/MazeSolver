package com.bit.iv.util;

import com.bit.iv.algorithms.AlgorithmPerformance;
import com.bit.iv.graph.Graph;
import com.bit.iv.graph.Node;
import com.bit.iv.util.ImageProcessor;

import java.awt.image.BufferedImage;

public class Maze {
    private boolean[][] maze; //height x width
    private int height;
    private int width;
    private Graph graph; //corresponding graph
    private BufferedImage img;
    private Node start;
    private Node end;

    public Maze(boolean[][] maze, int height, int width, Node start, Node end, Graph graph, BufferedImage img) {
        this.maze = maze;
        this.height = height;
        this.width = width;
        this.start = start;
        this.end = end;
        this.graph = graph;
        this.img = img;
    }

    public void saveFoundPath(String dest, AlgorithmPerformance performance) {
        BufferedImage solved = new BufferedImage(img.getColorModel(),
                img.copyData(null),
                img.isAlphaPremultiplied(),
                null);
        performance.setPathLength(graph.displayPath(solved));
        ImageProcessor.saveImage(solved, dest);
        System.out.println(performance.toString());
    }

    public void show() {
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                System.out.print(maze[j][i] ? "1" : "0");
            }
            System.out.println();
        }
    }

    public boolean[][] getMaze() {
        return this.maze;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }
}

