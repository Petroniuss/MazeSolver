package com.bit.iv.algorithm;

import com.bit.iv.Edge;
import com.bit.iv.Graph;
import com.bit.iv.Node;
import com.bit.iv.data.structures.MinHeap;

import java.util.*;
import java.util.stream.IntStream;

public class DijsktraShortestPath implements PathAlgorithm {
     /*
    //Works fine for small mazes for highers throws errors :/
    @Override
    public AlgorithmPerformance findPath(Graph graph, Node start, Node end) {
        int V = graph.getV();
        int nodesCounter = 0;
        long startTime = System.currentTimeMillis();
        MinDistanceNode[] vertices = new MinDistanceNode[V];
        Set<Node> inSPT = new HashSet<>(V);
        MinHeap<MinDistanceNode> minHeap = new MinHeap<>(vertices, 0, V);
        MinDistanceNode node = new MinDistanceNode(start, 0);
        minHeap.insert(node);
        while (!minHeap.isEmpty()) {
            MinDistanceNode parent = minHeap.extractMin();
            int parentDistance = parent.getKey();
            nodesCounter++;
            for(Edge edge : parent.node.getNeighboursCollection()) {
                Node u = edge.getOppositeNode(parent.node);
                if (MinDistanceNode.dist.get(u) == null) {
                    minHeap.insert(new MinDistanceNode(u, parentDistance + edge.getWeight()));
                    u.setParent(parent.node);
                } else if( parentDistance + edge.getWeight() < MinDistanceNode.dist.get(u)) {
                    minHeap.decreaseKey(new MinDistanceNode(u, MinDistanceNode.dist.get(u)), parentDistance + edge.getWeight());
                    u.setParent(parent.node);
                }
            }
        }

        long endTime = System.currentTimeMillis();

        return new AlgorithmPerformance((endTime - startTime) / 1000.0, nodesCounter, "Dijsktra Shortest Path ");
    }
    */

    //TODO: Implement minHeap!

    @Override
    public AlgorithmPerformance findPath(Graph graph, Node start, Node end) {
        Map<Node, Integer> keys = new HashMap<>(graph.getV()); // For updating the key
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(keys::get)); // Should run faster if we were to use TreeSet
        long startTime = System.currentTimeMillis();
        int nodesVisited = 0;

        graph.getVertices().stream().forEach(v -> keys.put(v, Integer.MAX_VALUE));
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


    private static class MinDistanceNode implements KeyHolder {
        private static Map<Node, Integer> dist = new HashMap<>();
        private Node node;

        MinDistanceNode(Node node, int distance) {
            this.node = node;
            dist.put(node, distance);
        }

        @Override
        public int getKey() {
            return dist.get(node);
        }

        @Override
        public void setKey(int newKey) {
            dist.put(node, newKey);
        }
    }
}
