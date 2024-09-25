package w04_nonlinear_structures;

import w03_linear_structures.LinkedListQueue;
import java.util.Random;

public class Problem1 {
    public static void main(String[] args) {
        TreeTraversal<Integer> tree = new TreeTraversal<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            int value = random.nextInt(100);
            System.out.println("Add: " + value);
            tree.add(value);
        }

        // Display results
        System.out.println("Pre Order Traversal: ");
        tree.preOrder();
        System.out.println("Post Order Traversal");
        tree.postOrder();
        System.out.println("In Order Traversal");
        tree.inOrder();
        System.out.println("Breadth First Traversal");
        tree.breadthFirst();
    }
}

class TreeTraversal<T extends Comparable<T>> extends BinarySearchTree<T> {
    private void preOrderRecursive(BinaryTreeNode<T> node) {
        if (node != null) {
            // Visit node
            System.out.println(node.data);
            // Traverse sub-trees
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    public void preOrder() {
        preOrderRecursive(root);
    }

    private void postOrderRecursive(BinaryTreeNode<T> node) {
        if (node != null) {
            // Traverse sub-trees
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            // Visit node
            System.out.println(node.data);
        }
    }

    public void postOrder() {
        postOrderRecursive(root);
    }

    private void inOrderRecursive(BinaryTreeNode<T> node) {
        if (node != null) {
            // Traverse left sub-tree
            inOrderRecursive(node.left);
            // Visit node
            System.out.println(node.data);
            // Traverse right sub-tree
            inOrderRecursive(node.right);
        }
    }

    public void inOrder() {
        inOrderRecursive(root);
    }

    public void breadthFirst() {
        // A queue is needed
        LinkedListQueue<BinaryTreeNode<T>> queue = new LinkedListQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            // Extract the first element in the queue
            BinaryTreeNode<T> node = queue.peekFront();
            queue.deQueue();
            if (node != null) {
                // Visit node
                System.out.println(node.data);
                // Enqueue sub-tree roots
                queue.enQueue(node.left);
                queue.enQueue(node.right);
            }
        }
    }
}