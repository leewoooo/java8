package com.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureStudy {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<String> foobar = executorService.submit(getCallable("foobar"));

        System.out.println("foobar.isCancelled() = " + foobar.isCancelled());
        System.out.println("foobar.isDone() = " + foobar.isDone());

        // invokeAll과 같은 경우는 컬렉션에 들어있는 모든 Callable이 끝날 때 까지 기다리게 된다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(getCallable("foobar"), getCallable("leewoooo")));
        for (Future<String> future : futures) {
            String s = future.get();
            System.out.println("s = " + s);
        }

        // invokeAny와 같은 경우는 컬렉션에 들어있는 Callable중 가장 먼저 끝나는 Callable의 결과를 바로 return한다.
        String s = executorService.invokeAny(Arrays.asList(getCallable("foobar"), getCallable("leewoooo")));
        System.out.println("s = " + s);

        executorService.shutdown();
    }

    private static Callable<String> getCallable(String message) {
        return () -> message;
    }
}
