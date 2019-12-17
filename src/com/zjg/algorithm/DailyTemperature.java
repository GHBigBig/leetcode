package com.zjg.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zjg
 * @create 2019-12-07 15:51
 *
 * 739，每日温度：
 *  根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperature {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        DailyTemperature dailyTemperature = new DailyTemperature();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperature.dailyTemperatures01(temperatures);

        System.out.println("temperatures : " + Arrays.toString(temperatures));
        System.out.println("result : " + Arrays.toString(result));


    }

    /**
     * 每天的温度都要和后面天的温度比较
     * 如果后面有比当前温度高的那么看看需要等多少天  先进先出 队列
     * 如果没有就标记为 0
     * @param T 一段天数的温度
     * @return
     *                          时间超出！！！
     */
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i : T) {
            queue.addLast(i);
        }

        while (!queue.isEmpty()) {
            Integer item = queue.remove();
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i) > item) {
                    result[T.length-queue.size()-1] = i+1;
                    break;
                }
                //都没有比当前温度高的
                if (i==queue.size()-1) {
                    result[T.length-queue.size()-1] = 0;
                }
            }
        }
        return result;
    }

    /**
     * 时间超出看来是思路没有问题，需要提高运行效率
     * 这个是官方的答案
     * @param T
     * @return
     */
    public int[] dailyTemperatures01(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i =T.length-1 ; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return ans;
    }

}
