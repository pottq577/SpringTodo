![header](https://capsule-render.vercel.app/api?type=Venom&color=auto&height=300&section=header&text=SpringTodo&fontSize=90&desc=Spring을%20활용한%20일정%20관리%20프로젝트입니다.&descAlignY=70&theme=gruvbox_light)

<br/>

## 🔗 목차

1. [📆 프로젝트 소개](#-프로젝트-소개)
2. [🕰️ 개발 기간](#%EF%B8%8F-개발-기간)
3. [📚 개발 환경](#-개발-환경)
4. [🌳 디렉토리 구조](#-디렉토리-구조)
5. [📝 프로젝트 명세](#-프로젝트-명세)

   5-1. [ERD](#erd)

   5-2. [API 명세서](#api-명세서)

6. [🛠️ 주요 기능](#%EF%B8%8F-주요-기능)

   6-1. [일정 생성 API](#-일정-생성-api-)

   6-2. [전체 일정 조회 API](#-전체-일정-조회-api-)

   6-3. [선택 일정 조회 API](#-선택-일정-조회-api-)

   6-4. [일정 수정 API](#-일정-수정-api-)

   6-5. [일정 삭제 API](#-일정-삭제-api-)
7. [💭 마무리](#-마무리)

<br/>

## 🧮 프로젝트 소개

1️⃣ 일정 생성 & 조회 API 만들기
<br/>
2️⃣ 일정 수정 & 삭제 API 만들기
<br/>
~~🆙 연관 관계 설정~~
<br/>
~~🆙 페이지네이션~~
<br/>
~~🆙 예외 발생 처리~~
<br/>
~~🆙 NULL 체크 및 특정 패턴에 대한 검증 수행~~

Spring을 활용해서 일정 관리 프로젝트의 백엔드 API를 구현한 프로젝트입니다.

Front-end는 구현하지 않고, 데이터 통신과 DB와의 연동 위주로 작성되었습니다.

### 자세한 개발 과정은 아래 블로그를 참조해주세요.

<a href="https://velog.io/@pottq577/TIL-36일차-일정-관리-앱-과제-day-1"><img src="https://img.shields.io/badge/일정 관리 앱 과제 day1-20C997?style=for-the-badge&logo=Velog&logoColor=white"/></a>

<a href="https://velog.io/@pottq577/TIL-37일차-일정-관리-앱-과제-day-2"><img src="https://img.shields.io/badge/일정 관리 앱 과제 day2-20C997?style=for-the-badge&logo=Velog&logoColor=white"/></a>

<a href="https://velog.io/@pottq577/TIL-38일차-일정-관리-앱-과제-day-3"><img src="https://img.shields.io/badge/일정 관리 앱 과제 day3-20C997?style=for-the-badge&logo=Velog&logoColor=white"/></a>

<a href="https://velog.io/@pottq577/TIL-39일차-일정-관리-앱-과제-day-4"><img src="https://img.shields.io/badge/일정 관리 앱 과제 day4-20C997?style=for-the-badge&logo=Velog&logoColor=white"/></a>


<br/>

## 🕰️ 개발 기간

- 2025.03.20 ~ 2025.03.26 (총 6일)

<br/>

## 📚 기술 스택

### Language

[![My Skills](https://skillicons.dev/icons?i=java)](https://skillicons.dev)

### Backend

[![My Skills](https://skillicons.dev/icons?i=spring)](https://skillicons.dev)

### Database

[![My Skills](https://skillicons.dev/icons?i=mysql)](https://skillicons.dev)

### Development Tools

[![My Skills](https://skillicons.dev/icons?i=idea,postman)](https://skillicons.dev)

### SCM

[![My Skills](https://skillicons.dev/icons?i=git,github)](https://skillicons.dev)


<br/>

## 🌳 디렉토리 구조

```java
SpringTodo/
    ├──src/
    │   ├──main/java/com/example/springtodo/
    │   │   ├──controller/
    │   │   │   └──TodoController.java
    │   │   ├──dto/
    │   │   │   ├──TodoRequestDto.java
    │   │   │   └──TodoResponseDto.java
    │   │   ├──entity/
    │   │   │   └──Todos.java
    │   │   ├──repository/
    │   │   │   ├──TodoRepository.java
    │   │   │   └──TodoRepositoryImpl.java
    │   │   ├──service/
    │   │   │   ├──TodoService.java
    │   │   │   └──TodoServiceImpl.java
    │   │   └──SpringTodoApplication.java
    │   ├──main/resources
    │   │   ├──static/
    │   │   │   ├──templates/
    │   │   │   └──application.properties
    ├── .gitignore
    └──README.md
```

<br/>

## 📝 프로젝트 명세

### ERD

| 논리 필드명 | 물리 필드명       | 타입          | NULL 유무  | 코멘트        |
|--------|--------------|-------------|----------|------------
| 일정고유번호 | schedule_id  | BIGINT      | NOT NULL | 일정 식별자 ID  
| 작성자명   | name         | VARCHAR(10) | NOT NULL | 클라이언트 이름   
| 일정 내용  | todo         | TEXT        | NOT NULL | 일정 내용      
| 비밀번호   | password     | VARCHAR(20) | NOT NULL | 클라이언트 비밀번호 
| 작성일    | created_date | TIMESTAMP   | NOT NULL | 일정 등록 날짜   
| 수정일    | updated_date | TIMESTAMP   | NULL     | 일정 수정 날짜   

[🛢️ ERDCloud 에서 보기](https://www.erdcloud.com/d/u5jdt6kexpaeH7i2p)

### API 명세서

| 기능       | Method | API Path                    | Parameters                                  | Request Body                                                                               | Response                                                                                                                                                                                                                           
|----------|--------|-----------------------------|---------------------------------------------|--------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
| 일정 생성    | POST   | api/schedules               | X                                           | {<br/>&emsp;"name": String,<br/>&emsp; "todo": String,<br/>&emsp; "password": String<br/>} | {<br/>&emsp;"schedule_id": Long,<br/>&emsp;"name": String,<br/>&emsp;"todo": String,<br/>&emsp;"created_date": String,<br/>&emsp;"updated_date": String<br/>}                                                                      
| 전체 일정 조회 | GET    | api/schedules               | Query: `updated_date`<br/>e.g. "2025-03-25" | X                                                                                          | [<br/>&emsp;{<br/>&emsp;&emsp;"schedule_id": Long,<br/>&emsp;&emsp;"name": String,<br/>&emsp;&emsp;"todo": String,<br/>&emsp;&emsp;"created_date": String,<br/>&emsp;&emsp;"updated_date": String<br/>&emsp;},<br/>&emsp;...<br/>] 
| 선택 일정 조회 | GET    | api/schedules/{schedule_id} | Path: `schedule_id`                         | X                                                                                          | {<br/>&emsp;"schedule_id": Long,<br/>&emsp;"name": String,<br/>&emsp;"todo": String,<br/>&emsp;"created_date": String,<br/>&emsp;"updated_date": String<br/>}                                                                      
| 선택 일정 수정 | PATCH  | api/schedules/{schedule_id} | Path: `schedule_id`                         | {<br/>&emsp;"name": String,<br/>&emsp;"todo": String,<br/>&emsp;"password": String<br/>}   | {<br/>&emsp;"schedule_id": Long,<br/>&emsp;"name": String,<br/>&emsp;"todo": String,<br/>&emsp;"created_date": String,<br/>&emsp;"updated_date": String<br/>}                                                                      
| 선택 일정 삭제 | DELETE | api/schedules/{schedule_id} | Path: `schdule_id`<br/>Query: `password`    | X                                                                                          | {<br/>&emsp;"message": String<br/>}                                                                                                                                                                                                

자세한 내용은 아래 노션에서 확인해주세요.

[📓 Notion 에서 보기](https://legendary-jaguar-30f.notion.site/Schedule-API-Spec-1bd65d3c37e5802f93f6d3dcd07602ae)

<br/>

## 🛠️ 주요 기능

### [ 일정 생성 API ]

#### HTTP Method

- `POST`

#### API Path

- `/api/schedules/`

#### Request

- name(String): 일정 작성자명
- todo(String): 일정 내용
- password(String): 일정의 비밀번호

```json
{
  "name": "test_name",
  "todo": "test_todo",
  "password": "test_pwd"
}
```

#### Response

```json
{
  "schedule_id": 1,
  "name": "name_test",
  "password": null,
  "todo": "todo_test",
  "created_date": "2025-03-25T19:37:07.48511",
  "updated_date": "2025-03-25T19:37:07.48511"
}
```

<br/>

### [ 전체 일정 조회 API ]

#### HTTP Method

- `GET`

#### API Path

- `api/schedules/`

#### Request

- name(String): 일정 작성자명
- updated_date(String): 수정일

&emsp;

- `updated_date` 로 요청

```json
{
  "updated_date": "2025-03-25"
}
```

- `name` 로 요청

```json
{
  "name": "test_name"
}
```

- `name`, `updated_date` 로 요청

```json
{
  "name": "test_name",
  "updated_dtae": "2025-03-25"
}
```

- 모든 일정 요청

```json
{

}
```

#### Response

- `updated_date` 로 요청 시

```json
[
  {
    "schedule_id": 1,
    "name": "test_name",
    "password": null,
    "todo": "test_todo",
    "created_date": "2025-03-25T19:30:42",
    "updated_date": "2025-03-25T19:30:42"
  },
  ...
]
```

- `name` 로 요청 시

```json
[
  {
    "schedule_id": 1,
    "name": "test_name",
    "password": null,
    "todo": "test_todo",
    "created_date": "2025-03-25T19:30:50",
    "updated_date": "2025-03-25T19:30:50"
  },
  ...
]
```

- `updated_date` , `name` 로 요청 시

```json
[
  {
    "schedule_id": 2,
    "name": "test_name1",
    "password": null,
    "todo": "test_todo2",
    "created_date": "2025-03-25T19:50:50",
    "updated_date": "2025-03-25T19:50:50"
  },
  {
    "schedule_id": 1,
    "name": "test_name1",
    "password": null,
    "todo": "test_todo2",
    "created_date": "2025-03-25T19:30:52",
    "updated_date": "2025-03-25T19:30:52"
  }
]
```

- 모든 일정 요청 시

```json
[
  {
    "schedule_id": 3,
    "name": "test_name3",
    "password": null,
    "todo": "test_todo3",
    "created_date": "2025-03-25T19:36:14",
    "updated_date": "2025-03-26T11:27:40"
  },
  {
    "schedule_id": 2,
    "name": "test_name2",
    "password": null,
    "todo": "test_todo2",
    "created_date": "2025-03-25T19:30:52",
    "updated_date": "2025-03-25T19:30:52"
  },
  ...
]
```

<br/>

### [ 선택 일정 조회API ]

#### HTTP Method

- `GET`

#### API Path

- `api/schedules/{schedule_id}`

#### Request

- URL에서 `schedule_id` 를 지정합니다.

#### Response

```json
{
  "schedule_id": 1,
  "name": "test_name",
  "password": null,
  "todo": "test_todo",
  "created_date": "2025-03-25T19:36:14",
  "updated_date": "2025-03-26T11:27:40"
}
```

<br/>

### [ 선택 일정 수정 API ]

#### HTTP Method

- `PATCH`

#### API Path

- `/api/schedules/{schedule_id}`

#### Request

- URL에서 `schedule_id` 를 지정합니다.
- name(String): 일정 작성자명
- updated_date(String): 수정일
- password(String): 일정 비밀번호

&emsp;

```json
{
  "name": "update_test_name",
  "todo": "update_test_todo",
  "password": "test_pwd"
}
```

#### Response

**✅ 성공 시**

```json
{
  "schedule_id": 1,
  "name": "update_test_name",
  "password": null,
  "todo": "update_test_todo",
  "created_date": "2025-03-25T19:36:14",
  "updated_date": "2025-03-26T11:27:40"
}
```

**❌ 실패 시**

비밀번호가 일치하지 않으면 `400 BAD REQUEST` 상태 코드를 반환합니다.

```json
{
  "timestamp": "2025-03-26T08:45:08.431+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/api/schedules/1"
}
```

<br/>

### [ 선택 일정 삭제 API ]

#### HTTP Method

- `DELETE`

#### API Path

- `/api/schedules/{schedule_id}`

#### Request

- URL에서 `schedule_id` 를 지정합니다.
- password(String): 일정의 비밀번호

#### Response

```json
{
  "password": "1"
}
```

**✅ 성공 시**

아무것도 반환하지 않습니다.

**❌ 실패 시**

비밀번호가 일치하지 않으면 `400 BAD REQUEST` 상태 코드를 반환합니다.

```json
{
  "timestamp": "2025-03-26T08:45:08.431+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/api/schedules/1"
}
```

<br/>

## 💭 마무리

### 느낀 점

스프링을 처음 진행하는 입장에서, 쉽다면 쉽고 어렵다면 어려웠던 프로젝트였던 것 같습니다.

각 레이어 간 상호작용이나, JdbcTemplate, DTO 등 데이터 흐름을 다루는 데 중점으로 진행한 프로젝트라고 생각됩니다.

이번 프로젝트를 통해 '스프링을 이해했다' 보다는,<br/> '스프링은 이렇게 쓰는 거구나' 를 익혀가는 과정으로 생각하고 있습니다.

### 아쉬운 점

예외 발생 시 에러 메세지를 이쁘게 꾸며서 반환하고 싶었는데, 시간과 능력의 부족으로 구현하지 못한 점이 아쉬웠습니다.

각 메소드마다 적절한 예외 처리를 하지 않아 `500 Internal Error` 가 발생할 수도 있습니다.


<br/>

## 🙋🏻‍♂️ 정보

[![Velog's GitHub stats](https://velog-readme-stats.vercel.app/api/badge?name=pottq577)](https://velog.io/@pottq577)
