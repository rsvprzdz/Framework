<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Mybatis</h1>
	<%--
		* 프레임워크 (framework)
		  - 개발자가 보다 편리한 환경에서 개발할 수 있도록 제공하는 틀, 뼈대
		  - 소프트웨어 개발 시 공통적으로 사용되는 라이브러리, 개발도구, 인터페이스 등을 의미
		  
		* 프레임워크 필요성
		  - 규모가 큰 프로젝트에서 작업 시 많은 개발자가 필요
		    => 이러한 개발자들이 통일성 있고, "빠르고", "안정적"으로 개발하기 위해 프레임워크 필요함
		    => 프레임워크 통하여 생산성을 높일 수 있음
			
		* 프레임워크 특징
		  - 개발자들이 따라야하는 가이드라인 제공
		    = 자유롭게 설계 또는 구현하지 않고, 가이드라인에 따라 설계하고 구현해야 함
		  - 개발 범위가 정해져 있음
		  - 개발자들을 위한 다양한 도구들을 지원
		  
		* 장점
		  - 개발 시간 단축
		  - 유지보수 용이
		
		* 단점
		  - 익숙해지는 데 시간이 필요
		  - 프레임워크에 의존 시 개발자들의 능력이 떨어짐
		  
		* 종류
		  - 영속성 프레임워크 : 데이터 관련 CRUD 기능들을 보다 편리할게 작업할 수 있도록 제공해주는 프레임워크
		  				  ex) mybatis, hibernate, jpa, ...
		  - 자바 프레임워크 : 웹 어플리케이션에 초점을 맞춰 필요한 요소들을 모듈화하여 제공해주는 프레임워크
		  				  ex) spring (framework)
		  - 화면 구현 프레임워크 : front-end를 보다 쉽게 구현할 수 있게 제공해주는 프레임워크
		  				  ex) bootstrap
		  - 기능 및 지원 프레임워크 : 특정 기능이나 업무 수행에 도움을 줄 수 있게 제공하는 프레임워크
		  				  ex) Log4j 		    
	 --%>
	 
	 <%-- index 페이지가 로딩되자마자 main.jsp 페이지로 바로포워딩 --%>
	 <jsp:forward page="WEB-INF/views/main.jsp"></jsp:forward>
</body>
</html>