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
<jsp:useBean id="proxIdEva" type="java.lang.Integer" scope="request"/>

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
                <form method="POST" action="DocenteServlet?action=evaRegistro" class="col-md-6 col-lg-6">

                    <h1 class='mt-5' style="margin-left: 110px;"><strong>Registro de Evaluación</strong></h1>
                    <hr>
                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name = "proxIdEva" value="<%=proxIdEva%>">
                    </div>
                    <div class="mb-3">
                        <label for="nombreAlumno">Nombre de Alumno</label>
                        <input type="text" class="form-control form-control-sm" name = "nombreAlumno" required>
                    </div>
                    <div class="mb-3">
                        <label for="codigoAlumno">Código de Alumno (8 dígitos)</label>
                        <input type="text" class="form-control form-control-sm" name="codigoAlumno" required>
                    </div>
                    <div class="mb-3">
                        <label for="correoAlumno">Correo de Alumno (@gmail.com)</label>
                        <input type="text" class="form-control form-control-sm" name="correoAlumno" required>
                    </div>
                    <div class="mb-3">
                        <label for="notaAlumno">Nota del Alumno (0-20)</label>
                        <input type="text" class="form-control form-control-sm" name="notaAlumno" required>
                    </div>

                    <a href="<%= request.getContextPath()%>/DocenteServlet" class="btn btn-danger">Cancelar</a>
                    <input type="submit" value="Guardar" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/bootstrap_footer.jsp"/>
    </body>
</html>
