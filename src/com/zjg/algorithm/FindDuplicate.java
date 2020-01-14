package com.zjg.algorithm;

import java.util.HashSet;

/**
 * 287. 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-31 15:19
 */
public class FindDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int result = findDuplicate(nums);
        System.out.println("result : " + result);
    }

    /**
     * 二分查找
     * @param nums
     * @return
     */
    public static int findDuplicate01(int[] nums) {
        HashSet<Integer> set = new HashSet<>();



        return Integer.MIN_VALUE;
    }

    /**
     * 暴力算法
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }else {
                set.add(num);
            }
        }
        return Integer.MIN_VALUE;
    }
}
