package com.lucas.algo.kmp;

/**
 * @file: KMP.java
 * @author: caisil
 * @date: 2020-02-22
 */
public class KMP {

    public static Integer simple(String a, String b) {
        int aIdx = 0;
        int bIdx = 0;

        while (aIdx < a.length() && bIdx < b.length()) {
            if (a.charAt(aIdx) == b.charAt(bIdx)) {
                ++aIdx;
                ++bIdx;
            } else {
                aIdx -= (bIdx - 1);
                bIdx = 0;
            }
        }

        if (bIdx == b.length()) {
            return aIdx - bIdx;
        }

        return -1;
    }

    public static void main(String[] args) {
        String a = "abcabaabcaabac";
        String b = "abaa";

//        System.out.println(simple(a, b));

    }
}
