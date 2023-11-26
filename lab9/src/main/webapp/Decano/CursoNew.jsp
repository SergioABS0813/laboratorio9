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
<jsp:useBean id="proxIdCurso" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="idFacultad" scope="request" type="java.lang.Integer"/>

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
                <form method="POST" action="DecanoServlet?action=registroCurso" class="col-md-6 col-lg-6">

                    <h1 class='mt-5' style="margin-left: 110px;"><strong>Registro de Curso</strong></h1>
                    <hr>

                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name = "idCurso" value="<%=proxIdCurso%>">
                    </div>
                    <div class="mb-3">
                        <label for="codigoCurso">Código del Curso</label>
                        <input type="text" class="form-control form-control-sm" name = "codigoCurso" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombreCurso">Nombre del Curso</label>
                        <input type="text" class="form-control form-control-sm" name="nombreCurso" required>
                    </div>

                    <div class="mb-3">
                        <input type="hidden" class="form-control form-control-sm" name="facultadDecanoId" value="<%=idFacultad%>">
                    </div>

                    <div class="mb-3">
                        <label for="docenteCurso">Docente del Curso (Docentes disponibles)</label>
                        <select name="docenteCurso" class="form-select">
                            <% for(Usuario docenteDisponible: listaDocentes){ %>
                            <option value="<%=docenteDisponible.getNombre()%>"> <%=docenteDisponible.getNombre()%> </option>
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
