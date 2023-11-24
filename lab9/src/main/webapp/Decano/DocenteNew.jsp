<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 24/11/2023
  Time: 00:35
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="proxId" type="java.lang.Integer" scope="request"/>
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
        <title>Registro de Docente</title>
    </head>
    <body>
        <div class='container'>
            <div class="row justify-content-center">
                <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">

                    <h1 class='mt-5' style="margin-left: 110px;"><strong>Registro de Docente</strong></h1>
                    <hr>

                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name = "department_id" value="<%=proxId%>">
                    </div>
                    <div class="mb-3">
                        <label for="department_id">Nombre</label>
                        <input type="text" class="form-control form-control-sm" name = "department_id">
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Correo</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>
                    <div class="mb-3">
                        <label for="department_name">Contraseña</label>
                        <input type="text" class="form-control form-control-sm" name="department_name" >
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name="department_name" value="0">
                    </div>

                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name="department_name" value="<%=LocalDate.now()%>">
                    </div>

                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name="department_name" value="<%=LocalTime.now()%>">
                    </div>

                    <a href="<%= request.getContextPath()%>/DecanoServlet?action=listaDocentes" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Guardar" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>