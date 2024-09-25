package w03_linear_structures;

// SinglyLinkedList-based implementation of queue
public class LinkedListQueue<T> {
    // This class is used as a container of data
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node<T> head;

    public LinkedListQueue() {
        size = 0;
        head = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean enQueue(T item) {
        // Add a new node at the beginning
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    public boolean deQueue() {
        // Remove the last node
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head = null;
            size = 0;
            return true;
        }
        Node<T> previous = head;
        Node<T> current = previous.next;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        size--;
        return true;
    }

    public T peekFront() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node.data;
    }

    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        queue.enQueue("Algorithm");
        queue.enQueue("Is");
        queue.enQueue("Easy");
        queue.enQueue("Right?");
        while (!queue.isEmpty()) {
            System.out.println(queue.peekFront());
            queue.deQueue();
        }
    }
}
