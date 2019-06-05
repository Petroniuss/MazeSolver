package com.bit.iv;

import com.bit.iv.algorithm.AlgorithmPerformance;
import com.bit.iv.algorithm.PathAlgorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private ArrayList<Node> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public List<Node> getVertices(){
        return vertices;
    }

    public void addVertex(Node vertex) {
        vertices.add(vertex);
    }

    public int getV() {
        return vertices.size();
    }

    public void refresh() {
        vertices.forEach(v -> {
            v.setVisited(false);
            v.setParent(null);
        });
    }

    AlgorithmPerformance findPath(PathAlgorithm pathAlgorithm) {
        return pathAlgorithm.findPath(this, vertices.get(0), vertices.get(getV() - 1));
    }

    public long displayPath(BufferedImage bufferedImage) {
        Node vertex = vertices.get(getV() - 1);
        long distance = 0;

        while (vertex.getParent() != null) {
            short from_y = vertex.getY();
            short from_x = vertex.getX();

            short to_x = vertex.getParent().getX();
            short to_y = vertex.getParent().getY();

            distance += Math.abs(from_y - to_y) + Math.abs(from_x - to_x);

            if(from_x == to_x) {
                if(from_y < to_y) {
                    while (from_y <= to_y) {
                        bufferedImage.setRGB(from_x, from_y, Color.RED.getRGB());
                        from_y++;
                    }
                } else {
                    while (from_y >= to_y) {
                        bufferedImage.setRGB(from_x, from_y, Color.RED.getRGB());
                        from_y--;
                    }
                }
            } else if(from_y == to_y) {
                if(from_x < to_x) {
                    while (from_x <= to_x) {
                        bufferedImage.setRGB(from_x, from_y, Color.RED.getRGB());
                        from_x++;
                    }
                } else {
                    while (from_x >= to_x) {
                        bufferedImage.setRGB(from_x, from_y, Color.RED.getRGB());
                        from_x--;
                    }
                }
            }

            vertex = vertex.getParent();
        }

        return distance;
    }

}

