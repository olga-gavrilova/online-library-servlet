<%@ page import="com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<p>

    <%
        Book book = (Book) request.getAttribute("book");

    %>
    <%= book %>
</p>
</body>
</html>
</body>
</html>
