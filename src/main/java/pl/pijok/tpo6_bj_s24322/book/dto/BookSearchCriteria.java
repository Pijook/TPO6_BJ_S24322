package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.Builder;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;

import java.time.LocalDate;

@Builder
public class BookSearchCriteria extends SearchCriteria {

    private Integer bookId;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private LocalDate publishDate;
    private Integer rating;
    private LocalDate creationDate;

}
