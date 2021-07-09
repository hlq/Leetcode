package org.stream.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindrome {

    /**
     *  最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] str = s.toCharArray();
        int[] startEnd = new int[]{0,0};
        for (int i = 0; i < str.length - 1; i++) {
            evenPalindrome(i, str, startEnd);
            oddPalindrome(i, str, startEnd);
        }
        char[] res = new char[startEnd[1] - startEnd[0] + 1];
        for (int i = startEnd[0]; i <= startEnd[1]; i++) {
            res[i - startEnd[0]] = str[i];
        }
        return new String(res);
    }

    public void evenPalindrome(int index, char[] str, int[]startEnd) {
        int times = str.length - index - 1 > index ? index : str.length - index - 2;
        if (times < 0) return;
        int i = 0;
        for (; i <= times; i++) {
            if (str[index - i] != str[index + 1 + i]) break;
        }
        if (i > 0) verifyLength(index - i + 1, index + i, startEnd);
    }

    public void oddPalindrome(int index, char[] str, int[]startEnd) {
        int times = str.length - index - 1 > index ? index - 1 : str.length - index - 2;
        if (times < 0) return;
        int i = 0;
        for (; i <= times; i++) {
            if (str[index - i - 1] != str[index + 1 + i]) break;
        }
        if (i > 0) verifyLength(index - i, index + i, startEnd);
    }

    public void verifyLength(int start,int end, int[] startEnd){
        if (end - start > startEnd[1] - startEnd[0]) {
            startEnd[0] = start;
            startEnd[1] = end;
        }
    }

    /**
     * 马拉车算法
     * @param s
     * @return
     */
    public String longestPalindromeManacher(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }




    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        LongestPalindrome solution = new LongestPalindrome();
//        solution.removeDuplicates(nums);
        String tmp = "ccc";
        tmp = solution.longestPalindrome(tmp);
        System.out.println(tmp);

    }



}
