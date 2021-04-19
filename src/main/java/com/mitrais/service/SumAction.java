package com.mitrais.service;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class SumAction extends RecursiveTask<Long> {

    private static final int SEQUENTIAL_THRESHOLD = 5;

    private List<Long> data;

    public SumAction(List<Long> data) {
        this.data = data;
    }

    @Override
    protected Long compute() {
        System.out.println(data.size());
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
            long sum = computeSumDirectly();
            System.out.format("Sum of %s: %d\n", data.toString(), sum);
            return sum;
        } else { // recursive case
            // Calcuate new range
            int mid = data.size() / 2;
            SumAction firstSubtask =
                    new SumAction(data.subList(0, mid));
            SumAction secondSubtask =
                    new SumAction(data.subList(mid, data.size()));

            firstSubtask.fork(); // queue the first task
//            secondSubtask.compute(); // compute the second task
//            firstSubtask.join(); // wait for the first task result
            return secondSubtask.compute()
                    +
                    firstSubtask.join();
            // Or simply call
//            invokeAll(firstSubtask, secondSubtask);
        }
    }

    private long computeSumDirectly() {
        long sum = 0;
        for (Long l : data) {
            sum += l;
        }
        return sum;
    }
}