package w11_greedy_and_dynamic_programming;

public class Problem1 {
    public static void main(String[] args) {
        int[][] distances = {
                {0, 3, 2, 0},
                {3, 0, 0, 5},
                {2, 0, 0, 9},
                {0, 5, 9, 0}
        };
        System.out.println("Shortest distance: " + shortestPath(distances, 0, distances.length - 1));
    }

    static int shortestPath(int[][] nodes, int src, int dest) {
        int n = nodes.length;

        int[] distances = new int[n];       // distance[i] stores the minimum distance from src to i
        boolean[] visited = new boolean[n]; // visited state
        int[] previous = new int[n];        // used to construct the shortest path; previous[i] stores the node that is visited before i

        // Initialization
        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        distances[src] = 0;  // zero distance from the src to itself

        while (true) {
            // Greedy choice: retrieve the shortest-distance node from unvisited nodes
            int shortest = Integer.MAX_VALUE;
            int shortestNode = -1;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                if (shortest > distances[i]) {
                    shortest = distances[i];
                    shortestNode = i;
                }
            }

            // Update the shortest distance through shortest node to all unvisited nodes
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                // shortestNode and i are connected?
                if (nodes[shortestNode][i] > 0) {
                    // Current distance to i > distance reached through shortestNode
                    if (distances[i] > distances[shortestNode] + nodes[shortestNode][i]) {
                        distances[i] = distances[shortestNode] + nodes[shortestNode][i];
                        previous[i] = shortestNode;
                    }
                }
            }

            if (shortestNode == dest) {
                // We reach the destination
                // Display the shortest path
                String path = shortestNode + "";
                while (previous[shortestNode] != -1) {
                    shortestNode = previous[shortestNode];
                    path = shortestNode + " -> " + path;
                }

                System.out.println("Shortest path: " + path);
                return distances[dest];
            }

            // Even shortest is INFINITY => stop
            if (shortest == Integer.MAX_VALUE) {
                // We cannot go further
                return Integer.MAX_VALUE;
            }
            // Continue the next round
            visited[shortestNode] = true;
        }
    }
}
