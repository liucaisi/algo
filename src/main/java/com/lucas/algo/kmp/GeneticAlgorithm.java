package com.lucas.algo.kmp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @file: GeneticAlgorithm.java
 * @author: caisil
 * @date: 2020-02-22
 */
public class GeneticAlgorithm {

    public static void main(String[] args) {

        // 初始群体
        double[] initGroup = new double[] {13, 24, 8, 19};

        // 适应函数 y = x * x
        Function<Double, Double> adaptFunc = (a) -> a * a;

        while (true) {

            // 适应度判断
            for (double e : initGroup) {
                if (DoubleMath.fuzzyCompare(adaptFunc.apply(e), adaptFunc.apply(31.0), 0.00001) == 0) {
                    System.out.println(e);
                    break;
                }
            }

            double sum = Arrays.stream(initGroup).sum();
            // 概率分布
            List<Double> probs = Arrays.stream(initGroup).mapToObj(x -> x / sum).collect(Collectors.toList());

            // 累计概率分布
            AtomicDouble preCumProb = new AtomicDouble(0);
            List<Double> cumProbs = probs.stream().map(preCumProb::addAndGet).collect(Collectors.toList());
            TreeMap<Double, Integer> cumProbGroupIdxMap = Maps.newTreeMap();
            for (int i = 0; i < cumProbs.size(); ++i) {
                cumProbGroupIdxMap.put(cumProbs.get(i), i);
            }

            // 选择运算
            Map<Integer, LongAdder> chosenGroupFreqsMap = Maps.newConcurrentMap();
            IntStream.range(0, initGroup.length).forEach(x -> {
                double prob = ThreadLocalRandom.current().nextDouble(0, 1);
                chosenGroupFreqsMap.computeIfAbsent(cumProbGroupIdxMap.ceilingEntry(prob).getValue(),
                        k -> { LongAdder longAdder = new LongAdder(); longAdder.increment();  return longAdder; }).increment();
            });

            for (Map.Entry<Integer, LongAdder> entry : chosenGroupFreqsMap.entrySet()) {
                initGroup[entry.getKey()] *= entry.getValue().intValue();
            }

            // 交叉运算
            // s1与s2, s3与s4
            double[] crossedGroup = new double[initGroup.length];
            List<Integer> crossedCoeff = Lists.newArrayList(0x7FFFFFFC, 0x0, 0x3, 0x0);
            for (int i = 0; i < initGroup.length; ++i) {
                crossedGroup[i] = 0.0;
                for (int j = 0; j < initGroup.length; ++j) {
                    crossedGroup[i] += (crossedCoeff.get((j - i + initGroup.length) % initGroup.length) & (int) initGroup[j]);
                }
            }

            System.out.println(Arrays.toString(crossedGroup));

            // 变异运算
            double mutateProb = 0.001;

            initGroup = crossedGroup;
        }
    }
}
