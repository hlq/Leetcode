package org.stream.leetcode.sort;

import org.stream.leetcode.util.SortUtil;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class QuickSort {
    /**
     * 快速排序 取第一个值key,从左到右比较key,
     * 将所有小于key值的放在start之后,确保i+1和start之间都是小于key的,再把位置i的值和start值替换,
     * 接下来分i左边和i右边的两组,做迭代处理, 直到start < end不成立 也就是最小相邻两个值排序吧
     *
     * @param array
     * @param start
     * @param end
     */
    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int key = array[start];
            int i = start;
            for (int j = start + 1; j <= end; j++) {
                SortUtil.outputArray(array);
                if (key > array[j]) SortUtil.swap(array, j, ++i);
            }
            array[start] = array[i];
            array[i] = key;
            quickSort(array, start, i - 1);
            quickSort(array, i + 1, end);
        }
    }

    /**
     * 感觉这玩意效率太低 太磨叽了
     *
     * @param a
     * @param start
     * @param end
     */
    public void quickSort2(int[] a, int start, int end) {
        // 栈解构后进先出
        Stack<Integer> temp = new Stack<>();
        temp.push(end);
        temp.push(start);
        while (!temp.empty()) {
            int i = temp.pop();
            int j = temp.pop();
            int k = partitionQuick2(a, i, j);
            if (k > i) {
                temp.push(k - 1);
                temp.push(i);
            }
            if (j > k) {
                temp.push(j);
                temp.push(k + 1);
            }
        }
    }

    public int partitionQuick2(int[] array, int start, int end) {
        int p = array[start];
        while (start < end) {
            while (start < end && array[end] >= p) --end;
            SortUtil.swap(array, start, end);
            while (start < end && array[start] <= p) ++start;
            SortUtil.swap(array, start, end);
        }
        return start;
    }

    /**
     * 快速排序，使得整数数组 arr 的 [L, R] 部分有序
     * @param arr
     * @param L
     * @param R
     */
    public void quickSort3(int[] arr, int L, int R) {
        if (L < R) {
            //取随机index数和最后元素交换
            SortUtil.swap(arr, new Random().nextInt(R - L + 1) + L, R);
            int[] p = partitionQuickSort3(arr, L, R);
            // 这里p[0]到p[1]之间是等于基准值的
            quickSort3(arr, L, p[0] - 1);
            quickSort3(arr, p[1] + 1, R);
        }
    }

    /**
     * 分区的过程，整数数组 arr 的[L, R]部分上，使得：
     * 大于 arr[R] 的元素位于[L, R]部分的右边，但这部分数据不一定有序
     * 小于 arr[R] 的元素位于[L, R]部分的左边，但这部分数据不一定有序
     * 等于 arr[R] 的元素位于[L, R]部分的中间
     * 返回等于部分的第一个元素的下标和最后一个下标组成的整数数组
     * <p>
     * 这里的分区就比上面的强,这里将大小都匹配了
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int[] partitionQuickSort3(int[] arr, int L, int R) {
        int p = arr[R];
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < p) {
                SortUtil.swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                SortUtil.swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    /**
     * 荷兰旗问题 分区
     * <p>
     * 给定一个整数数组，给定一个值K，这个值在原数组中一定存在，要求把数组中小于K的元素放到数组的左边，
     * 大于K的元素放到数组的右边，等于K的元素放到数组的中间，最终返回一个整数数组，其中只有两个值，
     * 分别是等于K的数组部分的左右两个下标值。
     * <p>
     * 给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4，那么经过处理原数组可能得一种情况是：
     * [2, 3, 1, 1, 4, 9, 7, 6, 5]，需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，
     * 返回等于4部分的左右两个下标，即[4, 4]
     * <p>
     * 总结下:  就是从左到右依次比较,循环标记位置L, 将小于p值的放在左边,边界向右加一,边界左边的全是小于p值的;
     * 当遇到等于p值的循环继续向右加一,这是左边界和L之间的是等于p值的数据;
     * 遇到了大于p值的数据都丢给右边,右边界向左移动,右边界的右边全是大于p值的数据,此时L不会变动,因为和右边交换的值
     * 是未知的,需要继续比较大小,大于向右边界扔,右边界左移,小于向左边界扔左边界右移,等于L右移
     * <p>
     * 因为是从左到右,所以 左边值的大小是预计的, 所以遇到小的时候L右移, 等于的时候也可以右移,
     * 而大于的时候右边的交换值未知,这个时候需要多次比较;
     * <p>
     * 我在想这里再交换后是不是可以在 左右边界外的序列做一个有序列表,这时应该就进化成了快速排序了吧
     *
     * @param arr
     * @param L   左边边界 0
     * @param R   右边边界 8
     * @param p   给定值 4
     * @return
     */
    public int[] partition(int[] arr, int L, int R, int p) {
        int less = L - 1;   //-1
        int more = R + 1;   //9
        while (L < more) {  // 从右到左循环
            System.out.println("L:" + L + ";lese:" + less + ";more:" + more + ";arr:" + Arrays.toString(arr));
            if (arr[L] < p) {   // 取每个值和4匹配
                SortUtil.swap(arr, ++less, L++);    //小于则0和0互换
            } else if (arr[L] > p) {
                SortUtil.swap(arr, --more, L);      //大于则8和1
            } else {
                L++;                                //等于则0+1
            }
        }
        System.out.println("L:" + L + ";lese:" + less + ";more:" + more + ";arr:" + Arrays.toString(arr));
        return new int[]{less + 1, more - 1};
    }


    public static void main(String[] args) {
//        int[] a = new int[]{2, 6, 4, 8, 3, 0, 2, 1, 9};
        int[] a = new int[]{2, 3, 1, 9, 7, 6, 1, 4, 5};
        QuickSort quickSort = new QuickSort();
//        quickSort.quickSort(a, 0, a.length - 1);
//        quickSort.partition(a,0,a.length-1,4);

        a = new int[]{3, 4, 5, 3, 3, 2, 1, 6, 7};
//        quickSort.quickSort(a, 0, a.length - 1);

        a = new int[]{3, 2, 1, 5, 4, 2, 1, 8, 9};
//        quickSort.quickSort2(a, 0, a.length - 1);

        SortUtil.outputArray(a);
        quickSort.quickSort3(a,0,a.length-1);
        SortUtil.outputArray(a);
    }
}


