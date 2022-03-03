package com.example.java8;

import java.util.Locale;

// static method와 default method가 있다고 하더라도 추상 method가 1개면 FunctionalInterface이다.
@FunctionalInterface
public interface Bar {

    String getName();

    // 현재 Bar interface를 구현하는 모든 class들은 해당 method를 사용할 수 있음.
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    // 현재 interface의 타입으로 어느 곳에서든 호출 할 수 있는 static method를 정의할 수 있다.
    static void printBar(){
        System.out.println("Bar");
    }
}
