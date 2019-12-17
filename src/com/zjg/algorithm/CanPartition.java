package com.zjg.algorithm;

/**
 * 416. 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-17 11:50
 */
public class CanPartition {

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        int[] nums = {1, 2, 3, 5};
        boolean b = canPartition.canPartition(nums);

        System.out.println("result : " + b);
    }

    /**
     * 暴力
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum%2 == 1) {
            return false;
        }
        sum /= 2;

        int[][] dp = new int[nums.length+1][sum+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i-1][j] > -1) {
                    dp[i][j] = dp[i-1][j];
                    if (j+nums[i-1] < dp[i].length) {
                        dp[i][j+nums[i-1]] = dp[i-1][j] + nums[i-1];
                    }
                }
            }
        }

        /*for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.printf("%4d", anInt);
            }
            System.out.println();
        }*/

        return dp[nums.length][sum]<0 ? false : true;

    }
}
