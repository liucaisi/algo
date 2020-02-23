package com.lucas.algo.kmp;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * @file: TowerOfHanoi.java
 * @author: caisil
 * @date: 2020-02-22
 */
public class TowerOfHanoi {

    public static void move(Stack<Integer> a, Integer moveSize, Stack<Integer> b, Stack<Integer> c) {
        if (1 == moveSize) {
            c.push(a.pop());
            return;
        }

        move(a, moveSize - 1, c, b);
        c.push(a.pop());
        move(b, moveSize - 1, a, c);
    }

    public static void main(String[] args) {
        Stack<Integer> a = new Stack<>();
        Stack<Integer> b = new Stack<>();
        Stack<Integer> c = new Stack<>();


        for (int i = 3; i >= 1; --i) {
            a.push(i);
        }

        System.out.println(a);

        move(a, a.size(), b, c);

        System.out.println(c);
    }
}
