package test1_real;

public class CuongBSTWithSize {
    CuongBinaryTreeNode root;
    int size;

    public CuongBSTWithSize() {
        this.root = null;
        this.size = 0;
    }

    // subTreeSize complexity = O(N)
    public int subTreeSize(CuongBinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        return subTreeSize(node.left) + subTreeSize(node.right) + 1;
    }

    // add complexity = O(logN)
    private CuongBinaryTreeNode oldAdd(int value) {
        CuongBinaryTreeNode newNode = null;
        if (root == null) {
            root = new CuongBinaryTreeNode(null, value);
            size++;
            return root;
        }
        CuongBinaryTreeNode node = root;
        while (node != null) {
            if (value < node.data) {
                if (node.left == null) {
                    newNode = new CuongBinaryTreeNode(node, value);
                    node.left = newNode;
                    size++;
                    return newNode;
                }
                node = node.left;
            } else if (value > node.data) {
                if (node.right == null) {
                    newNode = new CuongBinaryTreeNode(node, value);
                    node.right = newNode;
                    size++;
                    return newNode;
                }
                node = node.right;
            } else {
                return null;
            }
        }
        return null;
    }

    public CuongBinaryTreeNode add(int value) {
        CuongBinaryTreeNode newNode = oldAdd(value);
        CuongBinaryTreeNode temp = newNode.parent;
        while (temp != null) {
            temp.subtreeNodes++;
            temp = temp.parent;
        }
        return newNode;
    }

    // search complexity = O(logN)
    public CuongBinaryTreeNode search(int value) {
        CuongBinaryTreeNode node = root;
        while (node != null) {
            if (value == node.data) {
                return node;
            }
            if (value > node.data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    // Client code
    public static void main(String[] args) {
        CuongBSTWithSize tree = new CuongBSTWithSize();
        tree.add(15);
        tree.add(10);
        tree.add(20);
        tree.add(8);
        tree.add(12);
        tree.add(16);
        tree.add(25);
        CuongBinaryTreeNode node;
        node = tree.search(15);
        System.out.println("Subtree size at 15 is " + tree.subTreeSize(node));  // 7
        System.out.println("Subtree size at 15 is " + node.subtreeNodes);       // 7
        node = tree.search(10);
        System.out.println("Subtree size at 10 is " + tree.subTreeSize(node));  // 3
        System.out.println("Subtree size at 10 is " + node.subtreeNodes);       // 3
        node = tree.search(16);
        System.out.println("Subtree size at 16 is " + tree.subTreeSize(node));  // 1
        System.out.println("Subtree size at 16 is " + node.subtreeNodes);       // 1
    }
}

class CuongBinaryTreeNode<T> {
    public int data;
    public CuongBinaryTreeNode parent;
    public CuongBinaryTreeNode left = null;
    public CuongBinaryTreeNode right = null;
    public int subtreeNodes;
    int height;

    public CuongBinaryTreeNode(CuongBinaryTreeNode parent, int data) {
        this.data = data;
        this.parent = parent;
        subtreeNodes = 1;
        height = 0;
    }
}
