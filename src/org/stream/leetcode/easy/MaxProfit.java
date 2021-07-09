package org.stream.leetcode.easy;

public class MaxProfit {
    /**
     * 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices){
        int p = 0;
        for (int i = 1; i < prices.length; i++){
            if (prices[i] > prices[i-1]) {
                p += prices[i] - prices[i-1];
            }
        }
        return p;
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
//        76515364
//        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices = new int[]{7,6,4,3,1};
        int count = maxProfit.maxProfit(prices);
        System.out.println(count);
    }
}
