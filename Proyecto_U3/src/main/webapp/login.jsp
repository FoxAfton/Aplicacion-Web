<%-- 
    Document   : login
    Created on : 21 oct 2025, 10:42:24 a.m.
    Author     : Fox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h2>Inicio de sesión</h2>
<form action="login" method="post">
    <label>Usuario:</label>
    <input type="text" name="username" required><br>
    <label>Contraseña:</label>
    <input type="password" name="password" required><br>
    <button type="submit">Iniciar sesión</button>
</form>
<a href="signup.jsp">Crear cuenta nueva</a>
</body>
</html>
