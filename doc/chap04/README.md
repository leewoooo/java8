Optional
===

자바 프로그래밍에서 `NullPointException`을 보게 되는 이유는 아래와 같다.

- null을 return받아서 그대로 사용하였을 때
- return 받은 인스턴스에 대한 null check를 하지 않았을 때

## 메소드에서 작업 중 특별한 상황에서 값을 제대로 return할 수 없는 경우(예를 들어 null return) 선택할 수 있는 방법들

### 예외를 던진다.

`throw`를 이용하여 예외를 던질 수 있다. 하지만 java에서는 예외에 대한 `stacktrace`를 찍기 때문에 리소스 비용이 발생한다.

또한 `checkedException`과 같은 경우 client code에서 예외 처리를 강제하게 된다.

### null return

null을 return하기 때문에 리소스 비용은 고민하지 않아도 되지만 해당 code를 사용하는 쪽에서 **null check를 전부 해야 한다.**

### Optional<>을 return한다.

java8에 새로 들어온 instance이며 `Optional`을 return할 경우 code를 사용하는 client에게 **명시적으로 빈 값일 수도 있다는 걸 알려줄 수 있으며, 
빈 값일 경우에 대한 처리를 강제한다.**

## Optional

한 개의 값이 들어 있을 수도 있고 없을 수도 있는 컨테이너라고 생각하면 된다. **잠재적으로 null이 될 수 있는 객체를 한번 래핑하여 가지고 있는 컨테이너이다.**

## 주의점

- **return값으로만 사용할 것을 권장한다.**(매개변수 타입, class의 필드 타입 등으로 사용하는 것을 지양)
- Optional을 return하는 method에서 null을 return하지 말자.
- 프리미티브 타입용으로 지원하는 `OptionalInt, OptionalLong` 등이 있다. (`Optional`을 그냥 사용할 수 있지만 내부적으로 **boxing, unboxing이 일어나기 때문에 Optional에서 제공해주는 API를 사용하는 것을 권장**)
- `Collection, Map, Array, Stream`과 같이 비어있는지 확인이 가능한 객체들을 **Optional로 감싸지 말자**
- `Optional.of()`를 사용하여 `Optional`을 만들 수 있지만 해당 API을 파라미터로 null이 들어가면 동일하게 `NullPointException`이 발생 null일 확률이 있다면 `Optional.ofNullable()`을 이용