package pl.pijok.tpo6_bj_s24322.book.repository;

import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<BookDto> getBooks();

    Optional<BookDto> findBook(int bookId);

    List<BookDto> searchBooks(SearchCriteria criteria);

    boolean addBook(BookDto bookDto);

    void initTable();

}
