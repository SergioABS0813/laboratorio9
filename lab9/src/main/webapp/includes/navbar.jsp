<%@ page import="com.example.lab9.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLogueado" scope="session" type="com.example.lab9.Beans.Usuario" class="com.example.lab9.Beans.Usuario" />


<nav class="navbar navbar-expand-md navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand">Bienvenido <%=usuarioLogueado.getNombre()%></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link"
                       href="<%=request.getContextPath()%>/DecanoServlet">
                        Cursos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="<%=request.getContextPath()%>/DecanoServlet?action=listaDocentes">
                        Docentes
                    </a>
                </li>

                <%if (usuarioLogueado.getIdUsuario() == 0){%>
                <li class="nav-item">

                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;"
                       href="<%=request.getContextPath()%>/LoginServlet">
                        (Iniciar sesión)
                    </a>

                </li>
                <%}else{%>
                <li class="nav-item">
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;" href="<%=request.getContextPath()%>/LogoutServlet">(Cerrar sesión)</a>
                </li>
                <%}%>

            </ul>
        </div>
    </div>
</nav>
