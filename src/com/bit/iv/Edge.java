package com.bit.iv;

public class Edge {
    Edge(Node from, Node to, int weight){
        this.weight = weight;
        a = from;
        b = to;
    }
    private Node a;
    private Node b;
    private int weight;

    public Node getOppositeNode(Node x) {
        return x == a ? b : a;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge: distance: " + weight;
    }
}