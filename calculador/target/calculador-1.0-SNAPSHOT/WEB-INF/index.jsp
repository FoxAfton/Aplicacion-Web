<%-- 
    Document   : index.jsp
    Created on : 11 oct 2025, 10:59:28 a.m.
    Author     : Fox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="calculadora">
            <h2>Calculadora basica</h2>
        <form action="index.jsp" method="post">
        <input type="number" name="num1" placeholder="Número 1" required>
        <select name="operacion">
            <option value="suma">+</option>
            <option value="resta">-</option>
            <option value="multiplicacion">*</option>
            <option value="division">/</option>
        </select>
        <input type="number" name="num2" placeholder="Número 2" required>
        <button type="submit">Calcular</button>
    </form>
            double resultado = 0;
        String op = request.getParameter("operacion");
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");
        if (n1 != null && n2 != null) {
            double num1 = Double.parseDouble(n1);
            double num2 = Double.parseDouble(n2);
            switch (op) {
                case "suma": resultado = num1 + num2; break;
                case "resta": resultado = num1 - num2; break;
                case "multiplicacion": resultado = num1 * num2; break;
                case "division": resultado = num2 != 0 ? num1 / num2 : 0; break;
            }
            request.setAttribute("resultado", resultado);
        }
    %>

    <c:if test="${not empty resultado}">
        <div class="resultado">
            <h3>Resultado: <c:out value="${resultado}"/></h3>
        </div>
    </c:if>
            </div>
    </body>
</html>
