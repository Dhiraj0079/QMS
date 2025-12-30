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
<jsp:useBean id="lb" class="com.sunbeam.bean.LoginBean" scope="session"/>
<jsp:setProperty name="lb" property="*" />

${lb.authentication()}

<c:choose>
    <c:when test="${lb.user != null}">
        <c:choose>
            <c:when test="${lb.user.role eq 'admin'}">
                <c:redirect url="adminmenu.jsp"/>
            </c:when>
            <c:otherwise>
                <c:redirect url="studentmenu.jsp"/>
            </c:otherwise>
        </c:choose>
    </c:when>

    <c:otherwise>
        <h2>Login Failed</h2>
        <a href="index.jsp">Login Again</a>
    </c:otherwise>
</c:choose>

</body>
</html>
