package com.bit.iv.algorithms.find.path;

import com.bit.iv.algorithms.AlgorithmPerformance;
import com.bit.iv.graph.Graph;
import com.bit.iv.graph.Node;

public interface PathAlgorithm {

    AlgorithmPerformance findPath(Graph graph, Node start, Node end);
}
