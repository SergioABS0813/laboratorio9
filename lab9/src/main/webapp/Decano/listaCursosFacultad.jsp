<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab9.Dto.CursoDto" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Dto.CursoDto>" scope="request" id="listaCursos"/>
<jsp:useBean id="nombreFacu" scope="request" type="java.lang.String" class="java.lang.String"/>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Listar Cursos</title>


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

            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp"/>
            </jsp:include>

            <hr style="border: 2px solid #000000">

            <h1 class='mb-3' style="margin-left: 200px;"><strong>Lista de Cursos de la Facultad: <%=nombreFacu%></strong></h1>

            <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/DecanoServlet?action=registroCurso">Registrar Curso</a>

            <table class="table">
                <tr>
                    <th >ID Curso</th>
                    <th>Código Curso</th>
                    <th>Nombre Curso</th>
                    <th>Fecha Registro</th>
                    <th>Fecha Edición</th>
                    <th>Editar</th>
                    <th>Borrar</th>
                </tr>
                <%
                    for (CursoDto curso : listaCursos) {
                %>
                <tr>
                    <td><%=curso.getIdCurso()%>
                    </td>
                    <td><%=curso.getCodigoCurso()%>
                    </td>
                    <td><%=curso.getNombreCurso()%>
                    </td>
                    <td><%=curso.getFechaRegistro()%>
                    </td>
                    <td><%=curso.getFechaEdicion()%>
                    </td>
                    <td>
                        <a class="btn btn-primary"
                           href="<%=request.getContextPath()%>/DecanoServlet?action=editCurso&idCurso=<%=curso.getIdCurso()%>">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                    </td>
                    <%if (curso.getCantidadEvaluaciones() == 0){ %>
                    <td>
                        <a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger"
                           href="<%=request.getContextPath()%>/DecanoServlet?action=delCurso&idCurso=<%=curso.getIdCurso()%>">
                            <i class="bi bi-trash3"></i>
                        </a>
                    </td>
                    <%}else{

                    }%>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>





