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

    public static void move(Integer moveSize, String a, String b, String c) {
        if (1 == moveSize) {
            System.out.printf("%s --> %s\n", a, c);;
            return;
        }

        move(moveSize - 1, a, c, b);
        System.out.printf("%s --> %s\n", a, c);
        move(moveSize - 1, b, a, c);
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

        move(3, "A", "B", "C");

    }
}
