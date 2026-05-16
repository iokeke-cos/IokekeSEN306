public interface QueueADT {
    void enqueue(int element);
    int dequeue();
    boolean isEmpty();
    int size();
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class LinkedQueue implements QueueADT {

    private Node front;    //front of queue
    private Node rear;     //back of queue
    private int size;      //number of elemennts

    public LinkedQueue() {
        front = rear = null;
        size = 0;
    }

    @Override
    public void enqueue(int element) {
        Node newNode = new Node(element);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int data = front.data;
        front = front.next;
        size--;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}