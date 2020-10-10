<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div style="text-align: center">
    <h1 > Registration Form</h1>
    <h2 > Create Account</h2>
    <p>
            <%
        if(null!=request.getAttribute("newUser"))
    {
        out.println(request.getAttribute("newUser"));
    }

    %>

    <form action="registrationServlet" method="get">

        <i>Create Login <input type="text" name="login">
        </i>
        <br><br>
        <i>Create Password <input type="password" name="password"></i>
        <br><br>

        <select name="role">
            <option value="READER">Reader</option>
            <option value="ADMIN">Admin</option>
        </select>
        <button type="submit">Login</button>
    </form>
    </p>
</div>
</body>
</html>
