package pl.pijok.tpo6_bj_s24322.book.endpoint;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.pijok.tpo6_bj_s24322.lib.Mapper;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.service.BookService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "searchBooksServlet", value = "/search-books")
public class SearchBooksServlet extends HttpServlet {

    @Inject
    private BookService service;
    @Inject
    private Mapper mapper;

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        SearchCriteria criteria = mapper.mapSearchCriteria(request);

        out.println(gson.toJson(service.searchBooks(criteria)));
    }

}
