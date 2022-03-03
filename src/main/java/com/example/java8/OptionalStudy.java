package com.example.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalStudy {

    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "rest api development", true));

        Optional<OnlineClass> spring = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().contains("spring"))
                .findAny();

        boolean present = spring.isPresent();
        System.out.println("present = " + present);

        // 값 가져오기 (값이 존재 할 때)
        OnlineClass springClass = spring.get();
        System.out.println("springClass.getTitle() = " + springClass.getTitle());


        Optional<OnlineClass> jpa = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().contains("jpa"))
                .findAny();

        // 값 가져오기 (값이 없을 때) -> 값을 가져올 때 확인하지 않고 바로 get()을 이용하여 가져오는 것을 지양한다.
        try {
            OnlineClass jpaClass = jpa.get(); // runtimeException 발생 -> NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("값이 없습니다.");
        }

        boolean isJpaPresent = jpa.isPresent();
        System.out.println("isJpaPresent = " + isJpaPresent);

        boolean empty = jpa.isEmpty(); // java11 부터 isEmpty()를 제공해준다.
        System.out.println("empty = " + empty);

        // 값을 가져올 떄 get보다는 Optional에서 따로 제공해주는 API를 이용
        
        // 1. ifPresent() -> Consumer를 받아서 내부적으로 사용하고 끝
        spring.ifPresent(onlineClass -> {
            System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle());
        });

        // 2. orElse -> 만약 optional이 null일 경우 실행할 로직을 정의, 실행할 로직에서는 Optional에서 사용한 제네릭 타입의 인스턴스를 return 해야함.
        // 중요한 것은 Optional이 null이 아니더라도 orElse()에 넣어준 로직이 강제적으로 실행된다.
        spring.orElse(createOnlineClass());

        // 3. orElseGet -> 파라미터로 Supplier를 받는다. orElse와 다른점은 Optional이 null이 아니면 Supplier가 실행되지 않는다.
        // 정리 -> 이미 만들어져 있는 것을 return할 때는 orElse(), 동적으로 처리해야 한다면 orElseGet()
        spring.orElseGet(OptionalStudy::createOnlineClass);

        // 4. orElseThrow -> Optional의 값으 null일 경우 사용자 정의 Exception을 return할 수 있다. -> Default값은 NoSuchElementException
        spring.orElseThrow(IllegalArgumentException::new);


        // filter와 map을 사용할 수 있다. return 값으로는 Optional<>로 return 된다.
        Optional<OnlineClass> onlineClass = spring.filter(OnlineClass::isClosed);
        onlineClass.ifPresent(onlineClass1 -> System.out.println("onlineClass1.getTitle() = " + onlineClass1.getTitle()));

        Optional<Boolean> jpaClass = spring.map(onlineClass1 -> onlineClass1.getTitle().startsWith("jpa"));
        jpaClass.ifPresent(System.out::println);

        // 만약 Optional<Optional<>>의 타입을 핸들링 해야할 때는 Optional.flatMap()을 이용한다.
        Optional<OnlineClass> optionalSpringClass = springClasses.stream()
                .filter(onlineClass1 -> onlineClass1.getTitle().startsWith("spring"))
                .findAny();
        Optional<Optional<Progress>> progress = optionalSpringClass.map(OnlineClass::getProgress);

        // 내부적으로 Optional을 한번 벗겨준다.
        Optional<Progress> progress1 = optionalSpringClass.flatMap(OnlineClass::getProgress);
    }

    public static OnlineClass createOnlineClass(){
        System.out.println("create new Class");
        return new OnlineClass(10, "foobar", false);
    }
}
