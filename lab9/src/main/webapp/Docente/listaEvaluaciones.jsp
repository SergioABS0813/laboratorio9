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

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Listar de Evaluaciones</title>


        <style>
            body {
                background-image: url('<%=request.getContextPath()%>/resources/images/white-cubes.jpg');
                background-size: 150% 155%;
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

            <h1 class='mb-3' style="margin-left: 380px;"><strong>Lista de evaluaciones de @Curso</strong></h1>

            <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/DocenteServlet?action=registroEvaluaciones">Registrar Evaluación</a>

            <table class="table">
                <tr>
                    <th >ID Evaluación</th>
                    <th>Nombre</th>
                    <th>Código</th>
                    <th>Correo</th>
                    <th>Nota</th>
                    <th>Semestre</th>
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
                    <td>
                        <a class="btn btn-primary"
                           href="<%=request.getContextPath()%>/DocenteServlet?action=editEva&idEva=<%=evaluaciones.getIdEvaluaciones()%>">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                    </td>
                    <%if (evaluaciones.getSemestre().getHabilitado() == 1){ %>
                    <td>
                        <a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger"
                           href="#">
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
