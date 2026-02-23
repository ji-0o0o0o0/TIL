# Array & ArrayList

> 인덱스와 값을 일대일 대응해 관리하는 자료구조  
> 임의 접근(데이터에 한 번에 접근 가능)

## 목차
- [배열 (Array)](#배열-array)
- [ArrayList](#arraylist)
- [변환](#변환)
- [코테 자주 쓰는 패턴](#코테-자주-쓰는-패턴)
- [예시](#예시)

---

## 배열 (Array)

### 선언 & 초기화

```java
int[] arr = {1, 2, 3};
int[] arr2 = new int[5];
```

### 기본 메소드

```java
arr.length                          // 길이
Arrays.sort(arr);                   // 오름차순 정렬
Arrays.toString(arr);               // 출력 "[1, 2, 3]"
Arrays.fill(arr, 0);                // 0으로 채우기
```

### 배열 복사

```java
int[] arr1 = {1, 2, 3};

// 얕은 복사 (같은 배열 가리킴)
int[] arr2 = arr1;
arr2[0] = 100;
System.out.println(arr1[0]);  // 100 (같이 바뀜!)

// 깊은 복사 (새 배열 생성)
int[] arr3 = arr1.clone();
arr3[0] = 100;
System.out.println(arr1[0]);  // 1 (안 바뀜!)

// 또는
int[] arr4 = Arrays.copyOf(arr1, arr1.length);
```

### 내림차순 정렬

```java
// int[] 는 내림차순 불가 → Integer[] 필요
Integer[] arr = {3, 1, 2};
Arrays.sort(arr, Collections.reverseOrder());
// [3, 2, 1]
```

---

## ArrayList

### 선언 & 초기화

```java
ArrayList<Integer> list = new ArrayList<>();
ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
```

### 기본 메소드

```java
list.size()                         // 크기
list.isEmpty()                      // 비어있는지
list.get(0)                         // 인덱스로 접근
```

### 추가 & 삭제

```java
list.add(10);                       // 끝에 추가
list.add(0, 10);                    // 인덱스에 추가

list.remove(0);                     // 인덱스로 삭제
list.remove(Integer.valueOf(10));  // 값으로 삭제
```

**주의사항**
```java
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 10, 4));

list.remove(2);                     // 인덱스 2 삭제 → 값 2 제거
list.remove(Integer.valueOf(10));  // 값 10 삭제

// 숫자가 작으면 헷갈릴 수 있음!
// 값으로 삭제하려면 반드시 Integer.valueOf() 사용
```

### 검색

```java
list.contains(10)                   // 포함 여부
list.indexOf(10)                    // 인덱스 찾기 (없으면 -1)
```

### 정렬

```java
Collections.sort(list);                      // 오름차순
list.sort(Comparator.reverseOrder());        // 내림차순
```

### 기타

```java
Collections.reverse(list);          // 뒤집기
```

---

## 변환

### ArrayList → 배열

```java
Integer[] arr = list.toArray(new Integer[0]);
```

### 배열 → ArrayList

```java
// Integer[] 또는 값으로 직접
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

// int[] 에서 변환 (반복문 필요)
int[] arr = {1, 2, 3};
ArrayList<Integer> list = new ArrayList<>();
for(int num : arr) {
    list.add(num);
}
```

### Integer[] → int[]

```java
// Stream 사용 (나중에 배울 예정)
int[] result = Arrays.stream(integerArray)
    .mapToInt(Integer::intValue)
    .toArray();

// 또는 반복문
int[] result = new int[integerArray.length];
for(int i = 0; i < integerArray.length; i++) {
    result[i] = integerArray[i];
}
```

---

## 코테 자주 쓰는 패턴

### 중복 제거

```java
HashSet<Integer> set = new HashSet<>();
for(int num : arr) {
    set.add(num);
}
ArrayList<Integer> list = new ArrayList<>(set);
```

### 내림차순 정렬 후 상위 K개

```java
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
list.sort(Comparator.reverseOrder());
// [9, 8, 5, 2, 1]

// 상위 3개
for(int i = 0; i < 3; i++) {
    System.out.println(list.get(i));
}
```

### 빈도수 세기

```java
HashMap<Integer, Integer> map = new HashMap<>();
for(int num : arr) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

---

## 예시

### 문제: 정수 배열 중복값 제거 후 내림차순으로 정렬

#### 방법 1: HashSet 활용 (추천!)

```java
// 중복 제거
HashSet<Integer> set = new HashSet<>();
for(int num : array) {
    set.add(num);
}

// ArrayList로 변환
ArrayList<Integer> list = new ArrayList<>(set);

// 내림차순 정렬
list.sort(Comparator.reverseOrder());

// int[] 배열로 변환
int[] result = new int[list.size()];
for(int i = 0; i < list.size(); i++) {
    result[i] = list.get(i);
}

return result;
```

**장점:**
- 코드가 명확함
- 각 단계가 이해하기 쉬움
- 성능도 충분히 좋음

#### 방법 2: TreeSet 활용 (자동 정렬)

```java
// TreeSet = 자동 오름차순 정렬 + 중복 제거
TreeSet<Integer> set = new TreeSet<>();
for(int num : array) {
    set.add(num);
}

// 내림차순으로 배열 만들기
int[] result = new int[set.size()];
int idx = 0;
// descendingSet() = 내림차순 순회
for(int num : set.descendingSet()) {
    result[idx++] = num;
}

return result;
```

**장점:**
- TreeSet이 자동으로 정렬해줌
- `descendingSet()`으로 내림차순 가능
