package com.zjg.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 406. 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *      差的还很远
*
 * @author zjg
 * @create 2019-12-17 21:29
 */
public class ReconstructQueue {

    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] ints = reconstructQueue(people);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }

    }

    /**
     *  解题思路：先排序再插入
     *      * 1.排序规则：按照先H高度降序，K个数升序排序
     *      * 2.遍历排序后的数组，根据K插入到K的位置上
     *      *
     *      * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     *      *
     * @param people
     * @return
     */
    public static int[][] reconstructQueue(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

        Arrays.sort(people, (arr1, arr2) -> arr1[0]==arr2[0] ? arr1[1]-arr2[1] : arr2[0]-arr1[0]);

        /*for (int[] person : people) {
            System.out.println(Arrays.toString(person));
        }
        System.out.println();*/

        LinkedList<int[]> list = new LinkedList<>();

        for (int[] i : people) {
            list.add(i[1], i);
        }
//        list.forEach((arr) -> System.out.println(Arrays.toString(arr)));

        return list.toArray(new int[0][0]);
    }
}
