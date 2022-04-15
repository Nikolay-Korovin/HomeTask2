package java.by.aston.task2.servlets;

import java.by.aston.task2.entity.Product;
import java.by.aston.task2.entity.User;
import java.by.aston.task2.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/addproduct", name = "AddProductServlet")
public class AddProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            try {
                req.setAttribute("productsList", productService.getAllProducts());
            } catch (SQLException e) {
                req.setAttribute("addmessage", "something went wrong");
            }
        }

        getServletContext().getRequestDispatcher("/pages/mainPage").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productname");
        User user = (User) req.getSession().getAttribute("user");
        List<Product> productByName = null;
        try {
            productByName = productService.findProductByName(productName);
        } catch (SQLException e) {
            req.setAttribute("addmessage", productName + " something went wrong");
        }

        if (productByName == null) {
            try {
                productService.createProduct(new Product(productName), user);
            } catch (SQLException e) {
                req.setAttribute("addmessage", productName + " something went wrong");
            }
            req.setAttribute("addmessage", productName + " product successfully added");
            try {
                req.setAttribute("productsList", productService.getAllProducts());
            } catch (SQLException throwables) {
                req.setAttribute("addmessage", "something went wrong");
            }
            getServletContext().getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
            return;
        } else {
            try {
                req.setAttribute("productsList", productService.getAllProducts());
            } catch (SQLException throwables) {
                req.setAttribute("addmessage", "something went wrong");
            }
            req.setAttribute("addmessage", productName + " product already exists");
        }
        getServletContext().getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
    }


}