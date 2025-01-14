package com.personal.javastudy.service.serviceImpl.concurrent_programming;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class SumTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
}
