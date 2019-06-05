package com.bit.iv.algorithm;

import com.bit.iv.Edge;
import com.bit.iv.Graph;
import com.bit.iv.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements PathAlgorithm {

    private int nodesVisited = 0;

    @Override
    public AlgorithmPerformance findPath(Graph graph, Node start, Node end) {
        long startTime = System.currentTimeMillis();
        nodesVisited = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.setVisited(true);
        Node current = start;
        while (!queue.isEmpty() && current != end) {
            Node parent = queue.poll();
            current = parent;
            parent.getNeighbours().values().stream().map(e -> e.getOppositeNode(parent)).filter(n -> !n.isVisited()).forEach(n -> {
                n.setParent(parent);
                n.setVisited(true);
                increment();
                queue.add(n);
            });
        }
        long endTime = System.currentTimeMillis();
        return new AlgorithmPerformance((endTime - startTime) / 1000.0, nodesVisited, "Breadth FirstSearch");
    }

    private void increment() {
        nodesVisited++;
    }
}
