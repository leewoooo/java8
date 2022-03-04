package com.example.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureStudy2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> "Hello");

        // 2개의 future를 연결하여 연속된 작업을 진행할 경우 (두 작업이 연관관계가 있을 때)
        CompletableFuture<String> completableFuture = helloFuture.thenCompose(CompletableFutureStudy2::world);
        String result = completableFuture.get();

        System.out.println("result = " + result);

        // 2개의 future를 연결하여 연속된 작업을 진행할 경우 (두 작업이 연관관계가 없을 때)
        CompletableFuture<String> combineFuture = helloFuture.thenCombine(world("foobar"), (s, o) -> s + " " + o);
        String combineResult = combineFuture.get();
        System.out.println("combineResult = " + combineResult);

        // 예외처리
        boolean throwError = true;
        CompletableFuture<String> exceptionFuture = helloFuture
                .thenCombine(world("foobar"), (s, o) -> {
                    if (throwError) {
                        throw new IllegalStateException("generate Exception!");
                    }
                    return s + " " + o;
                })
                .exceptionally(e -> {
                    System.out.println("e.getMessage() = " + e.getMessage());
                    return "exception!";
                });

        String exceptionMessage = exceptionFuture.get();
        System.out.println("exceptionMessage = " + exceptionMessage);
    }

    private static CompletableFuture<String> world(String message) {
        return CompletableFuture.supplyAsync(() -> message + " " + "world");
    }
}
