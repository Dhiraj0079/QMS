<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Create New Quiz</h2>
    <form action="processcreatequiz.jsp" method="post" enctype="multipart/form-data">
        Quiz Title: <input type="text" name="title" required><br><br>
        Import Questions : <input type="file" name="questionFile" required><br><br>
        <input type="submit" value="Create and Import">
    </form>
    <br><a href="adminmenu.jsp">Back to Menu</a>
</body>
</html>