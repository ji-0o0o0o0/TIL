# HTTP

## 1. HTTP(HyperText Transfer Protocol)란?
- 클라이언트와 서버 간 데이터를 주고받기 위한 애플리케이션 계층 프로토콜
    > #### 네트워크 계층구조 
    > - 네트워크는 일반적으로 4계층으로 구성
    > 1. Application Layer
    >   - HTTP, HTTPS, FTP
    > 2. Transport Layer
    >   - TCP, UDP
    > 3. Internet Layer
    >   - IP
    > 4. Network Interface Layer
    >   - Ethernet 
- 웹 브라우저와 웹 서버가 통신할 때 사용
- 예)
  - Client -> Request -> Server
  - Client <- Response <- Server

### Client/ Server
- Client/Server 역할은 요청 방향으로 결정(요청을 보내는 쪽이 Client)
    - 브라우저 →**(HTTP)**→ 백엔드 서버 (브라우저가 Client)
    - 백엔드 →**(HTTP)**→ 다른 API 서버 (백엔드가 Client)
    - 백엔드 →**(JDBC 등 별도 프로토콜)**→ DB (백엔드가 Client)

### 특징
- Stateless
    - HTTP는 **상태를 저장하지 않는다.**
    - 이전 요청과 다음 요청이 서로 독립적 
    - 예)
        - 로그인요청
        -> 다음 요청에서 로그인 정보 모름
        </BR>=> 상태유지를 위해 쿠키/세션/JWT 사용 
- Request/Response
    - HTTP는 요청과 응답 구조로 동작

### HTTP 요청/응답 구조
#### 요청 구조
- Request 구성
  - Method (Get, Post 등)
  - URL
  - Header
  - Body
  </br>
  - 예)
    - GET /users Http/1.1
#### 응답 구조
- Response 구성
    - Status Code
    - Header
    - Body
    </br>
    - 예)
        - HTTP/1.1 200 OK
#### 응답 상태 코드
##### 200번대 (성공)
- 200 OK
- 201 Created

##### 300번대 (리다이렉트)
- 301 Moved Permanently
- 302 Found

##### 400번대 (클라이언트 오류)
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found

##### 500번대 (서버 오류)
- 500 Internal Server Error


### 웹 동작 흐름
1. 브라우저에서 요청
2. 프론트엔드 서버 처리
3. 백엔드 서버 처리
4. DB 조회
5. 백엔드 응답
6. 프론트엔드 렌더링
7. 브라우저 화면 출력

### 면접 질문
Q. HTTP 특징은?
<details>
    <summary>정답</summary>
A. HTTP는 Stateless한 프로토콜로, 각 요청이 독립적이다. 상태 유지를 위해 쿠키/세션/JWT를 사용한다.
또한, 요청/응답 구조로 동작한다.
</details>

</br>

Q. HTTP와 HTTPS의 차이는?
<details>
    <summary>정답</summary>

</details>

</br>

Q. Stateless의 단점과 해결 방법은?
<details>
    <summary>정답</summary>

</details>

</br>


### 💡 **실무 연결**
- HTTP Stateless 특성 때문에 
  로그인 유지를 위해 세션/Redis 같은 별도 저장소가 필요함

</br>

## 2. GET vs POST
|  | GET | POST |
|:---:|:---:|:---:|
| 목적 | 데이터 조회 | 데이터 전송/생성|
|데이터 위치|URL(쿼리스트링)|Body|
|보안|URL 노출|Body에 숨겨짐|
|멱등성| O|X|
|캐싱| O|X|
|히스토리 기록| O(브라우저)|X|
|길이제한| O|거의없음|

### 멱등성
같은 요청을 여러번 해도 결과가 같은 성질
- GET /users/1 → 몇 번 해도 같은 유저 조회 → 멱등성 O
- POST /users → 할 때마다 새로운 유저 생성 → 멱등성 X

### 면접 질문
Q. GET, POST 차이?</br>
<details>
    <summary>정답</summary>
A. GET은 데이터를 URL에 담아 사용하고, POST는 데이터를 Body에 담아 전송/생성할떄 사용</br>
GET은 멱등성 있어 같은 요청을 하면 결과 같지만 POST는 요청마다 새로운 결과 생성
</details>


### 💡 실무 연결: 
- 결제/로그인처럼 민감한 데이터는 URL 노출을 
막기 위해 POST를 사용. 단순 조회는 GET으로 캐싱 이점 활용.
