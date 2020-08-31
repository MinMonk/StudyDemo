package com.monk.aqs;

import java.util.concurrent.TimeUnit;

public class AqsTest {

    private MyLock lock = new MyLock();

    private int a = 0;

    private int increase(){
        return a++;
    }

    private int increaseAndLock() {
        try {
            lock.lock();
            return a++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AqsTest test = new AqsTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(test.increase());
            }).start();
        }
    }
}
