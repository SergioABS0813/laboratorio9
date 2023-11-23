package com.example.lab9.Servlets;

import com.example.lab9.Daos.DecanoDao;
import com.example.lab9.Daos.DocenteDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DocenteServlet", value = "/DocenteServlet")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        DocenteDao docenteDao = new DocenteDao();

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action){
            case "lista":
                request.setAttribute("listaEvaluaciones", docenteDao.listaEvaluaciones());
                view = request.getRequestDispatcher("Docente/listaEvaluaciones.jsp");
                view.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

