package com.lucas.algo.kmp;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

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

    public static Integer kmp(String a, String b) {
        int[] next = new int[b.length()];
        int[] last = new int[26];
        for (int i = 0; i < 26; ++i) {
            last[i] = -1;
        }
        for (int i = 0; i < b.length(); ++i) {
            // 上一个相等的字符
            next[i] = last[b.charAt(i) - 'a'];
            // 更新字符位置
            last[b.charAt(i) - 'a'] = i;
        }

        int aIdx = 0;
        int bIdx = 0;

        while (aIdx < a.length() && bIdx < b.length()) {
            if (a.charAt(aIdx) == b.charAt(bIdx)) {
                ++aIdx;
                ++bIdx;
            } else {
//                aIdx
//                bIdx =
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
        // a c a c
        // a c a b
        // a b a d
        // a b a a
        // a b a c a b
        // a b a c a d
        // 0 1 0

        System.out.println(kmp(a, b));

    }
}
