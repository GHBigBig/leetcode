package com.zjg.algorithm;

import java.util.HashMap;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zjg
 * @create 2019-12-16 18:44
 */
public class MinWindow {

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = minWindow.minWindow(s, t);

        System.out.println("result : " + result);
    }

    /**
     * 滑动窗口算法（labuladong的解答）：
     *      1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，
     *          把索引闭区间 [left, right] 称为一个「窗口」。
     *      2、我们先不断地增加 right 指针扩大窗口 [left, right]，
     *          直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
     *      3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，
     *          直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，
     *          每次增加 left，我们都要更新一轮结果。
     *      4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int right=0, left=0;    //左右指针
        HashMap<Character, Integer> needs = new HashMap<>();    //记录 t 中的字符及其个数
        //这里是我没有考虑到 windows 只要记录在 needs 中存在的字符即可
        HashMap<Character, Integer> windows = new HashMap<>();  // [left,right] 在 s 上选取的字串中字符及其个数
//        String result = s;  //记录结果
        int modCount = 0;     //用来记录 windows 中有多少有多少个字符符合 t 中的字符
        int start=0, minLen=Integer.MAX_VALUE;  //用来更新符合条件最短的字符串


        for (char c : t.toCharArray()) {
            Integer needValue = needs.getOrDefault(c, 0) + 1;
            needs.put(c, needValue);
        }

        while (right<s.length()) {
            char windowKey = s.charAt(right);

            //我没有考虑到 windows 只需记录在 needs 中的字符即可
            if (needs.containsKey(windowKey)) {
                Integer windowValue = windows.getOrDefault(windowKey, 0) + 1;
                windows.put(windowKey, windowValue);

                //记录 windows 中符合要求的
                if (windows.get(windowKey).equals(needs.get(windowKey))) {
                    modCount ++;
                }
            }

            right ++;


//            while (windows 包含 字符串t 所有的字符 ) {  实现代码
//            modCount 和  if (needs.containsKey(windowKey)) {}
            while (modCount == needs.size()) {
//                更新 result 的结果，
                if (right-left<minLen) {
                    start = left;
                    minLen = right-left;
                }
//                result = result   不用了上面的代码可以表示

                windowKey = s.charAt(left);
                if (needs.containsKey(windowKey)) {
                    int windowValue = windows.get(windowKey) - 1;
                    windows.put(windowKey, windowValue);
                    if (windows.get(windowKey) < needs.get(windowKey)) {
                        modCount --;
                    }

                }

//                windows.remove(left);
                left++;
//                判断 windows 是否包含 t 中的所有字符
//                        不符合就退出 while
//                        符合就继续
            }

        }

        return minLen==Integer.MAX_VALUE ? "" : s.substring(start, start+minLen);
    }
}
