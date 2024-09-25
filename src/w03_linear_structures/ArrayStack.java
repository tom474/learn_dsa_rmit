package w03_linear_structures;

// Array-based implementation of stack
public class ArrayStack<T> {
    private int size;
    // Assume the number of elements in the queue will never exceed CAPACITY
    private static int CAPACITY = 100;
    private T[] items;

    public ArrayStack() {
        size = 0;
        items = (T[]) new Object[CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(T item) {
        // Make sure the stack still have empty slot
        if (size < CAPACITY) {
            items[size] = item;
            size++;
            return true;
        }
        return false;
    }

    public boolean pop() {
        // Make sure the stack is not empty
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    public T peek() {
        // Make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return items[size - 1];
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("Hello");
        stack.push("World");
        stack.push("From");
        stack.push("RMIT");
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}
