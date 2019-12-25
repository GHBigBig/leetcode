package com.zjg.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 338. 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zjg
 * @create 2019-12-20 15:06
 */
public class CountBits {
    public static void main(String[] args) {
        int[] result = countBits(2);
        int[] result1 = countBits01(2);
        System.out.println("result : " + Arrays.toString(result1));
        for (int i = 0; i < result.length; i++) {
            if (result[i] != result1[i]) {
                System.out.println("错误在第几个数: " + i + " = " + result[i] + result1[i]);
                break;
            }
        }
    }

    /**
     * 暴力算法  -  超时
     * @param num
     * @return
     */
    public static int[] countBits(int num) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i <= num; i++) {
            String binary = Integer.toBinaryString(i);
//            System.out.println(i + "\t" + binary);
            int tmp = 0;
            for (int j = 0; j < binary.length(); j++) {
                if ("1".equals("" + binary.charAt(j))) {
                    tmp ++;
                }
            }
            list.add(tmp);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * 动态规划
     * @param num
     * @return
     */
    public static int[] countBits01(int num) {
        int[] nums = new int[num+1];

        if (num<2) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            return nums;
        }

        nums[0] = 0;
        nums[1] = 1;

        int prePow = 1;
        int pow = 2;
        for (int i = 2; i < nums.length; i++) {
            if (i==pow) {
                nums[i] = 1;
                prePow = pow;
                pow *= 2;
            }else {
                nums[i] = nums[prePow] + nums[i-prePow];
            }
        }

        return nums;
    }
}
