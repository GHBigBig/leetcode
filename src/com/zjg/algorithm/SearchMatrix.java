package com.zjg.algorithm;

/**
 * 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2020-01-01 11:09
 */
public class SearchMatrix {

    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        int[][] matrix = {{},{},{}};
        int target = 15;
        boolean result = searchMatrix03(matrix, target);
        System.out.println("result : " + result);
    }

    /**
     * 走着走着就找到了，
     * 关键是选取起始点，比其大和比其小只有一个方向
     * i.max,j.0 满足
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix03(int[][] matrix, int target){
        int row=matrix.length-1, col=0;   //row 一维坐标，col 二维坐标

        while (row>=0 && col<matrix[0].length) {
            int value = matrix[row][col];
            if (value ==target) {
                return true;
            }else if (value > target){
                row --;
            }else {
                col ++;
            }
        }

        return false;
    }

    /**
     * 进在暴力算法上优化每一行为二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix02(int[][] matrix, int target){
        for (int i = 0; i < matrix.length; i++) {
            if (binarySearch(matrix[i], 0, matrix[i].length-1, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param arr
     * @param target
     * @return
     */
    public static boolean binarySearch(int[] arr, int left, int right, int target) {
//        System.out.println("left : " + left + ", right : " + right);
        boolean result = false;
        //终止条件
        if (left<=right) {
            int mid = (left+right)/2;
            int value = arr[mid];
            if (target==value) {
                result = true;
            }else if (target<value){  //左边
                result = binarySearch(arr, left, mid-1, target);
            }else { //右边
                result = binarySearch(arr, mid+1, right, target);
            }
        }
        return result;
    }

    /**
     * 利用每个子矩形的左小角为此子矩形中最大值，进行判断
     *
     *      思路错误：虽然左下角最大为子矩阵中最大的数值，但是不能代表比其小的值都在其中
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix01(int[][] matrix, int target) {
        int i=0,j=0/*,i1=0,j1=0*/;  //不带 1 的表示上限（大），带 1 的表示下限 （小）
        int row=0, col=0; //row 一维， col 二维
        if (matrix.length==0) {
            row=0;
            col=0;
        }else if (matrix.length>0) {
            row=matrix.length;
            col=matrix[0].length;
        }

        while (i < row && j<col) {
            int value = matrix[i][j];
            if (value ==target) {
                return true;
            }else  {
                /*i1 = i;
                j1 = j;*/
                // i++, j++ ，以 i 为本，j 的增量应为 i/row*j，修正谁小以谁为本
                if (row>col) {
                    j++;
                    double v = (double) j / col * row;
                    i += Math.ceil(v);
                }else if (col>row){
                    i++;
                    double v = (double) i / row * col;
                    j += Math.ceil(v);
                }else {
                    i++;
                    j++;
                }

                if (value > target){    //以大于的哪个为终点
                    break;
                }
            }
        }

        for (int x = /*i1*/0; x < i; x++) {
            for (int y = /*j1*/0; y < j; y++) {
                if (matrix[x][y]==target) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 暴力算法
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j]==target) {
                    return true;
                }
            }
        }
        return false;
    }
}
