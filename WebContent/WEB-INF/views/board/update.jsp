<%@page import="web.dto.BoardFile"%>
<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% Board board = (Board) request.getAttribute("board"); %>
<% BoardFile boardFile = (BoardFile) request.getAttribute("boardFile"); %>
    
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">

//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

	try {
		elClickedObj.form.submit();
	} catch (e) {}
}

	

$(document).ready(function() {
	//작성버튼 동작
	$("#btnUpdate").click(function() {
			
		//스마트에디터의 내용을 <textarea>에 적용
		submitContents($("#btnWrite"));
			
		$("form").submit();
	});

	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});

})
</script>

<div id="container">

<form action="<%=response.encodeUrl("/board/update") %>" method="post" 
 enctype="multipart/form-data">
<h1>글 수정</h1>
<hr>

<input type="hidden" value="${board.boardno }" name="boardno" >

제목 : <input type="text" id="title" name="title" required="required"
value="<%=board.getTitle() %>"><br>
<%-- <input type="hidden" name="userid" value="${userid }" /><br> --%>
<!-- 유저 아이디를 받아서 입력하지만 사용자에게 안보이게 처리 -->
<textarea rows="30" cols="80" name="content" id="content" required="required">
${board.content }</textarea><br>

첨부파일 : <input type="file" name="upfile"><br>

<button id="btnUpdate">수정</button>
</form>
<br>
<button id="btnCancle">취소</button>

</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

<!-- 스마트 에디터 적용 코드 -->
<!-- <textarea>태그에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", // 에디터가 적용되는 <textaread>의 id
	sSkinURI: "/resources/se2/SmartEditor2Skin.html", // 에디터 스킨
	fCreator: "createSEditor2"
});

</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>