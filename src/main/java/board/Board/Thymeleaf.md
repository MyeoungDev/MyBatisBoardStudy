#Thymeleaf 정리

[타임리프 공식 사이트](https://www.thymeleaf.org/)

## 템플릿 엔진(Template Engine)

- 웹 서비스를 만들 떄에는 서버의 데이터와 정적자원(html, css, image)을 조합해야 한다.
- 서버에서 데이터를 보내 웹 서비스를 만드는 방법에는 크게 2가지가 있다.
    - #### SPA(Single Page Application)
        - 최초 한번 전체페이지를 다 불러오고 응답데이터만 페이지 특정부분 렌더링.
    - #### SSR(Server Side Rendering)
        - 전통적인 웹 애플리케이션 방식. 요청시마다 서버에서 처리한 후 새로고침으로 페이지에 대한 응답.

보통 자바에서 웹 개발시 JSP(Java Server Page)를 이용하여 진행한다.
<br>
JSP를 사용하면 <% %>형태의 스크립트릿을 사용하여 개발한다.
<br>
그러나 이 방식은 스크립트릿과 HTML이 혼재된 상태가 되고 HTML 태그의 반복적인 사요애으로 인해 수정하기 어려운 상황이 된다.
<br>
이러한 상태를 해결할 수 있는 것이 템플릿 엔진이다.

####템플릿 엔진이란 HTML(Markup)과 데이터를 결합한 결과물을 만들어 주는 도구이다.
타임리프는 템플릿 엔진 중 하나로, Spring Boot에서는 JSP가 아닌 Thymeleaf 사용을 권장하고 있다.

```gradle
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```

Gradle에 dependency를 추가해준다.

```html
<html lang="ko" xmlns:th="http://www.thymeleaf.org"
```
Gradle을 통해 라이브러리를 추가 한 후 
<br>
html 태그에 위의 코드를 추가해주는 것이 필요하다.
<br>
이제부터는 내가 사용하면서 썻던 Thymeleaf 문법을 작성할 것이다.

## xmlns:th
- 타임리프의 th속성을 사용하기 위해 선언된 네임스페이스이다.
- 순수 HTML로만 이루어진 페이지의 경우, 선언하지 않아도 무관하다.

## th:text
- JSP의 EL 표현식인 ${}와 마찬가지로 타임리프도 ${} 표현식을 사용해서 컨트롤러에서 전달받은 데이터에 접근할 수 있다.
- 해당 속성은 일반적인 텍스트 형식으로 화면에 출력한다.

## th:fragment
- ```<head>```태그에 해당 속성을 사용해서 fragment의 이름을 지정한다.
- fragment는 다른 HTML에서 include 또는 replace 속성을 사용해서 적용할 수 있다.

## th:block
- layoutL:fragment 속성에 이름을 지정해서 실제 Content 페이지의 내용을 채우는 기능이다.
- 해당 기능은 동적(Dynamic)인 처리가 필요할 때 사용된다.

## th:replace
- JSP의 ```<include>``` 태그와 유사한 속성이다.
- th:fragment을 통해 설정한 이름을 찾아 해당 코드로 치환한다.

## th:href
- ```<a>``` 태그의 href 속성과 동일하다.
- 웹 애플리케이션을 구분하는 콘텍스트 경로(Context Path)를 포함한다.

## xmlns:layout, xmlnslayout:decorator
- xmlns:layout은 타임리프의 레이아웃 기능을 사용하기 위한 선언이다.
- xmlnslayout:decorator 레이아웃으로 basic.html을 사용하겠다는 의미이다.

Gradle 추가

```	gradle 
implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect' /* Thymeleaf Layout */
```

선언방법

```html
<html lang="ko" xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorator="board/layout/basic">
```
위의 방식 오류

```
2021-11-21 17:08:00.095  WARN 22472 --- [nio-8080-exec-1] n.n.u.t.decorators.DecoratorProcessor    : The layout:decorator/data-layout-decorator processor has been deprecated and will be removed in the next major version of the layout dialect.
```
해결

```layout:decorator="board/layout/basic"``` 을

```layout:decorate="~{board/layout/basic}``` 으로 변경

즉, 아래의 코드처럼 변경 해야 한다.
```html
<html lang="ko" xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{board/layout/basic}">
```


## th:block layout:fragment
- layout:fragment 속성에 이름을 지정해서 실제 Content 페이지의 내용을 채우게 된다.
- 에를 들어 글쓰기 페이지는 "write page"로, 게시글 리스트 페이지는 "list page" 이런식 이다.
- 즉, 페이지마다 타이틀을 다르게 처리하고 싶을 때 해당 속성을 이용해서 타이틀을 동적(Dynamic)으로 처리할 수 있다.

## th:action
- ```<form>```태그 사용시 해당 경로로 요청을 보낼때 사용한다.


## th:object
- ```<form>```태그에서 submit을 할 때, 데이터가 th:object에 설정해둔 객체로 받아진다.
- 즉, 컨트롤러와 뷰(화면) 사이의 DTO클래스의 객체이다.

``` html
<form class="form-horizontal" th:action="@{/board/register.do}" th:object="${board}" method="post">
```

## th:field
- 위의 th:object 속성을 이용하면, th:field를 이용해서 HTML 태그에 멤버 변수를 매핑 할 수 있다.
- th:field을 이용한 사용자 입력 필드(input, textarea 등)는 id, name, value 속성 값이 자동으로 매핑된다. 
- 그렇기에, 각 속성을 따로 지정할 필요가 없다.
- th:field는 ${}표현식이 아닌, *{}표현식을 사용한다.
- th:object와 th:field는 컨트롤러에서 특정 클래스의 객체를 전달 받은 경우에만 사용 가능하다.

```html
<input type="text" th:field="*{title}" class="form-control" placeholder="제목을 입력해 주세요." />
```

## th:checked
- 체크박스의 경우, th:checked 속성을 이용해서 조건이 "true"에 해당하면, checked 속성을 적용한다.

## th:inline="javascript"
- ```<script>``` 태그에 th:inline 속성을 javascript로 지정해야 자바스크립트를 사용할 수 있다.

## th:if, th:unless
- th:if는 if 문과 동일하고, th:unless는 else 문과 같다고 볼 수 있다.
- th:unless는 일반적인 언어의 else 문과는 달리 th:if에 들어가는 조건과 동일한 조건을 지정해야 한다.

## th:each
- th:each는 JSTL의 ```<c:foreach>```, 자바의 forEach와 유사한 기능이다.

## th URI GET 파라미터 추가 방식
- 일반적인 GET 파라미터 추가 방식 

```/board/view.do?idx=${idx}&page=${page}```

- 타임리프 GET 파라미터 추가 방식

```/board/view.do(idx=${idx},page=${page}```

## <![CDATA[]]>
- 타임리프는 '<','> '태그를 엄격하게 검사하기 때문에 자바스크립트 코드는 꼭 CDATA로 묶어줘야 한다.
- CDATA는 특수문자를 전부 문자열로 치환할 때 사용한다.

---
# Thymeleaf 정리 - 2

## th:with
- JSTL의 `<c:set/>`과 마찬가지로 변수를 선언할 때 사용한다.

