package org.stream.leetcode.sort;

import org.stream.leetcode.util.SortUtil;

public class InsertSort {
    /**
     * 插入排序
     * 将最小值插入前面,
     *
     *
     * 目前看来冒泡 选择 插入都大同小异,
     * 实质就是将部分数据先位移排序,然后后续的数据比较的次数就会越来越少
     *
     * @param array
     */
    public void insertSort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int temp = array[i];
            for (; j > 0; j--) {
                if (array[j - 1] > temp) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = temp;
        }
    }

    /**
     * 二分插入排序
     * 高效,已经有序的左边序列从小到大,
     * 插入值的时候,在已经排序的列表里取中间index比较该位置值,
     * 如果中间值大于则取左边序列继续比较,小于则取右边序列比较,
     * 一点点减小区间,直到位移到low,这个low是最小的大于插入值,
     * 最后将插入值替换到low位置,之前数据整体提前一位
     * @param array
     */
    public void insertSort2(int[] array){
        int length = array.length;
        for (int i=1;i<length;i++){
            if (array[i-1]>array[i]){
                int key = array[i];
                int low = 0;
                int hight=i-1;
                while (low<=hight){
                    // 这里右移一位可以获得中间值, 2`4->3,2`5->3 偶数得减1
                    int mid = (low + hight) >> 1;
                    if (array[mid] > key){
                        hight = mid-1;
                    } else {
                        low = mid+1;
                    }
                }
                for(int j=i;j>low;j--){
                    array[j]=array[j-1];
                }
                array[low] = key;
            }
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] s = new int[]{5,3,4,2,7,8,0,6};
        insertSort.insertSort2(s);
        SortUtil.outputArray(s);
    }
}
