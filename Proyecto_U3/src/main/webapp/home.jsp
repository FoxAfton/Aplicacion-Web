<%-- 
    Document   : home
    Created on : 21 oct 2025, 10:42:10 a.m.
    Author     : Fox
--%>
<%@ page import="com.mycompany.proyecto_u3.model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Home</title></head>
<body>
<nav>
    Bienvenido, <strong><%= user.getUsername() %></strong> |
    <a href="logout">Cerrar sesión</a>
</nav>

<h3>Esta es la página Home protegida</h3>
</body>
</html>
