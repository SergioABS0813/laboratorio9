package com.example.lab9.Servlets;

import com.example.lab9.Daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DecanoServlet", value = "/DecanoServlet")
public class DecanoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        UsuarioDao usuarioDao = new UsuarioDao();

        // recibimos el parámetro
        String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

        switch (action){
            case "listaCursos":
                request.setAttribute("listaCursos", usuarioDao.listaCursos());
                view = request.getRequestDispatcher("Decano/listaCursosFacultad.jsp");
                view.forward(request, response);
                break;
            case "registroCurso":
                request.setAttribute("listaDocentes", usuarioDao.listaDocentesDisponibles());
                view = request.getRequestDispatcher("Decano/CursoNew.jsp");
                view.forward(request, response);
                break;

        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

