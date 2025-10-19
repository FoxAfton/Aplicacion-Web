/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.pokeapi.Servlet;

import com.mycompany.pokeapi.Model.Pokemon;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 *
 * @author Fox
 */
@WebServlet(name = "SvPokemon", urlPatterns = {"/SvPokemon"})
public class SvPokemon extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvPokemon</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvPokemon at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        if (nombre == null || nombre.isEmpty()) {
            nombre = "pikachu"; // valor por defecto
        }

        String url = "https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase();
        String resultado = "";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
    HttpGet requestApi = new HttpGet(url);
    try (CloseableHttpResponse responseApi = client.execute(requestApi)) {
        resultado = EntityUtils.toString(responseApi.getEntity());
    }


            JsonObject json = JsonParser.parseString(resultado).getAsJsonObject();

            String name = json.get("name").getAsString();
            int id = json.get("id").getAsInt();
            String imagen = json.get("sprites").getAsJsonObject()
                                .get("front_default").getAsString();

            // Crear el objeto Pokémon
            Pokemon pokemon = new Pokemon(id, name, imagen);

            // Enviar el objeto al JSP
            request.setAttribute("pokemon", pokemon);

        } catch (Exception e) {
            request.setAttribute("error", "Pokémon no encontrado o error al obtener datos: " + e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("resultado.jsp");
        rd.forward(request, response);
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
