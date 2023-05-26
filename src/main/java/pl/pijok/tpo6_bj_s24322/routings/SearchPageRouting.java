package pl.pijok.tpo6_bj_s24322.routings;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.pijok.tpo6_bj_s24322.MainListener;

import java.io.IOException;

@WebServlet(name = "searchBooksPage", value = "/searchBooks")
public class SearchPageRouting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("search.html");

        view.forward(req, resp);
    }
}
