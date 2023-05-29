package pl.pijok.tpo6_bj_s24322.book.service;

import jakarta.inject.Inject;
import pl.pijok.tpo6_bj_s24322.book.dto.BookSearchCriteria;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;
import pl.pijok.tpo6_bj_s24322.book.dto.BookDto;
import pl.pijok.tpo6_bj_s24322.book.repository.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Inject
    private BookRepository repository;

    @Override
    public List<BookDto> getBooks() {
        return repository.getBooks();
    }

    @Override
    public Optional<BookDto> findBook(int bookId) {
        return repository.findBook(bookId);
    }

    @Override
    public List<BookDto> searchBooks(SearchCriteria criteria) {
        return repository.searchBooks(criteria);
    }

    @Override
    public boolean addBook(BookDto bookDto) {
        bookDto.setCreationDate(new Date());
        return repository.addBook(bookDto);
    }



}
