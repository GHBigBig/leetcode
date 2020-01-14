package com.zjg.mine;

import java.util.ArrayList;
import java.util.List;

/**
 * 打算有所子集的情况
 * @author zjg
 * @create 2020-01-06 12:49
 */
public class Test {
    static int n =-1;
    static ArrayList<String> resultList = new ArrayList<>();

    public Test() {

    }
    public void print(List list)
    {
        String str = "";
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
            str += list.get(i);
        }
        resultList.add(str);
        System.out.println();
    }

    /*通过递归减少集合中元素的个数
     * @index 控制排除递归出重复的结果
     * @list 递归的List
     */
    public List deal(int index,List list)
    {
        //把原List克隆
        List newList =(List) ((ArrayList)list).clone();

        print(list);
        n++;
        for(int i=0;i<list.size();i++)
        {
            if(i>=index)
            {
//              System.out.println("list大小:"+list.size());
//              System.out.println("index:"+index+"  i:"+i);
                list.remove(i);
                //递归调用
                deal(i,list);
                list = (List) ((ArrayList)newList).clone();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        List<Character> list = new ArrayList();
        /*for(int i = 0 ; i < 5 ; i ++)
            list.add(new Integer(i));*/
        list.add('A');
        list.add('B');
        list.add('C');
        list.add('D');
        list.add('E');

        Test test = new Test();
        test.deal(0,list);
        System.out.println("@@@@@@@@@@@@@   "+n);

        resultList.stream()
                .sorted((str1, str2) -> {
                    if (str1.length()==str2.length()) {
                        return str1.compareTo(str2);
                    }else {
                        return str1.length()-str2.length();
                    }
                })
                .forEach(System.out::println);

    }
}
