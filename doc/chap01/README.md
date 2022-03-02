함수형 인터페이스, 람다 표현식
===

## 함수형 인터페이스

함수형 인터페이스는 **interface가 추상 method를 1개만 가진 것을 이야기한다.**

```java
public interface DoSomething{
    void doIt(); // == abstract void doIt();
}
```

위와 같이 1개의 추상 mehtod를 가지고 있다. `abstract`는 생략이 가능하다.

함수형 인터페이스를 조금 더 견고하게 관리를 할 수 있도록 java에서 제공해주는 어노테이션이 존재한다. 

`@FunctionalInterface`를 지원해주는데 java standard 라이브러리 이기 때문에 별도의 import는 존재하지 않으며 해당 어노테이셔을 사용하여 정의하였을 때
해당 인터페이스가 함수형 인터페이스 형식에 위반되면 **error가 발생하기 때문에 유지 보수 측면에서 유용하다.**

```
// 발생하는 error
Multiple non-overriding abstract methods found
```

## 람다 표현식

람다 표현식은 **함수형 인터페이스를 인스턴스를 만드는 방법으로 사용할 수 있다.**

람다 표현식을 사용하지 않으면 함수형 인터페이스를 아래와 같이 구현하여 사용해야 한다. (익명 내부 클래스를 사용해 구현)

```java
public void example(){
    // anonymous class
    DoSomething do = new DoSomething(){
        @override
        public void doIt(){
          // 해당 method 로직을 구현  
        };
    };
    
    // 생성된 do instance를 이용하여 코드를 작성해나간다.
}
```

하지만 람다 표현식을 사용하면 아래와 같이 코드도 짧아지고 간단하게 함수형 인터페이스의 인스턴스를 생성할 수 있다.

```java
public void example(){
    DoSomething do1 = () -> // 이곳에 doIt()에 들어갈 로직을 작성;

    DoSomething do2 = () -> {
        // 이곳에 doIt()에 들어갈 로직을 작성;
    };

// 생성된 do instance를 이용하여 코드를 작성해나간다.
}
```

`do1`은 **구현한 로직이 1줄일 경우** method body를 이용하지 않은 람다 표현식이며 `do2`는 **구현 method의 길이가 길어질 경우
method body를 이용하여** 함수형 인터페이스의 인스턴스를 생성한 것이다.

## java에서 제공하는 함수형 인터페이스

java에서도 이미 함수형 인터페이스를 API로 제공하고 있다. 

[java.util.function 패키지](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)를 참조하면 확인할 수 있다.

## 스코프

### 데이터 캡처

Local class, Anonymous class, 람다 표현식은 외부의 변수를 **캡처하여 사용할 수 있다.**

```java
public void run(){
    
    final int baseNumber = 10;
    
    // local class
    class LocalClass {
        public void printBaseNumber() {
            System.out.println("baseNumber = " + baseNumber);
        }
    };

    // anonymous class
    IntConsumer anonymous = new IntConsumer() {
        @Override
        public void accept(int value) {
            System.out.println("value+baseNumber = " + value + baseNumber);
        }
    };

    // lambda
    IntConsumer lambda = (i) -> {
        System.out.println("i+baseNumber = " + i + baseNumber);
    };
}
```

위의 코드와 같이 각각의 Local class, Anonymous class, 람다 표현식은 `run` method에 있는 `baseNumber`를 참조할 수 있다.

하지만 여기서 **Local class, Anonymous class와 람다표현식의 차이점이 있다.**

### 쉐도잉

**Local class, Anonymous class와 람다표현식** 전부 데이터 캡처가 가능하여 위의 예제 코드 처럼 `baseNumber`를 사용할 수 있었다. 

하지만 **Local class, Anonymous class와 람다표현식** 안에 동일한 `baseNumber`가 있다면 어떻게 될까?


Local class, Anonymous class는 별도의 스코프를 가지기 떄문에 **쉐도잉**이 일어난다. 쉐도잉을 간단하게 이야기 하자면 
외부 스코프의 변수와 class안에 있는 변수의 변수명이 겹칠 경우 **외부 스코프 변수가 가려진다는 것이다.**

**하지만 람다 표현식의 경우는 다르다**

Local class, Anonymous class는 쉐도잉이 일어나지만 **람다 표현식과 같은 경우는 외부와 동일한 스코프를 가지기 때문에
쉐도잉이 일어나지 않는다. 즉 외부 스코프의 변수명과 같은 변수명을 가진 local 변수를 사용할 수 없다는 것이다.**

### 정리

<img src="https://user-images.githubusercontent.com/74294325/156303614-3d0fb944-b345-4eed-9d3d-a7661026ce4c.png">

`run`이라는 method안에 **Local class, Anonymous class와 람다표현식**이 존재한다.

1. Local class에서는 외부의 `baseNumber`에 대한 데이터 캡처가 가능하고, 내부의 스코프에서 `baseNumber`와 동일한 변수명을 가진 변수 생성 가능

2. Anonymous class는 외부의 `baseNumber`에 대한 데이터 캡처가 가능하고, 내부의 스코프에서 `baseNumber`와 동일한 변수명을 가진 변수 생성 가능

3. 람다표현식은 `run` method와 동일한 스코프를 가지기 때문에 `baseNumber`에 대한 데이터 캡처는 가능하지만 `baseNumber`와 **동일한 변수명을 가진 변수 생성 불가능**

## 메소드 래퍼런스

함수형 인터페이스를 구현할 때 주로 람다 표현식을 이용하게 되는데 람다 표현식 대신 **기존 인스턴스의 method나 생성자를 통해 구현하고자 할 때 사용된다.**

|     방법      |             문법              |
|:-----------:|:---------------------------:|
|   스태틱 메소드   |        타입 :: 스태틱 메소드        |
| 특정 인스턴스 메소드 | 타입 :: 인스턴스.method |
| 임의 인스턴스 메소드 | 타입 :: 인스턴스.method |
|     생성자     |     타입 :: new     |


```java
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
```

위와 같이 메소드 래퍼런스로 함수형 인터페이스를 구현하였을 떄 **해당 함수형 인터페이스의 구현체가 정의한 method를 참조하는 것 뿐이지 실행을 한 것은 아니다.**