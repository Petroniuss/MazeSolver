package com.bit.iv.data.structures;

import com.bit.iv.algorithm.KeyHolder;

import java.util.HashMap;
import java.util.Map;

public class MinHeap<E extends KeyHolder> {

    private int size;
    private int capacity;
    private E[] arr;
    private Map<E, Integer> pos;

    public MinHeap(E[] arr, int size, int capacity) {
        this.arr = arr;
        this.capacity = capacity;
        this.size = size;
        this.pos = new HashMap<>(capacity);
    }

    public E extractMin() {
        E root = arr[0];
        E last = arr[size - 1];

        arr[0] = last;
        pos.replace(last, 0);
        pos.remove(root);
        size--;

        minHeapify(0);

        return root;
    }

    public void insert(E el) {
        size++;
        arr[size - 1] = el;
        int key = el.getKey();
        el.setKey(Integer.MAX_VALUE);
        pos.put(el, size - 1);
       decreaseKey(size - 1, key);
    }

    public void decreaseKey(E vertex, int key) {
        decreaseKey(pos.get(vertex), key);
    }

    public void decreaseKey(int i, int key) {
        arr[i].setKey(key);
        while (i > 0 && arr[(i - 1) / 2].getKey() > arr[i].getKey()) {
            E e = arr[(i - 1) / 2];
            arr[(i - 1) / 2] = arr[i];
            arr[i] = e;
            pos.replace(arr[i], i);
            pos.replace(arr[(i - 1) / 2],  (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void minHeapify(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < size && arr[left].getKey() < arr[smallest].getKey())
            smallest = left;
        if(right < size && arr[right].getKey() < arr[smallest].getKey())
            smallest = right;

        if(smallest != i) {
            E smlstNode = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = smlstNode;

            pos.replace(arr[smallest], smallest);
            pos.replace(arr[i], i);

            minHeapify(smallest);
        }
    }

}
