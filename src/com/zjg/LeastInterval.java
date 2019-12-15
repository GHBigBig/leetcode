package com.zjg;

import java.util.*;

/**
 * 621. 任务调度器
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 *
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-09 19:22
 */
public class LeastInterval {

    public static void main(String[] args) {
        LeastInterval leastInterval = new LeastInterval();
        char[] tasks = new char[1000];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = (char)((int)(Math.random()*26) + 65);
        }

//        char[] tasks = {'A','A','A','B','B','B'};
        long start = System.currentTimeMillis();
        int i = leastInterval.leastInterval05(tasks, 2);
        int i1 = leastInterval.leastInterval06(tasks, 2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("result : " + i);
        System.out.println("result1 : " + i1);
    }

    public int leastInterval06(char[] tasks, int n) {
        int[] recode = new int[26];
        for (char task : tasks) {
            recode[task%65]++;
        }
        Arrays.sort(recode);
        int max = recode[25];
        int count = 0;
        for (int i : recode) {
            if (i==max) {
                count ++;
            }
        }

        return (max-1)*(n+1) + count;
    }

    /**
     * 官方第二种答案
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval05(char[] tasks, int n) {
        int[] recode = new int[26];
        for (char task : tasks) {
            recode[task%65]++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int i : recode) {
            if (i>0) {
                queue.add(i);
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            ArrayList<Integer> tmp = new ArrayList<>();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek()>1) {
                        tmp.add(queue.poll()-1);
                    }else {
                        queue.poll();
                    }
                }
                time ++;
                if (queue.isEmpty() && tmp.size()==0) {
                    break;
                }
                i ++;
            }
            for (Integer integer : tmp) {
                queue.add(integer);
            }
        }
        return time;
    }


    /**
     * 官方解题一：
     *      比我多考虑了，每次执行完成后都要排序，选择最多的哪个任务；
     *      这个挺重要
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval04(char[] tasks, int n) {
        int[] recode = new int[26];
        for (char task : tasks) {
            recode[task%65]++;
        }
        Arrays.sort(recode);

        int time = 0;
        while (recode[25] > 0) {
            int i = 0;
            while (i<=n) {
                if (recode[25]==0) {
                    break;
                }
                if (i<=25 && recode[25-i]>0) {
                    recode[25-i] --;
                }
                time ++;
                i ++;
            }
            //每一次做完就要排序
            Arrays.sort(recode);

        }
        return time;
    }

    /**
     * 超出时间限制
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval03(char[] tasks, int n) {
        //记录每次执行的任务和冷却时间内执行的任务是必须的
        TaskCount[] recode = new TaskCount[n+1];
        //对要执行的任务进行分析，我需要得到每个任务需要执行的次数
        ArrayList<TaskCount> list = new ArrayList<>();
        TaskCount taskCount;
        for (char task : tasks) {
            taskCount = new TaskCount(task, 1);
            int index = list.indexOf(taskCount);
            if (index==-1) {    //不存在直接添加
                list.add(taskCount);
            }else { //存在就令其 count 加 1
                TaskCount taskCount1 = list.get(index);
                taskCount1.count ++;
                list.set(index, taskCount1);
            }
        }
        Collections.sort(list);

        //首次向 recode 添加数据就是打算执行的
        for (int i = 0, j=0; i < list.size() && j<recode.length; i++,j++) {
            recode[j] = list.get(i);
            list.set(i, null);
        }

        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        //最长的元素     //我没有考虑到执行后数组变动了，最大长度的数组可能有变
        //这样的话，怎么保持间隔 n 呢？排序后 同一个执行的任务只可能 > n
//        int len = recode[0].count;
//        for (int i = 0; i < len; i++) {
        while (!(listIsNull(list) && recodeIsNull(recode))) {

            for (int j = 0; j < recode.length; j++) {
                if (recode[j]==null) {
                    stringBuilder.append(" ");
                }else {
                    stringBuilder.append(recode[j].task);
                    if (--recode[j].count<=0) { //这个任务执行完毕，那么就去找个下个任务
                        for (int k = 0; k < list.size(); k++) {
                            if (list.get(k)!=null) {
                                recode[j] = list.get(k);
                                continue;
                            }
                        }
                        //到这一步就说明 list 中全为 null 那就证明没有可执行的任务了将 recode[i] 设为 null 即可
                        recode[j] = null;
                    }
                }

            }

            System.out.println(stringBuilder.toString());
            if (listIsNull(list) && recodeIsNull(recode)) {
                count += stringBuilder.toString().trim().length();
            }else {
                count += stringBuilder.toString().length();
            }
            stringBuilder.delete(0, stringBuilder.length());

        }


        return count;
    }

    public boolean listIsNull(ArrayList<TaskCount> list) {
        for (TaskCount taskCount : list) {
            if (taskCount!=null) {
                return false;
            }
        }
        return true;
    }

    public boolean recodeIsNull(TaskCount[] recode) {
        for (TaskCount taskCount : recode) {
            if (taskCount!=null) {
                return false;
            }
        }
        return true;
    }

    private static class TaskCount implements Comparable<TaskCount> {
        Character task;
        int count;

        public TaskCount(Character task, int count) {
            this.task = task;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TaskCount)) return false;

            TaskCount taskCount = (TaskCount) o;

            return task != null ? task.equals(taskCount.task) : taskCount.task == null;
        }

        @Override
        public int hashCode() {
            int result = task != null ? task.hashCode() : 0;
            result = 31 * result + count;
            return result;
        }

        @Override
        public int compareTo(TaskCount o) {
            return -(this.count-o.count);
        }
    }

    /**
     * 否决：最后一次无法准确统计
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval02(char[] tasks, int n) {
        int[] recode = new int[n+1];
        int[] tasksAndCounts = new int[26];
        ArrayList<Character> list = new ArrayList<>(Arrays.asList(new Character[26]));
        for (char task : tasks) {
            tasksAndCounts[task%65] ++;
            if (list.get(task%65)==null) {
                list.set(task%65, task);
            }
        }

        int count = 0;
        //首次赋值
        for (Character character : list) {
            if (character!=null) {
                recode[count] = character%65;
                if (++count == recode.length) {
                    break;
                }
            }
        }
        count = 0;



        while (true) {
            for (int i = 0; i < recode.length; i++) {
                count ++;
                if (!(recode[i]!=-1 && --tasksAndCounts[recode[i]]>0)) {    //一个位置的任务都执行完了
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j)!=null) {
                            recode[i] = list.get(j)%65;
                            break;
                        }
                        if (j==list.size()-1) {
                            recode[i] = -1;
                        }
                    }

                }
            }

            //当 recode 中没有
        }


    }



    /**
     * 最短时间
     * 要求：
     *      两个相同种类的任务之间必须有长度为 n 的冷却时间
     *      至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     *      选取 n 个单位时间内执行的任务尽可能多
     *
     *      用 char[] recode = new char[n] 的数组记录每次执行的任务
     *      执行完一次后下一次执行前还要判断 recode 里面的任务是否还需执行
     *      如果不用执行了那么换下一个，如没有下一个那么就令 CPU 空闲
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval01(char[] tasks, int n) {

        // -1 表示 CPU 空闲
        int[] recode = new int[n];
        //归纳任务共多少个任务，每个任务需要执行多少次
        int[] tasksAndCounts = new int[26];
        for (char task : tasks) {
            tasksAndCounts[task%65] ++;
        }

        //怎么添加任务

        int count = 0;

        //执行任务
        while (true) {
            for (int i = 0; i < recode.length; i++) {
                count ++;
                // CPU不为空闲， 那么 recode 记录的任务就要减少一次，并且这个任务还要可可执行的次数
                if (recode[i]!=-1 &&  --tasksAndCounts[recode[i]] >0) {
                    //如果 recode 中记录的任务没有可执行次数了，那么应该换下一个新的任务
                    int num = -1;   //表示哪个任务，默认为空闲
                    //选取任务，并且还不能与次记录中其他的位置记录的任务相同
                    for (int j = 0; j < tasksAndCounts.length; j++) {
                        //此任务还有次数
                        if (tasksAndCounts[j] > 0) {
                            //但是不能 recode 中已经记录的
                            for (int k = 0; k < recode.length; k++) {
                                if (i==recode[k]) {
                                    break;
                                }
                            }
                            num = j;
                            break;
                        }
                    }
                    recode[i] = num;
                }

                for (int j = 0; j < recode.length; j++) {
                    if (recode[j]!=-1) {
                        break;
                    }
                    if (j==recode.length-1) {
                        return count;
                    }
                }
            }

        }

    }

    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap();
        Integer count;
        for (char task : tasks) {
            count = map.get(task);
            if (count == null) {
                count = 1;
            }else {
                count = count+1;
            }

            map.put(task, count);
        }

        int max = 0;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue()>max) {
                max = entry.getValue();
            }
        }

        int num = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                num ++;
            }
        }

        return (max-1)*(n+1) + num;
    }
}
