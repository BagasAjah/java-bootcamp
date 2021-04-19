package com.mitrais.model;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedMonitor { // extends RecursiveTask<Integer> {
    private Object monitor;
    private volatile int counter = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public SharedMonitor(Object monitor) {
        if (monitor == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        this.monitor = monitor;
    }

    public int incCounterAtomic() {
//        synchronized (this.monitor) {
//            return this.incCounter();
//        }
        final int currentCounter = atomicInteger.incrementAndGet();
        System.out.println("Counter Ins : " + currentCounter);
        return currentCounter;
    }

    public int incCounterSync() {
        synchronized (this.monitor) {
            return this.incCounter();
        }
    }

    public int incCounter() {
//        synchronized (this.monitor){
            this.counter++;
            System.out.println("Counter Ins : " + this.counter);
//        }
        return this.counter;
    }

//    @Override
//    protected Integer compute() {
//        incCounterSync();
//        return this.counter;
//    }
}
