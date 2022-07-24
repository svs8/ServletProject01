package com.servletproject01;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"}
)

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        String regexName = "^[A-Z]{1}[a-zA-Z]{2,}$";
        String regexPassword = "^[A-Z]{1}[a-z0-9!@#$%^&*]{7,}$";


        if ( user.matches(regexName) &&  pwd.matches(regexPassword)) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is wrong.</font>");
                requestDispatcher.include(request, response);
            }
        }
    }
