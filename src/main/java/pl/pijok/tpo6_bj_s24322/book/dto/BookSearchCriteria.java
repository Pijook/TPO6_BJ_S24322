package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.Builder;
import pl.pijok.tpo6_bj_s24322.lib.SearchCriteria;

@Builder
public class BookSearchCriteria extends SearchCriteria {

    private Integer bookId;
    private String title;
    private String author;
    private String description;

}
