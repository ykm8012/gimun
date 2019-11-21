<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<body>

<div id="wrapper" style="min-height: 400px; margin: 0 auto;" >
<c:if test="${empty login }">

<h1>로그인 후 이용해 주세요</h1>
<hr>

<a href="/member/login"><button>로그인</button></a>
<a href="/member/join"><button>회원가입</button></a>
</c:if>

<c:if test="${not empty login }">
<h1>로그인 완료</h1>
<hr>

<a href="/board/list"><button>게시판 목록</button></a>
<a href="/member/logout"><button>로그 아웃</button></a>
</c:if>
</div>

</body>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>