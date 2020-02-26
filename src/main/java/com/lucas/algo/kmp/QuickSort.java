package com.lucas.algo.kmp;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @file: QuickSort.java
 * @author: caisil
 * @date: 2020-02-23
 */
public class QuickSort {

    public static void swap(List<Integer> nums, int a, int b) {
        Integer tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }

    public static void quickSort(List<Integer> nums, int start, int end) {


        int left = start - 1;
        int right = start;
        for (; right <= end; ++right) {
            if (nums.get(right) < 1) {
                ++left;
                swap(nums, left, right);
            }
        }

        right = left + 1;
        for (; right <= end; ++right) {
            if (nums.get(right) < 2) {
                ++left;
                swap(nums, left, right);
            }
        }
    }

    public static void quickSort2(List<Integer> nums, int start, int end) {
        int current = start;
        while (current <= end) {
            if (nums.get(current) == 0) {
                swap(nums, start, current);
                ++start;
                ++current;
            } else if (nums.get(current) == 1) {
                ++current;
            } else {
                swap(nums, current, end);
                --end;
            }
        }

    }

    public static void dutchFlag() {
        List<Integer> nums = IntStream.rangeClosed(0, 10)
                .mapToObj(x -> ThreadLocalRandom.current().nextInt(0, 3))
                .collect(Collectors.toList());

        System.out.println(nums);

        quickSort2(nums, 0, nums.size() - 1);

        System.out.println(nums);
    }

    public static void main(String[] args) {
        dutchFlag();
    }
}
