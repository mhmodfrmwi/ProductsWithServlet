package controllers;

import entity.Product;
import repository.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchType = req.getParameter("searchType");
        String query = req.getParameter("query");
        resp.setContentType("text/html");

        if (isInvalidQuery(query)) {
            resp.getWriter().println("<h1>Invalid Search Query</h1>");
            return;
        }

        Optional<Product> product = searchProduct(searchType, query, resp);

        if (product.isPresent()) {
            resp.getWriter().println("<h1>Search Results</h1>");
            resp.getWriter().println(product + "<br>");
        } else {
            resp.getWriter().println("<h1>No Product Found</h1>");
        }

        resp.getWriter().println("<a href='searchProduct.html'>Back to Search</a>");
    }

    private boolean isInvalidQuery(String query) {
        return query == null || query.trim().isEmpty();
    }

    private Optional<Product> searchProduct(String searchType, String query, HttpServletResponse resp) throws IOException {
        if ("id".equals(searchType)) {
            try {
                return ProductRepository.getProductById(Integer.parseInt(query));
            } catch (NumberFormatException e) {
                resp.getWriter().println("<h1>Error: Invalid ID format</h1>");
                return Optional.empty();
            }
        } else if ("name".equals(searchType)) {
            return ProductRepository.getProductByName(query);
        }
        return Optional.empty();
    }
}