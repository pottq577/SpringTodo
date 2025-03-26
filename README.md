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

   6-2. [일정 조회 API](#-일정-조회-api-)

   6-3. [일정 수정 API](#-일정-수정-api-)

   6-4. [일정 삭제 API](#-일정-삭제-api-)
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

#### API Path

- `/api/schedules/`

#### Request

- name(String): 일정 작성자명
- todo(String): 일정 내용
- password(String): 클라이언트 비밀번호

#### 성공 시

```json
{
  "name": "name_test",
  "todo": "todo_test",
  "password": "password_test"
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

### [ 도움말 출력 ]

<br/>

### [ 계산 기능 ]

<br/>

### [ 명령어 ]

<br/>

## 💭 마무리

### 느낀 점

### 아쉬운 점

<br/>

## 🙋🏻‍♂️ 정보

[![Velog's GitHub stats](https://velog-readme-stats.vercel.app/api/badge?name=pottq577)](https://velog.io/@pottq577)
