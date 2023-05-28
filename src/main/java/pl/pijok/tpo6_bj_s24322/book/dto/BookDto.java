package pl.pijok.tpo6_bj_s24322.book.dto;

import lombok.*;
import pl.pijok.tpo6_bj_s24322.annotations.Column;
import pl.pijok.tpo6_bj_s24322.lib.Dto;

import java.time.LocalDate;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class BookDto extends Dto {

    public BookDto() {
    }

    private Integer bookId;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private Date publishDate;
    private int rating;

    @Setter
    private Date creationDate;

}
