package com.zjg.algorithm;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 *
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2020-01-01 13:42
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] result = productExceptSelf01(nums);
        System.out.println("result : " + Arrays.toString(result));
    }



    /**
     * 乘积 = 当前数左边的乘积 * 当前数右边的乘积
     * @param nums
     * @return
     */
    public static int[] productExceptSelf01(int[] nums) {
        int[] result = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) { //左乘积
            result[i] = k;
            k *= nums[i];
        }

        k = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            result[i] *= k; //左乘积*右乘积
            k *= nums[i];
        }

        return result;
    }

    /**
     * 暴力算法
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i==j) {
                    continue;
                }
                res *= nums[j];
            }
            result[i] = res;
        }
        return result;
    }

}
