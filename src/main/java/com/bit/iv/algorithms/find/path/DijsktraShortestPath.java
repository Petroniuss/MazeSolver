package com.bit.iv.algorithms.find.path;

import com.bit.iv.algorithms.AlgorithmPerformance;
import com.bit.iv.graph.Edge;
import com.bit.iv.graph.Graph;
import com.bit.iv.graph.Node;
import com.bit.iv.data.structures.MinHeap;

import java.util.*;

public class DijsktraShortestPath implements PathAlgorithm {

    @Override
    public AlgorithmPerformance findPath(Graph graph, Node start, Node end) {
        return dijsktraOnPQ(graph, start, end);
    }

    private AlgorithmPerformance dijsktraOnPQ(Graph graph, Node start, Node end) {
        Map<Node, Integer> keys = new HashMap<>(graph.getV()); // For updating the key
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(keys::get)); // Should run faster if we were to use TreeSet
        long startTime = System.currentTimeMillis();
        int nodesVisited = 0;

        graph.getVertices().forEach(v -> keys.put(v, Integer.MAX_VALUE));
        keys.put(start, 0);
        minHeap.add(start);

        while(!minHeap.isEmpty()) {
            Node parent = minHeap.poll();
            int parentDistance = keys.get(parent);
            nodesVisited++;
            for(Edge edge : parent.getNeighbours().values()) {
                Node neigh = edge.getOppositeNode(parent);
                int newDist = parentDistance + edge.getWeight();

                if (newDist < keys.get(neigh)) {
                    minHeap.remove(neigh); // this is probably done in O(n) :/
                    keys.put(neigh, newDist);
                    minHeap.add(neigh);
                    neigh.setParent(parent);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        return new AlgorithmPerformance((endTime - startTime) / 1000.0, nodesVisited, "Dijsktra's Shortest Path Algorithm");
    }

    private AlgorithmPerformance dijsktraOnMinHeap(Graph graph, Node start, Node end) {
        int nodesVisited = 0;
        long timeStarted = System.currentTimeMillis();

        HashMap<Node, Integer> dist = new HashMap<>(graph.getV());
        MinHeap<Node> minHeap = new MinHeap<>(
                new Node[graph.getV()], 0, graph.getV(),
                dist::get, dist::put);

        graph.getVertices().forEach(u -> {
            dist.put(u, Integer.MAX_VALUE / 2);
            minHeap.insert(u);
        });
        minHeap.decreaseKey(start, 0);

        while (!minHeap.isEmpty()) {
            final Node u = minHeap.extractMin();
            u.getNeighboursCollection().forEach(e -> {
                final Node v = e.getOppositeNode(u);
                if (dist.get(u) + e.getWeight() < dist.get(v)) {
                    v.setParent(u);
                    minHeap.decreaseKey(v, dist.get(u) + e.getWeight());
                }
            });
            nodesVisited++;
            if (u == end)
                break;
        }

        long endTime = System.currentTimeMillis();

        return new AlgorithmPerformance((endTime - timeStarted) / 1000.0, nodesVisited, "Dijsktra implemented on  minHeap");
    }

}
