package w7_brute_force;

import w3_linear_structures.LinkedListQueue;

public class Problem2 {
    public static void main(String[] args) {
        MatrixGraph g = new MatrixGraph(6);
        g.setNodeLabel(0, "A");
        g.setNodeLabel(1, "B");
        g.setNodeLabel(2, "C");
        g.setNodeLabel(3, "D");
        g.setNodeLabel(4, "E");
        g.setNodeLabel(5, "F");
        g.addEdge(0, 1);  // A-B
        g.addEdge(0, 2);  // A-C
        g.addEdge(0, 3);  // A-D
        g.addEdge(1, 2);  // B-C
        g.addEdge(1, 4);  // B-E
        g.addEdge(2, 3);  // C-D
        g.addEdge(2, 4);  // C-E
        g.addEdge(2, 5);  // C-F
        g.addEdge(3, 5);  // D-F
        g.DFS();
        g.BFS();
    }
}

class MatrixGraph {
    // This matrix presents the connections in the graph
    int[][] matrix;
    // This array presents the labels of the vertices/nodes
    String[] nodeLabels;
    int size;

    // Create a graph with the number of vertices/nodes
    public MatrixGraph(int nodes) {
        size = nodes;
        matrix = new int[size][size];
        // No connection/edge initial
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        nodeLabels = new String[size];
    }

    // Set the label for a node
    public void setNodeLabel(int nodeIndex, String label) {
        nodeLabels[nodeIndex] = label;
    }

    // Create an edge between two nodes
    public void addEdge(int node1, int node2) {
        matrix[node1][node2] = 1;
        // For undirected graph, node1 -> node2 also means node2 -> node1
        matrix[node2][node1] = 1;
    }

    // Remove an edge between two nodes
    public void removeEdge(int node1, int node2) {
        matrix[node1][node2] = 0;
        // For undirected graph, node1 -> node2 also means node2 -> node1
        matrix[node2][node1] = 0;
    }

    // Depth First Search/Traversal
    public void DFS() {
        System.out.println("Depth First Search/Traversal: ");
        // Visited states
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }
        // Start the DFS recursively from node 0 (you can start from any node)
        DFSR(0, visited);

        // The above code assumes tha graph is connected
        // That mean you can reach all nodes from any node
        // If the graph is not connected, you must call DFSR on every node
    }

    public void DFSR(int nodeIndex, boolean[] visited) {
        if (visited[nodeIndex]) {
            // This node has been visited before
            return;
        }
        // This is the "visit" operation
        // Do whatever you want with this node
        System.out.println("Visit: " + nodeLabels[nodeIndex]);
        // Mark the visited state
        visited[nodeIndex] = true;
        // Apply the DFS process to all adjacency nodes
        for (int i = 0; i < size; i++) {
            if (matrix[nodeIndex][i] == 1 && !visited[i]) {
                DFSR(i, visited);
            }
        }
    }

    // Breadth First Search/Traversal
    public void BFS() {
        System.out.println("Breadth First Search/Traversal: ");
        // Visited states
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // To enqueue a node, we just need the node index
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // Start from node 0
        // You can start from any node
        queue.enQueue(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int nodeIndex = queue.peekFront();
            queue.deQueue();
            // "Visit" this node
            System.out.println("Visit: " + nodeLabels[nodeIndex]);

            // Add all adjacency nodes to the queue
            for (int i = 0; i < size; i++) {
                if (matrix[nodeIndex][i] == 1 && !visited[i]) {
                    queue.enQueue(i);
                    visited[i] = true;
                }
            }
        }

        // The above code assumes the graph is connected
        // That mean you can reach all nodes from any node
        // If the graph is not connected, you must call BFS on every node
        // to make sure you visit all nodes
    }
}