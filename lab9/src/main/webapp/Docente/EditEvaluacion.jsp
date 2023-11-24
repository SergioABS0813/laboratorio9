<%--
  Created by IntelliJ IDEA.
  User: Sergio
  Date: 23/11/2023
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id = "eva" scope="request" type="com.example.lab9.Beans.Evaluaciones"/>
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
      <h1 class='mt-5' style="margin-left: 40%;"><strong>Editar Evaluación</strong></h1>
      <hr>
      <form method="POST" action="<%=request.getContextPath()%>/DepartmentServlet?action=actualizar" class="col-md-6 col-lg-6" style="margin-left: 30%;">
        <input type="hidden" name="department_id" value="<%=eva.getIdEvaluaciones()%>"/>
        <div class="mb-3">
          <label for="department_id">Nombre de Alumno</label>
          <input type="text" class="form-control form-control-sm" name = "department_id" value="<%=eva.getNombreEstudiante()%>">
        </div>
        <div class="mb-3">
          <label for="department_name">Código de Alumno (8 dígitos)</label>
          <input type="text" class="form-control form-control-sm" name="department_name" value="<%=eva.getCodigoEstudiantes()%>">
        </div>
        <div class="mb-3">
          <label for="department_name">Correo de Alumno (@gmail.com)</label>
          <input type="text" class="form-control form-control-sm" name="department_name" value="<%=eva.getCorreoEstudiante()%>" >
        </div>
        <div class="mb-3">
          <label for="department_name">Nota del Alumno (0-20)</label>
          <input type="text" class="form-control form-control-sm" name="department_name" value="<%=eva.getNota()%>">
        </div>
        <div class="mb-3">
          <label for="department_name">Curso del Docente</label>
          <input type="text" class="form-control form-control-sm" name="department_name" >
        </div>
        <div class="mb-3">
          <label for="department_name">Semestre Habilitado por ADMIN</label>
          <input type="text" class="form-control form-control-sm" name="department_name" >
        </div>

        <a href="<%= request.getContextPath()%>/DocenteServlet" class="btn btn-danger mt-2">Cancelar</a>
        <button type="submit" class="btn btn-primary mt-2">Actualizar</button>

      </form>

    </div>
    <jsp:include page="../includes/bootstrap_footer.jsp"/>
  </body>
</html>
