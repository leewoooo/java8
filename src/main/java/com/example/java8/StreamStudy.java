package com.example.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStudy {

    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        // TODO
        List<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .collect(Collectors.toList());

        springClass.forEach(onlineClass -> System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle()));

        System.out.println("close 되지 않은 수업");
        // TODO
        Stream<OnlineClass> notClosedClass = springClass.stream()
//                .filter(onlineClass -> !onlineClass.isClosed());
//                .filter(OnlineClass::isClosed); // 여기서는 !를 이용하여 부정에 관련된 로직을 할 수 없기 때문에 아래와 같이 Predicate.not() 을 이용하여 처리, 메소드 레퍼런스
                .filter(Predicate.not(OnlineClass::isClosed));

        notClosedClass.forEach(onlineClass -> System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        List<String> titleList = springClass.stream()
                .map(onlineClass -> onlineClass.getTitle())
                .collect(Collectors.toList());
        titleList.forEach(System.out::println);


        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        keesunEvents.stream()
                .flatMap(Collection::stream) // flatMap을 이용하여 List<List<OnlineClass>>를 -> List<OnlineClass>로 변경 후 해당 List에서 Stream을 얻은 것이다.
                .forEach(onlineClass -> System.out.println("onlineClass.getId() = " + onlineClass.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // TODO
        boolean isContained = javaClasses
                .stream()
                .anyMatch(onlineClass -> onlineClass.getTitle().contains("Test"));

        System.out.println("isContained = " + isContained);
    }
}
