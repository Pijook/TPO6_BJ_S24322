package pl.pijok.tpo6_bj_s24322.book.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;
import pl.pijok.tpo6_bj_s24322.book.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addBookServlet", value = "/add-book")
public class AddBookServlet extends HttpServlet {

    @Inject
    private BookService service;

    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDto bookDto = gson.fromJson(request.getReader(), BookDto.class);

        System.out.println(bookDto);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean result = service.addBook(bookDto);
        if(result) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


}
