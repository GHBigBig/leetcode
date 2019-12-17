package com.zjg.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zjg
 * @create 2019-12-15 13:41
 */
public class FindAnagrams {
    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();

        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> list = findAnagrams.findAnagrams01(s, p);
        System.out.println(list);

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        for (int i = 0; i < sChars.length-pChars.length+1; i++) {

            ArrayList<Character> tmpList = new ArrayList<Character>();
            for (char c : pChars) {
                tmpList.add(c);
            }
            //看看 sChars 中的下一个字符是否在 pChars 中就是在 tmpList 中，如果在
            //那么应从 tmpList 中移除这个字符， 在比较 sChar 的下下个字符
            noExist:
            for (int j = 0; j < pChars.length; j++) {   //要进行 pChar.length-1 次
                for (int k = 0; k < tmpList.size(); k++) {
                    if (tmpList.get(k).equals(sChars[i+j])) {
                        tmpList.remove(k);
                        break;
                    }
                    if (k==tmpList.size()-1) {  //不合符标准的
                        break noExist;
                    }
                }
                if (j==pChars.length-1) {
                    list.add(i);
                }
            }
            //如果可以走到这里，那么说明 sChar 中的哪个字符不在 tmpList 中，

        }

        return list;
    }

    /**
     * 尝试使用双指针算法解决
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams01(String s, String p) {
        List<Integer> result = new ArrayList<>();

        HashMap<Character, Integer> windows = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        for (char c : p.toCharArray()) {
            Integer needValue = needs.getOrDefault(c, 0) + 1;
            needs.put(c, needValue);
        }
        int left=0, right=0;
        int modCount = 0;

        while (right < s.length()) {
            Character windowKey = s.charAt(right);
            if (needs.containsKey(windowKey)) {
                int windowValue = windows.getOrDefault(windowKey, 0) + 1;
                windows.put(windowKey, windowValue);

                if (windowValue == needs.get(windowKey)) {
                    modCount ++;
                }
            }
            right ++;

            while (modCount == p.length()) {
                // s 中所有是 p 的字母异位词的子串，字母异位词指字母相同，但排列不同的字符串。
                if (right-left == p.length()) {
                    result.add(left);
                }
                //windows 的 left 向前移动一个，缩小窗口
                windowKey = s.charAt(left);
                if (needs.containsKey(windowKey)) {
                    int windowValue = windows.get(windowKey) - 1;
                    windows.put(windowKey, windowValue);
                    if (windowValue < needs.get(windowKey)) {
                        modCount --;
                    }
                }

                left ++;
            }
        }

        return result;
    }
}
