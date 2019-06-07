import com.bit.iv.data.structures.MinHeap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MinHeapTest {

    private MinHeap<Node> minHeap;
    private Node[] arr = new Node[5];

    @Before
    public void setUp() {

        minHeap = new MinHeap<>(arr, 0, 5,
                Node::retrieveKey, Node::setKey);

        for (int i = 0; i < 5; i++) {
            minHeap.insert(new Node(6 - i));
        }
    }

    @Test
    public void shouldExtractCorrectly() {
        for (int i = 2; i < 7; i++) {
            assertEquals(minHeap.extractMin().key, i);
        }
        assertTrue(minHeap.isEmpty());
    }

    @Test
    public void shouldDecreaseKey() {
        minHeap.decreaseKey(arr[4], 0);
        assertEquals(0, minHeap.extractMin().key);
    }


    private static class Node {
        int key;
        Node(int key) {
            this.key = key;
        }

        static void setKey(Node n, int key){
            n.key = key;
        }

        static Integer retrieveKey(Node n) {
            return n.key;
        }
    }
}
