package com.zjg.algorithm;

import java.util.Arrays;

/**
 * 494. 目标和
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-6 17:28
 */
public class FindTargetSumWays {
    int count = 0;

    public static void main(String[] args) {
        FindTargetSumWays findTargetSumWays = new FindTargetSumWays();
        int[] nums = {2, 1, 1, 2};
        int result = findTargetSumWays.findTargetSumWays03(nums, 4);
        System.out.println("result : " + result);
    }


    /**
     * 官方   动态规划的基础上再优化下空间复杂度
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays04(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

    /**
     * 再动态规划的基础上再优化下空间复杂度
     * dp[][] 这个二维表只有最后一行是有效的信息
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays03(int[] nums, int S) {
        final int MAX_SUM = 1000;
        int[] dp = new int[MAX_SUM+MAX_SUM+1];

        dp[0+MAX_SUM] = 1;

        for (int i = 0; i < nums.length; i++) {
            int[] next = Arrays.copyOf(dp, dp.length);
            Arrays.fill(dp, 0);
            for (int sum=-MAX_SUM; sum<=MAX_SUM; sum++) {
                if (next[sum+MAX_SUM] > 0) {
                    dp[sum+nums[i] + MAX_SUM] += next[sum + MAX_SUM];
                    dp[sum-nums[i] + MAX_SUM] += next[sum + MAX_SUM];
                }
            }
        }

        return S>MAX_SUM ? 0 : dp[S+MAX_SUM];
    }

    /**
     * 动态规划
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays02(int[] nums, int S) {
        final int MAX_SUM = 1000;
        int[][] dp = new int[nums.length+1][MAX_SUM+MAX_SUM+1]; //还有 0，0 代表一个元素也没有

        //哨兵？
        dp[0][0+MAX_SUM] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int sum=-MAX_SUM; sum<=MAX_SUM; sum++) { // dp[i][j] 不仅仅是由 i-1 中的一个值算出来的
                //但是 i-1 这一行的元素要是有意义的
                if (dp[i][sum+MAX_SUM]>0) {
                    // dp[i+1] 和 nums[i] 是同级的
                    dp[i+1][sum+nums[i] + MAX_SUM] += dp[i][sum + MAX_SUM];
                    dp[i+1][sum-nums[i] + MAX_SUM] += dp[i][sum + MAX_SUM];
                }

            }
        }

        return S>MAX_SUM ? 0 : dp[nums.length][S+MAX_SUM];
    }

    /**
     * 	       -6      -5      -4      -3      -2      -1       0       1       2       3       4       5       6
     *
     *
     * 0		0       0       0       0       0       0       1       0       0       0       0       0       0
     *
     * 1{2}   	0       0       0       0     0+1       0       0       0     0+1       0       0       0       0
     *
     * 2{1}     0       0       0     0+1       0     0+1       0     0+1       0     0+1       0       0       0
     *
     * 3{1} 	0       0     0+1       0   0+1+1       0   0+1+1       0   0+1+1       0     0+1       0       0
     *
     * 4{2}   0+1       0     0+2       0   0+1+2       0   0+2+2       0   0+2+1       0     0+2       0     0+1
     *
     * 动态规划：
     *      我们用 dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。考虑第 i 个数 nums[i]，
     *      它可以被添加 + 或 -，因此状态转移方程如下：
     *          dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
     *      也可以写成递推的形式：
     *          dp[i][j + nums[i]] += dp[i - 1][j]
     *          dp[i][j - nums[i]] += dp[i - 1][j] 先看这个
     *          递推公式这里的确让我纠结了很久但是：一画图就明白了：
     *              当 i=1 或 i=2 ... 的时候 j 是可以不断变化的，i 个元素的和 j 的个数是由
     *              i-1 个元素的和为 j-nums[i] 的个数 + i-1 个元素的和为 j+nums[i] 的个数算出来的
     *              那么如果我们把 i-1 的和看为 j 的话那么，i 的和该怎么算出来？？？
     *              那么此时的 j 有可能是 j+nums[i]再-nums[i] 也有可能是 j-nums[i]再+nums[i]
     *
     *              i-1 对 i 无非就两种情况 +nums[i] 和 -nums[i]，把每一个 i-1 的 j 都遍历不就得出 i 的情况了吗
     *
     *
     *      由于数组中所有数的和不超过 10，那么 j 的最小值可以达到 -10。在很多语言中，
     *      是不允许数组的下标为负数的，因此我们需要给 dp[i][j] 的第二维预先增加 10，即：
     *          dp[i][j + nums[i] + 10] += dp[i - 1][j + 10]
     *          dp[i][j - nums[i] + 10] += dp[i - 1][j + 10]
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays01(int[] nums, int S) {
        int[][] dp = new int[nums.length][13];
        dp[0][nums[0] + 6] = 1;
        dp[0][-nums[0] + 6] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -6; sum <= 6; sum++) {
                if (dp[i - 1][sum + 6] > 0) {
                    System.out.println("dp["+i+"][sum + nums["+i+"] + 6] += dp["+ (i - 1) +"][sum + 6] -> " +
                            dp[i][sum + nums[i] + 6] + " += " + dp[i - 1][sum + 6]);
                    dp[i][sum + nums[i] + 6] += dp[i - 1][sum + 6];
                    System.out.println("dp["+i+"][sum - nums["+i+"] + 6] += dp["+(i - 1)+"][sum + 6]] -> " +
                            dp[i][sum - nums[i] + 6] + " += " + dp[i - 1][sum + 6]);
                    dp[i][sum - nums[i] + 6] += dp[i - 1][sum + 6];
                }
            }
        }

        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.printf("%8d", anInt);
            }
            System.out.println();
        }
        System.out.println("nums.length-1 : " + (nums.length - 1));
        System.out.println("S : " + S);
        return dp[nums.length-1][S+6];
    }

    public int findTargetSumWays(int[] nums, int S) {
        count(S, nums, 0, 0, 0);
        return count;
    }

    /**
     * 这里是使用了回溯方法，一个一个元素，一个一个符号的尝试
     *
     * @param sum
     * @param nums
     * @param index
     * @param flag  0 表示 -， 1 表示 +
     * @return
     */
    public void count(int sum, int[] nums, int index, int flag, int result) {
        //终止条件，同时也是判断条件
        if (index == nums.length) {
            if (result == sum) {
                count++;
            }
            return;
        }
        count(sum, nums, index + 1, flag, result + nums[index]);
        count(sum, nums, index + 1, flag + 1, result - nums[index]);
    }


}
