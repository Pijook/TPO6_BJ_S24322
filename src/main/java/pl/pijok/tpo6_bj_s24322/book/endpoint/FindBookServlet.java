package pl.pijok.tpo6_bj_s24322.book.endpoint;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.pijok.tpo6_bj_s24322.book.service.BookService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getBooksServlet", value = "/api/find-books")
public class FindBookServlet extends HttpServlet {

    @Inject
    private BookService service;

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdString = request.getParameter("bookId");

        if(bookIdString == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        int bookId = 0;

        try{
            bookId = Integer.parseInt(bookIdString);
        }
        catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.println(gson.toJson(service.findBook(bookId)));
    }
}
