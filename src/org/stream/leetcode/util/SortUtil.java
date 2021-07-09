package org.stream.leetcode.util;

import java.util.Arrays;
import java.util.Random;

public class SortUtil {
    /**
     * @Title: outputArray
     * @Description: 输出int类型数组
     * @param @param array
     * @return void
     * @throws
     */
    public static void outputArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * @Title: getRandomArray
     * @Description: 得到100范围内的随机数组
     * @param @param size
     * @param @return
     * @return int[]
     * @throws
     */
    public static int[] getRandomArray(int size) {
        Random rd = new Random(System.currentTimeMillis());
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(100);
        }
        return array;
    }

    /**
     * @Title: getRandomArray
     * @Description: 得到100范围内的长度为10的随即数组
     * @param @return
     * @return int[]
     * @throws
     */
    public static int[] getRandomArray() {
        return getRandomArray(10);
    }

    public static void swap(int[] A, int i, int j) {
//        System.out.println(j + "位:" + A[j] + "; 换 " + i + "位:" + A[i]);
        if (i != j) {
            A[i] ^= A[j];
            A[j] ^= A[i];
            A[i] ^= A[j];

        }
    }

}
