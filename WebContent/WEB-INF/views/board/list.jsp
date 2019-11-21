<%@page import="web.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<% List<Board> list = (List) request.getAttribute("list"); %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<style type="text/css">
table, th {
	text-align: center;
}

tr td:nth-child(2) {
	text-align: left;
}

tr td:not(:first-child), tr th:not(:first-child) {
	border-left: 3px solid white;
}

</style>

<div class="container">
<h1>리스트</h1>
<hr>
</div>

<div class="container">

<table class="table table-hover table-striped">
<tr class="info">
	<th style="width: 10%">BOARDNO</th>
	<th style="width: 40%">TITLE</th>
	<th style="width: 20%">ID</th>
	<th style="width: 10%">HIT</th>
	<th style="width: 20%">WRITTENDATE</th>
</tr>

<c:forEach items="${list }" var="i">
<tr>
	<td>${i.boardno }</td>
	<td><a href="/board/view?boardno=${i.boardno }">${i.title }</a></td>
	<td>${i.id }</td>
	<td>${i.hit }</td>
	<td>${i.writtendate }</td>
</tr>
</c:forEach>

</table>

<c:if test="${empty login }">

<a href="/main"><button>글쓰기</button></a>

</c:if>

<c:if test="${not empty login }">

<a href="/board/write"><button>글쓰기</button></a>

</c:if>

<jsp:include page="/WEB-INF/views/layout/paging.jsp"/>

</div><!-- .container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>