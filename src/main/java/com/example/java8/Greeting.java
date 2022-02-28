package com.example.java8;

public class Greeting {

    private String name;

    // 기본 생성자
    public Greeting() {
    }

    // argument constructor
    public Greeting(String name) {
        this.name = name;
    }

    // instance method
    public String hello(String name) {
        return "hello " + name;
    }

    // static method
    public static String hi(String name) {
        return "hi " + name;
    }

    public String getName() {
        return name;
    }
}
