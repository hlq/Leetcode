package org.stream.leetcode.KMP;

public class KMP_1 {
    private int[][] dp;
    private String pat;

    public KMP_1() {
    }

    public KMP_1(String pat) {
        this.pat = pat;
        // 通过 pat 构建 dp 数组
        // 需要 O(M) 时间
    }

    public int search(String txt) {
        // 借助 dp 数组去匹配 txt
        // 需要 O(N) 时间
        return 0;
    }

    public int bf(String ts, String ps) {
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0;
        int j = 0;

        while (i < t.length && j < p.length) {
            if (t[i] == p[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }


    /**
     * @param ts 主串
     * @param ps 模式串
     * @return
     *
     * abcabacwababqwe
     *
     *    abacwababq
     * x0x10x0x32
     */
    public int KMP(String ts, String ps) {
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0; //ts index
        int j = 0; // ps index
        int[] next = getNext(ps);
        int[] next1 = getKNext(ps);
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0, k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
    /**
     *  x0x10x0x32
     *  abacwababq
     *  x001001232
     *
     *  x0123001
     *  aaaabcac
     *  xxxx30x1
     *
     *  x000120
     *  akdakkd
     *  x00x020
     *  正常情况下akda a下面是0,但是a又等于前面的a,就吧a的x赋值在下面
     *  同理akdak在正常情况下是1,但是又等于前面的k,赋值为0
     *  akk在正常情况下是2,又不等于akd的d,则赋值为2
     *
     */
    public int[] getKNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0, k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k])
                next[++j] = ++k;
            else
                k = next[k];
        }
        return next;
    }

    public static void main(String[] args) {
        KMP_1 k = new KMP_1();
//        int b = k.KMP("bbcababaabaaaabcacbbc", "aaaabcac");
        int b = k.KMP("abcabacwababqwe", "abdabbd");
        System.out.println();
    }


}
