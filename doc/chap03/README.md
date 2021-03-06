스트림
===

## 스트림이란?

연속된 데이터를 처리하기 위한 operator들의 모음이라고 생각할 수 있다. (스트림 파이프라인.)

컬렉션(collection)은 실제로 Data를 들고있는 것을 의미하며 스트림(stream)은 컬렉션에 있는 Data들을 가공할 때 사용하는 것으로 이해할 수 있다.
(즉 스트림은 Data를 담고 있는 저장소가 아니다.)

### 특징

1. 컬렉션에서 스트림을 얻어올 떄 **원본의 컬렉션 Data는 변경하지 않는다.** (스트림을 이용하였을 때 새로운 스트림이 생성되는 것.)
2. 스트림으로 처리하는 데이터는 **오직 한번만 처리된다.** (하나의 operator안에서 Data는 한번만 처리된다.)
3. 중개 operator들은 일반적으로 **lazy**하다. -> **종료형 operator가 실행되기 전까지 중개형 operator는 실행을 하지 않는다.**
4. 병렬처리가 손쉬워 진다. (`parallelStream()`을 이용하면 JVM이 알아서 병렬처리를 해준다.) -> 언제 유용하냐면 다뤄야 하는 Data양이 방대할 때 사용하면 병렬처리의 장점을 가져갈 수 있다.

#### 중개형 operator

- **stream을 return한다.**

#### 종료형 operator

- **stream을 return하지 않는다.**
