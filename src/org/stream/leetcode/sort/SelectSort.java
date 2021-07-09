package org.stream.leetcode.sort;

public class SelectSort {
    /**
     * 选择排序
     * 选择排序和冒泡排序有一点点像，每次找到最小值,排到前面,
     * 冒泡是每个对比成功就交换
     *
     * 选择排序效率最高,选出最高或者最小次排除后,又选出最高或者最小,
     * 最后就以此排出序列了
     * @param array
     */
    public void selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) index = j;
            }
            if (i != index) swap(array, i, index);
        }

    }

    public void swap(int[] A, int i, int j) {
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];
        }
    }

    public static void main(String[] args) {

    }
}
