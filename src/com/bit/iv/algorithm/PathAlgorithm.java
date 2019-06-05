package com.bit.iv.algorithm;

import com.bit.iv.Graph;
import com.bit.iv.Node;

public interface PathAlgorithm {

    AlgorithmPerformance findPath(Graph graph, Node start, Node end);
}
