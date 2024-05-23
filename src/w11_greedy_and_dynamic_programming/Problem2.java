package w11_greedy_and_dynamic_programming;

public class Problem2 {
    public static void main(String[] args) {
        int[][] castles = {
                {0, 1, 2, 8},
                {1, 0, 3, 5},
                {2, 3, 0, 4},
                {8, 5, 4, 0}
        };
        System.out.println("Shortest total length: " + minimumSpanningTree(castles));
    }

    static int minimumSpanningTree(int[][] nodes) {
        int n = nodes.length;
        int length = 0;

        // Use this array to mark nodes have been added already
        boolean[] added = new boolean[n];

        // Distance between the tree being built and not-yet-added nodes
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        // Insert node zero (any node is OK) first, so set its distance to zero
        distances[0] = 0;

        // Stop when the number of added nodes = n
        int nodeCount = 0;
        while (nodeCount < n) {
            int shortest = Integer.MAX_VALUE;
            int shortestNode = -1;  // Index of the node having the shortest distance to the tree

            // Determine the shortest-distance node to the tree
            for (int i = 0; i < n; i++) {
                if (added[i]) {
                    continue;
                }
                if (shortest > distances[i]) {
                    shortest = distances[i];
                    shortestNode = i;
                }
            }

            if (shortest == Integer.MAX_VALUE) {
                // We cannot go further - the graph is not connected
                return Integer.MAX_VALUE;
            }

            // Add the shortest node to the tree
            added[shortestNode] = true;
            length += distances[shortestNode];
            nodeCount++;

            // Update other distances to the tree
            for (int i = 0; i < n; i++) {
                if (added[i]) {
                    continue;
                }
                // ShortestNode and i are connected
                if (nodes[shortestNode][i] > 0) {
                    // Connect through shortestNode is better?
                    if (distances[i] > nodes[shortestNode][i]) {
                        distances[i] = nodes[shortestNode][i];
                    }
                }
            }
        }
        return length;
    }
}
