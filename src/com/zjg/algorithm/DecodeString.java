package com.zjg.algorithm;

import java.util.Stack;

/**
 * 394. 字符串解码
 *
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zjg
 * @create 2019-12-18 12:39
 */
public class DecodeString {

    public static void main(String[] args) {
        String s = "100[leetcode]";
        String result = decodeString(s);


        System.out.println("result : " + result);
    }

    public static String decodeString(String s) {
        Stack<Character> charStack = new Stack<>(); //存放 s 中的非数字
        Stack<Character> tmpStack = new Stack<>();  //临时存放从 s 中取出来的符号
        Stack<Integer> numStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        StringBuilder strNum = new StringBuilder();
        strNum.append(s, 0, s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((""+c).matches("\\d+")) {   //数字，是否是一个完整的数字
                strNum.append(c);

                //最后一个字符数字结束，后面不是数字时数字结束
                if (i+1==s.length() || !(""+s.charAt(i+1)).matches("\\d+")) {
                    numStack.push(Integer.valueOf(strNum.toString()));
                    strNum.delete(0, strNum.length());
                }
            }else {
                if (!"]".equals(""+c)) {    //不是 ]
                    charStack.push(c);
                }else { //需要运算
                    // charStack pop 弹出一个 [ 就停止
                    while (!charStack.peek().equals('[')) {
                        tmpStack.push(charStack.pop());
                    }
                    charStack.pop();

                    //取出 tmpStack 中的数字循环 numStack 栈顶次
                    StringBuilder sb = new StringBuilder();
                    while (!tmpStack.isEmpty()) {
                        sb.append(tmpStack.pop());
                    }
                    if (numStack.isEmpty()) {   //重复了一次
                        result.append(sb);
                    }else { //重复了 k 次
                        Integer num = numStack.pop();
                        for (int j = 0; j < num; j++) {
                            result.append(sb);
                        }
                    }

                    for (int j = 0; j < result.length(); j++) {
                        charStack.push(result.charAt(j));
                    }
                    result.delete(0, result.length());
                }
            }
        }

        while (!charStack.isEmpty()) {
            tmpStack.push(charStack.pop());
        }

        while (!tmpStack.isEmpty()) {
            result.append(tmpStack.pop());
        }

        return result.toString();
    }
}
