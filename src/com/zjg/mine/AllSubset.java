package com.zjg.mine;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 打印一个集合的真子集情况
 * @author zjg
 * @create 2020-01-06 11:32
 */
public class AllSubset {
    static  ArrayList<String> resultList = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<Character> subset = new ArrayList<>();
        subset.add('A');
        subset.add('B');
        subset.add('C');
        subset.add('D');
        subset.add('E');
        subset.add('F');
        subset.add('G');


        /*
            subList(包含起始索引，不包含借宿索引)
         */
        /*List<Character> list = subset.subList(2, 2);
        System.out.println(list);*/
        subset01(subset, new String(""));
        System.out.println("*******************************************");
        System.out.println(resultList.size());

    }

    public static void subset01(List<Character> subset, String str) {
        //终止条件
        if (subset.size() < 1) {
            return;
        }
//        System.out.println(subset);
        for (int i = 0; i < subset.size(); i++) {
            String x = str + subset.get(i);
            subset01(subset.subList(i+1, subset.size()), x);
//            EDECEDEBEDECEDEAEDECEDEBEDECEDE
//            System.out.print(subset.get(i));
            System.out.println(x);
            resultList.add(x);
        }
//        System.out.println();

    }


    /**
     * 不适合使用 for 循环，因为 for 循环的层数不是递增的
     * 适合回溯
     * @param subset
     */
    public static void subset(ArrayList<Character> subset) {
        //当取一个元素的时候可以认为，增量为 1，
        for (int j = 0; j <subset.size() ; j+=1) {
            //每个数循环得起点是上一个起始元素，循环增量次即可，结束于 基准元素 + 增量
            for (int i = j; i < j+1; i++) {
                System.out.print(subset.get(i));
                System.out.print("\t");
            }
        }
        System.out.println();

        //当取两个元素得时候，固定一个元素，另一个在剩下那些元素中
        //固定得哪个元素也是++ 只不过是加到 subset.size-(2-1) 即可
        for (int i = 0; i < subset.size()-(2-1); i++) {
            //另一个在遍历剩下得那些元素，起始点就是固定得哪个元素 +1
            for (int j = i+1; j < subset.size(); j++) {
                System.out.print(subset.get(i));
                System.out.print(subset.get(j));

                System.out.print("\t");
            }
        }
        System.out.println();



        for (int i = 0; i < subset.size(); i++) {  // i+1 代表取几个元素

        }
    }
}
