package by.aston.task2.servlets;

import by.aston.task2.entity.User;
import by.aston.task2.service.ProductService;
import by.aston.task2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
        Optional<User> userByLog = null;
        try {
            userByLog = userService.findUserByLog(login);
        } catch (SQLException throwables) {
            req.setAttribute("message", "something went wrong");
        }
        if (userByLog.isPresent()) {
            User user = userByLog.get();
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                try {
                    req.setAttribute("userAndProductsMap",productService.getAllProducts((User)req.getSession().getAttribute("user")));
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