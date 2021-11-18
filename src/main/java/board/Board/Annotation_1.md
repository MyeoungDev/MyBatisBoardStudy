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
### Mapper
- 기존읜 스프링은 DAO(Data Access Object) 클래스에 @Repository를 선언해서 해당 클래스가 데이터베이스와 통신하는 클래스임을 나타냈다.
- 하지만, MyBatis는 인터페이스에 @Mapper만 지정해주면,
- XML Mapper에서 메서드의 이름과 일치하는 SQL 문을 찾아 실행한다.
- Mapper 영역은 데이터베이스와의 통신, 즉 SQL 쿼리를 호출한는 것이 전부이며, 다른 로직은 전혀 필요하지 않다.
