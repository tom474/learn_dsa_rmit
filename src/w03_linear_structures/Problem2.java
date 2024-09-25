package w03_linear_structures;

public class Problem2 {
    public static void main(String[] args) {
        // {1, 2, 3, 4, 5} => execute every 2nd person
        // After the first round, remain: 1, 3, 5
        // After the second round, remain: 3
        CircularList test1 = new CircularList();
        int m1 = 3;
        for (int i = 1; i <= 6; i++) {
            test1.add2End(i);
        }
        System.out.println("Test 1: n = 6, m = 3");
        Node head1 = test1.getHead();
        while (test1.getSize() > 1) {
            // Execute every m-nth person
            for (int i = 1; i < m1; i++) {
                head1 = test1.getNext(head1);
            }
            Node temp = head1;
            head1 = head1.next;
            System.out.printf("Remove %d\n", temp.value);
            test1.removeNode(temp);
        }
        System.out.printf("Remain %d\n", test1.getHead().value);

        // {1, 2, ..., 40, 41} => execute every 3rd person
        // Last one: 31
        CircularList test2 = new CircularList();
        int m2 = 3;
        for (int i = 1; i <= 41; i++) {
            test2.add2End(i);
        }
        System.out.println("Test 2: n = 41, m = 3");
        Node head2 = test2.getHead();
        while (test2.getSize() > 1) {
            // Execute every m-nth person
            for (int i = 1; i < m2; i++) {
                head2 = test2.getNext(head2);
            }
            Node temp = head2;
            head2 = head2.next;
            System.out.printf("Remove %d\n", temp.value);
            test2.removeNode(temp);
        }
        System.out.printf("Remain %d\n", test2.getHead().value);
    }

    static class CircularList {
        Node head, tail;
        int size;

        public CircularList() {
            head = tail = null;
            size = 0;
        }

        public Node getHead() {
            return head;
        }

        public int getSize() {
            return size;
        }

        // Add a new node to the tail of the list
        // tail.next must point back to the head
        public void add2End(int value) {
            Node n = new Node(value);
            if (size == 0) {
                head = tail = n;
            } else {
                tail.next = n;
            }
            tail = n;
            n.next = head;
            size++;
        }

        // Get the next node of node c
        public Node getNext(Node c) {
            return c.next;
        }

        // Remove the node passed to the method
        public void removeNode(Node c) {
            if (size == 0) {
                return;
            }
            // Decide the preceding node
            Node t = head;
            while (t.next != c) {
                t = t.next;
            }
            if (c == head) {
                head = c.next;
            }
            if (c == tail) {
                tail = t;
            }
            t.next = t.next.next;
            size--;
        }
    }

    // Create a basic node
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            next = null;
        }

        // Override hashCode() and equals() to compare object
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) {
                return false;
            } else {
                return value == ((Node)other).value;
            }
        }
    }
}
