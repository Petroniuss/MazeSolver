package com.bit.iv.algorithms;

public class AlgorithmPerformance {

    private double runningTime;
    private long pathLength;
    private int nodesVisited;
    private String name;

    public AlgorithmPerformance(double runningTime, int nodesVisited, String name) {
        this.runningTime = runningTime;
        this.nodesVisited = nodesVisited;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " {" +
                "runningTime=" + runningTime +
                "s, pathLength=" + pathLength +
                ", nodesVisited=" + nodesVisited +
                '}';
    }

    public long getPathLength() {
        return pathLength;
    }

    public void setPathLength(long pathLength) {
        this.pathLength = pathLength;
    }

    public double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public void setNodesVisited(int nodesVisited) {
        this.nodesVisited = nodesVisited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
