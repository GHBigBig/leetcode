package com.zjg.algorithm;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * @author zjg
 * @create 2019-12-28 11:10
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {1,3,6,7,9,4,10,5,6};
//        int result = lengthOfLIS(nums);
//        int result = lengthOfLIS01(nums);
        int result = lengthOfLIS02(nums);
        System.out.println("result : " + result);
    }

    /**
     * 动态规划：
     *  1，先定义信息的载体：要恰当的定义 dp 数组代表什么，需要几维数组来存储信息。
     *  2，数学归纳法计算：假设 dp[0~i-1] 都已知，那么 dp[i] 怎么可以根据已知的信息推断出来。
     *
     *  就像这个问题，dp[i] 存储 num[0~i] 最长的子序列的长度
     *  如果 dp[0~i-1] 都已知，那么 dp[i] 就为 dp[0~i-1] 中小于 nums[i]的哪个最长的子序列 +1 中最大的
     * @param nums
     * @return
     */
    public static int lengthOfLIS02(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1); //每个元素都可以看作上升序列中的元素，所以最少为 1

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (max<dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 暴力一个一个的算
     * @param nums
     * @return
     */
    public static int lengthOfLIS01(int[] nums) {
        int result = -1;

        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = i+1; j < nums.length-1; j++) {
                tmp = 0;
                for (int k = j; k < nums.length - 1; k++) {
                    if (nums[k]>nums[i]) {
                        tmp ++;
                    }
                }
            }
            if (tmp > result) {
                result = tmp;
            }
        }

        return result;
    }

    /**
     * 鼠目寸光：
     *      考虑的太单一
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length==0) {
            return 0;
        }
        int result = 1;
        int min = Integer.MAX_VALUE;
        for (int i = nums.length-1; i >= 1 ; i--) {
            int j = i-1; //前一个

            if (nums[j]<min && nums[j]<nums[i]) {
                min = nums[j];
                result ++;
            }


        }
        return result;
    }
}
