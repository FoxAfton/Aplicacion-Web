<%-- 
    Document   : resultado
    Created on : 16 oct 2025, 9:33:54 a.m.
    Author     : Fox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado Pokémon</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <c:if test="${empty error}">
            <h2>${nombre} (#${id})</h2>
            <img src="${imagen}" alt="${nombre}">
            <a href="index.jsp" class="btn">Buscar otro</a>
        </c:if>
    </div>
</body>
</html>

