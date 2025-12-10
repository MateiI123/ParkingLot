package com.proiect.parkinglotproject.servlets;
import com.proiect.parkinglotproject.common.UserDto;
import com.proiect.parkinglotproject.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Users", value = "/Users")
public class User extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();

        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/Pages/users.jsp")
                .forward(request, response);
    }
}