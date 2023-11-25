package com.example.lab9.Servlets;

import com.example.lab9.Beans.Usuario;
import com.example.lab9.Daos.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "login" : request.getParameter("action");

        switch (action){
            case "login":
                request.getRequestDispatcher("index.jsp").forward(request, response);

                break;

        }






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "login" : request.getParameter("action");

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");


        System.out.println("correo: " + correo + " | password: " + password);
        UsuarioDao usuarioDao = new UsuarioDao();

        if(usuarioDao.validarUsuarioPasswordHashed(correo,password)){
            System.out.println("usuario y password válidos");
            Usuario usuario = usuarioDao.obtenerUsuarioxCorreo(correo);
            HttpSession httpSession = request.getSession(); // inicia sesion
            //Redireccionamos según el rol
            int idRolUser = usuario.getIdRol();
            System.out.println(idRolUser);

            switch (idRolUser){
                case 3: //Decano
                    httpSession.setAttribute("usuarioLogueado",usuario);
                    httpSession.setMaxInactiveInterval(10*60); //10 minutos max de inactividad
                    response.sendRedirect("DecanoServlet");
                    break;
                case 4: //Docente
                    httpSession.setAttribute("usuarioLogueado",usuario);
                    httpSession.setMaxInactiveInterval(10*60); //10 minutos max de inactividad
                    response.sendRedirect("DocenteServlet");
                    break;
            }

        }else{ //Mandamos al login (index)
            System.out.println("usuario o password incorrectos");
            request.setAttribute("err","Usuario o password incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }











    }
}

