<%-- 
    Document   : index
    Created on : 16 oct 2025, 9:33:19 a.m.
    Author     : Fox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Pokémon</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Pokédex JSP</h1>
        <form action="SvPokemon" method="get">
            <input type="text" name="nombre" placeholder="Escribe un nombre de Pokémon...">
            <button type="submit">Buscar</button>
        </form>
    </div>
</body>
</html>

