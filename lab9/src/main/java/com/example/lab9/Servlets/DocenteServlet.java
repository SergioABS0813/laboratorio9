package com.example.lab9.Servlets;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Daos.EvaluacionesDao;
import com.example.lab9.Daos.SemestreDao;
import com.example.lab9.Daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DocenteServlet", value = "/DocenteServlet")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        UsuarioDao usuarioDao = new UsuarioDao();
        EvaluacionesDao evaluacionesDao = new EvaluacionesDao();
        SemestreDao semestreDao = new SemestreDao();

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action){
            case "lista":
                request.setAttribute("listaEvaluaciones", semestreDao.listaEvaluacionConSemestre());
                view = request.getRequestDispatcher("Docente/listaEvaluaciones.jsp");
                view.forward(request, response);
                break;
            case "registroEvaluaciones":
                int proximoIdEvaluacion = evaluacionesDao.proximoIdEvaluacion() + 1;
                request.setAttribute("proxIdEva", Integer.valueOf(proximoIdEvaluacion));
                view = request.getRequestDispatcher("Docente/EvaluacionNew.jsp");
                view.forward(request, response);
                break;
            case "editEva":
                String idEva = request.getParameter("idEva");
                int idEvaInt = Integer.parseInt(idEva); //Asumismos que lo colocará bien
                Evaluaciones evaluaciones = evaluacionesDao.obtenerEvaxId(idEvaInt);
                request.setAttribute("eva", evaluaciones);
                view = request.getRequestDispatcher("Docente/EditEvaluacion.jsp");
                view.forward(request, response);
                break;
            case "delEvaluacion":
                String idEvaDel = request.getParameter("idEva");
                int idEvaDelint = Integer.parseInt(idEvaDel);
                evaluacionesDao.borrarEvaluacion(idEvaDelint);
                response.sendRedirect("DocenteServlet?action=lista");
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

