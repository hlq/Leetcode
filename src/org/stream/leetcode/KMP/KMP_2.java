package org.stream.leetcode.KMP;

public class KMP_2 {

    public int[] getNext(String ts) {
        char[] p = ts.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int i = 0, q = -1;
        while (i < p.length - 1) {
            if (q == -1 || p[i] == p[q]) {
                if (p[++i] == p[++q]) {
                    next[i] = next[q];
                } else {
                    next[i] = q;
                }
            } else {
                q = next[q];
            }
        }
        return next;
    }


    public int[] getNext2(char[]p){
        int[]next = new int[p.length];
        next[0] = -1;
        int q = -1, k = 0;
        while (k < p.length -1){
            if(q == -1 || p[k] == p[q]){
                if (p[++k] == p[++q]){
                    next[k] = next[q];
                } else {
                    next[k] = q;
                }
            } else {
                q = next[q];
            }
        }
        return next;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        char[] pc = haystack.toCharArray();
        char[] tc = needle.toCharArray();
        int[] next = getNext(needle);
        int p = 0, t = 0;
        while (p < pc.length && t < tc.length) {
            if (t == -1 || pc[p] == tc[t]) {
                p++;
                t++;
            } else {
                t = next[t];
            }
        }
        if (t == tc.length) {
            return p - t;
        }
        return -1;
    }

    public int match(char[]sc, char[] fc){
        int[] next = getNext2(fc);
        int s = 0, f = 0;
        while (f < fc.length && s < sc.length){
            if (f == -1 || fc[f] == sc[s]){
                f++;
                s++;
            } else {
                f = next[f];
            }
        }

        if (f == fc.length) return s - f;
        return -1;
    }
    public int indexOf(String haystack, String  needle){
        char[] sc = haystack.toCharArray();
        char[] fc = needle.toCharArray();
        return match(sc, fc);
    }

    public int lastIndexOf(String haystack, String  needle){
        char[] sc = reverse(haystack.toCharArray());
        char[] fc = reverse(needle.toCharArray());
        int t = match(sc, fc);
        if (t == -1) return t;
        return sc.length - fc.length - t;
    }

    public char[] reverse(char[]s){
        char tmp = 0;
        for (int i = 0; i < s.length/2; i++){
            int t = s.length-1-i;
            tmp = s[i];
            s[i] = s[t];
            s[t] = tmp;
        }
        return s;
    }

    /**
     * KMP 算法 查找包含字符串的位置
     * @param args
     */


    public static void main(String[] args) {
        KMP_2 k = new KMP_2();
//        int i = k.indexOf("abaaaacaaababqaab", "aaacaaab");
        int i = k.indexOf("abaaaacaaababqaab", "aa");
        int x = k.lastIndexOf("abaaaacaaababqaab", "aa");
        System.out.println();
    }
}
