package com.bit.iv.data.structures;

import java.util.stream.IntStream;

public class DisjointSet {

    private int[] parent;
    private int[] rank;
    private int size;

    public DisjointSet(int size) {
        this.parent = new int [size];
        this.rank = new int [size];
        this.size = size;
        IntStream.range(0, size).forEach(i -> parent[i] = i);
    }

    public int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);

        return parent[i];
    }

   public void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if(pA == pB)
            return;
       if (rank[pA] > rank[pB]) {
           parent[pB] = pA;
       } else if (rank[pB] > rank[pA]) {
            parent[pA] = pB;
       } else {
           parent[pB] = pA;
           rank[pA]++;
       }
   }


}
