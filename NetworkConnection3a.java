// Problem Description:
// You have a network of n devices, each with its own communication module installed at a cost defined by the array modules. Alternatively, 
// devices can communicate with each other directly, with a cost specified by the array connections. The task is to determine the minimum total 
// cost to connect all devices in the network.

// The cost of connecting two devices is given by the connections array, where connections[j] = [device1, device2, cost] represents the cost to 
// connect devices device1 and device2.

// Objective:
// Determine the minimum total cost to connect all devices in the network using communication modules and/or direct connections.

// Approach:
// 1) Graph Representation: Treat devices as nodes and the direct connections between them as edges. Each device can also have a virtual edge connecting
//  it to a "super node" (node 0) with the cost of installing the communication module.

// 2)Kruskal's Algorithm: Sort all edges (direct connections and module installation costs) by their cost.
// Use Union-Find to efficiently manage the connected components and avoid cycles.
// Add edges to the minimum spanning tree (MST) by connecting devices until all are connected.

// 3) Union-Find: This data structure supports efficient find and union operations to keep track of connected components and ensure no cycles in the 
// MST.

// 4)Stop when all devices are connected: Once n edges are used (for n devices), the MST is complete, and the total cost is the minimum required to 
// connect all devices.


import java.util.*;

public class NetworkConnection3a {

    // Union-Find Data Structure for Kruskal's Algorithm
    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;  // Initialize each node as its own parent
                rank[i] = 0;    // Initialize rank to 0
            }
        }

        // Find the root of the set containing x, with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        // Union of two sets
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                // Union by rank to keep tree shallow
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    // Method to find the minimum cost to connect all devices
    public static int minCost(int n, int[] modules, int[][] connections) {
        List<int[]> edges = new ArrayList<>();

        // Add edges for installing communication modules (virtual edges)
        for (int i = 0; i < n; i++) {
            edges.add(new int[]{0, i + 1, modules[i]});  // [0, i+1, cost] is a virtual edge connecting to "super node"
        }

        // Add edges for the given direct connections
        for (int[] connection : connections) {
            edges.add(new int[]{connection[0], connection[1], connection[2]});
        }

        // Sort the edges by their cost
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // Initialize Union-Find
        UnionFind uf = new UnionFind(n + 1); // n + 1 because we have a "super node"

        int totalCost = 0;
        int edgesUsed = 0;

        // Iterate over the sorted edges and build the MST
        for (int[] edge : edges) {
            int device1 = edge[0];
            int device2 = edge[1];
            int cost = edge[2];

            // If the two devices are in different components, union them
            if (uf.find(device1) != uf.find(device2)) {
                uf.union(device1, device2);
                totalCost += cost;
                edgesUsed++;

                // If we've used n edges (which means we have connected all devices), stop
                if (edgesUsed == n) {
                    break;
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Example input
        int n = 3;
        int[] modules = {1, 2, 2};
        int[][] connections = {
            {1, 2, 1},
            {2, 3, 1}
        };

        // Call the minCost method
        int result = minCost(n, modules, connections);

        // Output the result
        System.out.println("The minimum cost to connect all devices is: " + result);
    }
}

/*Output :The minimum cost to connect all devices is: 3 */
