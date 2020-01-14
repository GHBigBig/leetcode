package com.zjg.algorithm;

import java.util.List;

/**
 * @author zjg
 * @create 2020-01-02 15:02
 */
public class LowestCommonAncestor {
    private Boolean pFlag = false;
    private Boolean qFlag = false;
    private TreeNode result = null;

    public static void main(String[] args) {
        int i = 1;
        i = i++;
        System.out.println("i : " + i);
    }

    public TreeNode lowestCommonAncestor02(TreeNode root, TreeNode p, TreeNode q) {
        this.recurseTree(root, p, q);
        return this.result;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        if (currentNode==null) {
            return false;
        }

        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
        /*
            mid 当前一个节点是另一个节点的父结点
            也是判断 此节点是否是 q p 中的节点
         */
        int mid = (currentNode==q || currentNode==p) ? 1 : 0;

        if (mid+left+right >= 2) {
            this.result = currentNode;
        }

        return (mid+left+right > 0);
    }



    /**
     * 后序遍历
     *
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor01(TreeNode root, TreeNode p, TreeNode q) {
        postOrder(root, p, q);
        return result;
    }

    /**
     * 思路错误，因为当 q 和 p 都找到了，最后哪个节点就会返回，
     * 因为以满足 qFlag、pFlag 都为 true，都不它两的共同节点
     * 必须在同一个节点里判断它的字节时候包含 q 和 p，在一结点做判断
     * @param root
     * @param p
     * @param q
     */
    public void postOrder(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return;
        }
        postOrder(root.left, p, q);
        postOrder(root.right, p, q);
        if (q.val == root.val) {
            qFlag = true;
        }
        if (p.val == root.val) {
            pFlag = true;
        }
        if (qFlag && pFlag) {
            //这里已经得到想要的 q p 的父结点了，这里可以终止此程序的运行了
            result = root;
            qFlag = false;
            pFlag = false;
        }
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return null;
    }

    /**
     * 再 root 这颗二叉树中找出 node 的所有父节点
     *
     * @param root
     * @param node
     * @param parentList 存放 node 的父结点
     */
    public void findAllParent(TreeNode root, TreeNode node, List<TreeNode> parentList) {
        if (null == root) {
            return;
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
