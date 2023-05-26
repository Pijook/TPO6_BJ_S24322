package pl.pijok.tpo6_bj_s24322.book.service;

import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> getBooks();

    Optional<BookDto> findBook(long bookId);

    List<BookDto> searchBooks(SearchCriteria criteria);

}
