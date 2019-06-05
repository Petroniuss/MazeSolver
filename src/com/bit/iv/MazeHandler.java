package com.bit.iv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import static com.bit.iv.Node.connectNodes;

public class MazeHandler {

    Maze loadMaze(String path) throws IOException {
        BufferedImage img = ImageProcessor.readImage(path);

        int width = img.getWidth();
        int height = img.getHeight();
        boolean[][] maze = new boolean[height][width];

        final byte[] pixels =  ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        final int pixelLength = img.getAlphaRaster() != null ? 4 : 3;
        int pixelIterator = 0;

        if(pixelLength == 4) {
            for(int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int argb = 0;
                    argb += (((int) pixels[pixelIterator] & 0xff) << 24); // alpha
                    argb += ((int) pixels[pixelIterator + 1] & 0xff); // blue
                    argb += (((int) pixels[pixelIterator + 2] & 0xff) << 8); // green
                    argb += (((int) pixels[pixelIterator + 3] & 0xff) << 16); // red
                    maze[j][i] = argb > 0; // argb = 0 when black then false else true
                    pixelIterator += pixelLength;
                }
            }
        } else {
            for(int j = 0; j < height; j++) {
                for(int i = 0; i < width; i++){
                    int argb = 0;
                    argb += ((int) pixels[pixelIterator] & 0xff); // blue
                    argb += (((int) pixels[pixelIterator + 1] & 0xff) << 8); // green
                    argb += (((int) pixels[pixelIterator + 2] & 0xff) << 16); // red
                    maze[j][i] = argb > 0; // argb = 0 when black then false else true
                    pixelIterator += pixelLength;
                }
            }
        }
        return getGraph(maze, height, width, img);
    }

    Maze getGraph(boolean[][] maze, int height, int width, BufferedImage img) {
        Graph graph = new Graph();
        int nodes = 2;
        Node[] nodesNorth = new Node[width];
        Node east = null;
        int[] distanceNorth = new int[width];
        int distanceEast = -1;

        for(int i = 0; i < width; i++) {
            distanceNorth[i] = -1;
        }

        Node start = null;
        for(int i = 0; i < width; i++) {
            if (maze[0][i]) {
                start = new Node((short) 0, (short) i);
                graph.addVertex(start);
                nodesNorth[i] = start;
                distanceNorth[i] = 1;
                break;
            }
        }
        Node from;
        for(int j = 1; j < height - 1; j++) {
            for(int i = 1; i < width - 1; i++) {
                if(checkNode(maze, j, i)){
                    from = new Node((short) j, (short) i);
                    nodes++;
                    graph.addVertex(from);
                    if(east == null || distanceEast == -1) {
                        east = from;
                        distanceEast = 1;
                    } else if(distanceEast != - 1) {
                        connectNodes(from, east, distanceEast, Node.Direction.EAST);
                        east = from;
                        distanceEast = 1;
                    }
                    if(nodesNorth[i] == null || distanceNorth[i] == -1) {
                        nodesNorth[i] = from;
                        distanceNorth[i] = 1;
                    } else if(distanceNorth[i] != -1) {
                        connectNodes(from, nodesNorth[i], distanceNorth[i], Node.Direction.NORTH);
                        nodesNorth[i] = from;
                        distanceNorth[i] = 1;
                    }
                } else if(!maze[j][i]){
                    distanceNorth[i] = -1;
                    distanceEast = -1;
                } else {
                    if(distanceEast != -1) distanceEast++;
                    if(distanceNorth[i] != -1) distanceNorth[i]++;
                }
            }
            east = null;
            distanceEast = -1;
        }
        Node end = null;
        for(int i = 1; i < width; i++) {
            if(maze[height - 1][i]){
                end = new Node((short) (height - 1), (short) i);
                graph.addVertex(end);
                connectNodes(end, nodesNorth[i], distanceNorth[i], Node.Direction.NORTH);
                break;
            }
        }
        System.out.printf("Created graph contains %d nodes %n", nodes);
        return new Maze(maze, height, width, start, end, graph, img);
    }

    private boolean checkNode(boolean[][] maze, int y, int x) {
        return maze[y][x] && !(checkIfPath(maze, y, x));
    }

    private boolean checkIfPath(boolean[][] maze, int y, int x) {
        return (maze[y - 1][x] && maze[y + 1][x] && !maze[y][x-1] && !maze[y][x+1]) ||
                (maze[y][x - 1] && maze[y][x + 1] && !maze[y - 1][x] && !maze[y + 1][x]);
    }

}
