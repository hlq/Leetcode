package org.stream.leetcode.easy;

public class RemoveDuplicates {
    /**
     * 删除排序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums){
        if (nums.length == 0) return 0;
        int index = 1;
        int tmp = nums[0];
        for (int i = 1; i < nums.length && index < nums.length; i++){
            if (nums[i] == tmp) continue;
            tmp = nums[i];
            nums[index++] = tmp;
        }
        return index;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = new int[]{};
        int length = removeDuplicates.removeDuplicates(nums);
        System.out.println(length);
    }
}
