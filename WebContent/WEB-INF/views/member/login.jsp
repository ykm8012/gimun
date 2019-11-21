<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<body>

<form action="<%=response.encodeUrl("/member/login") %>" method="post">
<h1>로그인 하자</h1>
<hr>

ID : <input type="text" id="userid" name="userid"><br>
PW : <input type="password" id="userpw" name="userpw"><br>
<br>
<button>로그인</button>
</form>

</body>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
