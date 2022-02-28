package com.example.java8;

import java.util.Arrays;
import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // interface에 추상 메소드가 하나인 경우 java8에서 부터 지원하는 lambda를 통해 간단하게 축약하여 사용할 수 있음.
        // 위의 코드와 내부적으로 동일하다.
        RunSomething runSomethingLambda = () -> System.out.println("Hello Lambda");

        // 구현하는 추상 메소드가 1줄이 아닌 경우 method 블럭을 이용하여 정의가 가능하다.
        RunSomething runSomethingLambda2 = () -> {
            System.out.println("Hello");
            System.out.println("Hello2");
        };

        runSomething.doIt();
        runSomethingLambda.doIt();
        runSomethingLambda2.doIt();


        PureFunction pureFunction = (int number) -> number + 10;
        System.out.println("pureFunction.addTen(1) = " + pureFunction.addTen(1));
        System.out.println("pureFunction.addTen(1) = " + pureFunction.addTen(1));
        System.out.println("pureFunction.addTen(1) = " + pureFunction.addTen(1));
        System.out.println("pureFunction.addTen(1) = " + pureFunction.addTen(1));
        System.out.println("pureFunction.addTen(1) = " + pureFunction.addTen(1));

        // 1. Function<T,R>
        Function<Integer, Integer> plus10 = (Integer number) -> number + 10;
        System.out.println("functionOne.apply(10) = " + plus10.apply(10));
        System.out.println("functionOne.apply(10) = " + plus10.apply(10));

        // 함수 조합
        Function<Integer, Integer> multiply2 = (Integer number) -> number * 2;

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println("multiply2AndPlus10.apply(10) = " + multiply2AndPlus10.apply(10)); // -> (10 * 2) + 10

        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println("plus10AndMultiply2.apply(2) = " + plus10AndMultiply2.apply(2)); // -> (2 + 10) * 2

        // 2. BiFunction<T,U,R>
        BiFunction<Integer, Integer, Integer> sum = (Integer i, Integer j) -> i + j;
        System.out.println("sum.apply(10,20) = " + sum.apply(10, 20)); // 10 + 20

        // 3. Consumer<T>
        Consumer<Integer> printNumber = (Integer number) -> System.out.println("n = " + number);
        printNumber.accept(10);

        // 4. Supplier<T> // 내가 가져올 값의 타입을 T로 정의
        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10.get() = " + get10.get());

        // 5. Predicate<T> // 하나의 인자를 받아 boolean을 return한다.
        Predicate<String> startWithleewooo = (String keyword) -> keyword.startsWith("leewoo");
        System.out.println("startWithleewooo.test(\"leewooo\") = " + startWithleewooo.test("leewooo"));

        Predicate<Integer> isEven = (Integer number) -> number % 2 == 0;
        Predicate<Integer> isOverZero = (Integer number) -> number > 0;
        System.out.println("isEven.and(isOverZero).test(10) = " + isEven.and(isOverZero).test(10));

        // 6. UnaryOperator -> Function<T,R>의 특수한 경우로 T와 R의 타입이 동일할 때 사용한다. = Function<T,T>
        UnaryOperator<Integer> plus2 = (Integer number) -> number + 2;
        System.out.println("plus2.apply(10) = " + plus2.apply(10));

        // 7. BinaryOperator -> BiFunction<T,U,R>의 특수한 경우로 T,U,R의 타입이 동일할 때 사용한다. = BiFunction<T,T,T>
        BinaryOperator<Integer> sum2 = (Integer a, Integer b) -> a + b;
        System.out.println("sum2.apply(2,5) = " + sum2.apply(2, 5));

        // method reference

        // 1. static
        UnaryOperator<String> hi = Greeting::hi; //method를 참조한 것이지 이 시점에서 실행이 되는 것이 아니다.
        hi.apply("leewoooo"); // apply되는 시점에 참조하고 있던 method를 excute

        // 2. instance
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        hello.apply("leewoooo");

        // 3. 생성자
        // no Arg
        Supplier<Greeting> generateGreeting = Greeting::new;
        generateGreeting.get(); // get을 호출하는 시점에 Greeting instance가 생성된다.

        Function<String, Greeting> generateGreetingWithArg = Greeting::new;
        Greeting greeting1 = generateGreetingWithArg.apply("leewoooo");
        String name = greeting1.getName();// output = leewooooo
        System.out.println("name = " + name);

        // 4. 임의 객체의 인스턴스 method참조
        String[] names = {"foo", "bar"};
        Arrays.sort(names,String::compareToIgnoreCase); // 배열안에 있는 String값들의 인스턴스 method를 이용함.
    }


    public void run() {

        // effected final  -> final이라는 키워드는 붙지 않았지만 변경되지 않고 사실상 final처럼 사용되는 변수를 말한다.
        // local class, anonymous class, nested class, lambda에서 현재 변수를 가져다 사용하려면 effected final 혹은 final이여야 한다.
        final int baseNumber = 10;

        // local class
        class LocalClass {
            // run이라는 method안에 있는 baseNumber가 쉐도잉 되어 현재 LocalClass에서 정의한 baseNumber에 가려진다.
            // 즉 run method의 스코프와 LocalClass의 스코프가 다르다는 것이며 LocalClass 안에서 baseNumber는 캡쳐가 일어나지 않고 내부에서
            // 다시 정의 된 baseNumber를 사용하게 된다.
            int baseNumber = 11;

            public void printBaseNumber() {
                System.out.println("baseNumber = " + baseNumber);
            }
        }

        // anonymous class
        IntConsumer anonymous = new IntConsumer() {

            // run이라는 method안에 있는 baseNumber가 쉐도잉 되어 현재 anonymous class에서 정의한 baseNumber에 가려진다.
            // 즉 run method의 스코프와 anonymous class의 스코프가 다르다는 것이며 anonymous class 안에서 baseNumber는 캡쳐가 일어나지 않고 내부에서
            // 다시 정의 된 baseNumber를 사용하게 된다.
            int baseNumber = 11;

            @Override
            public void accept(int value) {
                System.out.println("value+baseNumber = " + value + baseNumber);
            }
        };

        // lambda
        IntConsumer lambda = (i) -> {
            // compile time에서 error가 발생한다.
            // 즉 lambda는 local class와 anonymous class와 다른게 현재 lambda를 덮고 있는 method와 스코프가 동일하다.
            // 그렇기 때문에 baseNumber를 재정의 할 수 없다. (쉐도잉이 되지 않는다.)
//            int baseNumber = 11;
            System.out.println("i+baseNumber = " + i + baseNumber);
        };


    }
}
