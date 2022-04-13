package by.aston.task2.servlets;

import by.aston.task2.entity.Product;
import by.aston.task2.entity.User;
import by.aston.task2.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(urlPatterns = "/deleteproduct", name = "DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            try {
                req.setAttribute("userAndProductsMap", productService.getAllProducts((User) req.getSession().getAttribute("user")));
            } catch (SQLException throwables) {
                req.setAttribute("deletemessage", " something went wrong");
            }
        }
        getServletContext().getRequestDispatcher("/pages/mainPage").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productname");
        User user = (User) req.getSession().getAttribute("user");
        Optional<Product> productByName = null;
        try {
            productByName = productService.findProductByName(productName);
        } catch (SQLException throwables) {
            req.setAttribute("deletemessage", productName + " something went wrong");
        }

        if (productByName.isPresent()) {
            try {
                if (productService.deleteProduct(new Product(productName), user)) {
                    req.setAttribute("userAndProductsMap", productService.getAllProducts((User) req.getSession().getAttribute("user")));
                    req.setAttribute("deletemessage", productName + " product successfully deleted");
                } else {
                    req.setAttribute("deletemessage", user.getName() + " you dont have a permission to delete " + productName);
                }
            } catch (SQLException throwables) {
                req.setAttribute("deletemessage", productName + " something went wrong");
            }
            getServletContext().getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
            return;
        } else {
            try {
                req.setAttribute("userAndProductsMap", productService.getAllProducts((User) req.getSession().getAttribute("user")));
            } catch (SQLException throwables) {
                req.setAttribute("deletemessage", productName + " something went wrong");
            }
            req.setAttribute("deletemessage", productName + " product do not exists");
        }
        getServletContext().getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);

    }
}
