package org.stream.leetcode.sort;

import org.stream.leetcode.util.SortUtil;

public class BubbleSort {
    /**
     * 冒泡排序
     * 该方法将最小值向最前排,排完一个最小值,后面的最小值就无需再和上一个前排的值对比了
     *
     * @param array
     */
    public void bubbleSort1(int array[]) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }

    /**
     * 该方法将最大值向最后排,排完一个最大值,后面的最大值就无需再和上一个后排的值对比了
     *
     * @param array
     */
    public void bubbleSort2(int array[]) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                if (array[j] > array[j + 1]) swap(array, j, j + 1);
            }
        }
    }

    public void bubbleSort3(int array[]) {
        boolean flag = true;
        int count = array.length - 1;
        while (flag) {
            flag = false;
            for (int i = 0; i < count; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    flag = true;
                }
            }
            count--;
        }
    }

    /**
     * 这个交换方法牛逼啊 使用异或运算,不用引入新空间
     * i得到i异或j的值,j再异或该值就能得到原始i,
     * 该值再去异或新j也就是原来的i,就能得到原始的j,
     * 这样就交换了数据!
     *
     * @param A
     * @param i
     * @param j
     */
    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }


    public static void main(String[] args) {
        BubbleSort b = new BubbleSort();
        int[] a = new int[]{1, 4, 7, 3, 2};
        SortUtil.outputArray(a);
        b.bubbleSort3(a);
        SortUtil.outputArray(a);

    }
}
