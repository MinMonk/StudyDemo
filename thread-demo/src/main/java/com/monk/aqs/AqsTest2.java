package com.monk.aqs;

import java.util.concurrent.TimeUnit;

public class AqsTest2 {

    private MyLock lock = new MyLock();

    private void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    private void b() {
        /*try {
            if(!lock.tryLock(1, TimeUnit.SECONDS)){
                System.out.println("1111");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        AqsTest2 test = new AqsTest2();
        new Thread(() -> {
            test.a();
        }).start();
    }
}
