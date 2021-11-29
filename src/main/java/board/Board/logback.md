# Logback DB log

### Logback 특징
- 빠른 implementation
- 적은 메모리 공유
- XML로 logging 설정
- maxHistory 설정 값을 이용해 일정 기간이 지나면 로그파일 자동 삭제
- Filter 기능 : 사용자별 level 조정 가능


## 1. Log4JDBC 라이브러리 추가

Gradle
```properties
implementation 'org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16' /* Log4JDBC */
```
Maven

```html
<dependency>
    <groupId>org.bgee.log4jdbc-log4j2</groupId>
    <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
    <version>1.16</version>
</dependency>    
```




## 2. logback-spring.xml 추가하기

scr/main/resources 디렉토리에 logback-spring.xml 추가하기

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">

    <!-- Appenders -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <Pattern>%d %5p [%c] %m%n</Pattern>
        </encoder>
    </appender>

    <appender name="console-infolog" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <Pattern>%d %5p %m%n</Pattern>
        </encoder>
    </appender>

    <!-- Logger -->
    <logger name="com.board" level="DEBUG" appender-ref="console" /> # src/main/java 디렉터리 안의 자바 패키지 경로
    <logger name="jdbc.sqlonly" level="INFO" appender-ref="console-infolog" />
    <logger name="jdbc.resultsettable" level="INFO" appender-ref="console-infolog" />

    <!-- Root Logger -->
    <root level="off">
        <appender-ref ref="console" />
    </root>
</configuration>
```

### XML 선언 태그

|태그| 내용|
|---|---|
appender | 전달받은 로그를 어디에 출력할지 결정한다. (콘솔 출력, 파일 저장, 데이터베이스 저장 등)
encoder | appender에 포함되어 출력할 로그의 형식을 지정한다.
logger | 로그를 출력하는 요소로, level 속성을 통해 출력할 로그의 레벨을 조절하여 appender에 전달한다.


### Log level

| Level | 내용 |
|---|---|
fatal | 아주 심각한 에러가 발생한 상태를 나타낸다.
error | 요청을 처리하던 중 문제가 발생한 상태를 나타낸다.
warn | 프로그램 실행에는 문제가 없지만, 나중에 시스템 에러의 원인이 될 수 있는 경고 메세지를 나타낸다.
info | 어떠한 상태 변경과 같은 정보성 메시지를 나타낸다.
debug | 개발시에 디버그 용도로 사용하는 메세지이다.
trace | 디버그 레벨이 너무 광범위한 것을 해결하기 위해 좀 더 상세한 이벤트를 나타낸다.


### Log Type

| Type | 내용 |
| --- | --- |
sqlonly | SQL을 로그로 남기며, Prepared Statement와 관련된 파라미터는 자동으로 변경되어 SQL을 출력한다.
sqltiming | SQL과 SQL 실행 시간(milliseconds 단위)을 출력한다.
audit | ResultSet을 제외한 모든 JDBC 호출 정보를 출력한다. (JDBC 관련 문제를 추적하는 경우를 제외하고는 사용 권장 X)
resultset | ResultSet을 포함한 모든 JDBC 호출 정보를 출력한다.
resultsettable | SQL 조회 결과를 테이블 형태로 출력한다.
connection | Connection의 연결과 종료에 관련된 로그를 출력한다.

## 3. log4jdbc.log4j2.properties 추가하기

src/main/resources 디렉터리에 log4jdbc.log4j2.properties 추가하기

```properties
log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator
log4jdbc.dump.sql.maxlinelength=0
```

## 4. jdbc-url과 driver-class-name 변경하기

application.properties의 데이터 소스 설정 변경

```properties
#HikariCP 데이터 소스(DataSource)
spring.datasource.hikari.driver-class-name=net.sf.log4jdbc.sql.jdbcapi.DriverSpy
spring.datasource.hikari.jdbc-url=jdbc:log4jdbc:mysql://localhost:3306/DatabaseNamed?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.hikari.username=username
spring.datasource.hikari.password=password
spring.datasource.hikari.connection-test-query=SELECT NOW() FROM dual

#MyBatis
mybatis.configuration.map-underscore-to-camel-case=true
```


<br>
<br>
<br>


### SQL 쿼리 로그 출력 결과

```
2021-11-29 01:52:50,192 DEBUG [board.Board.mapper.BoardMapper.selectBoardDetail] ==>  Preparing: SELECT idx , title , content , writer , view_cnt , notice_yn , secret_yn , delete_yn , insert_time , update_time , delete_time FROM tb_board WHERE delete_yn = 'N' AND idx = ?
2021-11-29 01:52:50,194 DEBUG [board.Board.mapper.BoardMapper.selectBoardDetail] ==> Parameters: 56(Long)
2021-11-29 01:52:50,194  INFO [jdbc.sqlonly] SELECT

        idx
        , title
        , content
        , writer
        , view_cnt
        , notice_yn
        , secret_yn
        , delete_yn
        , insert_time
        , update_time
        , delete_time

        FROM
        tb_board
        WHERE
        delete_yn = 'N'
        AND
        idx = 56

2021-11-29 01:52:50,198  INFO [jdbc.resultsettable] 
|----|------------------|-------------------|-------|---------|----------|----------|----------|------------|------------|------------|
|idx |title             |content            |writer |view_cnt |notice_yn |secret_yn |delete_yn |insert_time |update_time |delete_time |
|----|------------------|-------------------|-------|---------|----------|----------|----------|------------|------------|------------|
|56  |UiUtils 테스트 제목입니다 |UiUtils 테스트 내용입니다. |작성자    |0        |N         |N         |N         |[unread]    |[unread]    |[unread]    |
|----|------------------|-------------------|-------|---------|----------|----------|----------|------------|------------|------------|

```

<br>
<br>

개발중 뿐 아니라 운영시점에도 로그 정보는 매우 중요하다.
<br>
특히 SQL정보는 제대로 된 로그가 아닐 경우 디버깅이 매우 힘들기 때문에,
<br>
SQL에 대한 로그는 각별히 신경써서 셋팅 하는 것이 좋다고 한다.

