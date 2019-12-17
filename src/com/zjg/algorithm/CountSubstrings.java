package com.zjg.algorithm;

/**
 * @author zjg
 * @create 2019-12-07 17:47
 *
 * 647. 回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSubstrings {

    public static void main(String[] args) {
        CountSubstrings countSubstrings = new CountSubstrings();

        String s = "abaasfdasf";

        long start = System.currentTimeMillis();
        int result = countSubstrings.countSubStrings01(s);
        System.out.println("耗时：" + (start - System.currentTimeMillis()));
        System.out.println();

        System.out.println("result : " + result);
    }

    /**
     * 遍历字符串得到所有的都为同一字符的子串
     * 计算每个回子串有多少个回文字串
     * @param s
     * @return
     * 错误
     */
    public int countSubstrings(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); ) {
            int j = 1;
            while (i+j<s.length() && s.charAt(i) == s.charAt(i + j)) {    //看看后面有多少字符是相同的
                j ++;
            }
            String substring = s.substring(i, i + j);
            result += count(substring.length());
            i += j;
        }

        return result;
    }

    /**
     * 求 len 的长度相同字符串有多少串回文子串
     * @param len
     * @return
     * 错误
     */
    public int count(int len) {
        if (len == 1) {
            return 1;
        }else {
            return len*count(len-1);
        }
    }

    int num = 0;

    /**
     * 看的大答案
     *      从字符串的某一位开始，尝试着去扩展子字符串。
     *      就是从这个数左右两面的字符都应该相等
     *      分回文字串奇偶两个情况
     * @param s
     * @return
     */
    public int countSubStrings01(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i); //奇
            count(s, i, i+1); //偶
        }
        return num;
    }

    /**
     * 从每个字符扩展，但是扩展必须符合回文的标准
     * @param s 要比较的字符串
     * @param start 中心数的左面
     * @param end 中心数的右面
     */
    public void count(String s, int start, int end) {
        while ((start >= 0 && end <= s.length() - 1) && s.charAt(start) == s.charAt(end)) {
            num ++;
            start --;
            end ++;
        }
    }


}
