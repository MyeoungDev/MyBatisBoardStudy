# Annotaiton 정리
 
### @EnableAutoConfiguration
- 스프링 부트는 개발에 필요한 몇 가지 필수적인 설정들의 처리가 되어있다.
- 해당 애노테이션에 의해 다양한 설정들의 일부가 자동으로 완료된다.

### @ComponentScan
- 기존의 XML 설정 방식의 스프링은 빈(Bean)의 등록 및 스캔을 위해
수동으로 ComponentScan을 여러 개 선언하는 방식을 사용하였다.
- 스프링 부트는 해당 애너테이션에 의해 자동으로 컴포넌트 클래스를 검색,
- 스프링 애플리케이션 콘텍스트(IoC 컨테이너)에 빈(Bean)으로 등록합니다. 
- 의존성 주입 과정이 더욱 간편해졌다고 생각할 수 있습니다.

### @Configuration
- @Configuration이 선언된 클래스는 자바 기반의 설정 파일로 인식된다.

### @Autowired
- 빈(Bean)으로 등록된 인스턴스(이하 객체)를 클래스에 주입하는데 사용
- @Autowired 이외에도 @Resource, @Inject 등이 존재

### ApplicationContext
- ApplicattionContext는 스프링 컨테이너(Spring Container)중 하나이다.
- 스프링 컨테이너는 빈(Bean)의 생성과 사용, 관계, 생명 주기 등을 관리.
- 빈(Bean)은 쉽게 이해하면 객체이다.
- 각 클래스 간의 의존적인 문제가 많으면 '결합도가 높다'라고 표현
- 이 문제를 컨테이너에 빈(Bean)을 주입받는 방법으로 해결 가능.

### @Bean
- Configuration 클래스의 메서드 레벨에만 지정이 가능하다.
- @Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈(Bean)으로 등록된다.

<br>

---
# 2편
### Mapper
- 기존읜 스프링은 DAO(Data Access Object) 클래스에 @Repository를 선언해서 해당 클래스가 데이터베이스와 통신하는 클래스임을 나타냈다.
- 하지만, MyBatis는 인터페이스에 @Mapper만 지정해주면,
- XML Mapper에서 메서드의 이름과 일치하는 SQL 문을 찾아 실행한다.
- Mapper 영역은 데이터베이스와의 통신, 즉 SQL 쿼리를 호출한는 것이 전부이며, 다른 로직은 전혀 필요하지 않다.

### @Service
- 해당 클래스가 비지니스 로직을 담당하는 서비스 클래스임을 의미.

### @Controller
- 해당 클래스가 사용자의 요청과 응답을 처리하는 컨트롤러 클래스임을 의미.

### @GetMapping
- 기존의 URI 매핑) @RequestMapping(value = "...", method = RequestMethod.XXX)
- @RequestMapping을 이용해서 value에는 URI 값을, method 속성에는 HTTP 요청 메서드를 지정하는 방식 사용.
- 스프링 4.3버전부터 @GetMapping, @PostMapping 등, 요청 메서드의 타입별로 매핑 처리 가능.

### @RequestParam
- 뷰(화면)에서 전달받은 파라미터를 처리하는데 사용 된다.
- value 타입은 String 으로 파라미터 이름을 나타낸다.
- required 타입은 boolean 으로 해당 파라미터가 반드시 필수 인지 여부이다. default값은 true이다.
```java
public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {

```
예를 들어, 게시글 리스트 페이지에서 게시글 등록 페이지로 이동하면,
<br>

게시글 번호(idx)는 null로 전송된다.
<br>

하지만, 게시글 상세 페이지에서 수정하기 버튼을 클릭하면
<br>

컨트롤러 게시글 번호(idx)가 파라미터로 전송되고, 컨트롤러는 전달받은 게시글 번호(idx)를 전달하게 된다.

---
# 3편

### @Component
- 스프링 컨테이너에 빈(Bean)으로 등록하기 위한 애너테이션이다.
- @Bean 은 개발자가 제어할 수 없는 외부 라이브러리를 빈(Bean)으로 등록할 떄 사용하고,
- @Component 는 개발자가 직접 정의한 클래스를 빈(Bean)으로 등록할때 사용한다.
