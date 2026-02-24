# 람다식 (Lambda Expression)

## 목차
- [기본 개념](#기본-개념)
- [문법 규칙](#문법-규칙)
- [함수형 인터페이스](#함수형-인터페이스)
- [메소드 참조](#메소드-참조)
- [코테 실전 활용](#코테-실전-활용)
- [주의사항](#주의사항)

---

## 기본 개념

### 람다식이란?
- **익명 함수를 짧게 쓰는 방법**
- 함수형 인터페이스(추상 메서드가 1개인 인터페이스)가 있어야 함

### 람다식 변환 과정

**Step 1: 익명 클래스 (너무 김)**
```java
// 기존 방식 (익명 구현 객체)
action(new Calculable() {
    @Override
    public void calculate(int x, int y) {
        System.out.println(x + y);
    }
});
```

**Step 2: 람다식 (간결)**
```java
// 람다식으로 줄이면
action((x, y) -> System.out.println(x + y));
```

**8줄 → 1줄!** ⚡

---

## 문법 규칙

### 기본 형태
```java
(매개변수) -> { 실행문; }
```

### 생략 규칙

**1. 실행문이 1줄이면 중괄호 생략 가능**
```java
(x, y) -> System.out.println(x + y)
```

**2. 매개변수가 1개면 괄호 생략 가능**
```java
x -> System.out.println(x)
```

**3. 매개변수가 없으면 빈 괄호**
```java
() -> System.out.println("Hello")
```

**4. return문만 있으면 중괄호와 return 생략 가능**
```java
// ✅ 생략 가능
(a, b) -> a + b

// ❌ 생략 불가 (여러 줄)
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

### @FunctionalInterface
- 추상 메서드가 2개 이상이면 컴파일 에러 → 함수형 인터페이스임을 보장

```java
@FunctionalInterface
interface Calculable {
    void calculate(int x, int y);
    // void add(int x, int y);  // ❌ 컴파일 에러!
}
```

---

## 함수형 인터페이스

### 자바 기본 제공 함수형 인터페이스

#### 1. Consumer\<T> - 매개변수 O, 리턴 X

**용도:** 값을 받아서 소비만 (출력, 저장 등)

```java
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello");  // "Hello" 출력

// 코테 활용: forEach
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
list.forEach(x -> System.out.println(x));
// 또는
list.forEach(System.out::println);
```

#### 2. Function\<T, R> - 매개변수 O, 리턴 O

**용도:** 값을 받아서 변환 후 리턴

```java
Function<String, Integer> length = s -> s.length();
int len = length.apply("Hello");  // 5

// 코테 활용: map
List<Integer> doubled = list.stream()
    .map(x -> x * 2)
    .collect(Collectors.toList());
// [2, 4, 6, 8, 10]
```

#### 3. Predicate\<T> - 매개변수 O, 리턴 boolean

**용도:** 조건 검사 (코테에서 매우 자주 씀!)

```java
Predicate<Integer> isPositive = x -> x > 0;
boolean result = isPositive.test(10);  // true

// 코테 활용: filter
List<Integer> positives = list.stream()
    .filter(x -> x > 0)
    .collect(Collectors.toList());
```

#### 4. Supplier\<T> - 매개변수 X, 리턴 O

**용도:** 값을 생성해서 리턴

```java
Supplier<Double> random = () -> Math.random();
double value = random.get();

Supplier<String> hello = () -> "Hello World";
String str = hello.get();  // "Hello World"
```

---

## 메소드 참조

람다식을 더 간결하게 표현하는 방법

### 1. 정적 메소드 참조

**형식:** `클래스::메소드`

```java
// 람다식
Function<String, Integer> func1 = s -> Integer.parseInt(s);

// 메소드 참조
Function<String, Integer> func2 = Integer::parseInt;

// 사용
int num = func2.apply("123");  // 123
```

### 2. 인스턴스 메소드 참조

**형식:** `참조변수::메소드`

```java
String str = "Hello";

// 람다식
Supplier<Integer> length1 = () -> str.length();

// 메소드 참조
Supplier<Integer> length2 = str::length;

// 사용
int len = length2.get();  // 5
```

### 3. 매개변수의 메소드 참조

**형식:** `클래스::instanceMethod`

람다식에서 제공되는 a 매개변수의 메소드를 호출해서 b 매개변수를 매개값으로 사용하는 경우

```java
// 람다식
BiFunction<String, String, Boolean> func1 = 
    (a, b) -> a.equals(b);

// 메소드 참조
BiFunction<String, String, Boolean> func2 = 
    String::equals;

// String 인스턴스 메소드 compareToIgnoreCase 예시
// 람다식
(a, b) -> a.compareToIgnoreCase(b)

// 메소드 참조
String::compareToIgnoreCase
```

### 4. 생성자 참조

**형식:** `클래스::new`

생성자 참조 = 객체를 생성

```java
// 람다식
Function<String, StringBuilder> func1 = 
    s -> new StringBuilder(s);

// 생성자 참조
Function<String, StringBuilder> func2 = 
    StringBuilder::new;

// 2개 매개변수
BiFunction<Integer, Integer, Point> func3 = 
    (x, y) -> new Point(x, y);

// 생성자 참조
BiFunction<Integer, Integer, Point> func4 = 
    Point::new;
```

---

## 코테 실전 활용

### 1. 정렬 (가장 많이 씀!) ⭐⭐⭐

#### 기본 정렬
```java
List<Integer> list = Arrays.asList(5, 2, 8, 1, 9);

// 오름차순
list.sort((a, b) -> a - b);
// [1, 2, 5, 8, 9]

// 내림차순
list.sort((a, b) -> b - a);
// [9, 8, 5, 2, 1]

// 배열도 동일
Integer[] arr = {5, 2, 8, 1, 9};
Arrays.sort(arr, (a, b) -> a - b);
```

#### 문자열 정렬
```java
List<String> words = Arrays.asList("apple", "banana", "kiwi");

// 길이순 정렬
words.sort((a, b) -> a.length() - b.length());
// ["kiwi", "apple", "banana"]

// 사전순 (오름차순)
words.sort((a, b) -> a.compareTo(b));
// 또는
words.sort(String::compareTo);

// 사전순 (내림차순)
words.sort((a, b) -> b.compareTo(a));
```

#### 2차원 배열 정렬
```java
int[][] arr = {{3, 5}, {1, 2}, {2, 8}};

// arr[i][0] 기준 오름차순
Arrays.sort(arr, (a, b) -> a[0] - b[0]);
// [[1, 2], [2, 8], [3, 5]]

// arr[i][1] 기준 내림차순
Arrays.sort(arr, (a, b) -> b[1] - a[1]);
// [[2, 8], [3, 5], [1, 2]]

// 여러 조건 (arr[i][0] 오름차순, 같으면 arr[i][1] 내림차순)
Arrays.sort(arr, (a, b) -> {
    if(a[0] == b[0]) {
        return b[1] - a[1];
    }
    return a[0] - b[0];
});
```

#### 객체 정렬
```java
class Student {
    String name;
    int score;
    
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

List<Student> students = new ArrayList<>();
students.add(new Student("김철수", 85));
students.add(new Student("이영희", 92));
students.add(new Student("박민수", 78));

// 점수순 오름차순
students.sort((a, b) -> a.score - b.score);

// 점수순 내림차순
students.sort((a, b) -> b.score - a.score);

// 이름순
students.sort((a, b) -> a.name.compareTo(b.name));
```

---

### 2. 필터링 (Stream과 함께) ⭐⭐

```java
List<Integer> numbers = Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5);

// 양수만 필터링
List<Integer> positives = numbers.stream()
    .filter(x -> x > 0)
    .collect(Collectors.toList());
// [1, 2, 3, 4, 5]

// 짝수만 필터링
List<Integer> evens = numbers.stream()
    .filter(x -> x % 2 == 0)
    .collect(Collectors.toList());
// [-2, 0, 2, 4]

// 여러 조건 (양수이면서 짝수)
List<Integer> result = numbers.stream()
    .filter(x -> x > 0 && x % 2 == 0)
    .collect(Collectors.toList());
// [2, 4]
```

---

### 3. 변환 (map) ⭐⭐

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// 각 요소에 2 곱하기
List<Integer> doubled = numbers.stream()
    .map(x -> x * 2)
    .collect(Collectors.toList());
// [2, 4, 6, 8, 10]

// 제곱
List<Integer> squared = numbers.stream()
    .map(x -> x * x)
    .collect(Collectors.toList());
// [1, 4, 9, 16, 25]

// 문자열 변환
List<String> strings = numbers.stream()
    .map(x -> "숫자: " + x)
    .collect(Collectors.toList());
// ["숫자: 1", "숫자: 2", ...]
```

---

### 4. forEach (출력/디버깅) ⭐

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

// 각 요소 출력
list.forEach(x -> System.out.println(x));

// 메소드 참조
list.forEach(System.out::println);

// 인덱스와 함께 출력 (for문 필요)
for(int i = 0; i < list.size(); i++) {
    int idx = i;
    System.out.println("인덱스 " + idx + ": " + list.get(i));
}
```

---

### 5. 종합 예시

#### 문제: 배열에서 양수만 골라서 제곱한 후 내림차순 정렬

```java
int[] arr = {-2, 3, -1, 5, 2, -4, 1};

List<Integer> result = Arrays.stream(arr)
    .filter(x -> x > 0)           // 양수만
    .map(x -> x * x)              // 제곱
    .boxed()
    .sorted((a, b) -> b - a)      // 내림차순
    .collect(Collectors.toList());

// [25, 9, 4, 1]
```

#### 문제: 문자열 배열에서 길이 3 이상만 대문자로 변환 후 정렬

```java
String[] words = {"a", "abc", "ab", "hello", "hi"};

List<String> result = Arrays.stream(words)
    .filter(s -> s.length() >= 3)    // 길이 3 이상
    .map(String::toUpperCase)        // 대문자 변환
    .sorted()                        // 정렬
    .collect(Collectors.toList());

// ["ABC", "HELLO"]
```

---

## 주의사항

### 1. 외부 변수는 final이어야 함

```java
int num = 10;

Consumer<Integer> lambda = x -> {
    // num = 20;  // ❌ 에러! 외부 변수 변경 불가
    System.out.println(x + num);  // ✅ 읽기만 가능
};

lambda.accept(5);  // 15
```

**이유:** 람다식은 변수를 캡처(복사)해서 사용 → 원본 변경 불가

---

### 2. return 생략 규칙

```java
// ✅ return 생략 가능 (단일 표현식)
Function<Integer, Integer> double1 = x -> x * 2;

// ✅ return 명시 (중괄호와 함께)
Function<Integer, Integer> double2 = x -> {
    return x * 2;
};

// ❌ 생략 불가 (여러 줄)
Function<Integer, Integer> func = x -> {
    int result = x * 2;
    return result;  // return 필수!
};
```

---

### 3. 정렬 시 주의사항

```java
// ❌ int 오버플로우 주의!
Arrays.sort(arr, (a, b) -> a - b);  
// a = Integer.MAX_VALUE, b = -1 이면 오버플로우!

// ✅ 안전한 방법
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));

// 또는
Arrays.sort(arr, Integer::compare);
```

---

## 정리

### 코테에서 가장 많이 쓰는 람다식 TOP 3

1. **정렬** (99% 사용!)
```java
list.sort((a, b) -> a - b);
Arrays.sort(arr, (a, b) -> a[0] - b[0]);
```

2. **필터링** (Stream)
```java
.filter(x -> x > 0)
```

3. **변환** (Stream)
```java
.map(x -> x * 2)
```

### 람다식의 장점
- ✅ 코드가 간결
- ✅ 가독성 향상
- ✅ 함수형 프로그래밍 가능

### 람다식 사용 원칙
- 간단한 로직만 람다식으로
- 복잡하면 일반 메소드로
- 코테에서는 정렬, 필터링, 변환에 집중!
