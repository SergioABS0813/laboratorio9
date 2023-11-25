package com.example.lab9.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "login" : request.getParameter("action");

        switch (action) {
            case "register":
                request.getRequestDispatcher("pages/system/register.jsp").forward(request, response);
                break;
            case "confirm_account":
                request.getRequestDispatcher("/pages/system/confirm_account.jsp").forward(request, response);
                break;
            case "validation_complete":
                request.getRequestDispatcher("/pages/system/validation_complete.jsp").forward(request, response);
                break;
            case "forgot_passwd":
                request.getRequestDispatcher("pages/system/password_recovery/email.jsp").forward(request, response);
                break;
            case "unvalid_session":
                request.getRequestDispatcher("pages/system/unvalid_session.jsp").forward(request, response);
                break;
            case "login":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "login" : request.getParameter("action");







    }
}

