<%-- 
    Document   : signup
    Created on : 21 oct 2025, 10:42:33 a.m.
    Author     : Fox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Registro</title></head>
<body>
<h2>Registro de usuario</h2>
<form action="register" method="post">
    Usuario: <input type="text" name="username" required><br>
    Correo: <input type="email" name="email" required><br>
    Contraseña: <input type="password" name="password" required><br>
    <button type="submit">Registrar</but    ton>
</form>
<a href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a>
</body>
</html>