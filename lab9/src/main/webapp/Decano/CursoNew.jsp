<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean scope="request" id="listaDocentes" type="java.util.ArrayList<com.example.lab9.Beans.Usuario>"/>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                background-image: url('<%=request.getContextPath()%>/resources/images/white-cubes.jpg');
                background-size: 300% 350%;
                background-position: center;
                background-repeat: no-repeat;
            }
        </style>
        <meta charset="utf-8">
        <jsp:include page="../includes/bootstrap_header.jsp"/>
        <title>Registro de Curso</title>
    </head>
    <body>
        <div class='container'>
            <div class="row justify-content-center">
                <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">

                    <h1 class='mt-5' style="margin-left: 110px;"><strong>Registro de Curso</strong></h1>
                    <hr>

                    <div class="mb-3">
                        <label for="department_id">ID del Curso</label>
                        <input type="text" class="form-control form-control-sm" name = "department_id">
                    </div>
                    <div class="mb-3">
                        <label for="department_id">Código del Curso</label>
                        <input type="text" class="form-control form-control-sm" name = "department_id">
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Nombre del Curso</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>

                    <div class="mb-3">
                        <label for="manager_id">Dep Manager</label>
                        <select name="manager_id" class="form-select" >
                            <option value="SIN-PROFESOR">----Designar Profesor----</option>
                            <% for(Usuario docenteDisponible: listaDocentes){ %>
                            <option value="<%=docenteDisponible.getIdUsuario()%>"> <%=docenteDisponible.getNombre()%> </option>
                            <% } %>
                        </select>
                    </div>

                    <a href="<%= request.getContextPath()%>/DecanoServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Guardar" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
