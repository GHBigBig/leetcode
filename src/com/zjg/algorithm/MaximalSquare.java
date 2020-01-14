package com.zjg.algorithm;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zjg
 * @create 2020-01-05 13:04
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {{'0', '0', '0', '1'}, {'1', '1', '0', '1'},
                {'1', '1', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}};

       /* char[][] matrix = new char[7][20];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i%2==0) {
                    matrix[i][j] = '1';
                }else {
                    matrix[i][j] = '0';
                }
            }
        }*/
        int max = maximalSquare02(new char[5][1]);
        System.out.println("result : " + max);
    }

    /**
     * 动态规划：
     * dp[i][j] 表示原二维矩阵中对应的最大正方形的的边长
     * 当 matrix[i][j]==1 是 dp[i][j] = min(dp[i][j-1],dp[i-1][j-],dp[i-1][j]) 左，对角，上
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare02(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int max = 0;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int min = Integer.MAX_VALUE;
                    if (i-1<0 || j-1<0) {
                        min = 0;
                    }else {
                        min = Math.min(min, dp[i-1][j]);
                        min = Math.min(min, dp[i-1][j-1]);
                        min = Math.min(min, dp[i][j-1]);
                    }
                    dp[i][j] = min + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max*max;
    }

    /**
     * 我们用一个变量去来记录迄今为止发现的最大正方形的边长，
     * 以及用一个变量记录当前正方形的大小，两个变量都初始化为 0；
     * <p>
     * 从矩阵的左上角开始搜索 1，找到 0 不需要做任何操作，
     * 只要找到 1 我们就试图找到由 1 组成的最大正方形；
     * <p>
     * 为此我们向右和向下移动，临时增加列索引和行索引，然后用标志标记该行列是否全都为 1；
     * <p>
     * 如果全都为 1，则继续检索行列，如果找到 0，便停止移动，更新最大正方形的边长。
     * 然后从最初发现 1 的元素旁边遍历矩阵，直到矩阵的所有元素都被遍历。Java
     * <p>
     * 这才是真的暴力算法，从右上角出发，以每个为正方形最左上角开始扫描并判断最大的正方形
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare01(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int maxsqlen = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int sqlen = 1;
                    boolean flag = true;
                    while (sqlen + i < rows && sqlen + j < cols && flag) {
                        /*
                            向右
                            这里 i+sqlen 是定值，k 才是变量
                         */
                        for (int k = j; k <= sqlen + j; k++) { //这里是判断列的边界 --- 错了
                            if (matrix[i + sqlen][k] == '0') {    //这里却判断行是否满足条件 --- 错了
                                flag = false;
                                break;
                            }
                        }
                        /*
                            向下
                            这里的 j+sqlen 是定值，k才是变量
                         */
                        for (int k = i; k <= sqlen + i; k++) { //这里判断了行的边界 --- 错了
                            if (matrix[k][j + sqlen] == '0') {    //这里却判断了列是否满足条件 --- 错了
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sqlen++;
                        }
                    }
                    if (maxsqlen < sqlen) {
                        maxsqlen = sqlen;
                    }
                }
            }
        }

        return maxsqlen * maxsqlen;
    }

    /**
     * 算法缺陷：暴力算法还丫的没想周全，没有无论从左到右还是从右到左，只考虑了线性，
     * 没有考虑到所有情况！！！！！！！！
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = row <= 0 ? 0 : matrix[0].length;

        int[][] nums = new int[row][col];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            int num = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if ('1' == matrix[i][j]) {
                    num++;
                } else if ('0' == matrix[i][j]) {
                    num = 0;
                }
                nums[i][j] = num;
                if (num > 1) {
                    end:
                    for (int k = j; k > j - num; k--) { // 这里是二维
                        // j+num 不能越界，越界就可以直接判断不是此 num 构不成正方形
                        if (i + num <= matrix.length) {
                            for (int l = i; l < i + num; l++) { //这里是一维
                                if (matrix[l][k] == '0') {
                                    break end;
                                }
                            }
                        } else {
                            break;
                        }
                        if (k == j - num + 1) { //全部都是 1
                            int product = num * num;
                            if (product > max) {
                                max = product;
                            }
                        }
                    }
                } else if (num == 1) {
                    int tmp = 1;
                    if (tmp > max) {
                        max = tmp;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            int num = 0;
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if ('1' == matrix[i][j]) {
                    num++;
                } else if ('0' == matrix[i][j]) {
                    num = 0;
                }
                nums[i][j] = num;
                if (num > 1 && j + num <= col) {
                    end:
                    for (int k = j; k < j + num; k++) { // 这里是二维
                        // j+num 不能越界，越界就可以直接判断不是此 num 构不成正方形
                        if (i + num <= row) {
                            for (int l = i; l < i + num; l++) { //这里是一维
                                if (matrix[l][k] == '0') {
                                    break end;
                                }
                            }
                        } else {
                            break;
                        }
                        if (k == j - num + 1) { //全部都是 1
                            int product = num * num;
                            if (product > max) {
                                max = product;
                            }
                        }
                    }
                } else if (num == 1) {
                    int tmp = 1;
                    if (tmp > max) {
                        max = tmp;
                    }
                }
            }
        }

        for (int[] num : nums) {
            for (int i : num) {
                System.out.printf("%4d", i);
            }
            System.out.println();
        }
        return max;
    }
}
