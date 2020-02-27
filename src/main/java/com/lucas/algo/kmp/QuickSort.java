package com.lucas.algo.kmp;

import com.google.common.collect.Lists;

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

    public static void quickSort3Num(List<Integer> nums, int start, int end) {


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

    public static void quickSort2NumOpt(List<Integer> nums, int start, int end) {
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

        quickSort2NumOpt(nums, 0, nums.size() - 1);

        System.out.println(nums);
    }

    public static int hoarePartition(List<Integer> nums, int start, int end) {
        int v = nums.get(end);
        int pivot = end;
        --start;
        while (true) {
            do {
                ++start;
            } while (start < end && nums.get(start) < v);

            do {
                --end;
            } while (start < end && nums.get(end) > v); // 采用do...while是为了防止重复元素时，出现死循环，如，10 10 2 10

            if (start < end) {
                swap(nums, start, end);
            } else {
                swap(nums, pivot, start);
                System.out.println(nums);
                return start; // 返回end而不是start，原因是end对应的元素一定小于等于pivot元素。而start只有在pivot是末尾元素时可以使用
            }
        }
    }

    public static void quickSort(List<Integer> nums, int start, int end) {
        if (start < end) {
            int pivot = hoarePartition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
    }
    public static void main(String[] args) {
//        dutchFlag();

        List<Integer> nums = Lists.newArrayList(10, 5, 3, 1);
        quickSort(nums, 0, nums.size() - 1);
        System.out.println(nums);
    }
}
