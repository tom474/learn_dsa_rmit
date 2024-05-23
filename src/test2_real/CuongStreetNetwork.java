package test2_real;

public class CuongStreetNetwork {
    int[][] network;

    public CuongStreetNetwork(int[][] network) {
        this.network = network;
    }

    // Complexity: O(N)
    public int nearestNeighbor() {
        int nearest = 0;
        for (int i = 0; i < network.length; i++) {
            if (network[0][i] > 0) {
                nearest = i;
            }
        }
        for (int i = 1; i < network.length; i++) {
            if (network[0][i] > 0) {
                if (network[0][i] < network[0][nearest]) {
                    nearest = i;
                }
            }
        }
        return nearest;
    }

    // Complexity O(N^2)
    public int shortestToSchool() {
        int src = 0;
        int dest = network.length - 1;
        int n = network.length;

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
                if (network[shortestNode][i] > 0) {
                    // Current distance to i > distance reached through shortestNode
                    if (distances[i] > distances[shortestNode] + network[shortestNode][i]) {
                        distances[i] = distances[shortestNode] + network[shortestNode][i];
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

    public static void main(String[] args) {
        int[][] network = {
                {0, -1, 5, 10},
                {-1, 0, 4, 2},
                {-1, 1, 0, 4},
                {3, -1, 7, 0}
        };
        CuongStreetNetwork sn = new CuongStreetNetwork(network);
        System.out.println(sn.nearestNeighbor());
        System.out.println(sn.shortestToSchool());
    }
}
