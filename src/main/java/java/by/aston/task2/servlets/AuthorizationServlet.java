package java.by.aston.task2.servlets;

import java.by.aston.task2.entity.User;
import java.by.aston.task2.service.ProductService;
import java.by.aston.task2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/authorization", name = "AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<User> userByLog = null;
        try {
            userByLog = userService.findUserByLog(login);
        } catch (SQLException throwables) {
            req.setAttribute("message", "something went wrong");
        }
        if (userByLog != null) {
            if (userByLog.get(0).getPassword().equals(password)) {
                req.getSession().setAttribute("user", userByLog.get(0));
                try {
                    req.setAttribute("productsList",productService.getAllProducts());
                } catch (SQLException throwables) {
                    req.setAttribute("message", "something went wrong");
                }
                getServletContext().getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
                return;
            } else {
                req.setAttribute("message", "wrong password");
            }
        } else {
            req.setAttribute("message", "user not found");
        }
        getServletContext().getRequestDispatcher("/pages/authorization.jsp").forward(req, resp);
    }
}