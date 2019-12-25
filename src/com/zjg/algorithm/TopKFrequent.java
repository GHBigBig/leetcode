package com.zjg.algorithm;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-18 14:55
 */
public class TopKFrequent {

    public static void main(String[] args) {
        /*int[] nums = new int[10];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println("排序前：" + Arrays.toString(nums));
        topKFrequent(nums, 0);
        System.out.println("排序后：" + Arrays.toString(nums));*/

        int[] nums = {1,1,1,2,2,3};
        List<Integer> result = topKFrequent02(nums, 1);
        System.out.println("result : " + result);

    }

    public static List<Integer> topKFrequent02(int[] nums, int k) {
        List<Knums> list = new LinkedList<>();
        int value = 0;
        for (int num : nums) {
            Knums knums = new Knums(num);
            if (list.contains(knums)) {
                Knums exist = list.get(list.indexOf(knums));
                exist.k = exist.k+1;
            }else {
                list.add(knums);
            };

        }
        Collections.sort(list);

        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(list.get(i).num);
        }
        return result;
    }

    static class Knums implements Comparable<Knums>{
        Integer num;
        Integer k;

        public Knums(Integer num) {
            this.num = num;
            this.k = 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Knums)) return false;

            Knums knums = (Knums) o;

            return num != null ? num.equals(knums.num) : knums.num == null;
        }

        @Override
        public int hashCode() {
            return num != null ? num.hashCode() : 0;
        }

        @Override
        public int compareTo(Knums o) {
            return -(this.k - o.k);
        }
    }

    /**
     * 错误的方法
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent01(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();

        for (Integer num : nums) {
            if (list.contains(num)) {
                list.remove(num);
            }

        }

        return null;
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        heapSort(nums);
        return null;
    }

    /**
     * 堆排序：
     * 1） 先将无序序列构建成一个堆，根据升序降序需求构建大小顶堆；
     * 2） 将堆顶元素与末尾元素交换，将最大元素 “沉” 到数组末尾；
     * 3） 重新调整结构，使其满足大顶推或小顶堆定义，然后继续交换堆顶元素
     * 与当前末尾元素，反复执行调整 + 交换步骤，直到整个序列有序。
     *
     * @param nums
     */
    public static void heapSort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            generateHeap(nums, i, nums.length);
        }

        int tmp;
        for (int i = nums.length-1 ; i > 0; i--) {
            tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;
            generateHeap(nums, 0, i);
        }
    }

    public static void generateHeap(int[] nums, int noLeaf, int len) {
        int tmp = nums[noLeaf]; //最后一个非叶子结点的值
        for (int i = noLeaf * 2 + 1; i < len; i = i * 2 + 1) {    //选取较大的子结点
            if (i + 1 < len && nums[i] < nums[i + 1]) {     //说明左子节点小于右子结点
                i++;    //i 指向右子结点，i 就指向了子结点中最大的结点
            }
            if (nums[i] > tmp) {  //真的有子结点大于父结点
                nums[noLeaf] = nums[i];
                noLeaf = i; //调整交换的哪个结点
            } else {
                break;
            }
        }
        nums[noLeaf] = tmp;
    }
}
