package org.stream.leetcode.sort;

import org.stream.leetcode.util.SortUtil;

public class MergeSort {
    public void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /**
     * 合并的原理, 就是把这些数组细分到两个元素的数组,然后排序,
     * 再把这2个元素数组合并到4个元素数组,
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public void merge(int[] arr, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;

        /**
         *  下面一段代码的原理: 2569 m 3478
         *  2和3比较得2, 2位移5, 5和3比较得23,3位移4,4和5比较得234,4位移7,7和5比较得2345,
         *  5位移6,6和7比较得23456,6位移9,9和7比较得234567,7位移8,8和9比较得2345678,8位移出位;
         *
         *  则这时候因为9等于m 所以9添加到后续的数组, 两个数组中的一个数组被匹配完了,而另一个数组没有匹配完,
         *  而两个数组都是合并来的数组 都是有序数组, 所以后续添加到临时数组的数据是有序的
         *
         *  如果是前一个数组,则边界不会超过mid,
         *  如果是后一个数组,则边界不会超过p2
         *
         */

        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while (p1 <= mid && p2 <= R) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[i++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for (i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = new int[]{2, 9, -9, 7, -6, 8, 0};
        mergeSort.mergeSort(a, 0, a.length - 1);
        SortUtil.outputArray(a);
    }
}
