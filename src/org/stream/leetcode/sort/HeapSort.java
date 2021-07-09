package org.stream.leetcode.sort;

import org.stream.leetcode.util.SortUtil;

public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        SortUtil.swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            SortUtil.swap(arr, 0, --size);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            SortUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆化
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            SortUtil.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换排序和冒泡排序
     * 按策略划分内部排序方法
     * 可以分为五类：插入排序、选择排序、交换排序、归并排序和分配排序。
     * 堆排序是一种选择排序。选择排序有直接选择排序和堆排序两种。
     *
     *
     * 冒泡 大值不断替换小值
     * 插入 将值插入大于它值的后面
     * 选择 选择最小插入之前 或选择最大的值插入之后
     * 快速 划分区域,得到相等的区域,不断递归大和小的区域
     * 合并 化小排序再两两合并排序
     *
     * 所以 冒泡和快速都是交换
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
