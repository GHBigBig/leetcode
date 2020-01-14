package com.zjg.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2020-01-08 13:05
 */
public class FindKthLargest {
    public static void main(String[] args) {
        int[] nums = new int[1];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        System.out.println("排序前：" + Arrays.toString(nums));
        quickSort(nums, 0, nums.length-1);
        System.out.println("排序后：" + Arrays.toString(nums));

    }

    public static int findKthLargest(int[] nums, int k) {
        heapSort(nums);
        return nums[nums.length - k];
    }


    private static void quickSort(int[] nums, int left, int right) {
        int pivot = nums[(left + right) / 2];
        int l = left, r = right;
        int tmp = 0;
        while (l < r) {
            while (nums[l] < pivot) {
                l++;
            }
            while (nums[r] > pivot) {
                r--;
            }
            //最极端的情况就是相等，让其不进入后面的判断即可

            if (l < r) {
                tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;

                if (nums[l] == pivot) {
                    r--;
                }
                if (nums[r] == pivot) {
                    l++;
                }
            }
        }

        if (l==r) {
            l++;
            r--;
        }

        //左
        if (left<r) {
            quickSort(nums, left, r);
        }
        //右
        if (right>l) {
            quickSort(nums, l, right);
        }



    }

    /**
     * 堆排序
     *
     * @param nums
     */
    private static void heapSort(int[] nums) {
        int tmp = 0;
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;

            adjustHeap(nums, 0, i);
        }

    }

    /**
     * @param nums
     * @param noLeaf 非叶子节点
     * @param len    有效长度
     */
    private static void adjustHeap(int[] nums, int noLeaf, int len) {
        int value = nums[noLeaf];
        for (int i = noLeaf * 2 + 1; i < len; i = i * 2 + 1) {
            if (i + 1 < len && nums[i] < nums[i + 1]) {
                i = i + 1;
            }
            if (nums[i] > value) {
                nums[noLeaf] = nums[i];
                //有可能会打乱其孙子节点的大顶堆定义
                noLeaf = i;
            } else {
                break;
            }
        }
        nums[noLeaf] = value;
    }
}
