package com.bit.iv.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node {

    private boolean visited = false; // useful for traversal
    private Map<Direction, Edge> neighbours;
    private short x, y;
    private Node parent;

    public Node(short y, short x) {
        neighbours = new HashMap<>(4);
        parent = null;
        this.y = y;
        this.x = x;
    }

    public Edge getNorth() {
        return neighbours.get(Direction.NORTH);
    }

    public void setNorth(Edge north) {
        neighbours.put(Direction.NORTH, north);
    }

    public Edge getEast() {
        return neighbours.get(Direction.EAST);
    }

    public void setEast(Edge east) {
        neighbours.put(Direction.EAST, east);
    }

    public Edge getWest() {
        return neighbours.get(Direction.WEST);
    }

    public void setWest(Edge west) {
        neighbours.put(Direction.WEST, west);
    }

    public Edge getSouth() {
        return neighbours.get(Direction.SOUTH);
    }

    public void setSouth(Edge south) {
        neighbours.put(Direction.SOUTH, south);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Collection<Edge> getNeighboursCollection() {
        return neighbours.values();
    }

    public Map<Direction, Edge> getNeighbours() {
        return neighbours;
    }

    public void displayEdge(Direction direction) {
        Edge neighbour = neighbours.get(direction);
        if(neighbour != null){
            System.out.println("Direction: " + direction.toString() + ", " + neighbour);
        }
    }

    public static void connectNodes(Node from, Node to, int distance, Direction direction) { // from in direction to to
        Edge edge = new Edge(from, to, distance);
        //System.out.println("Connecting nodes: distance: " + distance + ", direction" + direction);
        switch (direction) {
            case NORTH: from.setNorth(edge); to.setSouth(edge); break;
            case SOUTH: from.setSouth(edge); to.setNorth(edge); break;
            case WEST: from.setWest(edge); to.setEast(edge); break;
            case EAST: from.setEast(edge); to.setWest(edge); break;
        }
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
