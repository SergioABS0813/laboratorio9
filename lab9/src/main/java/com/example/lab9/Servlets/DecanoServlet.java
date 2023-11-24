package com.example.lab9.Servlets;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Usuario;
import com.example.lab9.Daos.CursoDao;
import com.example.lab9.Daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DecanoServlet", value = "/DecanoServlet")
public class DecanoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        UsuarioDao usuarioDao = new UsuarioDao();
        CursoDao cursoDao = new CursoDao();

        // recibimos el parámetro
        String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

        switch (action){
            case "listaCursos":
                request.setAttribute("listaCursos", cursoDao.listaCursoconEvaluaciones());
                view = request.getRequestDispatcher("Decano/listaCursosFacultad.jsp");
                view.forward(request, response);
                break;
            case "registroCurso": //Create
                int proximoIdCurso = cursoDao.proximoIdCurso() + 1;
                request.setAttribute("proxIdCurso", Integer.valueOf(proximoIdCurso));
                request.setAttribute("listaDocentes", usuarioDao.listaDocentesDisponibles());
                view = request.getRequestDispatcher("Decano/CursoNew.jsp");
                view.forward(request, response);
                break;
            case "editCurso":
                String idCurso = request.getParameter("idCurso");
                int idCursoInt = Integer.parseInt(idCurso); //Asumismos que lo colocará bien
                Curso curso = cursoDao.obtenerCursoxId(idCursoInt);
                request.setAttribute("curso", curso);
                view = request.getRequestDispatcher("Decano/EditCurso.jsp");
                view.forward(request, response);
                break;
            case "delCurso":

                    break;
            case "listaDocentes":
                ArrayList<Usuario> listaDocentesTotal = usuarioDao.listaDocentesSinCurso();
                request.setAttribute("listatotaldoc", listaDocentesTotal);
                view = request.getRequestDispatcher("Decano/ListaDocentes.jsp");
                view.forward(request, response);
                break;
            case "registroDocente":
                int proximoId = usuarioDao.proximoIdUsuario() + 1;
                request.setAttribute("proxId", Integer.valueOf(proximoId));
                view = request.getRequestDispatcher("Decano/DocenteNew.jsp");
                view.forward(request, response);
                break;
            case "editDocente":
                String idDoc = request.getParameter("idDocente");
                int idDocInt = Integer.parseInt(idDoc);
                Usuario docente = usuarioDao.obtenerUsuarioxId(idDocInt);
                request.setAttribute("docente", docente);
                view = request.getRequestDispatcher("Decano/EditDocente.jsp");
                view.forward(request, response);
                break;
            case "delDoc":
                if (request.getParameter("idDoc") != null) {
                    String docIdStr = request.getParameter("idDoc");
                    int docIdInt = 0;
                    try {
                        docIdInt = Integer.parseInt(docIdStr);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DecanoServlet?action=listaDocentes");
                    }

                    usuarioDao.borrarDocente(docIdInt);
                }
                response.sendRedirect("DecanoServlet?action=listaDocentes");
                break;


        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

