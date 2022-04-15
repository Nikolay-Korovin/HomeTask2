package java.by.aston.task2.servlets;

import java.by.aston.task2.entity.User;
import java.by.aston.task2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private final UserService registrationService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<User> userByLog = null;
        try {
            userByLog = registrationService.findUserByLog(login);
        } catch (SQLException throwables) {
            req.setAttribute("message", "something went wrong");
        }
        if (userByLog == null) {
            try {
                registrationService.createUser(new User(name, login, password));
            } catch (SQLException throwables) {
                req.setAttribute("message", "something went wrong");
            }
            resp.sendRedirect("/home");
            return;
        } else {
            req.setAttribute("message", "user already exists");
        }
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }
}