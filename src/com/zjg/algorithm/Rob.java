package com.zjg.algorithm;

import sun.management.snmp.jvmmib.JvmMemManagerTableMeta;

import javax.swing.*;
import java.util.HashMap;

/**
 * 337. 打家劫舍 III
 * <p>
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 *      3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2019-12-25 20:14
 */
public class Rob {
    private TreeNode root;

    public static void main(String[] args) {
        Rob rob = new Rob();
        int arr[] = {3,2,4,0,5,0,1,0,0,0,7,0,0,0,8};

        for (int i : arr) {
            rob.add(i);
        }

        System.out.println();

        int result = rob.rob(rob.root);

        System.out.println("result : " + result);
    }


    public int rob02(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode node) {
        if (node==null) {
            return new int[2];
        }
        int[] result = new int[2];

        int[] left = robInternal(node.left);
        int[] right = robInternal(node.right);

        result[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        result[1] = left[0]+right[0] + node.val;

        return result;
    }


    /**
     * 在递归判断的时候会造成很多重复的计算：
     *  我们发现爷爷在计算自己能偷多少钱的时候，同时计算了4个孙子能偷多少钱，
     *  也计算了2个儿子能偷多少钱。这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。
     *
     * 这里就是优化的地方
     * @param root
     * @return
     */
    public int rob01(TreeNode root) {
        HashMap<TreeNode, Integer> tmp = new HashMap<>();
        return treeCalculate(root, tmp);
    }

    public int treeCalculate(TreeNode node, HashMap<TreeNode, Integer> tmp) {
        if (node==null) {
            return 0;
        }
        if (tmp.containsKey(node)) {
            return tmp.get(node);
        }
        int money = node.val;

        if (node.left!=null) {
            money += treeCalculate(node.left.left,tmp) + treeCalculate(node.left.right,tmp);
        }
        if (node.right!=null) {
            money += treeCalculate(node.right.left,tmp) + treeCalculate(node.right.right,tmp);
        }
        int result = Math.max(money, treeCalculate(node.left,tmp)+treeCalculate(node.right,tmp));

        tmp.put(node, result);
        return result;
    }

    /**
     * 自己的解，大体思路没有问题，具体实施的时候出错了
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    /*public int rob(TreeNode root) {
        return Math.max(first(root), second(root));
    }

    public int first(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        if (root.left!=null) {
            left += first(root.left.left) + first(root.left.right);
        }
        int right = 0;
        if (root.right!=null) {
            right += first(root.right.left) + first(root.right.right);
        }

        return root.val + left + right;
    }

    public int second(TreeNode root) {
        if (root==null) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = 0;

        if (root.left!=null) {
            result += root.left.val;
            left = second(root.left.left) + second(root.left.right);
        }
        if (root.right!=null) {
            result += root.right.val;
            right = second(root.right.left) + second(root.right.right);
        }

        return result + left + right;
    }*/

    public void add(int i) {
        if (root==null) {
            root = new TreeNode(i);
        }else {
            add(i, root);
        }
    }

    public void add(int i, TreeNode node) {
        if (node==null || node.val==0) {
            return;
        }

        if (node.left==null) {
            node.left = new TreeNode(i);
            return;
        }else if (node.right==null) {
            node.right = new TreeNode(i);
            return;
        }else {
            add(i, node.left);
            add(i, node.right);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
