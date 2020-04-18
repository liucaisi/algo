package com.lucas.algo.kmp;

import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;

import java.math.RoundingMode;
import java.util.List;

/**
 * Range Minimum Query
 * 这是一个离线算法, 它做的是预处理工作。
 *
 * @file: RMQ.java
 * @author: caisil
 * @date: 2020-04-18
 */
public class RMQ {

    private static Integer rmq(List<Integer> a, int s, int e) {
        Integer mNum = Integer.MAX_VALUE;

        for (int i = s; i <= e; ++i) {
            if (a.get(i) < mNum) {
                mNum = a.get(i);
            }
        }

        return mNum;
    }

    private static List<List<Integer>> rmq2(List<Integer> a) {
        List<List<Integer>> memo = Lists.newArrayList();
        for (int i = 0; i < a.size(); ++i) {
            memo.add(Lists.newArrayList());
            for (int j = 0; j < a.size(); ++j) {
                memo.get(i).add(i != j ? 0 : i);
            }
        }

        for (int i = 0; i < memo.size(); ++i) {
            for (int j = i + 1; j < memo.size(); ++j) {
                if (a.get(memo.get(i).get(j - 1)) < a.get(j)) {
                    memo.get(i).set(j, memo.get(i).get(j - 1));
                } else {
                    memo.get(i).set(j, j);
                }
            }
        }

        return memo;
    }

    private static List<List<Integer>> rmq3(List<Integer> a) {
        List<List<Integer>> memo = Lists.newArrayList();
        int memoRowSize = DoubleMath.roundToInt(DoubleMath.log2(a.size()), RoundingMode.DOWN);
        for (int i = 0; i < a.size(); ++i) {
            memo.add(Lists.newArrayListWithCapacity(memoRowSize));
            for (int j = 0; j < memoRowSize; ++j) {
                memo.get(i).add(0 == j ? i : -1);
            }
        }

        // 如果不是2的整数幂怎么办?
        // 比如说，a.size()为3, 当要查询[0, 2]之间的最大值是，可转换为:
        // 1. k = log(3) = 1
        // 2. min(memo.get(0).get(k), memo.get(2-2^k-1).get(2))

        // 长度为2^j
        for (int j = 1; 1 << j <= a.size(); ++j) {
            // 起点
            for (int i = 0; i + (1 << j) - 1 < a.size(); ++i) {
                // 前半段[i, 2^(j-1)] 与 后半段[i+2^(j-1), 2^(j-1)]
                // 整段[i, 2^j]
                if (a.get(memo.get(i).get(j - 1)) < a.get(memo.get(i + (1 << (j - 1))).get(j - 1))) {
                    memo.get(i).set(j, memo.get(i).get(j - 1));
                } else {
                    memo.get(i).set(j, memo.get(i + (1 << j - 1)).get(j - 1));
                }
            }
        }

        return memo;
    }

    public static void main(String[] args) {
       List<Integer> a = Lists.newArrayList(2, 4, 3);
       // , 1, 6, 7, 8, 9, 1, 7)

       // 1. 基本方法
//        System.out.println(rmq(a, 2, 7));

        // 2. 求所有区间的算法
//        List<List<Integer>> rmq2Memo = rmq2(a);
//
//        for (List<Integer> r : rmq2Memo) {
//            System.out.println(r);
//        }

        // 3. 稀疏矩阵算法

        List<List<Integer>> rmq3Memo = rmq3(a);
        for (List<Integer> r : rmq3Memo) {
            System.out.println(r);
        }


    }
}
