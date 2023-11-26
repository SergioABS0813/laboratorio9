<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 24/11/2023
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id = "docente" scope="request" type="com.example.lab9.Beans.Usuario"/>
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
        <title>Editar Docente</title>
    </head>
    <body>
        <div class='container mb-4'>
            <h1 class='mt-5' style="margin-left: 40%;"><strong>Editar Docente</strong></h1>
            <hr>
            <form method="POST" action="<%=request.getContextPath()%>/DecanoServlet?action=actualizarDoc" class="col-md-6 col-lg-6" style="margin-left: 30%;">
                <input type="hidden" name="idDoc" value="<%=docente.getIdUsuario()%>"/>
                <div class="mb-3">
                    <label for="department_name">Nombre del Docente</label>
                    <input type="text" class="form-control form-control-sm mt-2" name="nombreDoc"
                           value="<%=docente.getNombre()== null ? "" : docente.getNombre()%>" required>
                </div>

                <a href="<%= request.getContextPath()%>/DecanoServlet?action=listaDocentes" class="btn btn-danger mt-2">Cancelar</a>
                <button type="submit" class="btn btn-primary mt-2">Actualizar</button>

            </form>

        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
