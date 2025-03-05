// Problem Description:
// You are given two sorted arrays, returns1 and returns2, representing the investment returns of two different portfolios. 
// You need to determine the kth smallest product that can be achieved by selecting one element from each array and multiplying them together.

// Objective:
// The goal is to find the kth smallest product formed by multiplying one element from returns1 with one element from returns2.

// Approach:
// 1) Min-Heap (Priority Queue): We use a min-heap to efficiently track the smallest product combinations. The heap will store the products along 
// with the indices of the elements from both arrays that created those products.

// 2) Initialization: We start by inserting products formed by combining each element of returns1 with the first element of returns2 into the heap. 
// This ensures that we can begin the process of finding the smallest products.

// 3) Heap Extraction: The smallest product (top of the heap) is repeatedly extracted from the heap. After extracting the smallest product, the next 
// possible combination of products, by increasing the index in returns2 (while keeping the same index in returns1), is added to the heap.

// 4) Repeat k times: This process continues until we have extracted k products from the heap. The kth extracted product is the result.

// 5) Time Complexity: This approach is efficient because each insertion and removal operation from the heap takes logarithmic time relative to the size
// of the heap. Since the heap will contain at most m * n products (where m and n are the sizes of returns1 and returns2), the overall complexity is 
// O(klog(min(m,n))).


import java.util.PriorityQueue;

public class KthSmallestProduct1b { 
    public static int findKthSmallestProduct(int[] returns1, int[] returns2, int k) {
        // Min-Heap (Priority Queue) to store product values along with indices from returns1 and returns2.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> 
            Integer.compare(a[0], b[0]) // Sorting by product value (smallest first)  
        );

        // Initializing the heap with the first element from returns1 and first element from returns2
        for (int i = 0; i < returns1.length; i++) {
            // Store {product, index of returns1, index of returns2}
            minHeap.offer(new int[]{returns1[i] * returns2[0], i, 0});
        }

        int result = 0; // Variable to store the kth smallest product

        // Process k smallest elements from the heap
        while (k-- > 0) {
            int[] top = minHeap.poll(); // Extract the smallest product from the heap
            result = top[0]; // Store the current smallest product as the result
            int i = top[1]; // Index from returns1
            int j = top[2]; // Index from returns2

            // If there is a next element in returns2, add the new product to the heap
            if (j + 1 < returns2.length) {
                minHeap.offer(new int[]{returns1[i] * returns2[j + 1], i, j + 1});
            }
        }

        return result; // Return the kth smallest product
    }

    public static void main(String[] args) {
        // Example test case 1
        int[] returns1 = {-4, -2, 0, 3}; // First sorted array
        int[] returns2 = {2, 4}; // Second sorted array
        int k = 6; // Target kth smallest product

        // Call the function and print the result
        System.out.println("The " + k + "th smallest investment return is: " + 
                            findKthSmallestProduct(returns1, returns2, k));
    }
}
/*
Example 1:

Input: 
returns1 = [2,5]
returns2 = [3,4]
k = 2

Heap Processing:
1st smallest product: 2 × 3 = 6
2nd smallest product: 2 × 4 = 8

Output:
The 2nd smallest investment return is: 8

Example 2:

Input: 
returns1 = [-4,-2,0,3]
returns2 = [2,4]
k = 6

Heap Processing:
1st smallest product: -4 × 4 = -16
2nd smallest product: -4 × 2 = -8
3rd smallest product: -2 × 4 = -8
4th smallest product: -2 × 2 = -4
5th smallest product:  0 × 2 = 0
6th smallest product:  0 × 4 = 0

Output:
The 6th smallest investment return is: 0
*/
