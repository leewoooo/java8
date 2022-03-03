package com.example.java8;

public class BarImpl implements Bar{

    private String name;

    public BarImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    // interface에서 정의하는 Default method를 구현하는 class에서 재 정의가 가능하다.
//    @Override
//    public void printNameUpperCase() {
//        System.out.println("name.toUpperCase() = " + name.toUpperCase());
//    }

    public static void main(String[] args) {
        Bar bar = new BarImpl("leewoooo");

        BarImpl barImpl = new BarImpl("foobar");
        barImpl.printNameUpperCase();

        System.out.println("bar.getName() = " + bar.getName()); // output: leewoooo
        bar.printNameUpperCase(); // output: LEEWOOOO

        Bar.printBar(); // output: Bar
    }
}
