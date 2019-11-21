<%@page import="web.dto.BoardFile"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% Board board = (Board) request.getAttribute("board"); %>
<% BoardFile boardFile = (BoardFile) request.getAttribute("boardFile"); %>      

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/board/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/board/update?boardno="+<%=board.getBoardno() %>);
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		$(location).attr("href", "/board/delete?boardno="+<%=board.getBoardno() %>);
	});
})

</script>

<div class="container">

<h1>게시물 조회</h1>
<hr>

<table class="table table-bordered">

<tr>
<td class="info">글번호</td><td colspan="3"><%=board.getBoardno() %></td>
</tr>

<tr>
<td class="info">제목</td><td colspan="3"><%=board.getTitle() %></td>
</tr>

<tr>
<td class="info">아이디</td><td><%=board.getId() %></td>
<td class="info">닉네임</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">조회수</td><td><%=board.getHit() %></td>
<td class="info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="3"><%=board.getWrittendate() %></td>
</tr>

<tr><td class="info"  colspan="4">본문</td></tr>
<tr><td colspan="4"><%=board.getContent() %></td></tr>

<c:if test="<%=boardFile.getOriginname()!=null %>">
<tr><td class="info"  colspan="4">첨부파일</td></tr>
<tr><td colspan="4"><%=boardFile.getOriginname() %> <a href="/board/download?fileno=<%=boardFile.getFileno() %>">다운로드</a></td></tr>
</c:if>

</table>

<!-- 목록 페이지로 요청을 발생시키는 링크 -->
<!-- <a href="/emp/list">목록</a> -->

<!-- 버튼을 통한 페이지 이동 -->
<div class="text-center">
	<button id="btnList" class="btn btn-primary">목록</button>
	<c:if test="${userid eq board.id }">
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
	</c:if>
</div>

</div><!-- .container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>