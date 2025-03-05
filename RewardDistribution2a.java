// Problem Description:
// You are given an array of performance ratings for n employees. You need to assign rewards to these employees based on the following rules:

// 1) Every employee must receive at least one reward.
// 2) Employees with a higher rating must receive more rewards than their adjacent colleagues.
// The goal is to determine the minimum number of rewards that need to be distributed while satisfying these conditions.

// Objective:
// Find the minimum number of rewards that need to be distributed according to the given rules.

// Approach:
// 1) Initial Setup: Start by initializing a rewards array with 1 for every employee (since every employee must get at least one reward).
// 2) First Pass (Left to Right): Traverse the array from left to right. For each employee, if their rating is higher than the previous employee, give 
// them one more reward than the previous employee.
// 3) Second Pass (Right to Left): Traverse the array from right to left. For each employee, if their rating is higher than the next employee, update 
// their reward to ensure they have more rewards than the next employee. Use Math.max() to ensure that the reward from the first pass isn't overwritten 
// incorrectly.
// 4) Total Rewards: Finally, sum up all the values in the rewards array to get the total number of rewards needed.
// Time Complexity: The solution requires two passes through the ratings array, so the time complexity is O(n), where n is the number of employees.

import java.util.Arrays;

public class RewardDistribution2a { // Class name RewardDistribution 

    // Function to calculate the minimum number of rewards for each reward 
    public static int minRewards(int[] ratings) {
        int n = ratings.length;
        // If there is only one employee, they get one reward
        if (n == 1) return 1;

        // Create an array to store rewards for each employee
        int[] rewards = new int[n];

        // Initializing rewards array with 1 reward for each employee 
        Arrays.fill(rewards, 1);

        // First pass: Traverse from left to right 
        for (int i = 1; i < n; i++) {
            // If the current rating is greater than the previous one, increment the reward
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        // Second pass: Traverse from right to left 
        for (int i = n - 2; i >= 0; i--) {
            // If the current rating is greater than the next one, ensure a higher reward than the next employee
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        // Sum up the rewards to get the total number of rewards needed 
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }

        return totalRewards;
    }

    public static void main(String[] args) {
        // Example 1
        int[] ratings1 = {1, 0, 2};
        System.out.println("Minimum Rewards (Example 1): " + minRewards(ratings1)); // Output: 5

        // Example 2
        int[] ratings2 = {1, 2, 2};
        System.out.println("Minimum Rewards (Example 2): " + minRewards(ratings2)); // Output: 4
    }
}
//Output: 
//Minimum Rewards (Example 1): 5
//Minimum Rewards (Example 2): 4
