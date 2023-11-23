package com.example.lab9.Servlets;

import com.example.lab9.Daos.DecanoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DecanoServlet", value = "/DecanoServlet")
public class DecanoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        DecanoDao decanoDao = new DecanoDao();

        // recibimos el parámetro
        String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

        switch (action){
            case "listaCursos":
                request.setAttribute("listaCursos", decanoDao.listaCursos());
                view = request.getRequestDispatcher("Decano/listaCursosFacultad.jsp");
                view.forward(request, response);
                break;
            case "registroCurso":

                break;

        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

