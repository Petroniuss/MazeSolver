package com.bit.iv.algorithms.find.path;

import com.bit.iv.algorithms.AlgorithmPerformance;
import com.bit.iv.graph.Edge;
import com.bit.iv.graph.Graph;
import com.bit.iv.graph.Node;

import java.util.Stack;

public class DepthFirstSearch implements PathAlgorithm {
    private int nodesVisited = 0;

    @Override
    public AlgorithmPerformance findPath(Graph graph, Node start, Node end) {
        long startTime = System.currentTimeMillis();
        iterativeDFS(start, end);
        long endTime = System.currentTimeMillis();
        return new AlgorithmPerformance((endTime - startTime) / 1000.0, nodesVisited, "Depth First Search");
    }

    private boolean dfs(Node parent, Node end) {
        if(parent == end)
            return true;
        //System.out.printf("Parent(%d, %d) %n", parent.getX(), parent.getY());
        parent.setVisited(true);
        nodesVisited++;
        for (Edge e : parent.getNeighbours().values()) {
            Node n = e.getOppositeNode(parent);
            if (!n.isVisited()) {
                n.setParent(parent);
                if(dfs(n, end))
                    return true;
            }
        }

        return false;
    }

    private boolean iterativeDFS(Node start, Node end) {
        Stack<Node> stack = new Stack<>();
        Node popped = start;
        popped.setVisited(true);
        stack.push(start);

        while (!stack.isEmpty() && popped != end) {
            popped = stack.pop();
            nodesVisited++;

            final Node parent = popped;
            popped.getNeighboursCollection().parallelStream().map(e -> e.getOppositeNode(parent))
                    .filter(v -> !v.isVisited()).forEach(u -> {
                u.setVisited(true);
                u.setParent(parent);
                stack.push(u);
            });

        }

        return popped == end;
    }

}
