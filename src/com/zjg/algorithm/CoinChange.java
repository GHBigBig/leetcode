package com.zjg.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 322. 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-26 22:34
 */
public class CoinChange {

    public static void main(String[] args) {

        /*int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println("排序前：" + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));


        int[] coins = {186,419,83,408};
        int amount = 6249;
        int result = coinChange(coins, amount);
        System.out.println("result : " + result);*/
        int[] coins = {186,419,83,408};
        int amount = 6249;

        int result = coinChange01(coins, amount);
        System.out.println("result : " + result);
    }

    /**
     * 避免了鼠目寸光的问题：
     *  并不是把面值最大的作为最优解而是选取所有的面值比较比较再选出最有解
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange01(int[] coins, int amount) {
        int[] f = new int[amount+1];
        int cost;
        f[0] = 0;   //哨兵
        for (int i = 1; i <= amount; i++) {
            cost = Integer.MAX_VALUE;

            for (int j = 0; j < coins.length; j++) {
                if (i-coins[j] >= 0) {  //这个硬币的面值大于 i（总金额）
                    if (f[i-coins[j]] != Integer.MAX_VALUE) {
                        cost = Math.min(cost, f[i-coins[j]] + 1);
                    }
                }
            }
            f[i] = cost;
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }

    /**
     * 贪心问题存在一个问题：
     *  鼠目寸光： 这一步的最优解不一定是全局的最优解
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        heapSort(coins);
        int dp[][] = new int[coins.length + 1][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            int res = 1;
            for (int j = coins[i]; j <= amount; j += coins[i]) {
                dp[i + 1][j] = res++;
            }
        }
        /*for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.printf("%4d", anInt);
            }
            System.out.println();
        }*/
        int result = 0;
        for (int z = dp.length - 1; z >= 1; z--) {
            for (int i = z; i >= 1; i--) {
                for (int j = dp[i].length - 1; j >= 1; j--) {
                    if (dp[i][j] != 0)  {
                        if (amount>= j) {
                            while (amount >= j) {
                                System.out.println("amount : " + amount + ", dp[" + i + "]" + "[" + j + "] : " + dp[i][j]);
                                result += dp[i][j];
                                amount -= j;
                            }
                            break;
                        }
                    }
                }
            }
        }

        if (amount==0) {
            return result;
        }else {
            System.out.println(amount);
            return -1;
        }
    }

    public static void heapSort(int[] coins) {
        //先调整为大顶堆
        for (int i = coins.length/2-1; i >= 0; i--) {
            generateHeap(coins, i, coins.length);
        }

        int tmp;
        //将最大的值沉到数组未端，最大的值就是堆顶元素
        for (int i = coins.length - 1; i > 0 ; i--) {
            tmp = coins[0];
            coins[0] = coins[i];
            coins[i] = tmp;

            generateHeap(coins, 0, i);
        }
    }

    /**
     * 调整数组使其称为大顶堆
     *  父结点大于等于子结点的值
     * @param coins 数组
     * @param i 要调整的非叶子结点
     * @param len 要调整的长度
     */
    public static void generateHeap(int[] coins, int i, int len) {
        int tmp = coins[i];
        //要看子结点
        for (int j = 2*i+1; j < len; j=2*j+1) {
            //比较左右子结点哪个值大
            if (j+1<len && coins[j]<coins[j+1]) {
                j ++;
            }
            //交换
            if (coins[j]>tmp) {
                coins[i] = coins[j];
                i = j;
            }else {
                break;
            }
        }
        coins[i] = tmp;
    }

}
