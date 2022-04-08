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
import java.util.Optional;

@WebServlet(urlPatterns = "/deleteproduct", name = "DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userAndProductsMap", productService.getAllProducts((User) req.getSession().getAttribute("user")));
        getServletContext().getRequestDispatcher("/WEB-INF/mainPage").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productname");
        User user = (User) req.getSession().getAttribute("user");
        Optional<Product> productByName = productService.findProductByName(productName);
        req.setAttribute("userAndProductsMap", productService.getAllProducts((User) req.getSession().getAttribute("user")));
        if (productByName.isPresent()) {

            if (productService.deleteProduct(new Product(productName), user)) {

                req.setAttribute("deletemessage", productName + " product successfully deleted");
            } else {
                req.setAttribute("deletemessage", user.getName() + " you dont have a permission to delete " + productName);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
            return;
        } else {
            req.setAttribute("deletemessage", productName + " product do not exists");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);

    }
}
