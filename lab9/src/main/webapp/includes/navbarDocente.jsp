<%@ page import="com.example.lab9.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% String currentPage = request.getParameter("currentPage"); %>


<nav class="navbar navbar-expand-md navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand">Bienvenido @Docente</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link"
                       href="#">
                        Evaluaciones
                    </a>
                </li>
                <li class="nav-item">

                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;"
                       href="#">
                        (Iniciar sesión)
                    </a>

                    <a class="nav-link disabled">Para logueo NTP</a>

                </li>

                <li class="nav-item">
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;" href="#">(Cerrar sesión)</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
