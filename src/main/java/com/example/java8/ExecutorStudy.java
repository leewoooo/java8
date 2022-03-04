package com.example.java8;

import java.util.concurrent.*;

public class ExecutorStudy {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(getRunnable());

        // shutdown()을 명시적으로 해줘야 한다 하지 않으면 종료되지 않음.
        // 일반적으로 graceful shutdown()을 제공한다. 즉 현재 ExecutorService를 통해 돌고 있는 Thread의 종료를 보장한 후 shotDown을 한다는 것.
        // Thread의 종료를 보장하지 않고 service를 종료하고자 한다면 shutdownNow()를 사용
        executorService.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable(), 1, 2, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

    private static Runnable getRunnable() {
        return () -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        };
    }
}
