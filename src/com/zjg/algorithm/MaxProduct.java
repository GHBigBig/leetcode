package com.zjg.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 152. 乘积最大子序列
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author zjg
 * @create 2020-01-14 22:02
 */
public class MaxProduct {
    public static void main(String[] args) {
        int[] nums = {-2,3,-4};
        int result = maxProduct(nums);
        System.out.println("result : " + result);
    }
    /**
     * dp 数据代表想对应的 nums 中最大连续子序列乘积的值
     * dp[n]为： max(dp[n-1]*nums[n], nums[n]);
     * 考虑的不周全，dp[n-1] 可能是负的没有 nums[n-1] 的值大但是如果dp[n]也是个负值时，dp[n-1]应该为负值
     * dp[n] 的定义有问题：
     * 现在我使用一个二维数据来记录dp，其中 dp[0] 表示正数的最大值，dp[1]表示负数的最小值
     * 那么dp[0 和 1][n] 要考虑正负的所有情况
     *      nums[n] 为正数
     *          dp[0] ==> max(dp[0][n-1]*nums,nums[n])
     *          dp[1] ==> min(dp[1][n-1]*nums,nums[n])
     *      nums[n] 为负数时
     *          dp[0] ==> max(dp[1][n-1]*nums,nums[n])
     *          dp[1] ==> min(dp[0][n-1]*nums,nums[n])
     *
     * dp[0][0] 和 dp[0][1] 设为 1，-1不会影响正数和负数的符号
     *
     * -------------------------考虑的不太周全---------------------------------------
     * 核心的一句话，负数不能丢，有可能下个数为负数
     * 正数的方法没有问题；
     * 如果是负数的话，那么与正数相乘越乘越小
     * 那么就是说负数需要保存，
     *
     * 负数会使
     *  负数变为正数
     *  正数变负数
     * 正数会使
     *  正数为正数
     *  负数为负数
     *
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        Integer[][] dp = new Integer[2][nums.length+1];
        dp[0][0] = null;
        dp[1][0] = null;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>=0) {
                Integer num1;
                if (dp[0][i] != null) {
                    num1 = dp[0][i]*nums[i];
                }else {
                    num1 = nums[i];
                }
                dp[0][i+1] = Math.max(num1, nums[i]);

                Integer num2;
                if (dp[1][i] != null) {
                    dp[1][i+1] = Math.min(dp[1][i]*nums[i], nums[i]);
                }else {
                    dp[1][i+1] = null;
                }

            } else {    //nums[i] 为负数
                Integer num1;
                if (dp[0][i]!=null) {   //看看存储的正数要得到负数
                    dp[1][i+1] = Math.min(dp[0][i] * nums[i], nums[i]);
                }else { //如果 dp[0][i]为null，那么负数的最小就是 nums[i]
                    dp[1][i+1] = nums[i];
                }

                Integer num2;
                if (dp[1][i]!=null) {
                    dp[0][i+1] = Math.max(dp[1][i]*nums[i], nums[i]);
                }else {
                    dp[0][i+1] = null;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j]!=null && max<dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }

        for (Integer[] integers : dp) {
            System.out.println(Arrays.toString(integers));
        }
        return max;
    }
}
