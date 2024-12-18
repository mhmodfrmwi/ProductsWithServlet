package controllers;

import repository.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deleteType = req.getParameter("deleteType");
        String query = req.getParameter("query");
        resp.setContentType("text/html");

        if (query == null || query.trim().isEmpty()) {
            displayResponse(resp, "Error: Invalid Input", "Please provide valid ID or Name.");
            return;
        }

        processDeletion(deleteType, query, resp);
        resp.getWriter().println("<br><a href='deleteProduct.html'>Back to Delete</a>");
    }

    private void displayResponse(HttpServletResponse resp, String title, String message) throws IOException {
        resp.getWriter().println("<h1>" + title + "</h1>");
        if (message != null) {
            resp.getWriter().println("<p>" + message + "</p>");
        }
    }

    private void processDeletion(String deleteType, String query, HttpServletResponse resp) throws IOException {
        try {
            if ("id".equals(deleteType)) {
                deleteById(Integer.parseInt(query), resp);
            } else if ("name".equals(deleteType)) {
                ProductRepository.deleteProduct(query);
                displayResponse(resp, "Deleted Product with Name: " + query, null);
            } else {
                displayResponse(resp, "Error: Invalid Delete Type", null);
            }
        } catch (NumberFormatException e) {
            displayResponse(resp, "Error: Invalid ID Format", null);
        } catch (RuntimeException e) {
            displayResponse(resp, "Error: Product Not Found", e.getMessage());
        }
    }

    private void deleteById(int id, HttpServletResponse resp) throws IOException {
        ProductRepository.deleteProduct(id);
        displayResponse(resp, "Deleted Product with ID: " + id, null);
    }
}