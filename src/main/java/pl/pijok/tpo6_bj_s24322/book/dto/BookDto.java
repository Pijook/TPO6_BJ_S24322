package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.*;
import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.lib.Dto;

import java.time.LocalDate;

@Getter
@ToString
public class BookDto extends Dto {

    public BookDto() {
    }

    private Integer bookId;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private LocalDate publishDate;
    private int rating;
    private LocalDate creationDate;

}
