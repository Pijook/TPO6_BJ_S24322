package pl.pijok.tpo6_bj_s24322.book.endpoint;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDtoMapper;
import pl.pijok.tpo6_bj_s24322.book.dto.BookSearchCriteria;
import pl.pijok.tpo6_bj_s24322.lib.Mapper;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.service.BookService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "searchBooksServlet", value = "/api/search-books")
public class SearchBooksServlet extends HttpServlet {

    @Inject
    private BookService service;
    @Inject
    private BookDtoMapper mapper;

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BookSearchCriteria criteria = (BookSearchCriteria) mapper.mapBookSearchCriteria(request);

        if (onlyIdNotNull(criteria)) {
            out.println(gson.toJson(
                    List.of(
                            service.findBook(criteria.getBookId()).get())));
        }
        else {
            out.println(gson.toJson(service.searchBooks(criteria)));
        }
    }

    private boolean onlyIdNotNull(BookSearchCriteria bookSearchCriteria) {
        return bookSearchCriteria.getTitle() == null &&
                bookSearchCriteria.getAuthor() == null &&
                bookSearchCriteria.getDescription() == null &&
                bookSearchCriteria.getIsbn() == null &&
                bookSearchCriteria.getPublishDate() == null &&
                bookSearchCriteria.getRating() == null &&
                bookSearchCriteria.getCreationDate() == null &&
                bookSearchCriteria.getBookId() != null;
    }

}
