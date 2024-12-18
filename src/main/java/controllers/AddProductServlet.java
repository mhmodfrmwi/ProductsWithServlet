package controllers;

import entity.Product;
import repository.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String priceParam = req.getParameter("price");
        resp.setContentType("text/html");

        handleProductAddition(name, priceParam, resp);
        resp.getWriter().println("<br><a href='addProduct.html'>Back to Add</a>");
    }

    private void handleProductAddition(String name, String priceParam, HttpServletResponse resp) throws IOException {
        try {
            double price = Double.parseDouble(priceParam);
            Product product = ProductRepository.addProduct(name, price);
            displayResponse(resp, "Added Product", product.toString());
        } catch (NumberFormatException | NullPointerException e) {
            displayResponse(resp, "Error: Invalid input", "Please provide valid 'name' and 'price' parameters.");
        } catch (RuntimeException e) {
            displayResponse(resp, "Error", e.getMessage());
        }
    }

    private void displayResponse(HttpServletResponse resp, String title, String message) throws IOException {
        resp.getWriter().println("<h1>" + title + "</h1>");
        if (message != null) {
            resp.getWriter().println("<p>" + message + "</p>");
        }
    }
}