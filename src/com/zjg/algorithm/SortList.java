package com.zjg.algorithm;


import java.util.List;

/**
 * @author zjg
 * @create 2020-01-19 15:10
 */
public class SortList {
    static int num = 0;
    public static void main(String[] args) {
        /*Random random = new Random();
        int[] arr = new int[13];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        int[] tmp = new int[arr.length];
        System.out.println("排序前：" + Arrays.toString(arr));
        mergerSort(arr, 0, arr.length - 1, tmp);
        System.out.println("排序后：" + Arrays.toString(arr));*/
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = head;

        step(head);

        System.out.println("step : " + num);
    }

    /**
     * 环形链表 slow 与 fast 不同的起点，走几步才能相遇
     * 当 fast 和 slow 相同时那么滑动相同的链表长度的步数就能再次相遇
     * slow=head.next fast=head 时，那么需要链表的长度 + 1次
     * @param head
     */
    public static void step(ListNode head) {
        int flag = 0;
        ListNode slow=head, fast=head;
        while (flag <= 1) {
            if (slow==fast) {
                flag ++;
                if (flag!=1) {
                    break;
                }
            }
            slow = slow.next;
            fast = fast.next.next;
            num++;
        }
    }


    public static ListNode sortList02(ListNode head) {
        return head==null ? null : cutL(head);
    }

    /**
     * 使用 slow-fast 快慢指针方法将数组切断
     * 快指针指到头部时慢指针指到中间
     * 让慢指针的 next=null，就把链表切断了
     * 递归 cut
     * @param head
     * @return
     */
    public static ListNode cutL(ListNode head) {
        if (head.next==null) {
            return head;
        }
        ListNode slow=head, fast=head, pre=null;
        while (fast!=null && fast.next!=null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //中间处切断链表
        pre.next = null;
        ListNode l = cutL(head);
        ListNode r = cutL(slow);
        return mergerL(l,r);
    }

    static ListNode mergerL(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val<r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            }else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
            if (l!=null) {
                cur.next=l;
            }
            if (r!=null) {
                cur.next=r;
            }
        }
        return dummyHead.next;
    }

    public static ListNode sortList01(ListNode head) {

        return null;
    }

    public static int size(ListNode head) {
        int size = 0;
        ListNode listNode = head;
        while (listNode != null) {
            size ++;
            listNode = listNode.next;
        }
        return size;
    }

    /**
     * 根据中心点
     * 分割将整个链表分为小的链表
     * @param head
     * @param left
     * @param right
     * @param tmp
     */
    public static void mergerSortList01(ListNode head, int left, int right, ListNode tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergerSortList01(head, left, mid, tmp);
            mergerSortList01(head, mid + 1, right, tmp);

            mergerList(head, left, mid, right, tmp);
        }
    }

    public static ListNode generateList(ListNode head, int left, int right) {
        for (int i = 0; i < left; i++) {
            head = head.next;
        }
        ListNode listNode = null;
        for (int i = left; i <= right; i++) {
            listNode = head;
            listNode = listNode.next;
            head = head.next;
        }
        return listNode;
    }


    /**
     * 归并的的思路了解，数组的套路可以走得通，链表的无法走通
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        //生成临时列表使用 Integer.MIN_VALUE
        ListNode tmp = new ListNode(0);
        int size = size(head);
        for (int i = 0; i < size-1; i++) {
            tmp.next = new ListNode(0);

        }
        mergerSortList(head, 0, size(head), tmp);
        return head;
    }



    /**
     * 尝试将数组的排序方法转为链表
     * 其中第一次中
     *      left = 0
     *      right = size(head)
     *
     *
     * @param head
     * @param left
     * @param right
     * @param tmp
     */
    public static void mergerSortList(ListNode head, int left, int right, ListNode tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergerSortList(head, left, mid, tmp);
            mergerSortList(head, mid + 1, right, tmp);

            mergerList(head, left, mid, right, tmp);
        }
    }

    public static void mergerList(ListNode leftList, ListNode rightList, ListNode tmp) {

    }


    /**
     * 尝试将数组的排序方法转为链表
     * 第一次
     *  arr head
     *  left => 0
     *  mid => mid
     *  right => right
     *  tmp
     *
     * @param head
     * @param left
     * @param mid
     * @param right
     * @param tmp
     */
    public static void mergerList(ListNode head, int left, int mid, int right, ListNode tmp) {
        int i = left;
        ListNode leftNode = head;
        for (int x = 0; x < i; x++) {
            leftNode = leftNode.next;
        }

        int j = mid + 1;
        ListNode rightNode = head;
        for (int y = 0; y < j; y++) {
            rightNode = rightNode.next;
        }

        ListNode tmpHead = tmp;
        ListNode startNode = leftNode;

        int tmpIndex = 0;

        //这里怎么表示从 左、右链表的头部到未端
        while (i <= mid && j <= right) {

            if (leftNode.val < rightNode.val) {
                tmp.val = leftNode.val;
                tmp = tmp.next;
                leftNode = leftNode.next;
                i++;
            } else {
                tmp.val = rightNode.val;
                tmp = tmp.next;
                rightNode = rightNode.next;
                j++;
            }
        }

        //左面的移动完移动右边的
        while (j <= right) {
            tmp = rightNode;
            tmp = tmp.next;
            rightNode = rightNode.next;
            j ++;
        }

        //右面的移动完移动左边的
        while (i <= mid) {
            tmp = leftNode;
            tmp = tmp.next;
            leftNode = leftNode.next;
            i ++;
        }

        //合并
        int tmpLeft = left;
        while (tmpLeft <= right) {
            startNode = tmpHead;
            startNode = startNode.next;
            tmpHead = tmpHead.next;
            tmpLeft ++;
        }
    }

    public static void mergerSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergerSort(arr, left, mid, tmp);
            mergerSort(arr, mid + 1, right, tmp);

            merger(arr, left, mid, right, tmp);
        }
    }

    public static void merger(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int tmpIndex = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[tmpIndex++] = arr[i++];
            } else {
                tmp[tmpIndex++] = arr[j++];
            }
        }

        //左面的移动完移动右边的
        while (j <= right) {
            tmp[tmpIndex++] = arr[j++];
        }

        //右面的移动完移动左边的
        while (i <= mid) {
            tmp[tmpIndex++] = arr[i++];
        }

        //合并
        int tmpLeft = left;
        tmpIndex = 0;
        while (tmpLeft <= right) {
            arr[tmpLeft++] = tmp[tmpIndex++];
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
