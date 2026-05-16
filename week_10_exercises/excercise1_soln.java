// Provided Node definition
class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

// Complete LinkedQueue Implementation
public class LinkedQueue implements QueueADT {
    private Node head;
    private Node tail;
    private int count;

    @Override
    public void enqueue(int element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Empty");
        }
        int value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
}


//Conceptual Question: "Why does this work?"
//Answer: It works perfectly without changing any client code in Main.java because both ArrayListQueue and LinkedQueue implement the same QueueADT interface. This is an application of Polymorphism and Abstraction; the client code only interacts with the behaviors specified by the interface, making the underlying implementation details completely interchangeable.