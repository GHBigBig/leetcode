package com.zjg.algorithm;

import java.util.ArrayList;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-31 21:12
 */
public class NumSquares {

    public static void main(String[] args) {
        int result = numSquares(15);
        System.out.println("result : " + result);
    }


    /**
     * dp 表示每个元素有的完全平方数组成的个数最少个数，下标就是值
     * 每一步不能鼠目寸光，比值少的平方数都看看
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int pow = (int)Math.pow(i, 2);
            if (pow >n) {
                break;
            }
            list.add(pow);
        }

        int[] nums = new int[list.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }


        return internal01(nums, n);
    }


    public static int internal01(int[] nums, int n) {
        int dp[] = new int[n+1];
        int min;
        dp[0] = 0;  //设不设没有影响，哨兵

        for (int i = 1; i < dp.length; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < nums.length; j++) {
                int tmp = i-nums[j];
                if (tmp>=0) {
                    if (dp[tmp]!=Integer.MAX_VALUE) {
                        min = Math.min(min, dp[tmp]+1);
                    }
                }
            }
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 我的思路没问题，代码写不出来
     * @param nums
     * @param n
     * @return
     */
    public static int internal(int[] nums, int n) {
        if (n<0) {
            return Integer.MAX_VALUE;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        int min = Integer.MAX_VALUE;
        for (int i = 2; i < dp.length; i++) {
            System.out.println("------------------------");
            for (int j = 0; j < nums.length; j++) {
                System.out.println("==========================");
                int tmp = i - nums[j];
                if (tmp<=0) {
                    tmp = Integer.MAX_VALUE;
                } else {
                    tmp = dp[tmp];
                }
                min = Math.min(min, tmp);
            }
            dp[i] = min + 1;
        }
        return dp[dp.length-1];
    }
}
