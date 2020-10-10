<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login In</title>
</head>
<body>
<div style="text-align: center">
<h1 > Login</h1>
<p>
        <%
        if(null!=request.getAttribute("message"))
    {
        out.println(request.getAttribute("message"));
    }

    %>

<form action="loginServlet" method="get">

    <i>Username <input type="text" name="username">
    </i>
    <br><br>
    <i>Password <input type="password" name="password"></i>
    <br><br>
    <button type="submit">Login</button>
</form>
    <form action="registration.jsp" method="get">
        <button type="submit">Sign in</button>
    </form>
</p>
</div>
</body>
</html>
