<%@ page import="com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gmail.olyagavrilova.onlinelibrary.model.BookDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<p>

    <%
        List<BookDto> books = (List<BookDto>) request.getAttribute("books");

    %>
    <%= books %>
</p>
</body>
</html>
</body>
</html>
