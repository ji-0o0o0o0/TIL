# Java Collection Framework

> 자바 컬렉션 프레임워크 전체 정리

## 목차
- [List](#list)
- [Set](#set)
- [Map](#map)
- [검색 기능 강화 컬렉션](#검색-기능-강화-컬렉션)
- [LIFO와 FIFO 컬렉션](#lifo와-fifo-컬렉션)
- [동기화된 컬렉션](#동기화된-컬렉션)
- [수정할 수 없는 컬렉션](#수정할-수-없는-unmodifiable-컬렉션)
- [코테 자주 쓰는 패턴](#코테-자주-쓰는-패턴)

---

## List

> 순서 보장, 중복 허용  
> 객체의 번지를 저장  
> 동일한 객체는 동일한 번지에 저장되며, null 저장 가능  
> 빈번한 삭제, 삽입 → `LinkedList` 사용

### ArrayList
- **내부 배열에 객체가 저장**
- 일반 배열과 차이점: 제한 없이 객체 추가 가능
- 인덱스를 통한 빠른 검색 가능 (O(1))
- 중간 삽입/삭제 시 성능 저하 (O(n))

```java
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.get(0);  // 인덱스로 접근
```

### Vector
- ArrayList와 동일한 내부 구조
- **동기화된 메소드로 구성**되어 있어 멀티 스레드가 동시에 Vector() 메소드 실행 불가
- **멀티 스레드 환경에서 안전**하게 추가/삭제 가능
- 단일 스레드 환경에서는 ArrayList보다 느림

```java
Vector<String> vector = new Vector<>();
// 멀티 스레드 환경에서 안전
```

### LinkedList
- ArrayList와 사용 방법은 같지만 **내부 구조는 다름**
- **인접 객체를 체인처럼 연결**해서 관리
  - 각 노드가 데이터와 다음 노드의 참조를 가짐
  - 앞뒤 노드의 링크만 변경하면 되므로 삽입/삭제가 빠름 (O(1))
- 인덱스 검색이 느림 (O(n)) - 처음부터 순회 필요
- ArrayList: 내부 배열에 객체 저장

```java
LinkedList<String> linkedList = new LinkedList<>();
linkedList.addFirst("A");  // 맨 앞 추가
linkedList.addLast("B");   // 맨 뒤 추가
```

**선택 기준**
- 검색이 많으면 → ArrayList
- 삽입/삭제가 많으면 → LinkedList

---

## Set

> 순서 보장X, 중복 허용X  
> 하나의 null만 저장 가능

### HashSet
- 동일한 객체 중복 저장 불가
- `hashCode()` 리턴값이 같고, `equals()` 리턴값이 true일 때 동등 객체라 판단
  - 같은 hashCode → 같은 버킷에 저장
  - equals로 최종 동등성 비교

```java
HashSet<String> set = new HashSet<>();
set.add("A");
set.add("A");  // 중복 - 저장 안됨
System.out.println(set.size());  // 1
```

### Set 순회 방법
Set은 인덱스로 검색하는 메소드가 없음 → 한 개씩 가져와야 함

**1. for-each 문 이용**
```java
Set<String> set = new HashSet<>();
for(String element : set) {
    System.out.println(element);
}
```

**2. iterator() 메소드 이용**
```java
Set<String> set = new HashSet<>();
Iterator<String> iterator = set.iterator();

while(iterator.hasNext()) {
    String element = iterator.next();
    System.out.println(element);
}
```

**Iterator 제공 메소드**
- `hasNext()` [boolean]: 가져올 객체가 있으면 true
- `next()` [E]: 컬렉션에서 하나의 객체 가져옴
- `remove()` [void]: 현재 객체 제거

---

## Map

> key와 value로 구성  
> key는 중복X, value는 중복O  
> HashMap, Hashtable 내부 구조 동일

### HashMap
- `hashCode()` 리턴값이 같고, `equals()` 리턴값이 true일 때 중복 저장 허용X
- key를 기준으로 value를 저장/조회

```java
Map<String, Integer> map = new HashMap<>();
map.put("홍길동", 85);
map.put("김철수", 90);
map.get("홍길동");  // 85
```

### keySet() vs entrySet()

**keySet()**
- 키만 담은 `Set<String>` 반환
- 값을 얻으려면 `map.get(key)`를 한 번 더 호출

```java
for(String key : map.keySet()) {
    Integer value = map.get(key);  // 맵 조회 한 번 더!
}
```

**entrySet()**
- 키+값 쌍(Entry)을 담은 `Set<Map.Entry<K,V>>` 반환
- `entry.getKey()`, `entry.getValue()`로 조회 가능

```java
for(Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();      // 바로 꺼냄
    Integer value = entry.getValue(); // 바로 꺼냄
}
```

**선택 기준**
- 키와 값을 둘 다 써야 할 때 → `entrySet` (더 효율적)
- 키만 필요하거나 특정 키 존재 여부만 확인 시 → `keySet`

### Hashtable
- HashMap과 다른 점: 동기화된 메소드로 구성 → 멀티 스레드 환경에서 안전
- 단일 스레드 환경에서는 HashMap보다 느림

---

## 검색 기능 강화 컬렉션

### TreeSet
- **이진 탐색 트리(Binary Search Tree)** 기반
- 자동으로 **오름차순 정렬**
- 부모 노드보다 작은 값은 왼쪽, 큰 값은 오른쪽에 저장
- 검색/삽입/삭제 시간복잡도: O(log n)

```java
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
treeSet.add(2);
// → {1, 2, 3} 자동 정렬!

treeSet.first();        // 최솟값: 1
treeSet.last();         // 최댓값: 3
treeSet.higher(2);      // 2보다 큰 값 중 최솟값: 3
treeSet.lower(2);       // 2보다 작은 값 중 최댓값: 1
treeSet.floor(2);       // 2 이하 값 중 최댓값: 2
treeSet.ceiling(2);     // 2 이상 값 중 최솟값: 2
```

### TreeMap
- 키를 기준으로 **자동 오름차순 정렬**
- 코테에서 정렬이 필요할 때 가끔 사용

```java
TreeMap<String, Integer> treeMap = new TreeMap<>();
treeMap.put("C", 3);
treeMap.put("A", 1);
treeMap.put("B", 2);
// → {A=1, B=2, C=3} 키 기준 자동 정렬
```

---

## LIFO와 FIFO 컬렉션

**LIFO(후입선출)** → Stack  
**FIFO(선입선출)** → Queue

### Stack
- **Last In First Out** 구조
- JVM 스택 메모리에서 자주 사용됨
- 메소드 호출 스택, 되돌리기(undo) 기능 등

```java
Stack<String> stack = new Stack<>();
stack.push("A");
stack.push("B");
stack.push("C");

stack.pop();   // "C" (마지막에 넣은 것)
stack.peek();  // "B" (제거하지 않고 확인)
```

### Queue
- **First In First Out** 구조
- 대표적인 예: 쓰레드 풀(Thread Pool)
- 작업 큐, 메시지 큐 등

```java
Queue<String> queue = new LinkedList<>();
queue.offer("A");
queue.offer("B");
queue.offer("C");

queue.poll();   // "A" (먼저 넣은 것)
queue.peek();   // "B" (제거하지 않고 확인)
```

---

## 동기화된 컬렉션

**기본 동기화 제공**
- `Vector`, `Hashtable`은 동기화된 메소드로 구성 → 멀티 스레드 환경에서 안전하게 처리

**기타 컬렉션을 동기화하려면**
- ArrayList → `Collections.synchronizedList(new ArrayList<>())`
- HashSet → `Collections.synchronizedSet(new HashSet<>())`
- HashMap → `Collections.synchronizedMap(new HashMap<>())`

```java
// ArrayList를 동기화된 List로 변환
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// HashSet을 동기화된 Set으로 변환
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

// HashMap을 동기화된 Map으로 변환
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
```

---

## 수정할 수 없는 (Unmodifiable) 컬렉션

요소를 추가, 삭제할 수 없는 컬렉션  
컬렉션 생성 시 저장된 요소를 변경하지 않을 때 유용

### 1. 정적 메소드 of()로 생성

```java
List<String> immutableList = List.of("A", "B", "C");
Set<String> immutableSet = Set.of("A", "B", "C");
Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2, "C", 3);

// immutableList.add("D");  // UnsupportedOperationException 발생!
```

### 2. copyOf()로 기존 컬렉션 복사

```java
List<String> originalList = new ArrayList<>();
originalList.add("A");

List<String> immutableList = List.copyOf(originalList);
Set<String> immutableSet = Set.copyOf(originalList);
```

### 3. 배열로부터 수정 불가능한 List 생성

```java
String[] arr = {"A", "B", "C"};
List<String> immutableList = Arrays.asList(arr);

// immutableList.add("D");  // UnsupportedOperationException 발생!
// 단, 배열 요소 값 변경은 가능: immutableList.set(0, "Z");
```

---

## 코테 자주 쓰는 패턴

### List 패턴

```java
List<String> list = new ArrayList<>();

// 정렬
Collections.sort(list);                      // 오름차순
list.sort(Comparator.reverseOrder());        // 내림차순

// 추가/삭제
list.add("값");
list.remove(0);         // 인덱스로 삭제
list.remove("값");      // 값으로 삭제

// 포함 여부
list.contains("값");

// 배열 → List
String[] arr = {"a", "b", "c"};
List<String> list2 = new ArrayList<>(Arrays.asList(arr));

// List → 배열
String[] arr2 = list.toArray(new String[0]);
```

### Set 패턴

```java
// 두 배열의 교집합/차집합
Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4));
Set<Integer> setB = new HashSet<>(Arrays.asList(3, 4, 5, 6));

Set<Integer> intersection = new HashSet<>(setA);
intersection.retainAll(setB);  // 교집합 → {3, 4}

Set<Integer> difference = new HashSet<>(setA);
difference.removeAll(setB);    // 차집합 → {1, 2}
```

### Map 패턴

```java
Map<String, Integer> map = new HashMap<>();

// 값 꺼낼 때 - getOrDefault (없으면 기본값 반환, 코테 단골!)
map.getOrDefault("홍길동", 0);

// 값 넣을 때 - 빈도수 세기 패턴 (매우 중요!)
map.put(key, map.getOrDefault(key, 0) + 1);

// 순회 방법 3가지
// 1. keySet
for (String key : map.keySet()) {
    Integer value = map.get(key);
}

// 2. entrySet (키+값 둘 다 필요할 때 효율적)
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
}

// 3. forEach (Java 8+)
map.forEach((key, value) -> {
    System.out.println(key + ": " + value);
});

// 정렬이 필요하면 List로 변환
List<String> keyList = new ArrayList<>(map.keySet());
Collections.sort(keyList);
```

---

## 학습 체크

- ✅ Stack은 LIFO 구조
- ✅ Queue는 FIFO 구조
- ✅ ArrayList 삭제 시 인덱스 재정렬됨
- ✅ Set에는 null 하나만 저장 가능
- ✅ Hashtable이 멀티 스레드 환경에 안전
- ✅ HashMap의 키 중복 체크: hashCode() + equals()
- ✅ entrySet()이 keySet()보다 효율적 (키+값 둘 다 필요할 때)
