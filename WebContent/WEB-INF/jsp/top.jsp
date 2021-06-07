<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <% pageContext.setAttribute("newLineChar", "\n"); %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyBoard without Framework</title>
</head>
<body>
<p>掲示板のサンプルです。</p>
<form action="<c:url value='/insert' />" method="post">
 <div id="postForm">
  <p>投稿フォーム</p>
  <label for="name">お名前：</label><input type="text" size="20" name="name"><br/>
  <label for="email">Email：</label><input type="text" size="40" name="email"><br/>
  <label for="msg">本文：</label><br/><textarea  name="msg" cols="40" rows="10"></textarea><br/>
  <button type="submit">投稿</button>
</div>
</form>

<hr>
<div id="posts">

 <c:forEach var="post" items="${list}" varStatus="status">
  <div class="post">
   No.<c:out value="${post.id}" /><br />
   名前：<c:out value="${post.name}" /><br />
   Email:<c:out value="${post.email}" /><br />
   本文<p>
   <c:forEach var="line" items="${fn:split(post.msg, newLineChar)}"><c:out value="${line}" /><br /></c:forEach>
    </p>
   <div align="right">
   投稿日時：<c:out value="${post.postedAt}" /><br />
   更新日時：<c:out value="${post.updatedAt}" /><br />
    <form action="<c:url value='/delete' />" method="post" style="display: inline">
       <input type="hidden" name="id" value="<c:out value="${post.id}" />" />
       <button type="submit">削除</button>
       <input type="button" onclick="location.href='<c:url value='/update'/>?id=<c:out value="${post.id}"/>'" value="更新" />
      </form>
      <form style="display: inline">
     </form>
   </div>
  </div>
  <hr />
 </c:forEach>
</div>
</body>
</html>
