package com.example.java8;

public class ThreadStudy {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            // 기다리는 도중 interrupted가 발생할 수 있음.
            System.out.println("myThread interrupted!");
            return;
        }

        // java8 이후 부터는 Runnable 구현을 lambda를 이용하여 가능.
        Thread thread = new Thread(() -> System.out.println("Runable: " + Thread.currentThread().getName()));
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            // 기다리는 도중 interrupted가 발생할 수 있음.
            System.out.println("thread interrupted!");
            return;
        }

        System.out.println("Hello: " + Thread.currentThread().getName());
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
