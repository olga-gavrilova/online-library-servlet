<%@ page import="com.gmail.olyagavrilova.onlinelibrary.dao.BookDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First JSP</title>
</head>

<body>
<h1> Testing JSP </h1>
<p>

<form action="bookServlet" method="get">
    Book ID<input type="text" name="bookId">
    <input type="submit">
</form>

</p>
</body>
</html>
