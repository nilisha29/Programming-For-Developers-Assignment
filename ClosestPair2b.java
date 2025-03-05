// Problem Description:
// You are given two arrays, x_coords and y_coords, representing the x and y coordinates of points in a 2D plane. The task is to find the pair of 
// points (one from each array) that are closest to each other based on the Manhattan distance.
// Among all possible pairs, you need to return the lexicographically smallest pair of indices. A pair (i1, j1) is considered lexicographically 
// smaller than (i2, j2) if i1 < i2 or if i1 == i2 and j1 < j2.

// Objective:
// •	Calculate the Manhattan distance between all possible pairs of points from the two arrays.
// •	Find the pair of indices that results in the smallest Manhattan distance.
// •	In case of ties, return the lexicographically smallest pair of indices.

// Approach:
// 1.	Brute-force Pair Comparison: Iterate through each pair of points from the two arrays and calculate their Manhattan distance.
// 2.	Track Minimum Distance: Keep track of the smallest distance and the corresponding pair of indices.
// 3.	Lexicographical Comparison: In case of multiple pairs having the same distance, choose the lexicographically smallest pair based on index 
// comparison.

// Time Complexity:
// •The time complexity is O(n^2), where n is the length of the input arrays, as every pair of points is compared.


public class ClosestPair2b { 

    // Function to find the lexicographically smallest pair of points with the smallest distance
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        int minDistance = Integer.MAX_VALUE; // Initialize the minimum distance to a large value
        int[] result = new int[2]; // Array to store the result indices

        // Iterate through all pairs of points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate the Manhattan distance between points (i, j)
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);

                // Check if the current distance is smaller than the minimum distance found so far
                if (distance < minDistance || (distance == minDistance && (i < result[0] || (i == result[0] && j < result[1])))) {
                    minDistance = distance; // Update the minimum distance
                    result[0] = i; // Update the first index of the result
                    result[1] = j; // Update the second index of the result
                }
            }
        }

        return result; // Return the result indices
    }

    public static void main(String[] args) {
        // Example input
        int[] x_coords = {1, 2, 3, 2, 4};
        int[] y_coords = {2, 3, 1, 2, 3};

        // Find the closest pair of points
        int[] result = findClosestPair(x_coords, y_coords);

        // Print the result
        System.out.println("The indices of the closest pair of points are: [" + result[0] + ", " + result[1] + "]");
    }
}
//Output:
//The indices of the closest pair of points are: [0, 3]