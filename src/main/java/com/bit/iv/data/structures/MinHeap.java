package com.bit.iv.data.structures;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/*
 * This implementation of minheap provides O(logN) for decrease key, which standard java library priority queue doesn't have
 */
public class MinHeap<E> {

    private int size;
    private E[] arr;
    private Map<E, Integer> pos;
    private final int capacity;
    private Function<E, Integer> retrieveKey;
    private BiConsumer<E, Integer> updateKey;

    public MinHeap(E[] arr, int size, int capacity,
                   Function<E, Integer> retrieveKey,
                   BiConsumer<E, Integer> updateKey) {
        this.arr = arr;
        this.size = size;
        this.pos = new HashMap<>(capacity);
        this.capacity = capacity;
        this.retrieveKey = retrieveKey;
        this.updateKey = updateKey;
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
        if(size >= capacity)
            throw new RuntimeException("Capacity of minHeap has been reached");
        size++;
        arr[size - 1] = el;
        int key = retrieveKey.apply(el);
        updateKey.accept(el, Integer.MAX_VALUE);
        pos.put(el, size - 1);
       decreaseKey(size - 1, key);
    }

    public void decreaseKey(E vertex, int key) {
        decreaseKey(pos.get(vertex), key);
    }

    private void decreaseKey(int i, int key) {
        updateKey.accept(arr[i], key);
        while (i > 0 && retrieveKey.apply(arr[( i - 1 ) / 2]) > retrieveKey.apply(arr[i])) {
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

        if(left < size && retrieveKey.apply(arr[left]) < retrieveKey.apply(arr[smallest]))
            smallest = left;
        if(right < size && retrieveKey.apply(arr[right]) < retrieveKey.apply(arr[smallest]))
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
