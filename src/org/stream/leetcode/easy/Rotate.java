package org.stream.leetcode.easy;

public class Rotate {
    /**
     * 旋转数组
     *
     * 这里是使用了临时数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] tmp = new int[k];
        int index = nums.length - k - 1;
        for (int i = nums.length - 1; i > -1; i--) {
            if (i > index) {
                tmp[i - index - 1] = nums[i];
            } else {
                nums[i + k] = nums[i];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[i] = tmp[i];
        }
    }

    /**
     * 环状替换
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;
        int index = 0;
        int tmp = nums[0];
        int t = 0;
        int P = index;
        while (t < nums.length) {
            //  下一个位置
            index = (index + k) % nums.length;
            int _tmp = nums[index];
            nums[index] = tmp;
            tmp = _tmp;
            if (index == P){
                P = ++index;
                tmp = nums[P];
            }
            t++;
        }
    }

    /**
     * 环状替换
     * gcd 指的是最大公约数
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        // 6和3的最大公约数是3, 则循环3次
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                current = (current + k) % n;
                int temp = nums[current];
                nums[current] = prev;
                prev = temp;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 输出时间:
     * 556
     * 9539
     * 经过测试还是第一种开辟一个新组数速度要快,空间上会多一些吧,但是环状替换要更多计算
     * @param args
     */
    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int length = (int) Math.pow(2, 28);
        int[] nums = new int[length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = i;
        }
        long tag = System.currentTimeMillis();
        rotate.rotate(nums, 33321);
        System.out.println(System.currentTimeMillis()-tag);
        tag = System.currentTimeMillis();
        rotate.rotate3(nums, 33321);
        System.out.println(System.currentTimeMillis()-tag);


    }
}
