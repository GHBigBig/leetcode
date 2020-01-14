package com.zjg.algorithm;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zjg
 * @create 2020-01-08 18:33
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        ListNode listNode = addTwoNumbers04(l1, l2);
    }


    /**
     * 02 算法改进，在遍历的时候就把结果算出来
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers04(ListNode l1, ListNode l2) {
        int flag = 0;
        ListNode root = null, tail = null;;
        while (l1 != null || l2 != null || flag != 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            if (l1!=null) {
                l1 = l1.next;
            }
            if (l2!=null) {
                l2 = l2.next;
            }

            int sum = num1 + num2 + flag;
            flag = 0;

            if (sum >= 10) {
                sum -= 10;
                flag = 1;
            }

            if (root == null) {
                root = new ListNode(sum);
                tail = root;
            }else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
        }

        return root;
    }

    /**
     * 优化 02
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers03(ListNode l1, ListNode l2) {
//        LinkedList<Integer> queue1 = new LinkedList<>();
//        LinkedList<Integer> queue2 = new LinkedList<>();
//        LinkedList<Integer> sumQueue = new LinkedList<>();
        Queue queue1 = new Queue();
        Queue queue2 = new Queue();
        Queue sumQueue = new Queue();
        int flag = 0;

        while (l1!=null) {
            queue1.add(new ListNode(l1.val));
            l1 = l1.next;
        }
        while (l2!=null) {
            queue2.add(new ListNode(l2.val));
            l2 = l2.next;
        }

        while (!queue1.isEmpty() || !queue2.isEmpty() || flag!=0) {
            int num1 = queue1.isEmpty() ? 0 : queue1.get().val;
            int num2 = queue2.isEmpty() ? 0 : queue2.get().val;
            int sum = num1 + num2 + flag;
            flag = 0;
            if (sum>=10) {
                flag = 1;
                sum -= 10;
            }
            sumQueue.add(new ListNode(sum));
        }

        ListNode root = null;
        if (!sumQueue.isEmpty()) {
            root = sumQueue.get();
            ListNode tmp = root;
            while (!sumQueue.isEmpty()) {
                tmp.next = sumQueue.get();
                tmp = tmp.next;
            }
        }


        return root;
    }

    public static ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        LinkedList<Integer> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();
        LinkedList<Integer> sumQueue = new LinkedList<>();
        int flag = 0;

        while (l1!=null) {
            queue1.addLast(l1.val);
            l1 = l1.next;
        }
        while (l2!=null) {
            queue2.addLast(l2.val);
            l2 = l2.next;
        }

        while (!queue1.isEmpty() || !queue2.isEmpty() || flag!=0) {
            int num1 = queue1.isEmpty() ? 0 : queue1.removeFirst();
            int num2 = queue2.isEmpty() ? 0 : queue2.removeFirst();
            int sum = num1 + num2 + flag;
            flag = 0;
            if (sum>=10) {
                flag = 1;
                sum -= 10;
            }
            sumQueue.addLast(sum);
        }

        ListNode root = null;
        if (sumQueue.size()>0) {
            root = new ListNode(sumQueue.removeFirst());
            ListNode tmp = root;
            while (!sumQueue.isEmpty()) {
                tmp.next = new ListNode(sumQueue.removeFirst());
                tmp = tmp.next;
            }
        }


        return root;
    }

    public static ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;

        while (tmp1 != null || tmp2 != null) {
            if (tmp1==null) {
                tmp1 = new ListNode(0);
            }
            if (tmp2==null) {
                tmp2 = new ListNode(0);
            }
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }



        return null;
    }

    /**
     * 思想没有问题，但是实现有点问题，测试也有点问题
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num = parse(l1) + parse(l2);
        ListNode listNode = generate(num);
        return listNode;
    }

    public static ListNode generate(long num) {
        Stack<Integer> stack = new Stack<>();
        int length = ("" + num).length();
        long pow;
        while (length > 0) {
            pow = (long) Math.pow(10, --length);
            stack.push((int) num/(int)pow);
            num = num % pow;
        }

        ListNode root = null;
        if (stack.size()>0) {
            root = new ListNode(stack.pop());
            ListNode tmp = root;
            while (!stack.isEmpty()) {
                tmp.next = new ListNode(stack.pop());
                tmp = tmp.next;
            }
        }
        return root;
    }

    /**
     * 解析链表变为整数
     * @param l
     * @return
     */
    public static long parse(ListNode l) {
        long result = 0;
        int pow = 0;
        while (l != null) {
            result += l.val * Math.pow(10, pow++);
            l = l.next;
        }
        return result;
    }

    static class Queue {
        ListNode head;
        ListNode tail;

        /**
         * 每个元素都加到最后
         * @param listNode
         */
        public void add(ListNode listNode) {
            if (listNode==null) {
                throw new RuntimeException("拒绝添加 " + listNode);
            }
            if (tail == null) {
                head = tail = listNode;
            }else {
                tail.next = listNode;
                tail = tail.next;
            }
        }

        /**
         * 空元素也可以
         * @return
         */
        public ListNode get() {
            if (head==null) {
                throw new RuntimeException("空队列，禁止 get");
            }
            ListNode res = head;
            //只有一个元素，首尾都要清除
            if (head == tail) {
                tail = null;
            }
            head = head.next;
            return res;
        }

        public boolean isEmpty() {
            if (null==head && null==tail) {
                return true;
            }
            return false;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
