<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 24/11/2023
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab9.Beans.Usuario" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9.Beans.Usuario>" scope="request" id="listatotaldoc"/>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Listar Cursos</title>


        <style>
            body {
                background-image: url('<%=request.getContextPath()%>/resources/images/white-cubes.jpg');
                background-size: 350% 400%;
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

            <h1 class='mb-3' style="margin-left: 430px;"><strong>Lista de los Docentes</strong></h1>

            <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/DecanoServlet?action=registroDocente">Registrar Docente</a>

            <table class="table">
                <tr>
                    <th >ID Docente</th>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Último Ingreso</th>
                    <th>Cantidad de Ingresos</th>
                    <th>Fecha Registro</th>
                    <th>Fecha Edición</th>
                    <th>Editar</th>
                    <th>Borrar</th>
                </tr>
                <%
                    for (Usuario docente : listatotaldoc) {
                %>
                <tr>
                    <td><%=docente.getIdUsuario()%>
                    </td>
                    <td><%=docente.getNombre()%>
                    </td>
                    <td><%=docente.getCorreo()%>
                    </td>
                    <td><%=docente.getUltimoIngreso()%>
                    </td>
                    <td><%=docente.getCantidadIngresos()%>
                    </td>
                    <td><%=docente.getFechaRegistro()%>
                    </td>
                    <td><%=docente.getFechaEdicion()%>
                    </td>
                    <td>
                        <a class="btn btn-primary"
                           href="<%=request.getContextPath()%>/DecanoServlet?action=editDocente&idDocente=<%=docente.getIdUsuario()%>">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                    </td>
                    <% if (docente.getCurso().getIdCurso() == 0 ){ %>
                    <td>
                        <a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger"
                           href="<%=request.getContextPath()%>/DecanoServlet?action=delDoc&idDoc=<%=docente.getIdUsuario()%>">
                            <i class="bi bi-trash3"></i>
                        </a>
                    </td>
                    <%}else{ %>

                    <%}%>

                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
