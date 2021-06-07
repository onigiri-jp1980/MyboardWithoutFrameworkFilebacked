<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>MyBoard without Framework</title>
</head>
<body>
<p>投稿内容の更新</p>
<form action="<c:url value='/update' />" method="post">
 <div id="postForm">
  <input type="hidden" name="id" value="<c:out value="${post.id}" />" />
  <label for="name">お名前：</label><input type="text" size="20" name="name" value="<c:out value="${post.name}" />"><br/>
  <label for="email">Email：</label><input type="text" size="40" name="email" value="<c:out value="${post.email}" />"><br/>
  <label for="msg">本文：</label><br/><textarea name="msg" cols="40" rows="10"><c:out value="${post.msg}" /></textarea><br/>
  <button type="submit">更新</button>
</div>
</form>
<hr />
</body>
</html>