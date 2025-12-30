<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="rb" class="com.sunbeam.bean.RegisterBean"/>
<jsp:setProperty name="rb" property="*"/>
<jsp:setProperty name="rb" property="role" value="student"/>
${rb.registerStudent()}
<c:choose>
  <c:when test="${rb.regStatus}"><h2>Congratulation Registration successful</h2>
  <a href="index.jsp"> Login Here</a>
  </c:when>
  <c:otherwise><h3> registration Failed</h3>
  <a href="newuser.jsp"> SignUp again</a>
  </c:otherwise>
</c:choose> 

</body>
</html>