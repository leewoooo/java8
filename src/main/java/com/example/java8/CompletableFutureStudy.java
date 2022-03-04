package com.example.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureStudy {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 기본적으로 Thread pool은 java7에서 들어온 ForkJoin Pool을 이용한다.
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("foobar");

        String value = completableFuture.get();
        System.out.println("value = " + value);

        // return 타입이 없을 경우 runAsync()를 이용
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("\"foobar\" = " + "foobar");
        });

        voidCompletableFuture.get();

        // return 타입이 있는 경우 supplyAsync()를 이용
        CompletableFuture<String> supplyAsuFuture = CompletableFuture.supplyAsync(() -> {
            return "leewoooo";
        });

        String supplyValue = supplyAsuFuture.get();
        System.out.println("supplyValue = " + supplyValue);

        // callBack을 부여할 수 있음. callback을 이용하여 return이 있을 때 thenApply()
        CompletableFuture<String> callBackFuture = CompletableFuture.supplyAsync(() -> {
            return "leewoooo";
        }).thenApply(s -> s.toUpperCase());

        String upperCase = callBackFuture.get();
        System.out.println("upperCase = " + upperCase);

        // callBack을 부여할 수 있음. callback을 이용하여 return이 없을 때 thenAccept()
        CompletableFuture<Void> callBackAccept = CompletableFuture.supplyAsync(() -> {
            return "leewoooo";
        }).thenAccept(System.out::println);

        callBackAccept.get();

        // callBack을 부여할 수 있음. callback을 이용하여 Runnable만 실생할 경우 thenRun()
        CompletableFuture<Void> callBackRun = CompletableFuture.supplyAsync(() -> {
            return "leewoooo";
        }).thenRun(() -> System.out.println("Runnable"));

        callBackRun.get();
    }
}
