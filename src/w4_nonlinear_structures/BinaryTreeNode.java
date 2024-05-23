package w4_nonlinear_structures;

public class BinaryTreeNode<T extends Comparable<T>> {
    T data = null;
    BinaryTreeNode<T> parent = null;
    BinaryTreeNode<T> left = null;
    BinaryTreeNode<T> right = null;
    int height;

    public BinaryTreeNode(BinaryTreeNode<T> parent, T data) {
        this.parent = parent;
        this.data = data;
        height = 0;
    }

    public int getHeight() {
        return height;
    }

    // Update and return the updated height
    public int updateHeight() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        height = Math.max(leftHeight, rightHeight) + 1;
        return height;
    }

    // Get balance factor, to check if tree rotation is needed
    public int getBalanceFactor() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        return rightHeight - leftHeight;
    }
}
