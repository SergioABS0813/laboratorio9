package com.example.lab9.Servlets;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Facultad;
import com.example.lab9.Beans.Usuario;
import com.example.lab9.Daos.CursoDao;
import com.example.lab9.Daos.Facultad_Has_DecanoDao;
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
        HttpSession httpSession = request.getSession(false);

        if (httpSession.getAttribute("usuarioLogueado") != null){

            Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");
            RequestDispatcher view;

            if (usuario.getIdRol() == 3){

                UsuarioDao usuarioDao = new UsuarioDao();
                CursoDao cursoDao = new CursoDao();
                Facultad_Has_DecanoDao facultadHasDecanoDao = new Facultad_Has_DecanoDao();

                // recibimos el parámetro
                String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

                switch (action){
                    case "listaCursos": //home Decano

                        if (usuario != null){
                            usuarioDao.actualizarFechaUltimaSesion(usuario.getIdUsuario(), usuario.getCantidadIngresos());
                            Usuario usuario1 = facultadHasDecanoDao.buscarFacultadxIdDecano(usuario.getIdUsuario());
                            request.setAttribute("nombreFacu", usuario1.getFacultad().getNombreFacultad());
                            request.setAttribute("listaCursos", cursoDao.listaCursoconEvaluaciones());
                            view = request.getRequestDispatcher("Decano/listaCursosFacultad.jsp");
                            view.forward(request, response);
                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }
                        break;
                    case "registroCurso": //Create

                        if (usuario != null){
                            //ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                            Usuario usuario2 = facultadHasDecanoDao.buscarFacultadxIdDecano(usuario.getIdUsuario());
                            int proximoIdCurso = cursoDao.proximoIdCurso();
                            request.setAttribute("idFacultad",usuario2.getFacultad().getIdFacultad());
                            request.setAttribute("proxIdCurso", Integer.valueOf(proximoIdCurso));
                            request.setAttribute("listaDocentes", usuarioDao.listaDocentesDisponibles());
                            view = request.getRequestDispatcher("Decano/CursoNew.jsp");
                            view.forward(request, response);

                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }

                        break;
                    case "editCurso":

                        if (usuario != null){

                            String idCurso = request.getParameter("idCurso");
                            int idCursoInt = Integer.parseInt(idCurso); //Asumismos que lo colocará bien
                            Curso curso = cursoDao.obtenerCursoxId(idCursoInt);
                            request.setAttribute("curso", curso);
                            view = request.getRequestDispatcher("Decano/EditCurso.jsp");
                            view.forward(request, response);
                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }
                        break;
                    case "delCurso":

                        if (request.getParameter("idCurso") != null) {
                            String cursoId = request.getParameter("idCurso");
                            int cursoIdint = 0;
                            try {
                                cursoIdint = Integer.parseInt(cursoId);
                            } catch (NumberFormatException ex) {
                                response.sendRedirect("DecanoServlet?action=listaDocentes");
                            }

                            cursoDao.borrarCurso(cursoIdint);
                        }
                        response.sendRedirect("DecanoServlet?action=listaCursos");

                        break;
                    case "listaDocentes":

                        if (usuario != null){
                            ArrayList<Usuario> listaDocentesTotal = usuarioDao.listaDocentesSinCurso();
                            request.setAttribute("listatotaldoc", listaDocentesTotal);
                            view = request.getRequestDispatcher("Decano/ListaDocentes.jsp");
                            view.forward(request, response);
                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }

                        break;
                    case "registroDocente":

                        if (usuario != null){
                            int proximoId = usuarioDao.proximoIdUsuario() + 1;
                            request.setAttribute("proxId", Integer.valueOf(proximoId));
                            view = request.getRequestDispatcher("Decano/DocenteNew.jsp");
                            view.forward(request, response);
                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }
                        break;
                    case "editDocente":
                        if (usuario != null){
                            String idDoc = request.getParameter("idDocente");
                            int idDocInt = Integer.parseInt(idDoc);
                            Usuario docente = usuarioDao.obtenerUsuarioxId(idDocInt);
                            request.setAttribute("docente", docente);
                            view = request.getRequestDispatcher("Decano/EditDocente.jsp");
                            view.forward(request, response);
                        }else { //Sesion terminada
                            response.sendRedirect("LoginServlet");
                        }
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



        String action = request.getParameter("action") == null ? "listaCursos" : request.getParameter("action");

        UsuarioDao usuarioDao = new UsuarioDao();
        CursoDao cursoDao = new CursoDao();
        Curso curso = new Curso();

        if (httpSession.getAttribute("usuarioLogueado") != null) {
            Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");


            if (usuario.getIdRol() == 3){

                String idCurso = request.getParameter("idCurso");
                String nombreCurso = request.getParameter("nombreCurso");

                switch (action) {
                    case "registroCurso": //Crea en ambas tablas de la db
                        String codigoCurso = request.getParameter("codigoCurso");
                        String facultadDecanoId = request.getParameter("facultadDecanoId");
                        String nameDocenteCurso = request.getParameter("docenteCurso");
                        Usuario docente = usuarioDao.obtenerUsuarioxNombre(nameDocenteCurso); //revisarrrrr
                        String fechaRegistro = request.getParameter("fechaRegistro");
                        String fechaEdicion = request.getParameter("fechaEdicion");


                        curso.setIdCurso(Integer.parseInt(idCurso));
                        curso.setCodigoCurso(codigoCurso);
                        curso.setNombreCurso(nombreCurso);
                        curso.setIdFacultad(Integer.parseInt(facultadDecanoId));
                        curso.setDocente(docente);
                        curso.setFechaRegistro(fechaRegistro);
                        curso.setFechaEdicion(fechaEdicion);
                        cursoDao.registroCurso(curso);

                        response.sendRedirect("DecanoServlet");

                        break;
                    case "editCurso":
                        curso.setIdCurso(Integer.parseInt(idCurso));
                        curso.setNombreCurso(nombreCurso);
                        cursoDao.actualizarCurso(curso);
                        response.sendRedirect("DecanoServlet");
                        break;
                    case "regDoc":
                        String proxId = request.getParameter("proxId");
                        int proxIdint = Integer.parseInt(proxId);
                        String nombreDoc = request.getParameter("nombreDoc");
                        String correoDoc = request.getParameter("correoDoc");
                        String contraDoc = request.getParameter("contraDoc");

                        Usuario docente1 = new Usuario();
                        docente1.setIdUsuario(proxIdint);
                        docente1.setNombre(nombreDoc);
                        docente1.setCorreo(correoDoc);
                        docente1.setPassword(contraDoc);
                        usuarioDao.registrarDocente(docente1);
                        response.sendRedirect("DecanoServlet?action=listaDocentes");

                        break;
                    case "actualizarDoc":
                        String idDocEd = request.getParameter("idDoc");
                        String nombreDocEd = request.getParameter("nombreDoc");
                        Usuario docente2 = new Usuario();
                        docente2.setIdUsuario(Integer.parseInt(idDocEd));
                        docente2.setNombre(nombreDocEd);
                        usuarioDao.actualizarDocente(docente2);
                        response.sendRedirect("DecanoServlet?action=listaDocentes");
                        break;
                }
            }else{
                httpSession.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }


        }else{
            httpSession.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}

