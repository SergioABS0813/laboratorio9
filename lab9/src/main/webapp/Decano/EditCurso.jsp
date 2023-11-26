<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id = "curso" scope="request" type="com.example.lab9.Beans.Curso"/>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                background-image: url('<%=request.getContextPath()%>/resources/images/white-cubes.jpg');
                background-size: 200% 550%;
                background-position: center;
                background-repeat: no-repeat;
            }
        </style>

        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Editar departamento</title>
    </head>
    <body>
        <div class='container mb-4'>
                <h1 class='mt-5' style="margin-left: 40%;"><strong>Editar Curso</strong></h1>
                <hr>
                <form method="POST" action="<%=request.getContextPath()%>/DecanoServlet?action=editCurso" class="col-md-6 col-lg-6" style="margin-left: 30%;">
                    <input type="hidden" name="idCurso" value="<%=curso.getIdCurso()%>"/>
                    <div class="mb-3">
                        <label for="department_name">Nombre del Curso</label>
                        <input type="text" class="form-control form-control-sm mt-2" name="nombreCurso"
                               value="<%=curso.getNombreCurso()== null ? "" : curso.getNombreCurso()%>" required>
                    </div>

                    <a href="<%= request.getContextPath()%>/DecanoServlet" class="btn btn-danger mt-2">Cancelar</a>
                    <button type="submit" class="btn btn-primary mt-2">Actualizar</button>

                </form>

        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
