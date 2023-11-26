package com.example.lab9.Servlets;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Semestre;
import com.example.lab9.Beans.Usuario;
import com.example.lab9.Daos.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "DocenteServlet", value = "/DocenteServlet")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);

        if (httpSession.getAttribute("usuarioLogueado") != null){
            Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");
            RequestDispatcher view;

            if (usuario.getIdRol() == 4){ //cuando ingresamos a Session solo ingresamos con los atributos NETAMENTE de Usuarios no de relacion con otras tablas
                //Fecha y Hora que ingresa
                UsuarioDao usuarioDao = new UsuarioDao();
                EvaluacionesDao evaluacionesDao = new EvaluacionesDao();
                SemestreDao semestreDao = new SemestreDao();
                Curso_Has_DocenteDao cursoHasDocenteDao = new Curso_Has_DocenteDao();
                CursoDao cursoDao =  new CursoDao();



                String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

                switch (action){
                    case "lista"://home de ingreso

                        usuarioDao.actualizarFechaUltimaSesion(usuario.getIdUsuario(), usuario.getCantidadIngresos());
                        Curso curso = cursoHasDocenteDao.buscarCursoxIdDoc(usuario.getIdUsuario()); //solo idCurso

                        ArrayList<Evaluaciones> listaEvaluaciones = semestreDao.listaEvaluacionConSemestreFinal(curso.getIdCurso());

                        request.setAttribute("listaEvaluaciones", listaEvaluaciones);
                        request.setAttribute("listaSemestre", semestreDao.listaSemestre());
                        Curso curso1 = cursoDao.obtenerCursoxId(curso.getIdCurso());
                        request.setAttribute("nombreCurso", curso1.getNombreCurso());
                        view = request.getRequestDispatcher("Docente/listaEvaluaciones.jsp");
                        view.forward(request, response);


                        break;
                    case "registroEvaluaciones":
                        int proximoIdEvaluacion = evaluacionesDao.proximoIdEvaluacion();
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
            }else{
                httpSession.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }
        else{
            httpSession.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);

        if (httpSession.getAttribute("usuarioLogueado") != null){
            Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");

            if (usuario.getIdRol() == 4) {
                EvaluacionesDao evaluacionesDao = new EvaluacionesDao();
                SemestreDao semestreDao = new SemestreDao();
                Evaluaciones evaluaciones = new Evaluaciones();
                Curso_Has_DocenteDao cursoHasDocenteDao = new Curso_Has_DocenteDao();


                String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

                switch (action){
                    case "evaRegistro":
                        String proxIdEva = request.getParameter("proxIdEva");
                        String nombreAlumno = request.getParameter("nombreAlumno");
                        String codigoAlumno = request.getParameter("codigoAlumno");
                        String correoAlumno = request.getParameter("correoAlumno");
                        String notaAlumno = request.getParameter("notaAlumno");
                        //falta idsemestre

                        evaluaciones.setNota(Integer.parseInt(notaAlumno));
                        evaluaciones.setIdEvaluaciones(Integer.parseInt(proxIdEva));
                        Semestre semestreHabilitado = semestreDao.buscarSemestreHabilitado();
                        evaluaciones.setSemestre(semestreHabilitado);
                        Curso curso = cursoHasDocenteDao.buscarCursoxIdDoc(usuario.getIdUsuario());
                        evaluaciones.setIdCurso(curso.getIdCurso()); // revisar si es null
                        evaluaciones.setNombreEstudiante(nombreAlumno);
                        evaluaciones.setCodigoEstudiantes(codigoAlumno);
                        evaluaciones.setCorreoEstudiante(correoAlumno);

                        evaluacionesDao.registrarEvaluacion(evaluaciones);
                        response.sendRedirect("DocenteServlet");

                        break;
                    case "actualizarEva":
                        String idEva = request.getParameter("idEva");
                        String notaEva = request.getParameter("notaEva");

                        evaluacionesDao.actualizarEva(Integer.parseInt(idEva), Integer.parseInt(notaEva));
                        response.sendRedirect("DocenteServlet");
                        break;
                    case "busqueda":
                        String nombreSemestre = request.getParameter("nombreSemestre");
                        Curso curso1 = cursoHasDocenteDao.buscarCursoxIdDoc(usuario.getIdUsuario());
                        ArrayList<Evaluaciones> listaEvaSem = evaluacionesDao.listaEvaluacionesxSem(nombreSemestre, curso1.getIdCurso());
                        request.setAttribute("nombreCurso",curso1.getNombreCurso());
                        request.setAttribute("listaEvaluaciones", listaEvaSem);


                        request.getRequestDispatcher("Docente/listaEvaluaciones.jsp").forward(request,response);
                        break;

                }




            }else{
                    httpSession.invalidate();
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                }

        }
        else{
            httpSession.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }



    }
}

