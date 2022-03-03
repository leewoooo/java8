인터페이스의 변화
===

java8로 넘어오면서 인터페이스의 형태가 변하게 되었다. 이전에는 인터페이스에서 제공할 수 있는 method라고는 `abstract method` 뿐이였다.

**하지만**

java8로 넘어오면서 interface에서도 **기본 메소드(Default), 스태틱 메소드(static)을 제공할 수 있게 되었다.**

기본 메소드(Default), 스태틱 메소드(static)를 가지고 있더라도 **추상 method의 갯수가 1개라면 `@FunctionalInterface`이다.**

## 기본 메소드 (Default)

interface 내부에서 `Default`라는 keyword를 이용하여 method를 정의할 수 있다.

기본 method는 **해당 interface를 구현한 모든 class의 인스턴스를 통해 사용이 가능하다.**

```java
// static method와 default method가 있다고 하더라도 추상 method가 1개면 FunctionalInterface이다.
@FunctionalInterface
public interface Bar {

    String getName();

    // 현재 Bar interface를 구현하는 모든 class들은 해당 method를 사용할 수 있음.
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }
}

//...
    
public static void main(String[] args) {
    Bar bar = new BarImpl("leewoooo");

    System.out.println("bar.getName() = " + bar.getName()); // output: leewoooo
    bar.printNameUpperCase(); // output: LEEWOOOO
}
```

위와 같이 `BarImpl`가 `Bar`를 구현한 class이기 때문에 `BarImpl`를 통해 생성한 인스턴스는 `Bar`에서 제공하는 `printNameUpperCase`를 사용할 수 있다.

```java
Bar bar = new BarImpl("leewoooo");
```

위에서는 interface 타입으로 인스턴스를 생성하였지만 **interface를 구현한 구현체 타입으로 인스턴스를 생성해도 사용이 가능하다.**

```java
BarImpl barImpl = new BarImpl("foobar");
barImpl.printNameUpperCase();
```

## 스테틱 메소드

기본 메소드를 제공하는 동시에 **스테틱 메소드 또한 지원을 한다.** 스테틱 메소드는 class의 static 메소드와 사용법이 크게 다르지 않다.

```java
public interface Bar {

    String getName();
    
    // ...

    // 현재 interface의 타입으로 어느 곳에서든 호출 할 수 있는 static method를 정의할 수 있다.
    static void printBar(){
        System.out.println("Bar");
    }
}
```

위와 같이 `static` keyword를 이용하여 정의할 수 있으며 사용은 **interface.메소드명으로 호출이 가능하다.**

```java
Bar.printBar(); // output: Bar
```

## Java8 이 후로 기본 메소드 기능 추가로 인한 변화

이전에는 interface를 구현할 때 구현하는 class들이 사용하지 않는 interface의 method 전체 구현을 막기 위해 **중간에 추상 class를 생성하여 빈 method를 이용하여
구현하였다.**

그럼 interface를 구현하는 class들은 **interface와 구현 class 사이에 있는 추상 class를 상속받아 필요한 기능들만 `override`하여 사용하였다.**

이렇게 되면 Java는 다중 상속을 지원하지 않기 때문에 **해당하는 추상 class 하나만을 상속받을 수 밖에 없었다.**

하지만 Java8에서 `기본 메소드(Default)와 스태틱 메소드(static)를 지원해주기 때문에` Spring API에서도 중간의 추상 class들을 `@Deprecated`되었다. 

구현을 강제하는 것들은 **추상 메소드로 정의하고** 그 이 외의 메소드들은 **기본 메소드(Default)와 스태틱 메소드(static)로 정의하여 사용하는 방향으로 API들이 변경되었다.** 






