<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<body>

<form action="<%=response.encodeUrl("/member/join") %>" method="post">
<h1>회원가입 하자</h1>
<hr>

ID : <input type="text" id="userid" name="userid" required="required"><br>
PW : <input type="password" id="userpw" name="userpw" required="required"><br>
NICK : <input type="text" id="usernick" name="usernick" required="required"><br>
<br>

<button>회원가입</button>
</form>

<a href="/main"><button>취소</button></a>

</body>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
