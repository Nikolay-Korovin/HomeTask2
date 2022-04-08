package by.aston.task2.servlets;

import by.aston.task2.dao.UserDao;
import by.aston.task2.entity.User;
import by.aston.task2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private final UserService registrationService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> userByLog = registrationService.findUserByLog(login);
        if (userByLog.isEmpty()) {
            registrationService.createUser(new User(name, login, password));
            resp.sendRedirect("/WEB-INF/home.jsp");
            return;
        } else {
            req.setAttribute("message", "user already exists");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }
}