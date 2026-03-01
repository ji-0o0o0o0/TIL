# Stream

## 목차
- [기본 개념](#기본-개념)
- [내부 반복자](#내부-반복자)
- [중간 처리와 최종 처리](#중간-처리와-최종-처리)
- [스트림 생성](#스트림-생성)
- [중간 처리 메소드](#중간-처리-메소드)
- [최종 처리 메소드](#최종-처리-메소드)
- [코테 실전 패턴](#코테-실전-패턴)
- [주의사항](#주의사항)

---

## 기본 개념

### Stream이란?
- 컬렉션, 배열 등의 데이터를 **함수형으로 처리**하는 기능
- **람다식**을 활용한 간결한 코드 작성 가능
- **파이프라인** 형태로 여러 연산을 연결

### Stream vs Iterator 차이점

| 구분 | Iterator | Stream |
|------|----------|--------|
| 반복 방식 | 외부 반복자 | 내부 반복자 |
| 병렬 처리 | 어려움 | 쉬움 (parallelStream) |
| 코드 | 장황함 | 간결함 (람다식) |
| 재사용 | 가능 | 불가능 (일회성) |

---

## 내부 반복자

### 외부 반복자 (Iterator, for문)
- 컬렉션 요소를 **외부로 가져와서** 처리
- 개발자가 직접 반복 코드 작성

```java
// for문 - 외부 반복자
for(String name : list) {
    System.out.println(name);
}

// Iterator - 외부 반복자
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### 내부 반복자 (Stream)
- 데이터 처리 방법을 **컬렉션 내부로 주입**
- 컬렉션이 **내부에서 요소를 반복** 처리
- **병렬 처리에 효율적** (멀티 코어 CPU 활용)

```java
// Stream - 내부 반복자
list.stream()
    .forEach(name -> System.out.println(name));

// 병렬 처리
list.parallelStream()
    .forEach(name -> {
        System.out.println(name + ": " + Thread.currentThread().getName());
    });
```

**장점:**
- 처리 속도 빠름
- 병렬 처리 효율적
- 코드 간결

---

## 중간 처리와 최종 처리

### 스트림 파이프라인
- 스트림은 **하나 이상 연결** 가능
- **중간 처리** + **최종 처리** 형태

```
원본 데이터 → 중간 처리 → 중간 처리 → 최종 처리
           (필터링)   (매핑)    (집계)
```

### 중간 처리 메소드
- filter() - 필터링
- map() - 매핑(변환)
- sorted() - 정렬
- distinct() - 중복 제거
- limit(), skip() - 개수 제한/건너뛰기

### 최종 처리 메소드
- forEach() - 반복
- collect() - 수집
- count(), sum(), average() - 집계
- anyMatch(), allMatch(), noneMatch() - 조건 검사

### 파이프라인 예시

```java
// Student 객체 → 점수 → 평균
double avg = list.stream()                    // Stream<Student>
    .mapToInt(student -> student.getScore())  // IntStream (중간)
    .average()                                 // OptionalDouble (최종)
    .getAsDouble();

// 메소드 체이닝 패턴
```

**⚠️ 주의:** 파이프라인 끝에는 **반드시 최종 처리**가 있어야 함!

---

## 스트림 생성

### 1. 컬렉션으로부터

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream = list.stream();

// 사용
stream.forEach(System.out::println);
```

### 2. 배열로부터

```java
// Integer 배열
Integer[] arr = {1, 2, 3, 4, 5};
Stream<Integer> stream = Arrays.stream(arr);

// int 배열 (IntStream)
int[] intArr = {1, 2, 3, 4, 5};
IntStream stream = Arrays.stream(intArr);

// String 배열
String[] strArr = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(strArr);
```

### 3. 직접 생성

```java
// Stream.of()
Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

Stream<String> stream = Stream.of("a", "b", "c");
```

### 4. 숫자 범위 (코테에서 자주 씀!) ⭐

```java
// range(start, end) - end 미포함
IntStream stream = IntStream.range(1, 5);  // 1, 2, 3, 4

// rangeClosed(start, end) - end 포함
IntStream stream = IntStream.rangeClosed(1, 5);  // 1, 2, 3, 4, 5

// 활용: 1~100 합계
int sum = IntStream.rangeClosed(1, 100).sum();  // 5050
```

### 5. 파일로부터

```java
// 텍스트 파일 행 단위 읽기
Path path = Paths.get("data.txt");
Stream<String> stream = Files.lines(path, Charset.defaultCharset());
stream.forEach(System.out::println);
stream.close();  // 반드시 닫아야 함!

// try-with-resources 권장
try (Stream<String> stream = Files.lines(path)) {
    stream.forEach(System.out::println);
}
```

### 6. 무한 스트림

```java
// iterate - 초기값 + 증가 규칙
Stream.iterate(0, n -> n + 2)  // 0, 2, 4, 6, 8...
    .limit(10)                 // 10개만
    .forEach(System.out::println);

// generate - 값 생성
Stream.generate(() -> Math.random())
    .limit(5)
    .forEach(System.out::println);
```

---

## 중간 처리 메소드

### 1. 필터링 (filter, distinct)

```java
// filter - 조건 만족하는 요소만
list.stream()
    .filter(x -> x > 0)           // 양수만
    .forEach(System.out::println);

list.stream()
    .filter(x -> x % 2 == 0)      // 짝수만
    .forEach(System.out::println);

// distinct - 중복 제거
List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 4);
list.stream()
    .distinct()                    // 중복 제거
    .forEach(System.out::println); // 1, 2, 3, 4
```

### 2. 매핑 (map, mapToInt, flatMap)

#### map - 요소 변환

```java
// 각 요소에 2 곱하기
list.stream()
    .map(x -> x * 2)
    .forEach(System.out::println);

// Student → 점수
studentList.stream()
    .map(s -> s.getScore())
    .forEach(System.out::println);

// 문자열 → 길이
List<String> words = Arrays.asList("apple", "banana", "kiwi");
words.stream()
    .map(s -> s.length())
    .forEach(System.out::println);  // 5, 6, 4
```

#### mapToInt, mapToDouble, mapToLong

```java
// Student → 점수 (IntStream)
IntStream scoreStream = studentList.stream()
    .mapToInt(s -> s.getScore());

// sum, average 등 집계 가능
int total = scoreStream.sum();

// String → Integer → int
List<String> strList = Arrays.asList("1", "2", "3");
int sum = strList.stream()
    .mapToInt(Integer::parseInt)
    .sum();  // 6
```

#### flatMap - 하나를 여러 개로

```java
// 문장 → 단어로 분리
List<String> sentences = Arrays.asList("this is java", "i am developer");
sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .forEach(System.out::println);
// "this", "is", "java", "i", "am", "developer"

// "10,20,30" → 숫자로 분리
List<String> list = Arrays.asList("10,20,30", "40,50");
list.stream()
    .flatMapToInt(data -> {
        String[] strArr = data.split(",");
        int[] intArr = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i].trim());
        }
        return Arrays.stream(intArr);
    })
    .forEach(System.out::println);
// 10, 20, 30, 40, 50
```

### 3. 정렬 (sorted)

```java
// 오름차순
list.stream()
    .sorted()
    .forEach(System.out::println);

// 내림차순
list.stream()
    .sorted(Comparator.reverseOrder())
    .forEach(System.out::println);

// 커스텀 정렬
studentList.stream()
    .sorted((s1, s2) -> Integer.compare(s1.getScore(), s2.getScore()))
    .forEach(System.out::println);

// 점수 내림차순
studentList.stream()
    .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
    .forEach(System.out::println);
```

### 4. 제한/건너뛰기 (limit, skip)

```java
// limit - 개수 제한
stream.limit(5)  // 앞에서 5개만
    .forEach(System.out::println);

// skip - 건너뛰기
stream.skip(3)   // 앞에서 3개 제외
    .forEach(System.out::println);

// 조합: 4번째~8번째
stream.skip(3).limit(5)
    .forEach(System.out::println);
```

### 5. 루핑 (peek, forEach)

```java
// peek - 중간 처리 (디버깅용)
list.stream()
    .peek(x -> System.out.println("필터 전: " + x))
    .filter(x -> x > 0)
    .peek(x -> System.out.println("필터 후: " + x))
    .collect(Collectors.toList());

// forEach - 최종 처리
list.stream()
    .forEach(System.out::println);
```

**⚠️ 주의:** peek()는 최종 처리가 없으면 동작 안 함!

---

## 최종 처리 메소드

### 1. 수집 (collect) - 가장 중요! ⭐⭐⭐

```java
// List로 수집
List<Integer> result = stream
    .filter(x -> x > 0)
    .collect(Collectors.toList());

// Set으로 수집 (중복 제거)
Set<Integer> result = stream
    .collect(Collectors.toSet());

// 배열로 변환
Integer[] arr = stream.toArray(Integer[]::new);
int[] arr = stream.mapToInt(x -> x).toArray();

// 문자열로 합치기
String result = list.stream()
    .map(String::valueOf)
    .collect(Collectors.joining(", "));
// "1, 2, 3, 4, 5"

// Map으로 수집
Map<String, Integer> map = studentList.stream()
    .collect(Collectors.toMap(
        s -> s.getName(),
        s -> s.getScore()
    ));
```

### 2. 집계 (count, sum, average, max, min)

```java
// count - 개수
long count = stream.count();

// sum - 합계 (IntStream, LongStream, DoubleStream만 가능)
int sum = IntStream.of(1, 2, 3, 4, 5).sum();  // 15

// average - 평균
OptionalDouble avg = IntStream.of(1, 2, 3).average();
double avgValue = avg.orElse(0.0);  // 값 없으면 0.0

// max - 최댓값
OptionalInt max = IntStream.of(1, 2, 3).max();
int maxValue = max.orElse(0);

// min - 최솟값
OptionalInt min = IntStream.of(1, 2, 3).min();
```

### 3. 매칭 (anyMatch, allMatch, noneMatch)

```java
// anyMatch - 하나라도 조건 만족?
boolean hasPositive = list.stream()
    .anyMatch(x -> x > 0);  // true

// allMatch - 모두 조건 만족?
boolean allPositive = list.stream()
    .allMatch(x -> x > 0);  // false

// noneMatch - 모두 조건 불만족?
boolean noNegative = list.stream()
    .noneMatch(x -> x < 0);  // true
```

### 4. 찾기 (findFirst, findAny)

```java
// findFirst - 첫 번째 요소
Optional<Integer> first = stream.findFirst();
int value = first.orElse(0);

// findAny - 아무거나 하나 (병렬 스트림에서 유용)
Optional<Integer> any = stream.findAny();
```

### 5. 반복 (forEach)

```java
// 각 요소 출력
list.forEach(System.out::println);

// 람다식
list.forEach(x -> System.out.println(x));
```

### 6. 커스텀 집계 (reduce)

```java
// reduce(초기값, (누적값, 요소) -> 연산)
int sum = IntStream.rangeClosed(1, 10)
    .reduce(0, (a, b) -> a + b);  // 55

// 최댓값
OptionalInt max = IntStream.of(1, 2, 3, 4, 5)
    .reduce((a, b) -> a > b ? a : b);
```

---

## 코테 실전 패턴

### 패턴 1: 필터링 + 수집

```java
// 양수만 골라서 리스트로
List<Integer> positives = list.stream()
    .filter(x -> x > 0)
    .collect(Collectors.toList());

// 짝수만 골라서 배열로
int[] evens = list.stream()
    .filter(x -> x % 2 == 0)
    .mapToInt(x -> x)
    .toArray();
```

### 패턴 2: 변환 + 수집

```java
// 제곱해서 리스트로
List<Integer> squared = list.stream()
    .map(x -> x * x)
    .collect(Collectors.toList());

// 문자열 길이 리스트
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList());
```

### 패턴 3: 필터 + 변환 + 정렬

```java
// 양수만, 제곱, 내림차순
List<Integer> result = list.stream()
    .filter(x -> x > 0)
    .map(x -> x * x)
    .sorted((a, b) -> b - a)
    .collect(Collectors.toList());
```

### 패턴 4: 중복 제거 + 정렬

```java
// 중복 제거 후 오름차순
List<Integer> result = list.stream()
    .distinct()
    .sorted()
    .collect(Collectors.toList());

// Set으로 수집 (자동 중복 제거)
Set<Integer> set = list.stream()
    .collect(Collectors.toSet());
```

### 패턴 5: 범위 생성 + 처리

```java
// 1~100까지 짝수의 합
int sum = IntStream.rangeClosed(1, 100)
    .filter(x -> x % 2 == 0)
    .sum();

// 1~10까지 제곱의 리스트
List<Integer> squares = IntStream.rangeClosed(1, 10)
    .map(x -> x * x)
    .boxed()  // IntStream → Stream<Integer>
    .collect(Collectors.toList());
```

### 패턴 6: 그룹핑

```java
// 점수대별 그룹핑
Map<String, List<Student>> groupedByGrade = studentList.stream()
    .collect(Collectors.groupingBy(s -> {
        if(s.getScore() >= 90) return "A";
        else if(s.getScore() >= 80) return "B";
        else return "C";
    }));

// 성별로 그룹핑 후 평균 점수
Map<String, Double> avgByGender = studentList.stream()
    .collect(Collectors.groupingBy(
        Student::getGender,
        Collectors.averagingDouble(Student::getScore)
    ));
```

---

## 주의사항

### 1. Stream은 재사용 불가!

```java
// ❌ 에러!
Stream<Integer> stream = list.stream();
stream.forEach(System.out::println);
stream.count();  // IllegalStateException!

// ✅ 다시 생성
Stream<Integer> stream1 = list.stream();
stream1.forEach(System.out::println);

Stream<Integer> stream2 = list.stream();
long count = stream2.count();
```

### 2. Optional 안전하게 처리

```java
// ❌ 위험
OptionalDouble avg = stream.average();
double value = avg.getAsDouble();  // 값 없으면 예외!

// ✅ 안전
double value = avg.orElse(0.0);     // 없으면 0.0
double value = avg.orElseGet(() -> 0.0);

// ✅ 존재 여부 확인
if(avg.isPresent()) {
    double value = avg.getAsDouble();
}
```

### 3. peek()는 중간 처리

```java
// ❌ 동작 안 함! (최종 처리 없음)
list.stream()
    .peek(System.out::println);

// ✅ 동작함 (최종 처리 있음)
list.stream()
    .peek(System.out::println)
    .collect(Collectors.toList());
```

### 4. 박싱/언박싱 주의

```java
// ❌ 느림 (박싱 발생)
List<Integer> list = Arrays.asList(1, 2, 3);
int sum = list.stream()
    .mapToInt(x -> x)  // Integer → int 언박싱
    .sum();

// ✅ 빠름 (int[] 직접 사용)
int[] arr = {1, 2, 3};
int sum = Arrays.stream(arr).sum();
```

### 5. 병렬 스트림 주의

```java
// 병렬 스트림
list.parallelStream()
    .forEach(System.out::println);

// 주의: 순서 보장 안 됨!
// 작은 데이터에서는 오히려 느릴 수 있음
// 공유 변수 접근 시 동기화 문제
```

---

## 정리

### 코테에서 가장 많이 쓰는 Stream 메소드 TOP 10

1. **filter()** - 필터링 ⭐⭐⭐
2. **map()** - 변환 ⭐⭐⭐
3. **collect(Collectors.toList())** - 리스트로 수집 ⭐⭐⭐
4. **sorted()** - 정렬 ⭐⭐
5. **distinct()** - 중복 제거 ⭐⭐
6. **toArray()** - 배열로 변환 ⭐⭐
7. **forEach()** - 반복 ⭐
8. **count()** - 개수 ⭐
9. **IntStream.range()** - 범위 생성 ⭐⭐
10. **mapToInt()** - IntStream 변환 ⭐

### Stream 사용 흐름

```
데이터 생성 → 중간 처리(여러 개 가능) → 최종 처리(필수)
   ↓              ↓                    ↓
 stream()     filter/map/sorted     collect/forEach
```

### 언제 Stream을 쓸까?

- ✅ 필터링 + 변환이 필요할 때
- ✅ 코드를 간결하게 하고 싶을 때
- ✅ 함수형 프로그래밍이 적합할 때
- ❌ 간단한 반복문으로 충분할 때
- ❌ 성능이 매우 중요할 때 (대용량 데이터)
