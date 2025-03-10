// Problem Description
// You have a material that reacts when exposed to temperatures higher than a certain critical temperature f (where 0 ≤ f ≤ n). 
// The goal is to determine f with the minimum number of tests.
// You are given k identical samples of the material and n temperature levels. If a material sample reacts, it can no longer be used for 
// further testing. If it remains unchanged, it can be reused.The challenge is to efficiently determine f while minimizing the number of tests.

// Objective
// The objective is to find the minimum number of measurements required to determine the critical temperature f, given k samples and n temperature 
// levels.

//Approach
// The problem is solved using dynamic programming to find the minimum number of tests required to determine the critical temperature. 
// We use a 2D DP table, where dp[k][n] represents the minimum tests needed with k samples and n temperature levels. 
// The approach involves:

// 1) Base Cases – If there’s only one sample, test sequentially; if there are no temperature levels, no tests are needed.
// 2) Iterative Testing – For multiple samples, we strategically test at different temperature levels, considering both possibilities:
//        If the sample reacts, the critical temperature is lower.
//        If it does not react, the critical temperature is higher.
// 3) Minimizing Worst-Case Tests – We take the worst scenario at each step and minimize the number of tests required using dynamic programming.
// 4) Final Result – The solution ensures the critical temperature is found with the least number of tests in all cases.




 public class CriticalTemperature1a {
    public static int minMeasurement(int k , int n){//Given that k = sample of material and n=temperature level of mateial
        int [][] dp = new int[k+1][n+1];//int [][]is take for 2darray which stores elements of k and n and k+1  for rows and n+1  for column is taken to  handle base case
        
        //base case
        for(int i = 0; i<k;i++){
            dp[i][0]=0;//at o0 temperature levelthe material will not be measuired
        }

        for(int j=0; j<n;j++){
            dp[1][j]=j;// if we have one sample than we have to measure or test the temperature level of material 1 to n linearly

        }
        for (int i = 2;i<=k;i++){//loop iterates over no of samples of material
            for(int j = 1;j<=n;j++){//Loop iterates over no of temp levels.Here j=0 has alredy been taken in base case so we staterd form j =1
                dp[i][j] = Integer.MAX_VALUE;// no  measurements have been done yet,so it started with the highest value(infinity) and it gets updated as we find min.no.of measurements 
                for(int x= 1;x<=j;x++){//loop iterates over each possible temperature level to test each one by one
                    int worstCase = Math.max(dp[i-1][x-1],dp[i][j-x]);// to calculate the worst case scenario
                    dp[i][j]=Math.min(dp[i][j],1+worstCase);//update the value in d[i][j] with min of previous result
                }
            }
 }
 return dp[k][n];//return result for sample k and temperature level n 

 
 }

 public static void main (String[] args){
    System.out.println("Minimum measurements (k=1, n=2):"+ minMeasurement(1, 2));
    System.out.println("Minimum measurements (k=1, n=2):"+ minMeasurement(2, 6));
    System.out.println("Minimum measurements (k=1, n=2):"+ minMeasurement(3, 14));
    

 }

        }

/*Output */
/*Minimum measurements (k=1, n=2):0
Minimum measurements (k=1, n=2):3
Minimum measurements (k=1, n=2):4 */