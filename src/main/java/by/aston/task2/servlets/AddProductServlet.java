package by.aston.task2.servlets;

import by.aston.task2.entity.Product;
import by.aston.task2.entity.User;
import by.aston.task2.service.ProductService;
import by.aston.task2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/addproduct", name = "AddProductServlet")
public class AddProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/mainPage").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productname");
        User user = (User) req.getSession().getAttribute("user");

        Optional<Product> productByName = productService.findProductByName(productName);
        if (productByName.isEmpty()) {
            productService.createItem(new Product(productName), user);
            getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
            return;
        } else {
            req.setAttribute("message", "user already exists");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }
}
