package controllers;

import entity.Product;
import repository.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showProducts")
public class ShowProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> allProducts = ProductRepository.getAllProducts();
        resp.setContentType("text/html");

        resp.getWriter().println(allProducts.isEmpty() ? "<h1>There are no Products</h1>" : "<h1>All Products</h1>");
        resp.getWriter().println("<ul>");
        allProducts.forEach(product -> {
            try {
                resp.getWriter().println("<li>Product ID: " + product.getId() + " | Name: " + product.getName() +
                        " | Price: $" + product.getPrice() + "</li>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        resp.getWriter().println("</ul>");
        resp.getWriter().println("<a href='index.html'>Back to Home</a>");
    }
}