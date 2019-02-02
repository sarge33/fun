class KnapSack {
    public int getBest( int [] values, int[] weights, int capacity) {
        print(values);
        print(weights);
        int n = weights.length;
        Integer dp[][] = new Integer[n][capacity+1]; // capacity + 1, because the weight array [0, 1, 2,..., capacity]
        int profitByTopDown =  helperTopDown(weights, values, capacity, 0, dp);
        int profitByBottomUp = helperBottomUp(weights, values, capacity);
//        print(dp);
        System.out.println("profitByTopDown: " + profitByTopDown);
        System.out.println("profitByBottomUp: " + profitByBottomUp);
        return profitByBottomUp;
    }

    int helperTopDown(int[] weights, int[] values, int capacity, int i, Integer dp[][] ) {
        int n = weights.length;
        if(i >= n || capacity <= 0 || i < 0) return 0;
        if(dp[i][capacity] != null) {
            return dp[i][capacity];
        }
        // include item
        int p1 = 0;
        if(capacity - weights[i] >= 0) {
            p1 = values[i] + helperTopDown(weights, values, capacity- weights[i], i+1, dp);
        }
        // exclude item
        int p2 = helperTopDown(weights, values, capacity, i+1, dp);

        dp[i][capacity] = Math.max(p1, p2);
        return dp[i][capacity];
    }

    int helperBottomUp(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        
        // initialized item 0 at row 1
        int[][] dp = new int[n+1][capacity+1];
        
        /*
        // Or we can initialized item 0 at row 0
        int[][] dp = new int[n][capacity+1];
        for(int c=0; c <= capacity; c++) {
          if(weights[0] <= c)
            dp[0][c] = profits[0];
        }
        */
        for(int i = 1; i < n+1; ++i) {
            int weightI = weights[i-1];
            int valueI = values[i-1];
            for(int j = 0; j<capacity+1; ++j) {
                if(i == 0 || j == 0)
                    dp[i][j] = 0; // initialization
                else if(j - weightI >=0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weightI] + valueI);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    void print(int[] a) {
        for(int n: a) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
    void print(Integer[][] p) {
        for(Integer[] a: p) {
            for(Integer n: a) {
                System.out.print((n==null ? 0 : n) + " ");
            }
            System.out.println();
        }
    }

}

public class Test {
    public static void main(String [] args) {
        KnapSack ks = new KnapSack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.getBest(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.getBest(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);

    }
}
