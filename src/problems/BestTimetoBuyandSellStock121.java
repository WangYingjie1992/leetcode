package problems;


/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * @User wangyj
 * @Date 2019/4/8  9:26 PM
 */
public class BestTimetoBuyandSellStock121 {

    public static void main(String[] args) {
        int[] pices = new int[]{2,1,2,1,0,1,2};
        System.out.println(maxProfit(pices));
    }

    //

    /**
     * dynamic programming
     * 动态方程 maxProfit=max(prices[i]-opt[i-1])
     */
    public static int dp(int[] prices) {

        //设opt[i]为从0到i中的最小值
        int[] opt = new int[prices.length+1];
        //处理边界值
        if (prices.length<2) return 0;
        if (prices.length==2) return prices[1]-prices[0]>0?prices[1]-prices[0]:0;
        opt[1]=Math.min(prices[0],prices[1]);
        int maxProfit = prices[1]-opt[1];
        for (int i=2 ;i<prices.length;i++){
            //记录最大值
            int maxTmp=prices[i]-opt[i-1];
            if (maxTmp>maxProfit) maxProfit=maxTmp;
            //记录最小值
            opt[i]=prices[i]<opt[i-1]?prices[i]:opt[i-1];
        }
        return maxProfit;
    }

    /**
     * 由于本地的opt[i]并不需要记录每个i对应的最小值，
     * 一趟循环下来只需要记录当前数字前面的数字中的最小值即可
     * 故只需一个变量记录即可
     */
    public static int maxProfit(int[] prices) {
        if (prices.length<2) return 0;
        if (prices.length==2) return prices[1]-prices[0]>0?prices[1]-prices[0]:0;
        int opt =prices[0];
        opt=Math.min(prices[0],prices[1]);
        int maxProfit = prices[1]-opt;
        for (int i=2 ;i<prices.length;i++){
            if (prices[i]>opt){
                maxProfit=prices[i]-opt>maxProfit?prices[i]-opt:maxProfit;
            }
            if (opt>prices[i]) opt =prices[i];
        }
        return maxProfit;
    }
}
