// Problem Description:
// You are given a graph where each node represents a location in a city, and each edge represents a road between two locations. 
// Each location has a package (either 0 or 1), where 1 indicates a package is available to be collected. The objective is to determine the minimum number of roads you need to traverse to collect all packages and return to your starting location. You can move to an adjacent location and collect packages from locations within a distance of 2 from your current location.

// Objective:
// The goal is to find the minimum number of roads that must be traversed to collect all packages and return to the starting location. 
// The result should consider the possibility of traversing the same roads multiple times.

// Approach:
// 1) Graph Representation: The city is represented as a graph where locations are nodes, and roads are edges.
// Use an adjacency list to store the connections between locations.

// 2) Depth-First Search (DFS): Start from each location, perform DFS to explore all possible paths while collecting packages.
// Track the number of roads traversed and the number of packages collected along the way.

// 3) Breadth-First Search (BFS): For each location, use BFS to calculate the shortest path to all other locations.
// This ensures that we can efficiently calculate the number of roads to traverse to collect packages within a distance of 2.

// 4) Package Collection: At each location, collect packages from locations within a distance of 2. Track the total number of packages collected.

// 5) Backtracking: After collecting all packages, backtrack to the starting point while minimizing the number of roads traveled.

// 6)Return the Minimum Roads: The final result is the minimum number of roads traversed to collect all packages and return to the starting location.


import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    private List<List<Integer>> graph; // Adjacency list representation of the graph
    private int[] packages; // Array to store package information for each location
    private boolean[] visited; // Array to keep track of visited nodes during DFS
    private int minRoads; // Variable to store the minimum number of roads traversed

    public int PackageCollection(int[] packages, int[][] roads) {
        // Initialize class variables
        this.packages = packages;
        this.graph = buildGraph(packages.length, roads);
        this.visited = new boolean[packages.length];
        this.minRoads = Integer.MAX_VALUE; // Initialize with a large value

        // Trying to  start from each location to find the optimal starting point
        for (int i = 0; i < packages.length; i++) {
            dfs(i, i, 0, new HashSet<>());
        }

        return minRoads;
    }

    private void dfs(int start, int current, int roads, Set<Integer> collected) {
        // If all packages are collected, update minRoads and return
        if (collected.size() == countPackages()) {
            // Add the distance to return to the starting point
            minRoads = Math.min(minRoads, roads + shortestPath(current, start));
            return;
        }

        visited[current] = true; // Mark current node as visited

        // Collect packages within 2 steps of the current location
        for (int i = 0; i < packages.length; i++) {
            if (shortestPath(current, i) <= 2 && packages[i] == 1) {
                collected.add(i);
            }
        }

        // Explore all neighboring locations
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                // Recursive call to explore this path
                dfs(start, neighbor, roads + 1, new HashSet<>(collected));
            }
        }

        visited[current] = false; // Backtrack: mark current node as unvisited
    }

    private List<List<Integer>> buildGraph(int n, int[][] roads) {
        // Create an adjacency list representation of the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Add edges to the graph (undirected)
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        return graph;
    }

    private int shortestPath(int from, int to) {
        // BFS to find shortest path between two nodes
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[packages.length];
        int[] distance = new int[packages.length];
        Arrays.fill(distance, Integer.MAX_VALUE);

        queue.offer(from);
        visited[from] = true;
        distance[from] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == to) return distance[current];

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return Integer.MAX_VALUE; // If no path found
    }

    private int countPackages() {
        // Count the total number of packages to be collected
        int count = 0;
        for (int p : packages) {
            if (p == 1) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] packages = {1, 0, 0, 0, 0, 1};
        int[][] roads = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(solution.PackageCollection(packages, roads));
    }
}

//Output: 4
