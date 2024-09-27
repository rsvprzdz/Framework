<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mybatis</title>
    <style>
    
        .login-area > * { float: right;}
    
        .login-area a { text-decoration: none; color: black; font-size: 12px; }
    
        .nav-area { background-color: rgb(87, 206, 57);}
        .menu { display: table-cell; width: 150px; height: 50px;}
        .menu a { text-decoration: none; 
                color: white; 
                font-size: 20px; 
                font-weight: 900; 
                display: inline-block;
                width: 100%; 
                height: 100%; 
                line-height: 50px;}
        .menu a:hover { background-color: lightsalmon; }
    </style>

	


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<body>
<%
	String contextPath = request.getContextPath();		// => /jsp
	
	String alertMsg = (String)session.getAttribute("alertMsg");
%>
	<% if (alertMsg != null) { %>
		<script>
			alert("<%= alertMsg %>");
		</script>
		<% session.removeAttribute("alertMsg"); %>
	<% } %>
	<h1 align="center">Hello My World!</h1>
	
    <div class="login-area">

		<c:choose>
			<c:when test="${ empty loginUser }">
	        <!-- 로그인 전-->
	        <form action="<%= contextPath %>/login.me" method="post">
	        
	            <table>
	                <tr>
	                    <th>아이디</th>
	                    <td>
	                        <input type="text" name="userId" required>
	                    </td>
	                </tr>
	                <tr>
	                    <th>패스워드</th>
	                    <td>
	                        <input type="password" name="userPwd" required>
	                    </td>
	                </tr>
	                <tr>
	                    <th colspan="2">
	                        <button type="submit">로그인</button>
	                        <button type="button" onclick="enrollPage();">회원가입</button>
	
	                    </th>
	                </tr>
	            </table>
	            <script>
	                // 회원가입 페이지 요청
	                function enrollPage(){
										// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp";
										// => 웹 페이지에서 폴더 구조를 그대로 노출하게 되면 보안에 취약하기 때문에 서블릿을 통해 페이지를 이동시켜줄거임
										
										// 서블릿을 사용하여 페이지만 요청
										location.href = "<%= contextPath %>/enrollForm.me";
	                }
	            </script>
	        </form>
			</c:when>
			<c:otherwise>
	        <!-- 로그인 후 -->
	        <div>
	            <b>${ loginUser.userName }</b>님의 방문을 환영합니다 ^^ <br><br>
	            <div align="center">
	                <a href="<%= contextPath %>/myPage.me">마이페이지</a>
	                <a href="<%= contextPath %>/logout.me">로그아웃</a>
	            </div>
	        </div>
	        </c:otherwise>
        </c:choose>
    </div>

    <br clear="both">
    <br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="<%= contextPath %>">HOME</a></div>
        <div class="menu"><a href="#">소개</a></div>
        <div class="menu"><a href="#">축제정보</a></div>
        <div class="menu"><a href="#">축제홍보</a></div>
    </div>
    
    <script>
		// 브라우저 탭 (제목) 변경
		document.title='hello Mybatis';
	</script>
</body>
</html>