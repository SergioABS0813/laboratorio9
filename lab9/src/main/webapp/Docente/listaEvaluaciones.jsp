<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab9.Beans.Evaluaciones" %>
<%@ page import="com.example.lab9.Beans.Semestre" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Evaluaciones>" scope="request" id="listaEvaluaciones"/>
<jsp:useBean id="usuarioLogueado" scope="session" type="com.example.lab9.Beans.Usuario" class="com.example.lab9.Beans.Usuario" />}
<jsp:useBean id="nombreCurso" scope="request" type="java.lang.String" class="java.lang.String"/>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
        <title>Listar de Evaluaciones</title>


        <style>
            body {
                background-image: url('<%=request.getContextPath()%>/resources/images/white-cubes.jpg');
                background-size: 300% 450%;
                background-position: center;
                background-repeat: no-repeat;
            }
        </style>

    </head>
    <body>
        <div class='container'>

            <jsp:include page="../includes/navbarDocente.jsp">
                <jsp:param name="currentPage" value="emp"/>
            </jsp:include>

            <hr style="border: 2px solid #000000">

            <h1 class='mb-3' style="margin-left: 380px;"><strong>Lista de evaluaciones de <%=nombreCurso%></strong></h1>

            <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/DocenteServlet?action=registroEvaluaciones">Registrar Evaluación</a>

            <div class="col-md-10">

                <form method="post" action="<%=request.getContextPath()%>/DocenteServlet?action=busqueda">

                    <div class="custom-form-group">
                        <input type="text" class="form-control" name="nombreSemestre" placeholder="Buscar por nombre de semestre (YYYY-X)" style="width: 60%; margin-bottom: 8" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Aplicar filtros</button>


                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/DocenteServlet" role="button">Borrar filtros</a>

                </form>
            </div>


            <table class="table">
                <tr>
                    <th >ID Evaluación</th>
                    <th>Nombre Alumno</th>
                    <th>Código Alumno</th>
                    <th>Correo</th>
                    <th>Nota</th>
                    <th>Nombre Semestre</th>
                    <th>Fecha de Edición</th>
                    <th>Editar</th>
                    <th>Borrar</th>
                </tr>
                <%
                    for (Evaluaciones evaluaciones : listaEvaluaciones) {
                %>
                <tr>
                    <td><%=evaluaciones.getIdEvaluaciones()%>
                    </td>
                    <td><%=evaluaciones.getNombreEstudiante()%>
                    </td>
                    <td><%=evaluaciones.getCodigoEstudiantes()%>
                    </td>
                    <td><%=evaluaciones.getCorreoEstudiante()%>
                    </td>
                    <td><%=evaluaciones.getNota()%>
                    </td>
                    <td><%=evaluaciones.getSemestre().getNombre()%>
                    </td>
                    <td><%=evaluaciones.getFechaEdicion()%>
                    </td>
                    <td>
                        <a class="btn btn-primary"
                           href="<%=request.getContextPath()%>/DocenteServlet?action=editEva&idEva=<%=evaluaciones.getIdEvaluaciones()%>">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                    </td>
                    <%if (evaluaciones.getSemestre().getHabilitado() == 1){ %>
                    <td>
                        <a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger"
                           href="<%=request.getContextPath()%>/DocenteServlet?action=delEvaluacion&idEva=<%=evaluaciones.getIdEvaluaciones()%>">
                            <i class="bi bi-trash3"></i>
                        </a>
                    </td>
                    <% }else{

                        }
                        %>

                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
