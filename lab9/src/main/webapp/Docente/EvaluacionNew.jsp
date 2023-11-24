<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

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
        <title>Registro de Evaluación de Alumno</title>
    </head>
    <body>
        <div class='container'>
            <div class="row justify-content-center">
                <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">

                    <h1 class='mt-5' style="margin-left: 110px;"><strong>Registro de Evaluación</strong></h1>
                    <hr>
                    <div class="mb-3">
                        <label for="department_id">Nombre de Alumno</label>
                        <input type="text" class="form-control form-control-sm" name = "department_id">
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Código de Alumno (8 dígitos)</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Correo de Alumno (@gmail.com)</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Nota del Alumno (0-20)</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>

                    <a href="<%= request.getContextPath()%>/DocenteServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Guardar" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
