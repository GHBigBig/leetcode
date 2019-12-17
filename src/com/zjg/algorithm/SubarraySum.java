package com.zjg.algorithm;

import java.util.HashMap;

/**
 *560. 和为K的子数组
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zjg
 * @create 2019-12-10 15:38
 */
public class SubarraySum {
    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        int[] nums = {1,2,3,-3,3};
        int i = subarraySum.subarraySum01(nums, 3);
        System.out.println("result : " + i);

    }

    /**
     * 官方：方法四 采用哈希表 [通过]
     * 我的大体理解是在遍历数组即记录…………
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum01(int[] nums, int k) {
        int count=0, sum=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);   //?
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum-k)) {
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * 代码描述待进步，没有考虑到负数
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = i;
            int sum = 0;
            while (tmp<nums.length) {  //又是没有考虑负数的情况，可能是前面的和 > 100 了，但是后面有负数
                sum += nums[tmp++];
                if (sum==k) {
                    count ++;
                    break;
                }
            }
            /*if (sum==k) {
                count ++;
            }*//*else if (sum<k) {    //没有考虑负数的情况
                if (tmp==nums.length) {   //如果加到数组的未端都没有 >k 的那么以后的也不会有了
                    return count;
                }
            }*/

        }
        return count;
    }
}
