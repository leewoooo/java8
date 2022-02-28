package com.example.java8;

// java standard library -> import가 따로 없음. (선언해줌으로 interface를 더 견고하게 관리 가능)
@FunctionalInterface
public interface RunSomething {
    void doIt();
}
